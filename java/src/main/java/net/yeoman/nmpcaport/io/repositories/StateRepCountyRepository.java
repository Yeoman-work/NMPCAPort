package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.StateRepCountyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepCountyRepository extends CrudRepository<StateRepCountyEntity, Long> {

    List<StateRepCountyEntity> findAll();

    StateRepCountyEntity findByStateRepCountyId(String stateRepCountyId);

}
