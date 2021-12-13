package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SiteFundingDetailsRepository extends CrudRepository<SiteFundingDetailsEntity, Long> {

    List<SiteFundingDetailsEntity> findAll();

    SiteFundingDetailsEntity findBySiteFundingDetailsId(String id);
}
