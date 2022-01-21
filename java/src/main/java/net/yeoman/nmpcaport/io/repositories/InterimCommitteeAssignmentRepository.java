package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.InterimCommitteeAssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterimCommitteeAssignmentRepository extends CrudRepository<InterimCommitteeAssignmentEntity, Long> {

    Boolean existsByPublicId(String publicId);
}
