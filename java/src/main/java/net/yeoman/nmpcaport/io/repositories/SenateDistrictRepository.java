package net.yeoman.nmpcaport.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;

public interface SenateDistrictRepository extends PagingAndSortingRepository<SenateDistrictEntity, Long> {

    List<SenateDistrictEntity> findAll();
    
    List<SenateDistrictEntity> findByNameContaining(String name);
    
    SenateDistrictEntity findBySenateDistrictId(String senateDistrictId);

    Boolean existsByName(String name);
}
