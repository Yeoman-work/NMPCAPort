package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.SenateStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SenateStatusRepository extends CrudRepository<SenateStatusEntity, Long> {

    List<SenateStatusEntity> findAll();

    SenateStatusEntity findBySenateStatusId(String senateStatusId);
}
