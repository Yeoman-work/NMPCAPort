package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.CountyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountyRepository extends CrudRepository<CountyEntity, Long> {

    List<CountyEntity> findAll();

    CountyEntity findByCountyId(String countyId);

    Boolean existsByName(String name);
}
