package net.yeoman.nmpcaport.repositories;

import net.yeoman.nmpcaport.entities.FundEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FundRepository extends CrudRepository<FundEntity, Long> {

    FundEntity findByFundId(String fundId);
    List<FundEntity> findAll();
    Boolean existsByFundId(String fundId);

}
