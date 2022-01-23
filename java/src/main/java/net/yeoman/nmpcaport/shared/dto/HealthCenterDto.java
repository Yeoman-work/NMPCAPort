package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationEssentialsResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.io.response.fund.FundEssentialsResponse;
import net.yeoman.nmpcaport.io.response.fund.FundNestedResponse;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.service.ServiceEssentialsResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceNestedResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsResponse;
import net.yeoman.nmpcaport.io.response.site.SiteEssentialsResponse;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.Date;
import java.util.List;

public class HealthCenterDto {

    //health Center info
    private Long id;
    private String healthCenterId;
    private String name;
    private String nameAbbr;
    private Date createdAt;
    private Date updatedAt;

    //health Center relationships
    //new site request
    private List<SiteDetailsRequestModel> sitesRequest;

    //user, contact and site ids
    private List<String> users;
    private List<String> contacts;
    private List<String> sites;


    //contacts
    private List<ContactResponseModel> contactResponseList;
    private List<ContactNestedResponseModel> contactNestedResponseList;

    private List<UserDetailsResponseModel> userDetailsResponseList;
    private List<SiteDetailsResponse> siteResponse;
    private List<SiteDetailsNestedResponse> siteDetailsNestedResponseList;

    private List<SiteEntity> siteEntities;
    private List<UserEntity> userEntities;
    private List<ContactEntity> contactEntities;
    private List<HouseDistrictNestedResponse> nmHouseDistrictNestedResponses;
    private List<CongressionalDistrictResponse> congressionalDistrictResponseList;
    private List<SenateDistrictResponseModel> senateDistrictResponseModelList;
    private List<SenateDistrictNestedResponse> senateDistrictNestedResponseList;
    private List<HouseDistrictEntity> nmHouseDistrictsEntities;
    private List<CongressionalDistrictEntity> congressionalDistrictEntities;
    private List<CongressionalDistrictNestedResponse> congressionalDistrictNestedResponseList;
    private List<ServiceEntity> serviceEntities;
    private List<SenateDistrictEntity> senateDistrictEntities;
    private List<FundEntity> fundEntities;
    private List<FundNestedResponse> fundNestedResponses;
    private List<ServiceResponse> serviceResponseList;
    private List<FundResponseModel> fundResponseList;
    private List<ServiceResponse> serviceResponses;
    private List<ServiceNestedResponse> serviceNestedResponses;
    private List<FundResponseModel> fundResponseModels;

    //healthCenter dashboard
    private List<SiteEssentialsResponse> siteEssentialsResponses;
    private List<ServiceEssentialsResponse> serviceEssentialsResponses;
    private List<FundEssentialsResponse> fundEssentialsResponses;
    private List<LocationEssentialsResponse> locationEssentialsResponseList;
    private List<HouseDistrictEssentialResponse> nmHouseDistrictEssentialResponses;
    private List<SenateDistrictEssentialResponse> senateDistrictEssentialResponses;
    private List<CongressionalDistrictEssentialsResponse> congressionalEssentialsResponses;



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

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public void setSites(List<String> sites) {
        this.sites = sites;
    }

    public List<SiteEntity> getSiteEntities() {
        return siteEntities;
    }

    public void setSiteEntities(List<SiteEntity> siteEntities) {
        this.siteEntities = siteEntities;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public List<ContactEntity> getContactEntities() {
        return contactEntities;
    }

    public void setContactEntities(List<ContactEntity> contactEntities) {
        this.contactEntities = contactEntities;
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

    public List<String> getSites() {
        return sites;
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

    public List<HouseDistrictEntity> getNmHouseDistrictsEntities() {
        return nmHouseDistrictsEntities;
    }

    public void setNmHouseDistrictsEntities(List<HouseDistrictEntity> nmHouseDistrictsEntities) {
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

    public List<HouseDistrictNestedResponse> getNmHouseDistrictNestedResponses() {
        return nmHouseDistrictNestedResponses;
    }

    public void setNmHouseDistrictNestedResponses(List<HouseDistrictNestedResponse> nmHouseDistrictNestedResponses) {
        this.nmHouseDistrictNestedResponses = nmHouseDistrictNestedResponses;
    }

    public List<SenateDistrictResponseModel> getSenateDistrictResponseModelList() {
        return senateDistrictResponseModelList;
    }

    public void setSenateDistrictResponseModelList(List<SenateDistrictResponseModel> senateDistrictResponseModelList) {
        this.senateDistrictResponseModelList = senateDistrictResponseModelList;
    }

    public List<ServiceResponse> getServiceResponseList() {
        return serviceResponseList;
    }

    public void setServiceResponseList(List<ServiceResponse> serviceResponseList) {
        this.serviceResponseList = serviceResponseList;
    }

    public List<FundResponseModel> getFundResponseList() {
        return fundResponseList;
    }

    public void setFundResponseList(List<FundResponseModel> fundResponseList) {
        this.fundResponseList = fundResponseList;
    }

    public List<SiteDetailsRequestModel> getSitesRequest() {
        return sitesRequest;
    }

    public void setSitesRequest(List<SiteDetailsRequestModel> sitesRequest) {
        this.sitesRequest = sitesRequest;
    }

    public List<ServiceResponse> getServiceResponses() {
        return serviceResponses;
    }

    public void setServiceResponses(List<ServiceResponse> serviceResponses) {
        this.serviceResponses = serviceResponses;
    }

    public List<FundResponseModel> getFundResponseModels() {
        return fundResponseModels;
    }

    public void setFundResponseModels(List<FundResponseModel> fundResponseModels) {
        this.fundResponseModels = fundResponseModels;
    }

    public List<ServiceNestedResponse> getServiceNestedResponses() {
        return serviceNestedResponses;
    }

    public void setServiceNestedResponses(List<ServiceNestedResponse> serviceNestedResponses) {
        this.serviceNestedResponses = serviceNestedResponses;
    }

    public List<FundNestedResponse> getFundNestedResponses() {
        return fundNestedResponses;
    }

    public void setFundNestedResponses(List<FundNestedResponse> fundNestedResponses) {
        this.fundNestedResponses = fundNestedResponses;
    }

    public List<SenateDistrictNestedResponse> getSenateDistrictNestedResponseList() {
        return senateDistrictNestedResponseList;
    }

    public void setSenateDistrictNestedResponseList(List<SenateDistrictNestedResponse> senateDistrictNestedResponseList) {
        this.senateDistrictNestedResponseList = senateDistrictNestedResponseList;
    }

    public List<CongressionalDistrictNestedResponse> getCongressionalDistrictNestedResponseList() {
        return congressionalDistrictNestedResponseList;
    }

    public void setCongressionalDistrictNestedResponseList(List<CongressionalDistrictNestedResponse> congressionalDistrictNestedResponseList) {
        this.congressionalDistrictNestedResponseList = congressionalDistrictNestedResponseList;
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

    public List<LocationEssentialsResponse> getLocationEssentialsResponseList() {
        return locationEssentialsResponseList;
    }

    public void setLocationEssentialsResponseList(List<LocationEssentialsResponse> locationEssentialsResponseList) {
        this.locationEssentialsResponseList = locationEssentialsResponseList;
    }

    public List<HouseDistrictEssentialResponse> getNmHouseDistrictEssentialResponses() {
        return nmHouseDistrictEssentialResponses;
    }

    public void setNmHouseDistrictEssentialResponses(List<HouseDistrictEssentialResponse> nmHouseDistrictEssentialResponses) {
        this.nmHouseDistrictEssentialResponses = nmHouseDistrictEssentialResponses;
    }

    public List<SenateDistrictEssentialResponse> getSenateDistrictEssentialResponses() {
        return senateDistrictEssentialResponses;
    }

    public void setSenateDistrictEssentialResponses(List<SenateDistrictEssentialResponse> senateDistrictEssentialResponses) {
        this.senateDistrictEssentialResponses = senateDistrictEssentialResponses;
    }

    public List<CongressionalDistrictEssentialsResponse> getCongressionalEssentialsResponses() {
        return congressionalEssentialsResponses;
    }

    public void setCongressionalEssentialsResponses(List<CongressionalDistrictEssentialsResponse> congressionalEssentialsResponses) {
        this.congressionalEssentialsResponses = congressionalEssentialsResponses;
    }

    public List<SiteEssentialsResponse> getSiteEssentialsResponses() {
        return siteEssentialsResponses;
    }

    public void setSiteEssentialsResponses(List<SiteEssentialsResponse> siteEssentialsResponses) {
        this.siteEssentialsResponses = siteEssentialsResponses;
    }
}
