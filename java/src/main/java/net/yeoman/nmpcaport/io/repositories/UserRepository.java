package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    Boolean existsByEmail(String email);

    UserEntity findByUserId(String userId);

    Boolean existsByUserId(String userId);
}
