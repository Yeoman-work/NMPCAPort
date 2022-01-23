package net.yeoman.nmpcaport.io.response.site;

import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

public class SiteDetailsResponse {

    private String siteId;
    private String name;
    private String createdAt;
    private String updatedAt;
    private CountyResponse countyResponse;
    private CityResponse cityResponse;
    private ZipCodeResponse zipCodeResponse;
    private HealthCenterNestedResponseModel healthCenterResponse;
    private HouseDistrictResponse houseDistrictResponse;
    private SenateDistrictResponseModel senateDistrictResponseModel;
    private CongressionalDistrictResponse congressionalDistrictResponse;

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

    public CountyResponse getCountyResponse() {
        return countyResponse;
    }

    public void setCountyResponse(CountyResponse countyResponse) {
        this.countyResponse = countyResponse;
    }

    public CityResponse getCityResponse() {
        return cityResponse;
    }

    public void setCityResponse(CityResponse cityResponse) {
        this.cityResponse = cityResponse;
    }

    public ZipCodeResponse getZipCodeResponse() {
        return zipCodeResponse;
    }

    public void setZipCodeResponse(ZipCodeResponse zipCodeResponse) {
        this.zipCodeResponse = zipCodeResponse;
    }

    public HealthCenterNestedResponseModel getHealthCenterResponse() {
        return healthCenterResponse;
    }

    public void setHealthCenterResponse(HealthCenterNestedResponseModel healthCenterResponse) {
        this.healthCenterResponse = healthCenterResponse;
    }

    public HouseDistrictResponse getHouseDistrictResponse() {
        return houseDistrictResponse;
    }

    public void setHouseDistrictResponse(HouseDistrictResponse houseDistrictResponse) {
        this.houseDistrictResponse = houseDistrictResponse;
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
