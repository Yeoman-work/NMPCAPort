package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.HouseCommitteeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseCommitteeRepository extends CrudRepository<HouseCommitteeEntity, Long> {

    Boolean existsByPublicId(String publicId);
}
