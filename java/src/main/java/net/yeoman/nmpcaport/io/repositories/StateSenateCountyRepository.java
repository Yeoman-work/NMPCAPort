package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.StateSenateCountyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateSenateCountyRepository extends CrudRepository<StateSenateCountyEntity, Long> {

    List<StateSenateCountyEntity> findAll();

    StateSenateCountyEntity findBySenateCountyId(String senateCountyId);
}
