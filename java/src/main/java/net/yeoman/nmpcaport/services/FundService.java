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


    FundDto createFund(FundDto fundDto);
    FundDto updateFund(FundDto fundDto, String fundId);
    FundDto deleteFund(String fundId);
    List<FundDto> getAllFunding();
    List<FundDto> createFundBulk(FundRequestListModel fundRequestListModel);

    FundResponseModel getFundResponse(String fundId);

    //get essentials from entity
    FundEssentialsResponse entityToEssential(FundEntity fundEntity);
    List<FundEssentialsResponse> entityToEssential(List<FundEntity> fundList);

    //get fundList
    void gatherFundEntity(List<SiteFundingDetailsEntity> fundingDetailsEntities, List<FundEntity> fundEntities);

    //get funds from funding ids
    FundEntity getFundEntity(String fundId);
    List<FundEntity> getFundEntities(List<String> fundIds);

    //get essentials from sites
    //used for the health Center dashboard
    FundEssentialsResponse getEssentialsFromSite(String fundId, String fundName);
    List<FundEssentialsResponse> getEssentialsFromSite(List<SiteEntity> siteEntities);

    FundDto entityToDto(FundEntity fundEntity);
    List<FundDto> entityToDto(List<FundEntity> fundEntityList);

    FundNestedResponse dtoToNestedResponse(FundDto fundDto);
    List<FundNestedResponse> dtoToNestedResponse(List<FundDto> fundDtoList);
    FundResponseModel dtoToResponse(FundDto fundDto);
    List<FundResponseModel> dtoToResponse(List<FundDto> fundDtoList);

    Boolean entityIsNull(FundEntity fundEntity);
    Boolean entityIsNull(List<FundEntity> fundEntityList);
    Boolean dtoIsNull(List<FundDto> fundDtoList);
    Boolean dtoIsNull(FundDto fundDto);
}
