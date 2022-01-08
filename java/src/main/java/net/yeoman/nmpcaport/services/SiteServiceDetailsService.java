package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.SiteServiceDetailsEntity;
import net.yeoman.nmpcaport.shared.dto.SiteServiceDetailsDto;

public interface SiteServiceDetailsService {


    public SiteServiceDetailsDto getSiteService(String id);
    public SiteServiceDetailsDto deleteSiteService(String id);
    public SiteServiceDetailsDto createSiteService(SiteServiceDetailsDto siteServiceDetailsDto);
    public SiteServiceDetailsDto updateSiteService(SiteServiceDetailsDto serviceDetailsDto, String id);
    public SiteServiceDetailsEntity createSiteServiceEntity(SiteServiceDetailsEntity serviceDetailsEntity);
    public Boolean existByPublicId(String publicId);

}
