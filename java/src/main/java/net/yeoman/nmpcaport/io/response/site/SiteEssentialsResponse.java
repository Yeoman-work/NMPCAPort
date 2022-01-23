package net.yeoman.nmpcaport.io.response.site;

import net.yeoman.nmpcaport.io.response.County.CountyEssentials;
import net.yeoman.nmpcaport.io.response.city.CityEssentials;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.fund.FundEssentialsResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceEssentialsResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeEssentials;

import java.util.List;

public class SiteEssentialsResponse {


    private String siteId;
    private String name;
    private String streetAddress;
    private CountyEssentials countyEssentials;
    private CityEssentials cityEssentials;
    private ZipCodeEssentials zipCodeEssentials;
    private List<FundEssentialsResponse> fundEssentialsResponses;
    private List<ServiceEssentialsResponse> serviceEssentialsResponses;
    private HouseDistrictEssentialResponse nmHouseDistrictEssentialResponse;
    private SenateDistrictEssentialResponse senateDistrictEssentialResponse;
    private CongressionalDistrictEssentialsResponse congressionalDistrictEssentialsResponse;
    private String createdAt;
    private String updatedAt;

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

    public CountyEssentials getCountyEssentials() {
        return countyEssentials;
    }

    public void setCountyEssentials(CountyEssentials countyEssentials) {
        this.countyEssentials = countyEssentials;
    }

    public CityEssentials getCityEssentials() {
        return cityEssentials;
    }

    public void setCityEssentials(CityEssentials cityEssentials) {
        this.cityEssentials = cityEssentials;
    }

    public ZipCodeEssentials getZipCodeEssentials() {
        return zipCodeEssentials;
    }

    public void setZipCodeEssentials(ZipCodeEssentials zipCodeEssentials) {
        this.zipCodeEssentials = zipCodeEssentials;
    }

    public List<FundEssentialsResponse> getFundEssentialsResponses() {
        return fundEssentialsResponses;
    }

    public void setFundEssentialsResponses(List<FundEssentialsResponse> fundEssentialsResponses) {
        this.fundEssentialsResponses = fundEssentialsResponses;
    }

    public List<ServiceEssentialsResponse> getServiceEssentialsResponses() {
        return serviceEssentialsResponses;
    }

    public void setServiceEssentialsResponses(List<ServiceEssentialsResponse> serviceEssentialsResponses) {
        this.serviceEssentialsResponses = serviceEssentialsResponses;
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

    public HouseDistrictEssentialResponse getNmHouseDistrictEssentialResponse() {
        return nmHouseDistrictEssentialResponse;
    }

    public void setNmHouseDistrictEssentialResponse(HouseDistrictEssentialResponse nmHouseDistrictEssentialResponse) {
        this.nmHouseDistrictEssentialResponse = nmHouseDistrictEssentialResponse;
    }

    public SenateDistrictEssentialResponse getSenateDistrictEssentialResponse() {
        return senateDistrictEssentialResponse;
    }

    public void setSenateDistrictEssentialResponse(SenateDistrictEssentialResponse senateDistrictEssentialResponse) {
        this.senateDistrictEssentialResponse = senateDistrictEssentialResponse;
    }

    public CongressionalDistrictEssentialsResponse getCongressionalDistrictEssentialsResponse() {
        return congressionalDistrictEssentialsResponse;
    }

    public void setCongressionalDistrictEssentialsResponse(CongressionalDistrictEssentialsResponse congressionalDistrictEssentialsResponse) {
        this.congressionalDistrictEssentialsResponse = congressionalDistrictEssentialsResponse;
    }
}
