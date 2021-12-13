package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.StateHouseCommitteeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StateHouseCommitteeRepository extends CrudRepository<StateHouseCommitteeEntity, Long> {

    List<StateHouseCommitteeEntity> findAll();
    StateHouseCommitteeEntity findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByHouseCommitteeId(String committeeId);
}
