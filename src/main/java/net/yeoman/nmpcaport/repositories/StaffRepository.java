package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.StaffEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaffRepository extends CrudRepository<StaffEntity, Long> {

    List<StaffEntity> findAll();
    StaffEntity findByStaffId(String staffId);
    Boolean existsByStaffId(String staffId);

}
