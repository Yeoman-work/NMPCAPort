package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestListModel;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.io.response.site.SiteEssentialsResponse;
import net.yeoman.nmpcaport.shared.dto.SiteDto;

import java.util.List;

public interface SiteService {


    //create site from response
    void createSiteEntity(SiteDetailsRequestModel siteDetailsRequestModel, HealthCenterEntity healthCenterEntity);
    void createSitesBulk(List<SiteDetailsRequestModel> siteDetailsRequestModels, HealthCenterEntity healthCenterEntity);


    //generated public id
    SiteEntity generateUniqueSiteId(SiteEntity siteEntity);

    //save site
    SiteEntity saveSite(SiteEntity siteEntity);


    //site for health Center dashboard
    SiteEssentialsResponse entityToEssential(SiteEntity siteEntity);
    List<SiteEssentialsResponse> entityToEssential(List<SiteEntity> siteEntities);


    //object is null
    Boolean entityIsNull(SiteEntity siteEntity);Boolean entityIsNull(List<SiteEntity> siteEntities);
    Boolean dtoIsNull(SiteDto siteDto);
    Boolean dtoIsNull(List<SiteDto> siteDtoList);
    Boolean requestIsNull(SiteDetailsRequestModel siteDetailsRequestModel);
    Boolean requestIsNull(List<SiteDetailsRequestModel> siteDetailsRequestModels);


    //get site entity
    SiteEntity getSiteEntity(String siteId);

    //delete site
    void deleteSite(String siteId);

    //exist
    Boolean siteExist(String publicId);

}
