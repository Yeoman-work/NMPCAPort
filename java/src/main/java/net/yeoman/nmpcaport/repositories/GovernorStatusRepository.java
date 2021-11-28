package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.GovernorStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GovernorStatusRepository extends CrudRepository<GovernorStatusEntity, Long> {

    List<GovernorStatusEntity> findAll();

    GovernorStatusEntity findByGovStatusId(String id);
}
