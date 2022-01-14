package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.io.response.fund.FundNestedResponse;
import net.yeoman.nmpcaport.shared.dto.FundDto;
import net.yeoman.nmpcaport.shared.dto.SiteFundingDetailsDto;

import java.util.List;

public interface SiteFundingDetailsService {

    public SiteFundingDetailsDto getSiteFunding(String id);
    public SiteFundingDetailsDto deleteSiteFunding(String id);
    public List<SiteFundingDetailsDto> getAllSiteFunding();
    public SiteFundingDetailsDto createSiteFunding(SiteFundingDetailsEntity siteFundingDetailsServiceEntity);
    public SiteFundingDetailsEntity createSiteFundingEntity(SiteFundingDetailsEntity siteFundingDetailsEntity);
    public Boolean existByPublicId(String publicId);

    //get fund entity
    public FundEntity getFundEntity(SiteFundingDetailsEntity siteFundingDetailsEntity);
    public List<FundEntity> getFundEntities(List<SiteFundingDetailsEntity> siteFundingDetailsEntities);

    //get site entity
    public SiteEntity getSiteEntity(SiteFundingDetailsEntity siteFundingDetailsEntity);
    public List<SiteEntity> getSiteEntities(List<SiteFundingDetailsEntity> siteFundingDetailsEntityList);

    public Boolean entityIsNull(SiteFundingDetailsEntity siteFundingDetailsEntity);
    public Boolean entityIsNull(List<SiteFundingDetailsEntity> siteFundingDetailsEntities);
    public Boolean dtoIsNull(SiteFundingDetailsEntity siteFundingDetailsEntity);
    public Boolean dtoIsNull(List<SiteFundingDetailsEntity> siteFundingDetailsEntities);
}
