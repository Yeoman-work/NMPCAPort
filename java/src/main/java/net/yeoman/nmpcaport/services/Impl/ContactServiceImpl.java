package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.contact.*;
import net.yeoman.nmpcaport.io.repositories.ContactRepository;
import net.yeoman.nmpcaport.services.ContactService;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
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
    public void processContact(ContactDto contactDto) {


        //create contact entity with contactId
        ContactEntity contactEntity = this.generateUniqueContactId(
                this.utils.objectMapper().map(
                        contactDto, ContactEntity.class));

        //get healthCenter
        HealthCenterEntity healthCenterEntity = this.healthCenterService.getHealthCenterEntity(
                contactDto.getHealthCenter()
        );


        //check for health center
        if(healthCenterEntity == null)
            throw new HealthCenterServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //set health Center
        contactEntity.setHealthCenter(healthCenterEntity);

        //save contact entity
        ContactEntity savedContact = this.savedContactEntity(contactEntity);


        //process networking groups
        if(contactDto.getNetworkingGroups() != null){


            //convert Ids(String) to entities
            List<NetworkingGroupEntity> networkingGroupEntities =
                    this.networkingGroupService.getMultipleNetworkingGroups(contactDto.getNetworkingGroups());

            //assign groups to entities
            this.assignedNetworkingGroupService.assignNetworkingGroupsToContact(savedContact, networkingGroupEntities);

        }

        if(contactDto.getPhoneNumbers() != null){

            List<PhoneNumberEntity> phoneNumberEntityList = this.phoneNumberService.processBulkPhoneNumbers(
                    contactDto.getPhoneNumbers()
            );

            this.assignedNumberService.assignmentNumberContactProcess(phoneNumberEntityList, savedContact);
        }

        return;
    }

    @Override
    public ContactDto partialEntityToDto(ContactEntity contactEntity) {

        if(this.entityIsNull(contactEntity))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(contactEntity, ContactDto.class);
    }

    @Override
    public List<ContactDto> partialEntityToDto(List<ContactEntity> contactEntityList) {
        final long start = System.currentTimeMillis();
        if(this.entityIsNull(contactEntityList))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ContactDto> returnValue = new ArrayList<>();

        for(ContactEntity contact: contactEntityList){

            returnValue.add(this.partialEntityToDto(contact));
        }

        final long stop = System.currentTimeMillis();

        System.out.println("convert contact entity to DTO total time: " + (stop - start));

        return returnValue;
    }

    @Override
    public ContactDashBoard contactDashboardData(ContactEntity contactEntity) {

        if(this.entityIsNull(contactEntity)) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ContactDashBoard contactEssentials = new ContactDashBoard();


        contactEssentials.setEmail(contactEntity.getEmail());

        contactEssentials.setFirstName(contactEntity.getFirstName());

        contactEssentials.setLastName(contactEntity.getLastName());

        contactEssentials.setTitle(contactEntity.getTitle());

        contactEssentials.setHealthCenterEssentials(
                this.healthCenterService.healthCenterEssentials
                        (contactEntity.getHealthCenter()
                        )
        );

        contactEssentials.setNetworkingGroupEssentials(
                this.assignedNetworkingGroupService.getNetworkingEssentials(
                        contactEntity.getAssignedNetworkingGroupEntities()
                )
        );

        contactEssentials.setPhoneNumbers(
                this.assignedNumberService.getPhoneNumberEssentials(
                        contactEntity.getAssignedNumberEntities()
                )
        );

        return contactEssentials;
    }

    @Override
    public List<ContactDashBoard> contactDashboardData(List<ContactEntity> contactEntities) {

        if(this.entityIsNull(contactEntities))
            throw  new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ContactDashBoard> returnValue = new ArrayList<>();

       for(ContactEntity contactEntity: contactEntities){

           returnValue.add(this.contactDashboardData(contactEntity));
       }

        return returnValue;
    }

    @Override
    public ContactEssentials getContactEssentials(ContactEntity contactEntity) {

        if(this.entityIsNull(contactEntity))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ContactEssentials contactEssentials = new ContactEssentials();

        contactEssentials.setContactId(contactEntity.getContactId());
        contactEssentials.setTitle(contactEntity.getTitle());
        contactEssentials.setFirstName(contactEntity.getFirstName());
        contactEssentials.setLastName(contactEntity.getLastName());
        contactEssentials.setEmail(contactEntity.getEmail());


        return contactEssentials;
    }

    @Override
    public List<ContactEssentials> getContactEssentials(List<ContactEntity> contactEntities) {

        if(this.entityIsNull(contactEntities))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ContactEssentials> returnValue = new ArrayList<>();

        for(ContactEntity contactEntity: contactEntities){

            returnValue.add(this.getContactEssentials(contactEntity));
        }

        return returnValue;
    }



    @Override
    public void createContact(ContactDetailsRequestModel contactDetailsRequestModel) {

        ContactDto contactDto = this.requestToDto(contactDetailsRequestModel);

        this.processContact(contactDto);

        return;
    }

    @Override
    public ContactNestedResponseModel dtoToNestedResponse(ContactDto contactDto) {

        if(this.dtoIsNull(contactDto))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(contactDto, ContactNestedResponseModel.class);
    }

    @Override
    public List<ContactNestedResponseModel> dtoToNestedResponse(List<ContactDto> contactDtoList) {

        final long start = System.currentTimeMillis();

        List<ContactNestedResponseModel> returnValue = new ArrayList<>();

        for(ContactDto contactDto: contactDtoList){

            returnValue.add(this.dtoToNestedResponse(contactDto));
        }

        final long stop = System.currentTimeMillis();

        System.out.println("contact dto to nested Response total time: " + (stop - start));

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

        if(this.requestIsNull(contactDetailsRequestModel))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(contactDetailsRequestModel, ContactDto.class);

    }

    @Override
    public List<ContactDto> requestToDto(List<ContactDetailsRequestModel> contactDetailsRequestModelList) {

        if(this.requestIsNull(contactDetailsRequestModelList))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ContactDto> returnValue = new ArrayList<>();

        for(ContactDetailsRequestModel requestModel: contactDetailsRequestModelList){

            returnValue.add(this.requestToDto(requestModel));
        }

        return returnValue;
    }

    @Override
    public List<ContactDto> entityToDto(List<ContactEntity> contactEntities) {

        final long start = System.currentTimeMillis();

        if(this.entityIsNull(contactEntities)) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ContactDto> returnValue = new ArrayList<>();

        for(ContactEntity contactEntity: contactEntities){

            returnValue.add(this.entityToDto(contactEntity));

        }

        final long stop = System.currentTimeMillis();

        System.out.println("convert contact entity to DTO total time: " + (stop - start));

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

            List<NetworkingGroupEntity> networkingGroupEntities =
                    this.assignedNetworkingGroupService.networkingGroupEntities(
                            contactEntity.getAssignedNetworkingGroupEntities()
                    );

            if(networkingGroupEntities == null)
                throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

            contactDto.setNetworkingGroupResponses(this.networkingGroupService.dtoToResponse(
                    this.networkingGroupService.entityToDtoBase(networkingGroupEntities)));
        }


        if(contactEntity.getAssignedNumberEntities() != null){

            List<PhoneNumberEntity> phoneNumberEntityList =
                    this.assignedNumberService.getPhoneNumberEntities(contactEntity.getAssignedNumberEntities());


            contactDto.setPhoneNumberResponses(this.phoneNumberService.dtoToResponse(
                    this.phoneNumberService.entityToDto(phoneNumberEntityList)));
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
