package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsResponse;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.List;

public class HealthCenterDto {

    private Long id;
    private String healthCenterId;
    private String name;
    private String nameAbbr;
    private String createdAt;
    private String updatedAt;
    List<String> userIds;
    List<String> contactIds;
    List<String> siteIds;
    List<ContactResponseModel> contactResponseList;
    List<ContactNestedResponseModel> contactNestedResponseList;
    List<UserDetailsResponseModel> userDetailsResponseList;
    List<SiteDetailsResponse> siteResponse;
    List<SiteEntity> sites;
    List<UserEntity> users;
    List<ContactEntity> contacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<String> contactIds) {
        this.contactIds = contactIds;
    }

    public List<String> getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(List<String> siteIds) {
        this.siteIds = siteIds;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactEntity> contacts) {
        this.contacts = contacts;
    }

    public List<ContactResponseModel> getContactResponseList() {
        return contactResponseList;
    }

    public void setContactResponseList(List<ContactResponseModel> contactResponseList) {
        this.contactResponseList = contactResponseList;
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

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }

    public List<SiteDetailsResponse> getSiteResponse() {
        return siteResponse;
    }

    public void setSiteResponse(List<SiteDetailsResponse> siteResponse) {
        this.siteResponse = siteResponse;
    }
}
