package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;
import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.io.repositories.ContactRepository;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.services.ContactService;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;
import net.yeoman.nmpcaport.shared.dto.PhoneNumberDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
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
    public ContactDto processContactEntity(ContactDto contactDto) {

        //create contact entity
        ContactEntity contactEntity = this.dtoToEntity(contactDto);

        //get healthCenter
        HealthCenterEntity healthCenterEntity = this.healthCenterService.getHealthCenterEntity(contactDto.getHealthCenter());

        //check for health center
        if(healthCenterEntity == null) throw new HealthCenterServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //set health Center
        contactEntity.setHealthCenter(healthCenterEntity);

        //save contact entity
        ContactEntity savedContact = this.savedContactEntity(contactEntity);

        //save contact
        ContactDto savedContactDto = this.EntityToDto(savedContact);

        //check if dto is null
        if(this.dtoIsNull(savedContactDto)) throw new ContactServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //process networking groups
        if(contactDto.getNetworkingGroups() != null){
            List<NetworkingGroupEntity> savedNetworkingGroupEntities = new ArrayList<>();
            //convert Ids(String) to entities
            List<NetworkingGroupEntity> networkingGroupEntities = this.networkingGroupService.getMultipleNetworkingGroups(contactDto.getNetworkingGroups());

            //loop through entities
            for(NetworkingGroupEntity group: networkingGroupEntities){

                //create assigned networking group
                AssignedNetworkingGroupEntity savedAssignNetworkingGroup = this.assignedNetworkingGroupService.createAndSaveAssignedNetworkingGroupsContact(group, savedContact);

                //check savedAssignmentGroup
                if(this.assignedNetworkingGroupService.entityIsNull(savedAssignNetworkingGroup)) throw new AssignedNetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());



            }

            //convert networking group entities to DTO
            List<NetworkingGroupDto> savedNetworkingGroupDtoList = this.networkingGroupService.entityArrayToDtoArray(savedNetworkingGroupEntities);

            //convert DTO to response
            contactDto.setNetworkingGroupResponses(this.networkingGroupService.DtoArrayToResponseArray(savedNetworkingGroupDtoList));

        }

        if(contactDto.getPhoneNumbers() != null){

            //convert phone number request into DTO
            List<PhoneNumberDto> phoneNumberDtoList = this.phoneNumberService.requestArrayToDto(contactDto.getPhoneNumbers());

            //convert Phone numbers into Entities
            List<PhoneNumberEntity> savedPhoneNumberList = this.phoneNumberService.dtoArrayToEntityArray(phoneNumberDtoList);

            //create relationship between contact and phone numbers
            List<AssignedNumberEntity> savedAssignments = this.assignedNumberService.savedAssignmentEntityBulk(savedContact,savedPhoneNumberList);

            //convert phone number entity to dto
            List<PhoneNumberEntity> phoneNumberEntityList = this.assignedNumberService.getPhoneNumberAssignments(savedAssignments);

            //convert Entities to Dto
            List<PhoneNumberDto> savedPhoneNumberDtoList = this.phoneNumberService.entityArrayToDtoArray(phoneNumberEntityList);

            //convert dto to response
            savedContactDto.setPhoneNumberResponses(this.phoneNumberService.dtoArrayToResponseArray(savedPhoneNumberDtoList));
        }

        return savedContactDto;
    }

    @Override
    public void saveContact(ContactEntity contact) {
         this.contactRepository.save(contact);
    }

    @Override
    public List<ContactResponseModel> getAllContacts() {
        List<ContactResponseModel> returnValue = new ArrayList<>();

        List<ContactEntity> contacts = this.contactRepository.findAll();

        for(ContactEntity contact: contacts){

            ContactDto contactDto = new ModelMapper().map(contact, ContactDto.class);

            returnValue.add(new ModelMapper().map(contactDto, ContactResponseModel.class));
        }

        return returnValue;
    }

    @Override
    public Boolean dtoIsNull(ContactDto contactDto) {
        return contactDto == null;
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
    public Boolean responseIsNull(ContactResponseModel contactResponseModel) {
        return contactResponseModel == null;
    }

    @Override
    public Boolean nestedResponseIsNull(ContactNestedResponseModel contactNestedResponseModel) {
        return contactNestedResponseModel == null;
    }


    @Override
    public ContactDto getContact(String contactId) {
        
    	ContactEntity contactEntity = this.contactRepository.findByContactId(contactId);

    	ContactDto contactDto = new ModelMapper().map(contactEntity, ContactDto.class);

    	if(contactEntity == null) throw new RuntimeException(contactId);


        if(contactDto.getHealthCenter() != null){

            contactDto.setHealthCenterNestedResponse(new ModelMapper().map(contactDto.getHealthCenter(), HealthCenterNestedResponseModel.class));

        }

    	return contactDto;
        
    }

    //contact entity mapper
    @Override
    public ContactEntity dtoToEntity(ContactDto contactDto) {

        return utils.objectMapper().map(contactDto, ContactEntity.class);
    }

    @Override
    public List<ContactEntity> dtoArrayToEntityArray(List<ContactDto> contactDtoList) {

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
    public List<ContactResponseModel> dtoArrayToResponse(List<ContactDto> contactDtoList) {

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
    public List<ContactDto> requestArrayToDtoArray(List<ContactDetailsRequestModel> contactDetailsRequestModelList) {

        List<ContactDto> returnValue = new ArrayList<>();

        for(ContactDetailsRequestModel requestModel: contactDetailsRequestModelList){

            returnValue.add(this.requestToDto(requestModel));
        }

        return returnValue;
    }

    @Override
    public ContactDto EntityToDto(ContactEntity contactEntity) {

        return utils.objectMapper().map(contactEntity, ContactDto.class);

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
