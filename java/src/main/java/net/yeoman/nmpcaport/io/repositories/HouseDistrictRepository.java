package net.yeoman.nmpcaport.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.yeoman.nmpcaport.entities.HouseDistrictEntity;

@Repository
public interface HouseDistrictRepository extends PagingAndSortingRepository<HouseDistrictEntity, Long> {

    List<HouseDistrictEntity> findAll();
    
    List<HouseDistrictEntity> findByNameContaining(String name);

    HouseDistrictEntity findByHouseDistrictId(String houseDistrictId);

    Boolean existsByHouseDistrictId(String houseDistrict);

    Boolean existsByName(String name);
}
