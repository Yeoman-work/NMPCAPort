package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.NMSenateCommitteeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NMSenateCommitteeRepository extends CrudRepository<NMSenateCommitteeEntity, Long> {

    List<NMSenateCommitteeEntity> findAll();
    NMSenateCommitteeEntity findByNmSenateCommitteeId(String committeeId);

    Boolean existsByNmSenateCommitteeId(String committeeId);
    Boolean existsByName(String name);

}
