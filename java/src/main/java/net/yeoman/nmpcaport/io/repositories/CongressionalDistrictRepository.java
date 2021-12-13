package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.CongressionalDistrictEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongressionalDistrictRepository extends CrudRepository<CongressionalDistrictEntity, Long> {

    List<CongressionalDistrictEntity> findAll();

    Boolean existsByName(String name);

    CongressionalDistrictEntity findByCongressionalDistrictId(String congressionalDistrictId);
}
