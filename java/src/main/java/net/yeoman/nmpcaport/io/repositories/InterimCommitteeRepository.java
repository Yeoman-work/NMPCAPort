package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.InterimCommitteeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterimCommitteeRepository extends CrudRepository<InterimCommitteeEntity, Long> {

    List<InterimCommitteeEntity> findAll();
    InterimCommitteeEntity findByPublicId(String publicId);
    Boolean existsByPublicId(String publicId);
}
