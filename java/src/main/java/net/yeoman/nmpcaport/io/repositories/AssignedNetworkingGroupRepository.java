package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignedNetworkingGroupRepository extends CrudRepository<AssignedNetworkingGroupEntity, Long> {


    AssignedNetworkingGroupEntity findByPublicId(String assignmentId);

    List<AssignedNetworkingGroupEntity> findAll();

    Boolean existsByPublicId(String publicId);
}
