package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactFormListResponse;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.io.repositories.ContactRepository;
import net.yeoman.nmpcaport.services.ContactService;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;
import net.yeoman.nmpcaport.shared.dto.PhoneNumberDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private NetworkingGroupServiceImpl networkingGroupService;

    @Autowired
    private AssignedNetworkingGroupServiceImpl assignedNetworkingGroupService;

    @Autowired
    private PhoneNumberServiceImpl phoneNumberService;

    @Autowired
    private AssignedNumberServiceImpl assignedNumberService;

    @Autowired
    private HealthCenterServiceImpl healthCenterService;

    @Autowired
    private Utils utils;





    @Override
    public ContactEntity getContactEntity(String contactId) {

        return this.contactRepository.findByContactId(contactId);
    }

    @Override
    public List<ContactEntity> getAllContactEntities() {

        return this.contactRepository.findAll();
    }

    @Override
    public ContactDto processContactEntity(ContactDto contactDto) {

        //create contact entity with contactId
        ContactEntity contactEntity = this.generateUniqueContactId(utils.objectMapper().map(contactDto, ContactEntity.class));

        //get healthCenter
        HealthCenterEntity healthCenterEntity = this.healthCenterService.getHealthCenterEntity(contactDto.getHealthCenter());

        System.out.println("health care entity");
        System.out.println(healthCenterEntity.getName());
        //check for health center
        if(healthCenterEntity == null) throw new HealthCenterServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //set health Center
        contactEntity.setHealthCenter(healthCenterEntity);

        //save contact entity
        ContactEntity savedContact = this.savedContactEntity(contactEntity);

        //save contact
        ContactDto savedContactDto = this.entityToDto(savedContact);

        //health center entity to nested response
        HealthCenterDto healthCenterDto = this.healthCenterService.entityToDto(savedContact.getHealthCenter());


        //check health center dto
        if(this.healthCenterService.dtoIsNull(healthCenterDto)) throw  new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterNestedResponseModel healthCenterNestedResponseModel = this.healthCenterService.dtoToHealthCenterNestedResponseModel(healthCenterDto);


        //set health center entity to response
        savedContactDto.setHealthCenterNestedResponse(this.healthCenterService.dtoToHealthCenterNestedResponseModel(healthCenterDto));

        //check if dto is null
        if(this.dtoIsNull(savedContactDto)) throw new ContactServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //process networking groups
        if(contactDto.getNetworkingGroups() != null){

            List<NetworkingGroupResponseModel> networkingGroupResponseModels = new ArrayList<>();

            //convert Ids(String) to entities
            List<NetworkingGroupEntity> networkingGroupEntities = this.networkingGroupService.getMultipleNetworkingGroups(contactDto.getNetworkingGroups());

            //loop through entities
            for(NetworkingGroupEntity group: networkingGroupEntities){

                //create assigned networking group
                AssignedNetworkingGroupEntity savedAssignNetworkingGroup = this.assignedNetworkingGroupService.createAndSaveAssignedNetworkingGroupsContact(group, savedContact);

                //check savedAssignmentGroup
                if(this.assignedNetworkingGroupService.entityIsNull(savedAssignNetworkingGroup)) throw new AssignedNetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                //convert entity to DTO
                NetworkingGroupDto networkingGroupDto = this.networkingGroupService.entityToDto(savedAssignNetworkingGroup.getNetworkingGroupEntity());

                //add dto to response
                networkingGroupResponseModels.add(this.networkingGroupService.dtoToResponse(networkingGroupDto));

            }

            savedContactDto.setNetworkingGroupResponses(networkingGroupResponseModels);

        }

        if(contactDto.getPhoneNumbers() != null){

            //convert phone number request into DTO
            List<PhoneNumberDto> phoneNumberDtoList = this.phoneNumberService.requestToDto(contactDto.getPhoneNumbers());

            //convert Phone numbers into Entities
            List<PhoneNumberEntity> savedPhoneNumberList = this.phoneNumberService.dtoArrayToEntityArray(phoneNumberDtoList);

            //create relationship between contact and phone numbers
            List<AssignedNumberEntity> savedAssignments = this.assignedNumberService.savedAssignmentEntityBulk(savedContact, savedPhoneNumberList);

            //convert phone number entity to dto
            List<PhoneNumberEntity> phoneNumberEntityList = this.assignedNumberService.getPhoneNumberAssignments(savedAssignments);

            //convert Entities to Dto
            List<PhoneNumberDto> savedPhoneNumberDtoList = this.phoneNumberService.entityToDto(phoneNumberEntityList);

            //convert dto to response
            savedContactDto.setPhoneNumberResponses(this.phoneNumberService.dtoToResponse(savedPhoneNumberDtoList));
        }

        return savedContactDto;
    }

    @Override
    public ContactNestedResponseModel dtoToNestedResponse(ContactDto contactDto) {

        return this.utils.objectMapper().map(contactDto, ContactNestedResponseModel.class);
    }

    @Override
    public List<ContactNestedResponseModel> dtoToNestedResponse(List<ContactDto> contactDtoList) {

        List<ContactNestedResponseModel> returnValue = new ArrayList<>();

        for(ContactDto contactDto: contactDtoList){

            returnValue.add(this.dtoToNestedResponse(contactDto));
        }

        return returnValue;
    }

    @Override
    public ContactFormListResponse contactsForNetworkingGroup() {

        List<ContactEntity> contactEntities = this.getAllContactEntities();

        ContactFormListResponse returnValue = new ContactFormListResponse();

        returnValue.setContactNestedResponses(this.dtoToNestedResponse(this.entityToDto(contactEntities)));

        return returnValue;
    }

    @Override
    public String peelOffContactIds(ContactEntity contactEntity) {

        return contactEntity.getContactId();
    }

    @Override
    public List<String> peelOffContactIds(List<ContactEntity> contactEntities) {

        List<String> returnValue = new ArrayList<>();

        for(ContactEntity contactEntity: contactEntities){

            returnValue.add(this.peelOffContactIds(contactEntity));
        }

        return returnValue;
    }

    @Override
    public void saveContact(ContactEntity contact) {
         this.contactRepository.save(contact);
    }

    @Override
    public List<ContactResponseModel> getAllContacts() {

        List<ContactEntity> contactEntities = this.contactRepository.findAll();

        List<ContactDto> contactDtoList = this.entityToDto(contactEntities);


        return this.dtoToResponse(contactDtoList);
    }

    @Override
    public Boolean dtoIsNull(ContactDto contactDto) {
        return contactDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<ContactDto> contactDtoList) {
        return contactDtoList == null;
    }

    @Override
    public Boolean entityIsNull(List<ContactEntity> contactEntities) {

        return contactEntities == null;
    }

    @Override
    public Boolean entityIsNull(ContactEntity contactEntity) {
        return contactEntity == null;
    }

    @Override
    public Boolean requestIsNull(ContactDetailsRequestModel contactDetailsRequestModel) {
        return contactDetailsRequestModel == null;
    }

    @Override
    public Boolean requestIsNull(List<ContactDetailsRequestModel> contactDetailsRequestModelList) {
        return null;
    }

    @Override
    public Boolean responseIsNull(ContactResponseModel contactResponseModel) {
        return contactResponseModel == null;
    }

    @Override
    public Boolean responseIsNull(List<ContactResponseModel> contactResponseModels) {
        return null;
    }

    @Override
    public Boolean nestedResponseIsNull(List<ContactNestedResponseModel> contactNestedResponseModels) {
        return null;
    }

    @Override
    public Boolean nestedResponseIsNull(ContactNestedResponseModel contactNestedResponseModel) {
        return contactNestedResponseModel == null;
    }

    @Override
    public ContactEntity generateUniqueContactId(ContactEntity contactEntity) {

        contactEntity.setContactId(utils.generateRandomID());

        while(this.contactRepository.existsByContactId(contactEntity.getContactId())){

            contactEntity.setContactId(utils.generateRandomID());

        }

        return contactEntity;
    }


    @Override
    public ContactEntity getContact(String contactId) {

        if(contactId == null || contactId.length() < 30 ) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

    	return this.contactRepository.findByContactId(contactId);
        
    }

    @Override
    public List<ContactEntity> getMultipleContacts(List<String> memberIds) {

        List<ContactEntity> returnValue = new ArrayList<>();

        for(String memberId: memberIds){

            ContactEntity contactEntity = this.getContactEntity(memberId);

            if(contactEntity != null){

                returnValue.add(contactEntity);
            }
        }

        return returnValue;
    }

    //contact entity mapper
    @Override
    public ContactEntity dtoToEntity(ContactDto contactDto) {

        if(contactDto == null) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ContactEntity contactEntity = this.utils.objectMapper().map(contactDto, ContactEntity.class);

        return contactEntity;
    }

    @Override
    public ContactEntity createContactEntity() {

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setContactId(utils.generateRandomID());

        return contactEntity;
    }

    @Override
    public List<ContactEntity> dtoToEntity(List<ContactDto> contactDtoList) {

        List<ContactEntity> returnValue = new ArrayList<>();

        if(contactDtoList == null) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        for(ContactDto contact: contactDtoList){

            returnValue.add(this.dtoToEntity(contact));
        }

        return returnValue;
    }

    @Override
    public ContactResponseModel dtoToResponse(ContactDto contactDto) {

        return utils.objectMapper().map(contactDto, ContactResponseModel.class);
    }


    @Override
    public List<ContactResponseModel> dtoToResponse(List<ContactDto> contactDtoList) {

        List<ContactResponseModel> returnValue = new ArrayList<>();

        for(ContactDto contactDto: contactDtoList){

            returnValue.add(this.dtoToResponse(contactDto));
        }

        return returnValue;
    }


    @Override
    public ContactDto requestToDto(ContactDetailsRequestModel contactDetailsRequestModel) {


        return utils.objectMapper().map(contactDetailsRequestModel, ContactDto.class);

    }

    @Override
    public List<ContactDto> requestToDto(List<ContactDetailsRequestModel> contactDetailsRequestModelList) {

        List<ContactDto> returnValue = new ArrayList<>();

        for(ContactDetailsRequestModel requestModel: contactDetailsRequestModelList){

            returnValue.add(this.requestToDto(requestModel));
        }

        return returnValue;
    }

    @Override
    public List<ContactDto> entityToDto(List<ContactEntity> contactEntities) {

        if(this.entityIsNull(contactEntities)) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ContactDto> returnValue = new ArrayList<>();

        for(ContactEntity contactEntity: contactEntities){

            returnValue.add(this.entityToDto(contactEntity));

        }

        return returnValue;
    }

    @Override
    public ContactDto entityToDto(ContactEntity contactEntity) {
        //check if entity is null
        if(entityIsNull(contactEntity)) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //convert contact
        ContactDto contactDto = utils.objectMapper().map(contactEntity, ContactDto.class);

        //convert health center entity
        HealthCenterDto healthCenterDto = this.healthCenterService.entityToDto(contactEntity.getHealthCenter());

        //convert dto to response
        contactDto.setHealthCenterNestedResponse(this.healthCenterService.dtoToHealthCenterNestedResponseModel(healthCenterDto));

        if(contactEntity.getAssignedNetworkingGroupEntities() != null){

            List<NetworkingGroupEntity> networkingGroupEntities = this.assignedNetworkingGroupService.networkingGroupEntities(contactEntity.getAssignedNetworkingGroupEntities());

            if(networkingGroupEntities == null) throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

            contactDto.setNetworkingGroupResponses(this.networkingGroupService.dtoToResponse(this.networkingGroupService.entityToDtoBase(networkingGroupEntities)));
        }


        if(contactEntity.getAssignedNumberEntities() != null){

            List<PhoneNumberEntity> phoneNumberEntityList = this.assignedNumberService.getPhoneNumberEntities(contactEntity.getAssignedNumberEntities());


            contactDto.setPhoneNumberResponses(this.phoneNumberService.dtoToResponse(this.phoneNumberService.entityToDto(phoneNumberEntityList)));
        }
        return contactDto;
    }


    @Override
    public ContactEntity savedContactEntity(ContactEntity contactEntity) {

        return this.contactRepository.save(contactEntity);
    }


    @Override
    public ContactDto updateContact(String contactId, ContactDto contactDto) {

    	return contactDto;
    }


    @Override
    public ContactDto deleteContact(String contactId) {
        return null;
    }




}
