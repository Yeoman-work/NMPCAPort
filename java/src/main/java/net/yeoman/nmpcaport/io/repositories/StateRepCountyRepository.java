package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.StateRepCountyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateRepCountyRepository extends CrudRepository<StateRepCountyEntity, Long> {

    List<StateRepCountyEntity> findAll();

    StateRepCountyEntity findStateRepCountyId(String stateRepCountyId);

}
