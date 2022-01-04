package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;


import java.util.Date;
import java.util.List;

public class CongressionalRepDto {

    private Long id;
    private String congressionalRepId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String politicalParty;
    private String congressionalDistrict;
    private CongressionalDistrictEntity districtEntity;
    private CongressionalDistrictResponse districtResponse;
    private PoliticalPartyEntity politicalPartyEntity;
    private PoliticalPartyResponse politicalPartyResponse;
    private List<AssignedNumberEntity> assignedNumberEntity;
    private List<OfficeAssignmentEntity> officeAssignmentEntities;
    private List<LocationEntity> locationEntities;
    private List<LocationResponse> locationResponses;
    private List<PhoneNumberResponse> phoneNumberResponse;
    private List<StaffEntity> staffEntities;
    private List<StaffResponse> staffResponses;
    private PhoneNumberEntity phoneNumberEntity;
    private String website;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCongressionalRepId() {
        return congressionalRepId;
    }

    public void setCongressionalRepId(String congressionalRepId) {
        this.congressionalRepId = congressionalRepId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getCongressionalDistrict() {
        return congressionalDistrict;
    }

    public void setCongressionalDistrict(String congressionalDistrict) {
        this.congressionalDistrict = congressionalDistrict;
    }

    public CongressionalDistrictEntity getDistrictEntity() {
        return districtEntity;
    }

    public void setDistrictEntity(CongressionalDistrictEntity districtEntity) {
        this.districtEntity = districtEntity;
    }

    public CongressionalDistrictResponse getDistrictResponse() {
        return districtResponse;
    }

    public void setDistrictResponse(CongressionalDistrictResponse districtResponse) {
        this.districtResponse = districtResponse;
    }

    public String getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    public PoliticalPartyResponse getPoliticalPartyResponse() {
        return politicalPartyResponse;
    }

    public void setPoliticalPartyResponse(PoliticalPartyResponse politicalPartyResponse) {
        this.politicalPartyResponse = politicalPartyResponse;
    }

    public PoliticalPartyEntity getPoliticalPartyEntity() {
        return politicalPartyEntity;
    }

    public void setPoliticalPartyEntity(PoliticalPartyEntity politicalPartyEntity) {
        this.politicalPartyEntity = politicalPartyEntity;
    }

    public List<AssignedNumberEntity> getAssignedNumberEntity() {
        return assignedNumberEntity;
    }

    public void setAssignedNumberEntity(List<AssignedNumberEntity> assignedNumberEntity) {
        this.assignedNumberEntity = assignedNumberEntity;
    }

    public List<PhoneNumberResponse> getPhoneNumberResponse() {
        return phoneNumberResponse;
    }

    public void setPhoneNumberResponse(List<PhoneNumberResponse> phoneNumberResponse) {
        this.phoneNumberResponse = phoneNumberResponse;
    }

    public PhoneNumberEntity getPhoneNumberEntity() {
        return phoneNumberEntity;
    }

    public void setPhoneNumberEntity(PhoneNumberEntity phoneNumberEntity) {
        this.phoneNumberEntity = phoneNumberEntity;
    }

    public List<LocationEntity> getLocationEntities() {
        return locationEntities;
    }

    public void setLocationEntities(List<LocationEntity> locationEntities) {
        this.locationEntities = locationEntities;
    }

    public List<LocationResponse> getLocationResponses() {
        return locationResponses;
    }

    public void setLocationResponses(List<LocationResponse> locationResponses) {
        this.locationResponses = locationResponses;
    }

    public List<OfficeAssignmentEntity> getOfficeAssignmentEntities() {
        return officeAssignmentEntities;
    }

    public void setOfficeAssignmentEntities(List<OfficeAssignmentEntity> officeAssignmentEntities) {
        this.officeAssignmentEntities = officeAssignmentEntities;
    }

    public List<StaffEntity> getStaffEntities() {
        return staffEntities;
    }

    public void setStaffEntities(List<StaffEntity> staffEntities) {
        this.staffEntities = staffEntities;
    }

    public List<StaffResponse> getStaffResponses() {
        return staffResponses;
    }

    public void setStaffResponses(List<StaffResponse> staffResponses) {
        this.staffResponses = staffResponses;
    }
}
