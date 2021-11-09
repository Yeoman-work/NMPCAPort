package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.USSenatorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface USSenatorRepository extends CrudRepository<USSenatorEntity, Long> {

    List<USSenatorEntity> findAll();

    USSenatorEntity findBySenatorId(String senatorId);

    Boolean existsBySenatorId(String senatorId);
}
