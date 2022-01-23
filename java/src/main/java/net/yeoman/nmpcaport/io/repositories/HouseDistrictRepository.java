package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.HouseDistrictEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseDistrictRepository extends CrudRepository<HouseDistrictEntity, Long> {

    List<HouseDistrictEntity> findAll();

    HouseDistrictEntity findByHouseDistrictId(String houseDistrictId);

    Boolean existsByName(String name);
}
