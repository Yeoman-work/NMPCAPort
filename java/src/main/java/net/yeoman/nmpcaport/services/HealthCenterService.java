package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.ServiceEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.repositories.SiteServiceDetailsRepository;
import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseBaseModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.fund.FundNestedResponse;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceNestedResponse;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;

import java.util.List;

public interface HealthCenterService {


    //return dto
    public HealthCenterDto getHealthCenter(String healthCenterId);
    public HealthCenterDto updateHealthCenter(String healthCenterId, HealthCenterDto healthCenterDto);
    public HealthCenterDto deleteHealthCenter(String healthCenterId);
    public List<HealthCenterDto> getHealthCenters(int page, int limit);
    public List<HealthCenterDto> getAllHealthCenters();

    //convert request
    public HealthCenterDto requestToDto(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel);


    //create healthCenter
    public HealthCenterResponseModel createHealthCenter(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel);

    //return entity
    public HealthCenterEntity generateHealthCenterWithUniqueHealthCenterId(HealthCenterEntity healthCenter);
    public HealthCenterEntity getHealthCenterEntity(String healthCenterId);
    public HealthCenterEntity savedHealthCenterEntity(HealthCenterEntity healthCenterEntity);

    //convert entity to DTO
    public HealthCenterDto entityToDto(HealthCenterEntity healthCenterEntity);
    public List<HealthCenterDto> entityToDto(List<HealthCenterEntity> healthCenterEntityList);

    //convert dto to entity
    public HealthCenterEntity dtoToEntity(HealthCenterDto healthCenterDto);
    public List<HealthCenterEntity> dtoToEntity(List<HealthCenterDto> healthCenterDtoList);

    //convert dto to health center response
    public HealthCenterResponseModel dtoToHealthCenterResponse(HealthCenterDto healthCenterDto);
    public List<HealthCenterResponseModel> dtoToHealthCenterResponseModel(List<HealthCenterDto> healthCenterDtoList);

    //get services from sites
    public List<ServiceNestedResponse> getHealthCenterServicesFromSites(List<SiteEntity> siteEntities);


    //get funding from sites
    public List<FundNestedResponse> getHealthCenterFundingFromSites(List<SiteEntity> siteEntities);

    //get NM house districts from sites
    public List<NMHouseDistrictNestedResponse> getHealthCenterNMHouseDistrictsFromSites(List<SiteEntity> siteEntities);

    //get senate districts from states
    public List<SenateDistrictNestedResponse> getHealthCenterSenateDistrictFromSites(List<SiteEntity> siteEntities);

    //get congressional districts from sites
    public List<CongressionalDistrictNestedResponse> getHealthCenterCongressionalDistrictFromSites(List<SiteEntity> siteEntities);

    public HealthCenterResponseBaseModel dtoToHealthCenterResponseBaseModel(HealthCenterDto healthCenterDto);
    public List<HealthCenterResponseBaseModel> dtoToHealthCenterResponseBaseModel(List<HealthCenterDto> healthCenterDtoList);
    public HealthCenterNestedResponseModel dtoToHealthCenterNestedResponseModel(HealthCenterDto healthCenterDto);
    public List<HealthCenterNestedResponseModel> dtoToHealthCenterNestedResponseModelArray(List<HealthCenterDto> healthCenterDtoList);
    public HealthCenterDetailsRequestModel dtoToHealthCenterDetailsRequestModel(HealthCenterDto healthCenterDto);
    public List<HealthCenterDetailsRequestModel> dtoToHealthCenterDetailsRequestModel(List<HealthCenterDto> healthCenterDtoList);


    //check if object is null
    public Boolean entityIsNull(HealthCenterEntity healthCenter);
    public Boolean entityArrayIsNull(List<HealthCenterEntity> healthCenterEntityList);
    public Boolean dtoIsNull(HealthCenterDto healthCenterDto);
    public Boolean dtoArrayIsNull(List<HealthCenterDto> healthCenterDtoList);
    public Boolean responseIsNull(List<HealthCenterResponseModel> healthCenterResponseModelList);
    public Boolean responseIsNull(HealthCenterResponseModel healthCenterResponseModel);
    public Boolean requestIsNull(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel);
    public Boolean requestIsNull(List<HealthCenterDetailsRequestModel> healthCenterDetailsRequestModelList);

}
