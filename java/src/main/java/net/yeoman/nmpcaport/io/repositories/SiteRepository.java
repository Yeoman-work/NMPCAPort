package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.SiteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SiteRepository extends CrudRepository<SiteEntity, Long> {

    List<SiteEntity> findAll();

    SiteEntity findBySiteId(String siteId);

}
