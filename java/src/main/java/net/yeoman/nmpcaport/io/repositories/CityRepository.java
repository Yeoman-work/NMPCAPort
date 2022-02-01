package net.yeoman.nmpcaport.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.yeoman.nmpcaport.entities.CityEntity;

@Repository
public interface CityRepository extends PagingAndSortingRepository<CityEntity, Long> {

    CityEntity findByCityId(String cityId);

    List<CityEntity> findAll();
    
    List<CityEntity> findByNameContaining(String name);
    	            
    

    Boolean existsByCityId(String cityId);
    Boolean existsByName(String name);


}
