package net.yeoman.nmpcaport.io.response.HealthCenter;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.io.response.CongressionalRepResponse.CongressionalRepResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsResponse;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.Date;
import java.util.List;

public class HealthCenterResponseModel {

    private String healthCenterId;
    private String name;
    private String nameAbbr;
    private List<UserDetailsResponseModel> userDetailsResponseList;
    private List<ContactNestedResponseModel> contactNestedResponseList;
    private List<SiteDetailsNestedResponse> siteDetailsNestedResponse;
    private List<NMHouseDistrictNestedResponse> nmHouseDistrictNestedResponseList;
    private List<SenateDistrictResponseModel> senateDistrictResponseList;
    private List<CongressionalDistrictResponse> congressionalDistrictResponseList;
    private String createdAt;
    private String updatedAt;

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

    public List<UserDetailsResponseModel> getUserDetailsResponseList() {
        return userDetailsResponseList;
    }

    public void setUserDetailsResponseList(List<UserDetailsResponseModel> userDetailsResponseList) {
        this.userDetailsResponseList = userDetailsResponseList;
    }

    public List<ContactNestedResponseModel> getContactNestedResponseList() {
        return contactNestedResponseList;
    }

    public void setContactNestedResponseList(List<ContactNestedResponseModel> contactNestedResponseList) {
        this.contactNestedResponseList = contactNestedResponseList;
    }

    public List<SiteDetailsNestedResponse> getSiteDetailsNestedResponse() {
        return siteDetailsNestedResponse;
    }

    public void setSiteDetailsNestedResponse(List<SiteDetailsNestedResponse> siteDetailsNestedResponse) {
        this.siteDetailsNestedResponse = siteDetailsNestedResponse;
    }

    public List<NMHouseDistrictNestedResponse> getNmHouseDistrictNestedResponseList() {
        return nmHouseDistrictNestedResponseList;
    }

    public void setNmHouseDistrictNestedResponseList(List<NMHouseDistrictNestedResponse> nmHouseDistrictNestedResponseList) {
        this.nmHouseDistrictNestedResponseList = nmHouseDistrictNestedResponseList;
    }

    public List<SenateDistrictResponseModel> getSenateDistrictResponseList() {
        return senateDistrictResponseList;
    }

    public void setSenateDistrictResponseList(List<SenateDistrictResponseModel> senateDistrictResponseList) {
        this.senateDistrictResponseList = senateDistrictResponseList;
    }

    public List<CongressionalDistrictResponse> getCongressionalDistrictResponseList() {
        return congressionalDistrictResponseList;
    }

    public void setCongressionalDistrictResponseList(List<CongressionalDistrictResponse> congressionalDistrictResponseList) {
        this.congressionalDistrictResponseList = congressionalDistrictResponseList;
    }
}
