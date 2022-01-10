package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface ContactService {

    ContactDto getContact(String contactId);
    public ContactEntity dtoToEntity(ContactDto contactDto);
    public List<ContactEntity> dtoArrayToEntityArray(List<ContactDto> contactDtoList);
    public ContactResponseModel dtoToResponse(ContactDto contactDto);
    public List<ContactResponseModel> dtoArrayToResponse(List<ContactDto> contactDtoList);
    public ContactDto requestToDto(ContactDetailsRequestModel contactDetailsRequestModel);
    public List<ContactDto> requestArrayToDtoArray(List<ContactDetailsRequestModel> contactDetailsRequestModelList);
    public ContactDto EntityToDto(ContactEntity contactEntity);
    ContactEntity savedContactEntity(ContactEntity contactEntity);
    ContactDto updateContact(String contactId, ContactDto contactDto);
    ContactDto deleteContact(String contactId);
    ContactEntity getContactEntity(String contactId);
    public ContactDto processContactEntity(ContactDto contactDto);
    void saveContact(ContactEntity contact);
    List<ContactResponseModel> getAllContacts();
    public Boolean dtoIsNull(ContactDto contactDto);
    public Boolean entityIsNull(ContactEntity contactEntity);
    public Boolean requestIsNull(ContactDetailsRequestModel contactDetailsRequestModel);
    public Boolean responseIsNull(ContactResponseModel contactResponseModel);
    public Boolean nestedResponseIsNull(ContactNestedResponseModel contactNestedResponseModel);
}
