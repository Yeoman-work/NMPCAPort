package net.yeoman.nmpcaport.io.request.site;

import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

public class SiteDetailsRequestModel {

    private String name;
    private String streetAddress;
    private String cityId;
    private String countyId;
    private String zipCodeId;
    private String healthCenterId;
    private String nmHouseDistrictId;
    private String senateDistrictId;
    private String congressionalDistrictId;

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
}
