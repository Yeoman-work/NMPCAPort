package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.NMHouseDistrictCountyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepCountyRepository extends CrudRepository<NMHouseDistrictCountyEntity, Long> {

    List<NMHouseDistrictCountyEntity> findAll();

    NMHouseDistrictCountyEntity findByStateRepCountyId(String stateRepCountyId);

}
