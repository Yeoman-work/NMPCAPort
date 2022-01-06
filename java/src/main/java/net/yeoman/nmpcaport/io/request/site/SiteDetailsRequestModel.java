package net.yeoman.nmpcaport.io.request.site;

import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

import java.util.List;

public class SiteDetailsRequestModel {

    private String name;
    private String streetAddress;
    private String city;
    private String county;
    private String zipCode;
    private String healthCenter;
    private String nmHouseDistrict;
    private String senateDistrict;
    private String congressionalDistrict;
    private List<String> funding;
    private List<String> service;

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

    public List<String> getFunding() {
        return funding;
    }

    public void setFunding(List<String> funding) {
        this.funding = funding;
    }

    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        this.service = service;
    }
}
