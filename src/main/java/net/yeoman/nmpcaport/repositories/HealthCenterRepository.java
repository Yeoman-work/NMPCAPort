package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthCenterRepository extends CrudRepository<HealthCenterEntity, Long> {

    HealthCenterEntity findByHealthCenterId(String healthCenterId);
}
