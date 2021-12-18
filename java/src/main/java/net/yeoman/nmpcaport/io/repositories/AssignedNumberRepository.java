package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedNumberRepository extends CrudRepository<AssignedNumberEntity, Long> {

    AssignedNumberEntity findByAssignmentId(String assignmentId);
}
