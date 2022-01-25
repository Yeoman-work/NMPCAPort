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
    public SiteEntity createSiteEntity(SiteDetailsRequestModel siteDetailsRequestModel);
    public List<SiteEntity> createSitesBulk(List<SiteDetailsRequestModel> siteDetailsRequestModels);


    //generated public id
    public SiteEntity generateUniqueSiteId(SiteEntity siteEntity);

    //save site
    public SiteEntity saveSite(SiteEntity siteEntity);


    //site for health Center dashboard
    public SiteEssentialsResponse entityToEssential(SiteEntity siteEntity);
    public List<SiteEssentialsResponse> entityToEssential(List<SiteEntity> siteEntities);


    //object is null
    public Boolean entityIsNull(SiteEntity siteEntity);
    public Boolean entityIsNull(List<SiteEntity> siteEntities);
    public Boolean dtoIsNull(SiteDto siteDto);
    public Boolean dtoIsNull(List<SiteDto> siteDtoList);
    public Boolean requestIsNull(SiteDetailsRequestModel siteDetailsRequestModel);
    public Boolean requestIsNull(List<SiteDetailsRequestModel> siteDetailsRequestModels);


    //get site entity
    public SiteEntity getSiteEntity(String siteId);

    //delete site
    public void deleteSite(String siteId);

    //exist
    public Boolean siteExist(String publicId);

}
