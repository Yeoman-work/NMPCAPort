package net.yeoman.nmpcaport.io.response.HealthCenter;

import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.contact.ContactEssentials;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.site.SiteEssentialsResponse;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.Date;
import java.util.List;

public class HealthCenterResponseModel {

    private String healthCenterId;
    private String name;
    private String nameAbbr;
    private List<UserDetailsResponseModel> userDetailsResponseList;
    private List<ContactEssentials> contactEssentials;
    private List<SiteEssentialsResponse> siteEssentialsResponses;
    private List<HouseDistrictEssentialResponse> houseDistrictEssentialResponses;
    private List<SenateDistrictEssentialResponse> senateDistrictEssentialResponses;
    private List<CongressionalDistrictEssentialsResponse> congressionalDistrictEssentialsResponses;
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

    public List<UserDetailsResponseModel> getUserDetailsResponseList() {
        return userDetailsResponseList;
    }

    public void setUserDetailsResponseList(List<UserDetailsResponseModel> userDetailsResponseList) {
        this.userDetailsResponseList = userDetailsResponseList;
    }

    public List<ContactEssentials> getContactEssentials() {
        return contactEssentials;
    }

    public void setContactEssentials(List<ContactEssentials> contactEssentials) {
        this.contactEssentials = contactEssentials;
    }

    public List<SiteEssentialsResponse> getSiteEssentialsResponses() {
        return siteEssentialsResponses;
    }

    public void setSiteEssentialsResponses(List<SiteEssentialsResponse> siteEssentialsResponses) {
        this.siteEssentialsResponses = siteEssentialsResponses;
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

    public List<CongressionalDistrictEssentialsResponse> getCongressionalDistrictEssentialsResponses() {
        return congressionalDistrictEssentialsResponses;
    }

    public void setCongressionalDistrictEssentialsResponses(List<CongressionalDistrictEssentialsResponse> congressionalDistrictEssentialsResponses) {
        this.congressionalDistrictEssentialsResponses = congressionalDistrictEssentialsResponses;
    }
}
