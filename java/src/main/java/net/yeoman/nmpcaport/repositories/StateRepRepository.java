package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.StateRepEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepRepository extends CrudRepository<StateRepEntity, Long> {

    List<StateRepEntity> findAll();

    StateRepEntity findByStateRepId(String stateRepId);

}
