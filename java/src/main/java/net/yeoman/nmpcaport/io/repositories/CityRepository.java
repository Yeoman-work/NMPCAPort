package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {

    CityEntity findByCityId(String cityId);

    List<CityEntity> findAll();


}
