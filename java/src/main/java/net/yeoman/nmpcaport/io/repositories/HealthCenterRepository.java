package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthCenterRepository extends PagingAndSortingRepository<HealthCenterEntity, Long> {

    HealthCenterEntity findByHealthCenterId(String healthCenterId);

    List<HealthCenterEntity> findAll();
}
