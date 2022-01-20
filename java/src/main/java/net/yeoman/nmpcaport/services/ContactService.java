package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.contact.*;
import net.yeoman.nmpcaport.shared.dto.ContactDto;

import java.util.List;

public interface ContactService {

    //get contact dto functions
    public ContactDto requestToDto(ContactDetailsRequestModel contactDetailsRequestModel);
    public List<ContactDto> requestToDto(List<ContactDetailsRequestModel> contactDetailsRequestModelList);
    public ContactDto entityToDto(ContactEntity contactEntity);
    public ContactDto updateContact(String contactId, ContactDto contactDto);
    public ContactDto deleteContact(String contactId);
    public List<ContactDto> entityToDto(List<ContactEntity> contactEntities);
    public void processContact(ContactDto contactDto);

    //entity dto
    public ContactDto partialEntityToDto(ContactEntity contactEntity);
    public List<ContactDto> partialEntityToDto(List<ContactEntity> contactEntity);

    //contact dashboard
    public ContactDashBoard contactDashboardData(ContactEntity contactEntity);
    public List<ContactDashBoard> contactDashboardData(List<ContactEntity> contactEntities);

    //contact essentials
    public ContactEssentials getContactEssentials(ContactEntity contactEntity);
    public List<ContactEssentials> getContactEssentials(List<ContactEntity> contactEntities);

    //get memberIds


    //create contact
    public void createContact(ContactDetailsRequestModel contactDetailsRequestModel);

    //nested response
    public ContactNestedResponseModel dtoToNestedResponse(ContactDto contactDto) ;
    public List<ContactNestedResponseModel> dtoToNestedResponse(List<ContactDto> contactDtoList);

    //contactsNetworkingGroupForm
    public ContactFormListResponse contactsForNetworkingGroup();
    public String peelOffContactIds(ContactEntity contactEntity);
    public List<String> peelOffContactIds(List<ContactEntity> contactEntities);


    //contact entity
    public ContactEntity dtoToEntity(ContactDto contactDto);
    public ContactEntity createContactEntity();
    public List<ContactEntity> dtoToEntity(List<ContactDto> contactDtoList);
    public ContactEntity savedContactEntity(ContactEntity contactEntity);
    public ContactEntity getContactEntity(String contactId);
    public List<ContactEntity> getAllContactEntities();
    public ContactEntity getContact(String contactId);
    public List<ContactEntity> getMultipleContacts(List<String> memberIds);



    //contact response
    public ContactResponseModel dtoToResponse(ContactDto contactDto);
    public List<ContactResponseModel> dtoToResponse(List<ContactDto> contactDtoList);


    //save
    public void saveContact(ContactEntity contact);
    List<ContactResponseModel> getAllContacts();

    //check object is null
    public Boolean dtoIsNull(ContactDto contactDto);
    public Boolean dtoIsNull(List<ContactDto> contactDtoList);
    public Boolean entityIsNull(List<ContactEntity> contactEntities);
    public Boolean entityIsNull(ContactEntity contactEntity);
    public Boolean requestIsNull(ContactDetailsRequestModel contactDetailsRequestModel);
    public Boolean requestIsNull(List<ContactDetailsRequestModel> contactDetailsRequestModelList);
    public Boolean responseIsNull(ContactResponseModel contactResponseModel);
    public Boolean responseIsNull(List<ContactResponseModel> contactResponseModels);
    public Boolean nestedResponseIsNull(List<ContactNestedResponseModel> contactNestedResponseModels);
    public Boolean nestedResponseIsNull(ContactNestedResponseModel contactNestedResponseModel);

    //generate contact entity
    public ContactEntity generateUniqueContactId(ContactEntity contactEntity);
}
