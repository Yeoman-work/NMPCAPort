package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {

    List<ServiceEntity> findAll();

    ServiceEntity findByServiceId(String serviceId);
}
