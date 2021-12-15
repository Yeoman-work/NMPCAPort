package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.NMHouseStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NMHouseStatusRepository extends CrudRepository<NMHouseStatusEntity, Long> {

    List<NMHouseStatusEntity> findAll();
    NMHouseStatusEntity findByHouseStatusId(String statusId);
}
