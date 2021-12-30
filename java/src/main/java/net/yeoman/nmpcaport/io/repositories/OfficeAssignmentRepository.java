package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.OfficeAssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeAssignmentRepository extends CrudRepository<OfficeAssignmentEntity, Long> {

    List<OfficeAssignmentEntity> findAll();

    OfficeAssignmentEntity findByPublicId(String publicId);
}
