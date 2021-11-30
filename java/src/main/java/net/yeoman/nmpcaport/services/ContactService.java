package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.shared.dto.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto getContact(String contactId);
    ContactDto createContact(ContactDto contactDto);
    ContactDto updateContact(String contactId, ContactDto contactDto);
    ContactDto deleteContact(String contactId);
    ContactDto nestedNetworkingGroups(ContactDto contactDto);
    ContactEntity getContactEntity(String contactId);
    void saveContact(ContactEntity contact);
    List<ContactResponseModel> getAllContacts();
}
