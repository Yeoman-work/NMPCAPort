package net.yeoman.nmpcaport.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.yeoman.nmpcaport.entities.SenateCommitteeEntity;

@Repository
public interface SenateCommitteeRepository extends PagingAndSortingRepository<SenateCommitteeEntity, Long> {

	List<SenateCommitteeEntity> findAll();
	
	List<SenateCommitteeEntity> findByNameContaining(String name);
	
	Boolean existByName(String name);
	
    Boolean existsByPublicId(String publicId);
    
}
