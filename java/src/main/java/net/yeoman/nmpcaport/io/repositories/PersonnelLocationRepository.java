package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.PersonnelLocationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonnelLocationRepository extends CrudRepository<PersonnelLocationEntity, Long> {

    List<PersonnelLocationEntity> findAll();

    PersonnelLocationEntity findByPublicId(String publicId);
}
