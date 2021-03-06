package net.yeoman.nmpcaport.shared.dto;


import net.yeoman.nmpcaport.entities.LocationEntity;
import net.yeoman.nmpcaport.entities.OfficeAssignmentEntity;
import net.yeoman.nmpcaport.entities.PoliticalPartyEntity;
import net.yeoman.nmpcaport.entities.StaffEntity;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class USSenatorDto {

    private Long id;
    private String senatorId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String website;
    private String politicalParty;
    private List<StaffEntity> staffEntities;
    private List<StaffResponse> staffResponses;
    private PoliticalPartyEntity politicalPartyEntity;
    private LocationEntity locationEntity;
    private List<OfficeAssignmentEntity> officeAssignmentEntities;
    private List<LocationResponse> locationResponses;
    private PoliticalPartyResponse politicalPartyResponse;
    private LocationResponse locationResponse;
    private LocalDate elected;
    private LocalDate nextElection;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenatorId() {
        return senatorId;
    }

    public void setSenatorId(String senatorId) {
        this.senatorId = senatorId;
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

    public LocalDate getElected() {
        return elected;
    }

    public void setElected(LocalDate elected) {
        this.elected = elected;
    }

    public LocalDate getNextElection() {
        return nextElection;
    }

    public void setNextElection(LocalDate nextElection) {
        this.nextElection = nextElection;
    }

    public String getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    public PoliticalPartyEntity getPoliticalPartyEntity() {
        return politicalPartyEntity;
    }

    public void setPoliticalPartyEntity(PoliticalPartyEntity politicalPartyEntity) {
        this.politicalPartyEntity = politicalPartyEntity;
    }

    public PoliticalPartyResponse getPoliticalPartyResponse() {
        return politicalPartyResponse;
    }

    public void setPoliticalPartyResponse(PoliticalPartyResponse politicalPartyResponse) {
        this.politicalPartyResponse = politicalPartyResponse;
    }

    public LocationEntity getLocationEntity() {
        return locationEntity;
    }

    public void setLocationEntity(LocationEntity locationEntity) {
        this.locationEntity = locationEntity;
    }

    public LocationResponse getLocationResponse() {
        return locationResponse;
    }

    public void setLocationResponse(LocationResponse locationResponse) {
        this.locationResponse = locationResponse;
    }

    public List<OfficeAssignmentEntity> getOfficeAssignmentEntities() {
        return officeAssignmentEntities;
    }

    public void setOfficeAssignmentEntities(List<OfficeAssignmentEntity> officeAssignmentEntities) {
        this.officeAssignmentEntities = officeAssignmentEntities;
    }

    public List<LocationResponse> getLocationResponses() {
        return locationResponses;
    }

    public void setLocationResponses(List<LocationResponse> locationResponses) {
        this.locationResponses = locationResponses;
    }

    public List<StaffResponse> getStaffResponses() {
        return staffResponses;
    }

    public void setStaffResponses(List<StaffResponse> staffResponses) {
        this.staffResponses = staffResponses;
    }

    public List<StaffEntity> getStaffEntities() {
        return staffEntities;
    }

    public void setStaffEntities(List<StaffEntity> staffEntities) {
        this.staffEntities = staffEntities;
    }
}
