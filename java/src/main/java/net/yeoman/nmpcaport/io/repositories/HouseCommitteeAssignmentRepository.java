package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.HouseCommitteeAssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseCommitteeAssignmentRepository extends CrudRepository<HouseCommitteeAssignmentEntity, Long> {

    Boolean existsByPublicId(String publicId);
}
