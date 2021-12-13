package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.GovernorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GovernorRepository extends CrudRepository<GovernorEntity, Long> {

    List<GovernorEntity> findAll();

    GovernorEntity findByGovernorId(String governorId);

    Boolean existsByGovernorId(String governorId);
}
