package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.StateSenatorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateSenatorRepository extends CrudRepository<StateSenatorEntity, Long> {

    List<StateSenatorEntity> findAll();

    StateSenatorEntity findByStateSenatorId(String senatorId);

    Boolean existsByStateSenatorId(String stateSenatorId);

    Boolean existsByEmail(String email);
}
