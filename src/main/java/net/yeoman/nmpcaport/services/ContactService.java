package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.shared.dto.ContactDto;

public interface ContactService {

    ContactDto getContact(String contactId);
    ContactDto createContact(ContactDto contactDto);
    ContactDto updateContact(String contactId, ContactDto contactDto);
    ContactDto deleteContact(String contactId);
    ContactDto nestedNetworkingGroups(ContactDto contactDto);
    ContactEntity getContactEntity(String contactId);
}
