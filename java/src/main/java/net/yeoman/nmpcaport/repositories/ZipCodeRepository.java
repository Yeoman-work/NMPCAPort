package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCodeEntity, Long> {

    List<ZipCodeEntity> findAll();
    ZipCodeEntity findByZipCodeId(String zipCodeId);
    Boolean existsByName(String name);
}
