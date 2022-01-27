package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.io.response.fund.FundNestedResponse;
import net.yeoman.nmpcaport.shared.dto.FundDto;
import net.yeoman.nmpcaport.shared.dto.SiteFundingDetailsDto;

import java.util.List;

public interface SiteFundingDetailsService {

    SiteFundingDetailsDto getSiteFunding(String id);
    void deleteSiteFunding(String id);
    List<SiteFundingDetailsDto> getAllSiteFunding();
    SiteFundingDetailsEntity savedSiteFundDetails(SiteFundingDetailsEntity siteFundingDetailsEntity);

    SiteFundingDetailsEntity saveSiteFundDetailsWithReturnEntity(SiteFundingDetailsEntity siteFundingDetailsServiceEntity);
    SiteFundingDetailsEntity createSiteFundingEntity();
    SiteFundingDetailsEntity generateUniqueId(SiteFundingDetailsEntity siteFundingDetailsEntity);
    Boolean existByPublicId(String publicId);

    //get fund entity
    FundEntity getFundEntity(SiteFundingDetailsEntity siteFundingDetailsEntity);
    List<FundEntity> getFundEntities(List<SiteFundingDetailsEntity> siteFundingDetailsEntities);


    //create funding relationship to site
    void linkFundingToSites(List<FundEntity> fundEntityList, SiteEntity siteEntity);

    //get site entity
    SiteEntity getSiteEntity(SiteFundingDetailsEntity siteFundingDetailsEntity);
    List<SiteEntity> getSiteEntities(List<SiteFundingDetailsEntity> siteFundingDetailsEntityList);


    Boolean entityIsNull(SiteFundingDetailsEntity siteFundingDetailsEntity);
    Boolean entityIsNull(List<SiteFundingDetailsEntity> siteFundingDetailsEntities);
    Boolean dtoIsNull(SiteFundingDetailsEntity siteFundingDetailsEntity);
    Boolean dtoIsNull(List<SiteFundingDetailsEntity> siteFundingDetailsEntities);
}
