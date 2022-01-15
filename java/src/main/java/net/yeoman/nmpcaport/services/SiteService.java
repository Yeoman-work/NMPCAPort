package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestListModel;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.shared.dto.SiteDto;

import java.util.List;

public interface SiteService {

    //get site DTO
    public SiteDto getSite(String siteId);
    public SiteDto updatedSite(SiteDto site);
    public List<SiteDto> createSiteBulk(List<SiteDetailsRequestModel> siteDetailsRequestModels, HealthCenterEntity healthCenterEntity);

    public

    //convert entity to dto
    public SiteDto entityToDto(SiteEntity siteEntity);
    public List<SiteDto> entityToDto(List<SiteEntity> siteEntities);

    //convert request to dto
    public SiteDto requestToDto(SiteDetailsRequestModel siteDetailsRequestModel);
    public List<SiteDto> requestToDto(List<SiteDetailsRequestModel> siteDetailsRequestModels);

    //convert dto to nestedResponse
    public SiteDetailsNestedResponse dtoToNestedResponse(SiteDto siteDto);
    public List<SiteDetailsNestedResponse> dtoNestedResponse(List<SiteDto> siteDtoList);


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
