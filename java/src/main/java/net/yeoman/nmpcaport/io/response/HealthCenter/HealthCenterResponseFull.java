package net.yeoman.nmpcaport.io.response.HealthCenter;

import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.Date;
import java.util.List;

public class HealthCenterResponseFull {

    private String healthCenterId;
    private String name;
    private String nameAbbr;
    private List<NMHouseDistrictNestedResponse> nmHouseDistrictNestedResponses;
    private List<CongressionalDistrictResponse> congressionalDistrictResponseList;
    private List<SenateDistrictResponseModel> senateDistrictResponseModelList;
    private List<SiteDetailsNestedResponse> siteDetailsNestedResponseList;
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

    public List<NMHouseDistrictNestedResponse> getNmHouseDistrictNestedResponses() {
        return nmHouseDistrictNestedResponses;
    }

    public void setNmHouseDistrictNestedResponses(List<NMHouseDistrictNestedResponse> nmHouseDistrictNestedResponses) {
        this.nmHouseDistrictNestedResponses = nmHouseDistrictNestedResponses;
    }

    public List<CongressionalDistrictResponse> getCongressionalDistrictResponseList() {
        return congressionalDistrictResponseList;
    }

    public void setCongressionalDistrictResponseList(List<CongressionalDistrictResponse> congressionalDistrictResponseList) {
        this.congressionalDistrictResponseList = congressionalDistrictResponseList;
    }

    public List<SenateDistrictResponseModel> getSenateDistrictResponseModelList() {
        return senateDistrictResponseModelList;
    }

    public void setSenateDistrictResponseModelList(List<SenateDistrictResponseModel> senateDistrictResponseModelList) {
        this.senateDistrictResponseModelList = senateDistrictResponseModelList;
    }

    public List<SiteDetailsNestedResponse> getSiteDetailsNestedResponseList() {
        return siteDetailsNestedResponseList;
    }

    public void setSiteDetailsNestedResponseList(List<SiteDetailsNestedResponse> siteDetailsNestedResponseList) {
        this.siteDetailsNestedResponseList = siteDetailsNestedResponseList;
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
