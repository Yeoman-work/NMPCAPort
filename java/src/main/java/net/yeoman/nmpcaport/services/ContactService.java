package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.contact.*;
import net.yeoman.nmpcaport.shared.dto.ContactDto;

import java.util.List;

public interface ContactService {

    //delete contact
    void deleteContact(String contactId);

    //contact dashboard
    ContactDashBoard contactDashboardData(ContactEntity contactEntity);
    List<ContactDashBoard> contactDashboardData(List<ContactEntity> contactEntities);

    //contact essentials
    ContactEssentials getContactEssentials(ContactEntity contactEntity);
    List<ContactEssentials> getContactEssentials(List<ContactEntity> contactEntities);

    //create contact
    void createContact(ContactDetailsRequestModel contactDetailsRequestModel);


    //contactsNetworkingGroupForm
    String peelOffContactIds(ContactEntity contactEntity);
    List<String> peelOffContactIds(List<ContactEntity> contactEntities);


    //contact entity
    ContactEntity createContactEntity();
    ContactEntity savedContactEntity(ContactEntity contactEntity);
    ContactEntity getContactEntity(String contactId);
    List<ContactEntity> getAllContactEntities();
    List<ContactEntity> getMultipleContacts(List<String> memberIds);

    //get contact response
    ContactResponseModel getContactResponseModel(ContactEntity contactEntity);


    //save
    void saveContact(ContactEntity contact);


    //check object is null
    Boolean dtoIsNull(ContactDto contactDto);
    Boolean dtoIsNull(List<ContactDto> contactDtoList);
    Boolean entityIsNull(List<ContactEntity> contactEntities);
    Boolean entityIsNull(ContactEntity contactEntity);
    Boolean requestIsNull(ContactDetailsRequestModel contactDetailsRequestModel);
    Boolean requestIsNull(List<ContactDetailsRequestModel> contactDetailsRequestModelList);

    //generate contact entity
    ContactEntity generateUniqueContactId(ContactEntity contactEntity);
}
