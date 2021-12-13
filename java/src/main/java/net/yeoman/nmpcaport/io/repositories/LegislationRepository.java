package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.LegislationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegislationRepository extends CrudRepository<LegislationEntity, Long> {

    List<LegislationEntity> findAll();

    LegislationEntity findByLegislationId(String legislationId);

    Boolean existsByLegislationId(String Id);
}
