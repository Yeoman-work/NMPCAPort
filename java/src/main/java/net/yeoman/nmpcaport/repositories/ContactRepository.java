package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Long> {

    ContactEntity findByContactId(String contactId);
}