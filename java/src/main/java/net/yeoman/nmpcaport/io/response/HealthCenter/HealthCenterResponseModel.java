package net.yeoman.nmpcaport.io.response.HealthCenter;

import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
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
    private List<SiteDetailsResponse> siteResponse;
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

    public List<SiteDetailsResponse> getSiteResponse() {
        return siteResponse;
    }

    public void setSiteResponse(List<SiteDetailsResponse> siteResponse) {
        this.siteResponse = siteResponse;
    }
}
