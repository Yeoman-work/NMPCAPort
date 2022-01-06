package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.io.request.fund.FundRequestModel;
import net.yeoman.nmpcaport.io.request.service.ServiceDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

import java.util.Date;
import java.util.List;

public class SiteDto {

    private Long id;
    private String site_identifier;
    private String name;
    private String streetAddress;
    private String city;
    private String county;
    private String zipCode;
    private String healthCenter;
    private String nmHouseDistrict;
    private String senateDistrict;
    private String congressionalDistrict;
    private List<String> fund;
    private List<String> service;
    private CityResponse cityResponse;
    private CityEntity cityEntity;
    private CountyResponse countyResponse;
    private CountyEntity countyEntity;
    private HealthCenterResponseModel healthCenterResponse;
    private HealthCenterEntity healthCenterEntity;
    private ZipCodeResponse zipCodeResponse;
    private ZipCodeEntity zipCodeEntity;
    private List<FundEntity> fundEntities;
    private List<FundRequestModel> fundRequestModels;
    private List<FundResponseModel> fundResponseModels;
    private List<ServiceEntity> serviceEntities;
    private List<ServiceDetailsRequestModel> serviceDetailsRequestModels;
    private List<ServiceResponse> serviceResponses;
    private NMHouseDistrictEntity nmHouseDistrictEntity;
    private SenateDistrictEntity senateDistrictEntity;
    private CongressionalDistrictEntity congressionalDistrictEntity;
    private NMHouseDistrictResponse nmHouseDistrictResponse;
    private SenateDistrictResponseModel senateDistrictResponseModel;
    private CongressionalDistrictResponse congressionalDistrictResponse;
    private Date createdAt;
    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSite_identifier() {
        return site_identifier;
    }

    public void setSite_identifier(String site_identifier) {
        this.site_identifier = site_identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(String healthCenter) {
        this.healthCenter = healthCenter;
    }

    public String getNmHouseDistrict() {
        return nmHouseDistrict;
    }

    public void setNmHouseDistrict(String nmHouseDistrict) {
        this.nmHouseDistrict = nmHouseDistrict;
    }

    public String getSenateDistrict() {
        return senateDistrict;
    }

    public void setSenateDistrict(String senateDistrict) {
        this.senateDistrict = senateDistrict;
    }

    public String getCongressionalDistrict() {
        return congressionalDistrict;
    }

    public void setCongressionalDistrict(String congressionalDistrict) {
        this.congressionalDistrict = congressionalDistrict;
    }

    public List<String> getFund() {
        return fund;
    }

    public void setFund(List<String> fund) {
        this.fund = fund;
    }

    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        this.service = service;
    }

    public CityResponse getCityResponse() {
        return cityResponse;
    }

    public void setCityResponse(CityResponse cityResponse) {
        this.cityResponse = cityResponse;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public CountyResponse getCountyResponse() {
        return countyResponse;
    }

    public void setCountyResponse(CountyResponse countyResponse) {
        this.countyResponse = countyResponse;
    }

    public CountyEntity getCountyEntity() {
        return countyEntity;
    }

    public void setCountyEntity(CountyEntity countyEntity) {
        this.countyEntity = countyEntity;
    }

    public HealthCenterResponseModel getHealthCenterResponse() {
        return healthCenterResponse;
    }

    public void setHealthCenterResponse(HealthCenterResponseModel healthCenterResponse) {
        this.healthCenterResponse = healthCenterResponse;
    }

    public HealthCenterEntity getHealthCenterEntity() {
        return healthCenterEntity;
    }

    public void setHealthCenterEntity(HealthCenterEntity healthCenterEntity) {
        this.healthCenterEntity = healthCenterEntity;
    }

    public ZipCodeResponse getZipCodeResponse() {
        return zipCodeResponse;
    }

    public void setZipCodeResponse(ZipCodeResponse zipCodeResponse) {
        this.zipCodeResponse = zipCodeResponse;
    }

    public ZipCodeEntity getZipCodeEntity() {
        return zipCodeEntity;
    }

    public void setZipCodeEntity(ZipCodeEntity zipCodeEntity) {
        this.zipCodeEntity = zipCodeEntity;
    }

    public List<FundEntity> getFundEntities() {
        return fundEntities;
    }

    public void setFundEntities(List<FundEntity> fundEntities) {
        this.fundEntities = fundEntities;
    }

    public List<FundRequestModel> getFundRequestModels() {
        return fundRequestModels;
    }

    public void setFundRequestModels(List<FundRequestModel> fundRequestModels) {
        this.fundRequestModels = fundRequestModels;
    }

    public List<FundResponseModel> getFundResponseModels() {
        return fundResponseModels;
    }

    public void setFundResponseModels(List<FundResponseModel> fundResponseModels) {
        this.fundResponseModels = fundResponseModels;
    }

    public List<ServiceEntity> getServiceEntities() {
        return serviceEntities;
    }

    public void setServiceEntities(List<ServiceEntity> serviceEntities) {
        this.serviceEntities = serviceEntities;
    }

    public List<ServiceDetailsRequestModel> getServiceDetailsRequestModels() {
        return serviceDetailsRequestModels;
    }

    public void setServiceDetailsRequestModels(List<ServiceDetailsRequestModel> serviceDetailsRequestModels) {
        this.serviceDetailsRequestModels = serviceDetailsRequestModels;
    }

    public List<ServiceResponse> getServiceResponses() {
        return serviceResponses;
    }

    public void setServiceResponses(List<ServiceResponse> serviceResponses) {
        this.serviceResponses = serviceResponses;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public NMHouseDistrictEntity getNmHouseDistrictEntity() {
        return nmHouseDistrictEntity;
    }

    public void setNmHouseDistrictEntity(NMHouseDistrictEntity nmHouseDistrictEntity) {
        this.nmHouseDistrictEntity = nmHouseDistrictEntity;
    }

    public SenateDistrictEntity getSenateDistrictEntity() {
        return senateDistrictEntity;
    }

    public void setSenateDistrictEntity(SenateDistrictEntity senateDistrictEntity) {
        this.senateDistrictEntity = senateDistrictEntity;
    }

    public CongressionalDistrictEntity getCongressionalDistrictEntity() {
        return congressionalDistrictEntity;
    }

    public void setCongressionalDistrictEntity(CongressionalDistrictEntity congressionalDistrictEntity) {
        this.congressionalDistrictEntity = congressionalDistrictEntity;
    }

    public NMHouseDistrictResponse getNmHouseDistrictResponse() {
        return nmHouseDistrictResponse;
    }

    public void setNmHouseDistrictResponse(NMHouseDistrictResponse nmHouseDistrictResponse) {
        this.nmHouseDistrictResponse = nmHouseDistrictResponse;
    }

    public SenateDistrictResponseModel getSenateDistrictResponseModel() {
        return senateDistrictResponseModel;
    }

    public void setSenateDistrictResponseModel(SenateDistrictResponseModel senateDistrictResponseModel) {
        this.senateDistrictResponseModel = senateDistrictResponseModel;
    }

    public CongressionalDistrictResponse getCongressionalDistrictResponse() {
        return congressionalDistrictResponse;
    }

    public void setCongressionalDistrictResponse(CongressionalDistrictResponse congressionalDistrictResponse) {
        this.congressionalDistrictResponse = congressionalDistrictResponse;
    }
}
