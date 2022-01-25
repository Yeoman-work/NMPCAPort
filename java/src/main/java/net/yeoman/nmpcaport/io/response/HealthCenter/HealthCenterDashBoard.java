package net.yeoman.nmpcaport.io.response.HealthCenter;

import net.yeoman.nmpcaport.io.response.LocationResponse.LocationEssentialsResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.fund.FundEssentialsResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceEssentialsResponse;
import net.yeoman.nmpcaport.io.response.site.SiteEssentialsResponse;

import java.util.Date;
import java.util.List;

public class HealthCenterDashBoard {


    private String healthCenterId;
    private String name;
    private String nameAbbr;
    private List<SiteEssentialsResponse> siteEssentialsResponses;
    private List<ServiceEssentialsResponse> serviceEssentialsResponses;
    private List<FundEssentialsResponse> fundEssentialsResponses;
    private List<HouseDistrictEssentialResponse> houseDistrictEssentialResponses;
    private List<SenateDistrictEssentialResponse> senateDistrictEssentialResponses;
    private List<CongressionalDistrictEssentialsResponse> congressionalDistrictEssentialResponses;
    private Date createdAt;
    private Date updatedAt;

    public String getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(String healthCenterId) {
        this.healthCenterId = healthCenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }

    public List<ServiceEssentialsResponse> getServiceEssentialsResponses() {
        return serviceEssentialsResponses;
    }

    public void setServiceEssentialsResponses(List<ServiceEssentialsResponse> serviceEssentialsResponses) {
        this.serviceEssentialsResponses = serviceEssentialsResponses;
    }

    public List<FundEssentialsResponse> getFundEssentialsResponses() {
        return fundEssentialsResponses;
    }

    public void setFundEssentialsResponses(List<FundEssentialsResponse> fundEssentialsResponses) {
        this.fundEssentialsResponses = fundEssentialsResponses;
    }


    public List<HouseDistrictEssentialResponse> getHouseDistrictEssentialResponses() {
        return houseDistrictEssentialResponses;
    }

    public void setHouseDistrictEssentialResponses(List<HouseDistrictEssentialResponse> houseDistrictEssentialResponses) {
        this.houseDistrictEssentialResponses = houseDistrictEssentialResponses;
    }

    public List<SenateDistrictEssentialResponse> getSenateDistrictEssentialResponses() {
        return senateDistrictEssentialResponses;
    }

    public void setSenateDistrictEssentialResponses(List<SenateDistrictEssentialResponse> senateDistrictEssentialResponses) {
        this.senateDistrictEssentialResponses = senateDistrictEssentialResponses;
    }

    public List<CongressionalDistrictEssentialsResponse> getCongressionalDistrictEssentialResponses() {
        return congressionalDistrictEssentialResponses;
    }

    public void setCongressionalDistrictEssentialResponses(List<CongressionalDistrictEssentialsResponse> congressionalDistrictEssentialResponses) {
        this.congressionalDistrictEssentialResponses = congressionalDistrictEssentialResponses;
    }

    public List<SiteEssentialsResponse> getSiteEssentialsResponses() {
        return siteEssentialsResponses;
    }

    public void setSiteEssentialsResponses(List<SiteEssentialsResponse> siteEssentialsResponses) {
        this.siteEssentialsResponses = siteEssentialsResponses;
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
}
