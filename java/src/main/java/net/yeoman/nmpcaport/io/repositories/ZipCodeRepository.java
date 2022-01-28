package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCodeEntity, Long> {

	//find all zip codes
    List<ZipCodeEntity> findAll();
    
    //find one zipCode entity
    ZipCodeEntity findByZipCodeId(String zipCodeId);
    
    // zipCode exist by name
    Boolean existsByName(String name);
    
    //check if public id is taken
    Boolean existsByZipCodeId(String publicId);
}
