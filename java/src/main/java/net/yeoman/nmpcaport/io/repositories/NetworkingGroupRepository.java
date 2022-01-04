package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkingGroupRepository extends CrudRepository<NetworkingGroupEntity, Long> {

    List<NetworkingGroupEntity> findAll();
    NetworkingGroupEntity findByNetworkingGroupId(String netGrpId);
    Boolean existsByName(String name);

}
