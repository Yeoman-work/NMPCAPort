package net.yeoman.nmpcaport.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.yeoman.nmpcaport.entities.ZipCodeEntity;

@Repository
public interface ZipCodeRepository extends PagingAndSortingRepository<ZipCodeEntity, Long> {

	//find all zip codes
    List<ZipCodeEntity> findAll();
    
    //find one zipCode entity
    ZipCodeEntity findByZipCodeId(String zipCodeId);
    
    // zipCode exist by name
    Boolean existsByName(String name);
    
    //check if public id is taken
    Boolean existsByZipCodeId(String publicId);
    
    List<ZipCodeEntity> findByNameContaining(String name);
}
