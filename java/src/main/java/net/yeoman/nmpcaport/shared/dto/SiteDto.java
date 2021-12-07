package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

import java.util.List;

public class SiteDto {

    private Long id;
    private String siteId;
    private String name;
    private String streetAddress;
    private String cityId;
    private String countyId;
    private String zipCodeId;
    private String healthCenterId;
    private String nmHouseDistrictId;
    private String senateDistrictId;
    private String congressionalDistrictId;
    private List<String> fundIds;
    private List<String> serviceIds;
    private CityResponse cityResponse;
    private CityEntity city;
    private CountyResponse countyResponse;
    private CountyEntity county;
    private HealthCenterResponseModel healthCenterResponse;
    private HealthCenterEntity healthCenter;
    private ZipCodeResponse zipCodeResponse;
    private ZipCodeEntity zipCode;
    private String fundId;
    private String serviceId;
    private String createdAt;
    private String updatedAt;
    private NMHouseDistrictEntity nmHouseDistrictEntity;
    private SenateDistrictEntity senateDistrictEntity;
    private CongressionalDistrictEntity congressionalDistrict;
    private NMHouseDistrictResponse nmHouseDistrictResponse;
    private SenateDistrictResponseModel senateDistrictResponseModel;
    private CongressionalDistrictResponse congressionalDistrictResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getZipCodeId() {
        return zipCodeId;
    }

    public void setZipCodeId(String zipCodeId) {
        this.zipCodeId = zipCodeId;
    }

    public String getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(String healthCenterId) {
        this.healthCenterId = healthCenterId;
    }

    public CityResponse getCityResponse() {
        return cityResponse;
    }

    public void setCityResponse(CityResponse cityResponse) {
        this.cityResponse = cityResponse;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public CountyResponse getCountyResponse() {
        return countyResponse;
    }

    public void setCountyResponse(CountyResponse countyResponse) {
        this.countyResponse = countyResponse;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }

    public HealthCenterResponseModel getHealthCenterResponse() {
        return healthCenterResponse;
    }

    public void setHealthCenterResponse(HealthCenterResponseModel healthCenterResponse) {
        this.healthCenterResponse = healthCenterResponse;
    }

    public HealthCenterEntity getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenterEntity healthCenter) {
        this.healthCenter = healthCenter;
    }

    public ZipCodeResponse getZipCodeResponse() {
        return zipCodeResponse;
    }

    public void setZipCodeResponse(ZipCodeResponse zipCodeResponse) {
        this.zipCodeResponse = zipCodeResponse;
    }

    public ZipCodeEntity getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCodeEntity zipCode) {
        this.zipCode = zipCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
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

    public CongressionalDistrictEntity getCongressionalDistrict() {
        return congressionalDistrict;
    }

    public void setCongressionalDistrict(CongressionalDistrictEntity congressionalDistrict) {
        this.congressionalDistrict = congressionalDistrict;
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

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getNmHouseDistrictId() {
        return nmHouseDistrictId;
    }

    public void setNmHouseDistrictId(String nmHouseDistrictId) {
        this.nmHouseDistrictId = nmHouseDistrictId;
    }

    public String getSenateDistrictId() {
        return senateDistrictId;
    }

    public void setSenateDistrictId(String senateDistrictId) {
        this.senateDistrictId = senateDistrictId;
    }

    public String getCongressionalDistrictId() {
        return congressionalDistrictId;
    }

    public void setCongressionalDistrictId(String congressionalDistrictId) {
        this.congressionalDistrictId = congressionalDistrictId;
    }

    public List<String> getFundIds() {
        return fundIds;
    }

    public void setFundIds(List<String> fundIds) {
        this.fundIds = fundIds;
    }

    public List<String> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<String> serviceIds) {
        this.serviceIds = serviceIds;
    }
}
