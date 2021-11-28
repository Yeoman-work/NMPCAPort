package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.PoliticalPartyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PoliticalPartyRepository extends CrudRepository<PoliticalPartyEntity, Long> {

    List<PoliticalPartyEntity> findAll();

    PoliticalPartyEntity findByPartyId(String partyId);

    Boolean existsByPartyId(String partyId);
}
