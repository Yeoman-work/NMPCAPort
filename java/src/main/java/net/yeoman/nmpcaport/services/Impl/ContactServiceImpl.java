package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.contact.*;
import net.yeoman.nmpcaport.io.repositories.ContactRepository;
import net.yeoman.nmpcaport.services.ContactService;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {


    private final ContactRepository contactRepository;

    private final NetworkingGroupServiceImpl networkingGroupService;

    private final AssignedNetworkingGroupServiceImpl assignedNetworkingGroupService;

    private final PhoneNumberServiceImpl phoneNumberService;

    private final AssignedNumberServiceImpl assignedNumberService;

    private final HealthCenterServiceImpl healthCenterService;

    private final Utils utils;

    public ContactServiceImpl(ContactRepository contactRepository,
                              NetworkingGroupServiceImpl networkingGroupService,
                              AssignedNetworkingGroupServiceImpl assignedNetworkingGroupService,
                              PhoneNumberServiceImpl phoneNumberService,
                              AssignedNumberServiceImpl assignedNumberService,
                              HealthCenterServiceImpl healthCenterService,
                              Utils utils
    ){
        this.contactRepository = contactRepository;
        this.networkingGroupService = networkingGroupService;
        this.assignedNetworkingGroupService = assignedNetworkingGroupService;
        this.phoneNumberService = phoneNumberService;
        this.assignedNumberService = assignedNumberService;
        this.healthCenterService = healthCenterService;
        this.utils = utils;
    }





    @Override
    public ContactEntity getContactEntity(String contactId) {

        return this.contactRepository.findByContactId(contactId);
    }

    @Override
    public List<ContactEntity> getAllContactEntities() {

        return this.contactRepository.findAll();
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

        if(this.requestIsNull(contactDetailsRequestModel))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ContactEntity contactEntity = this.createContactEntity();

        contactEntity.setFirstName(contactDetailsRequestModel.getFirstName());
        contactEntity.setLastName(contactDetailsRequestModel.getLastName());
        contactEntity.setEmail(contactDetailsRequestModel.getEmail());
        contactEntity.setTitle(contactDetailsRequestModel.getTitle());
        contactEntity.setHealthCenter(
                this.healthCenterService.getHealthCenterEntity(
                        contactDetailsRequestModel.getHealthCenter()
                )
        );

        ContactEntity savedContact = this.savedContactEntity(contactEntity);

        if(contactDetailsRequestModel.getNetworkingGroups() != null){

            List<NetworkingGroupEntity> networkingGroupEntities =
                    this.networkingGroupService.getMultipleNetworkingGroups(
                            contactDetailsRequestModel.getNetworkingGroups()
                    );

            this.assignedNetworkingGroupService.assignNetworkingGroupsToContact(savedContact, networkingGroupEntities);

        }



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
    public ContactEntity generateUniqueContactId(ContactEntity contactEntity) {

        contactEntity.setContactId(utils.generateRandomID());

        while(this.contactRepository.existsByContactId(contactEntity.getContactId())){

            contactEntity.setContactId(utils.generateRandomID());

        }

        return contactEntity;
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

    @Override
    public ContactResponseModel getContactResponseModel(ContactEntity contactEntity) {

        if(this.entityIsNull(contactEntity))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ContactResponseModel contactResponseModel = new ContactResponseModel();


        contactResponseModel.setContactId(contactEntity.getContactId());
        contactResponseModel.setFirstName(contactEntity.getFirstName());
        contactResponseModel.setLastName(contactEntity.getLastName());
        contactResponseModel.setTitle(contactEntity.getTitle());
        contactResponseModel.setEmail(contactEntity.getEmail());
        contactResponseModel.setCreatedAt(contactEntity.getCreatedAt());
        contactResponseModel.setUpdatedAt(contactEntity.getUpdatedAt());

        contactResponseModel.setHealthCenterEssentials(
                this.healthCenterService.healthCenterEssentials(
                        contactEntity.getHealthCenter()
                )
        );

        if(contactEntity.getAssignedNumberEntities() != null){
            contactResponseModel.setPhoneNumberEssentials(
                    this.phoneNumberService.getPhoneNumberEssentials(
                            this.assignedNumberService.getPhoneNumberEntities(
                                    contactEntity.getAssignedNumberEntities()
                            )
                    )
            );
        }

        if(contactEntity.getAssignedNetworkingGroupEntities() != null){

            contactResponseModel.setNetworkingGroupEssentials(
                    this.assignedNetworkingGroupService.getNetworkingEssentials(
                            contactEntity.getAssignedNetworkingGroupEntities()
                    )
            );

        }

        return contactResponseModel;
    }




    @Override
    public ContactEntity createContactEntity() {

        ContactEntity contactEntity = new ContactEntity();

        return this.generateUniqueContactId(contactEntity);
    }


    @Override
    public ContactEntity savedContactEntity(ContactEntity contactEntity) {

        return this.contactRepository.save(contactEntity);
    }



    @Override
    public void deleteContact(String contactId) {

        this.contactRepository.delete(this.getContactEntity(contactId));
    }

}
