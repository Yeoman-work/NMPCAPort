package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ServiceEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteServiceDetailsEntity;
import net.yeoman.nmpcaport.shared.dto.SiteServiceDetailsDto;

import java.util.List;

public interface SiteServiceDetailsService {


    public SiteServiceDetailsDto getSiteService(String id);

    public SiteServiceDetailsDto deleteSiteService(String id);

    public SiteServiceDetailsDto createSiteService(SiteServiceDetailsDto siteServiceDetailsDto);

    public SiteServiceDetailsDto updateSiteService(SiteServiceDetailsDto serviceDetailsDto, String id);

    public SiteServiceDetailsEntity createSiteServiceEntity(SiteServiceDetailsEntity serviceDetailsEntity);

    public Boolean existByPublicId(String publicId);

    //get sitesFrom site details
    public SiteEntity getSiteEntity(SiteServiceDetailsEntity siteServiceDetailsEntity);
    public List<SiteEntity> getSiteEntities(List<SiteServiceDetailsEntity> siteServiceDetailsEntities);

    //get service from details
    public ServiceEntity getServiceEntity(SiteServiceDetailsEntity siteServiceDetailsEntity);
    public List<ServiceEntity> getServiceEntities(List<SiteServiceDetailsEntity> siteServiceDetailsEntities);


    //check if entity is null
    public Boolean entityIsNull(SiteServiceDetailsEntity siteServiceDetailsEntity);
    public Boolean entityIsNull(List<SiteServiceDetailsEntity> siteServiceDetailsEntities);

}
