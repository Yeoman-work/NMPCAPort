package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.entities.SiteServiceDetailsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SiteServiceDetailsRepository extends CrudRepository<SiteServiceDetailsEntity, Long> {

    List<SiteServiceDetailsEntity> findAll();
    SiteServiceDetailsEntity findBySiteServiceDetailsId(String id);

}
