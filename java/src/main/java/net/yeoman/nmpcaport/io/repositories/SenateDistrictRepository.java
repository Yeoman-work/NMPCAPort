package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SenateDistrictRepository extends CrudRepository<SenateDistrictEntity, Long> {

    List<SenateDistrictEntity> findAll();

    SenateDistrictEntity findBySenateDistrictId(String senateDistrictId);

    Boolean existsByName(String name);
}
