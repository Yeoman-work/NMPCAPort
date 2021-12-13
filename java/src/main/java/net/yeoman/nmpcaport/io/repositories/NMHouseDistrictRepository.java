package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.NMHouseDistrictEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NMHouseDistrictRepository extends CrudRepository<NMHouseDistrictEntity, Long> {

    List<NMHouseDistrictEntity> findAll();

    NMHouseDistrictEntity findByHouseDistrictId(String houseDistrictId);

    Boolean existsByName(String name);
}
