package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkingGroupRepository extends CrudRepository<NetworkingGroupEntity, Long> {

    NetworkingGroupEntity findByNetworkingGroupId(String netGrpId);
    Boolean existsByName(String name);

    List<NetworkingGroupEntity> findByUsersContains(UserEntity user);
}
