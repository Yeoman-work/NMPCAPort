package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
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
    private List<String> userIds;
    private List<String> contactIds;
    private List<String> siteIds;
    private List<ContactResponseModel> contactResponseList;
    private List<ContactNestedResponseModel> contactNestedResponseList;
    private List<UserDetailsResponseModel> userDetailsResponseList;
    private List<SiteDetailsResponse> siteResponse;
    private List<SiteDetailsNestedResponse> siteDetailsNestedResponseList;
    private List<SiteEntity> sites;
    private List<UserEntity> users;
    private List<ContactEntity> contacts;
    private List<NMHouseDistrictNestedResponse> nmHouseDistrictNestedResponses;
    private List<CongressionalDistrictResponse> congressionalDistrictResponseList;
    private List<SenateDistrictResponseModel> senateDistrictResponseModelList;
    private List<NMHouseDistrictEntity> nmHouseDistrictsEntities;
    private List<CongressionalDistrictEntity> congressionalDistrictEntities;
    private List<ServiceEntity> serviceEntities;
    private List<SenateDistrictEntity> senateDistrictEntities;
    private List<FundEntity> fundEntities;


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

    public List<SiteDetailsNestedResponse> getSiteDetailsNestedResponseList() {
        return siteDetailsNestedResponseList;
    }

    public void setSiteDetailsNestedResponseList(List<SiteDetailsNestedResponse> siteDetailsNestedResponseList) {
        this.siteDetailsNestedResponseList = siteDetailsNestedResponseList;
    }

    public List<NMHouseDistrictEntity> getNmHouseDistrictsEntities() {
        return nmHouseDistrictsEntities;
    }

    public void setNmHouseDistrictsEntities(List<NMHouseDistrictEntity> nmHouseDistrictsEntities) {
        this.nmHouseDistrictsEntities = nmHouseDistrictsEntities;
    }

    public List<CongressionalDistrictEntity> getCongressionalDistrictEntities() {
        return congressionalDistrictEntities;
    }

    public void setCongressionalDistrictEntities(List<CongressionalDistrictEntity> congressionalDistrictEntities) {
        this.congressionalDistrictEntities = congressionalDistrictEntities;
    }

    public List<ServiceEntity> getServiceEntities() {
        return serviceEntities;
    }

    public void setServiceEntities(List<ServiceEntity> serviceEntities) {
        this.serviceEntities = serviceEntities;
    }

    public List<SenateDistrictEntity> getSenateDistrictEntities() {
        return senateDistrictEntities;
    }

    public void setSenateDistrictEntities(List<SenateDistrictEntity> senateDistrictEntities) {
        this.senateDistrictEntities = senateDistrictEntities;
    }

    public List<FundEntity> getFundEntities() {
        return fundEntities;
    }

    public void setFundEntities(List<FundEntity> fundEntities) {
        this.fundEntities = fundEntities;
    }

    public List<CongressionalDistrictResponse> getCongressionalDistrictResponseList() {
        return congressionalDistrictResponseList;
    }

    public void setCongressionalDistrictResponseList(List<CongressionalDistrictResponse> congressionalDistrictResponseList) {
        this.congressionalDistrictResponseList = congressionalDistrictResponseList;
    }

    public List<NMHouseDistrictNestedResponse> getNmHouseDistrictNestedResponses() {
        return nmHouseDistrictNestedResponses;
    }

    public void setNmHouseDistrictNestedResponses(List<NMHouseDistrictNestedResponse> nmHouseDistrictNestedResponses) {
        this.nmHouseDistrictNestedResponses = nmHouseDistrictNestedResponses;
    }

    public List<SenateDistrictResponseModel> getSenateDistrictResponseModelList() {
        return senateDistrictResponseModelList;
    }

    public void setSenateDistrictResponseModelList(List<SenateDistrictResponseModel> senateDistrictResponseModelList) {
        this.senateDistrictResponseModelList = senateDistrictResponseModelList;
    }

}
