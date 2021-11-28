package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {

    List<LocationEntity> findAll();

    LocationEntity findByLocationId(String locationId);
}
