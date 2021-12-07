package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestListModel;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.shared.dto.SiteDto;

import java.util.List;

public interface SiteService {

    public SiteDto getSite(String siteId);
    public SiteDto createSite(SiteDetailsRequestModel site);
    public SiteDto updatedSite(SiteDto site);
    public SiteEntity getSiteEntity(String siteId);
    public void deleteSite(String siteId);
    public SiteDto convertFromRequestToDto(SiteDetailsRequestModel siteRequest);
    public List<SiteDto> createSiteBulk(List<SiteDto> siteDtoList);

}
