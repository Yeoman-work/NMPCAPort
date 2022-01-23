package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.SenateCommitteeAssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenateCommitteeAssignment extends CrudRepository<SenateCommitteeAssignmentEntity, Long> {

    Boolean existsByPublicId(String publicId);
}
