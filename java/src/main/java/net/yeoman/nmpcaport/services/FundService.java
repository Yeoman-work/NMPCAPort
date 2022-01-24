package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.io.request.fund.FundRequestListModel;
import net.yeoman.nmpcaport.io.response.fund.FundEssentialsResponse;
import net.yeoman.nmpcaport.io.response.fund.FundNestedResponse;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.shared.dto.FundDto;

import java.util.List;

public interface FundService {

    public FundEntity getFund(String fundId);
    public FundDto createFund(FundDto fundDto);
    public FundDto updateFund(FundDto fundDto, String fundId);
    public FundDto deleteFund(String fundId);
    public FundEntity getFundEntity(String fundId);
    public List<FundDto> getAllFunding();
    public List<FundDto> createFundBulk(FundRequestListModel fundRequestListModel);
    public List<FundEntity> getFunds(List<String> fundIds);
    public FundResponseModel getFundResponse(String fundId);

    //get essentials from entity
    public FundEssentialsResponse entityToEssential(FundEntity fundEntity);
    public List<FundEssentialsResponse> entityToEssential(List<FundEntity> fundList);

    //get fundList
    public List<FundEntity> gatherFundEntity(List<SiteFundingDetailsEntity> fundingDetailsEntities, List<FundEntity> fundEntities);


    //get essentials from sites
    //used for the health Center dashboard
    public FundEssentialsResponse getEssentialsFromSite(SiteEntity siteEntity);
    public List<FundEssentialsResponse> getEssentialsFromSite(List<SiteEntity> siteEntities);

    public FundDto entityToDto(FundEntity fundEntity);
    public List<FundDto> entityToDto(List<FundEntity> fundEntityList);

    public FundNestedResponse dtoToNestedResponse(FundDto fundDto);
    public List<FundNestedResponse> dtoToNestedResponse(List<FundDto> fundDtoList);
    public FundResponseModel dtoToResponse(FundDto fundDto);
    public List<FundResponseModel> dtoToResponse(List<FundDto> fundDtoList);

    public Boolean entityIsNull(FundEntity fundEntity);
    public Boolean entityIsNull(List<FundEntity> fundEntityList);
    public Boolean dtoIsNull(List<FundDto> fundDtoList);
    public Boolean dtoIsNull(FundDto fundDto);
}
