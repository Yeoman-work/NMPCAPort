package net.yeoman.nmpcaport.io.response.USSenator;

import net.yeoman.nmpcaport.io.response.LocationResponse.LocationEssentialsResponse;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyEssentials;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.staff.StaffEssentials;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class USSenatorResponse {

    private String senatorId;
    private String firstName;
    private String lastName;
    private String email;
    private String website;
    private String picture;
    private LocalDate elected;
    private LocalDate nextElection;
    private List<StaffEssentials> staffEssentials;
    private PoliticalPartyEssentials politicalPartyEssentials;
    private List<LocationEssentialsResponse> locationEssentialsResponses;
    private Date createdAt;
    private Date updatedAt;

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public List<LocationEssentialsResponse> getLocationEssentialsResponses() {
        return locationEssentialsResponses;
    }

    public void setLocationEssentialsResponses(List<LocationEssentialsResponse> locationEssentialsResponses) {
        this.locationEssentialsResponses = locationEssentialsResponses;
    }

    public List<StaffEssentials> getStaffEssentials() {
        return staffEssentials;
    }

    public void setStaffEssentials(List<StaffEssentials> staffEssentials) {
        this.staffEssentials = staffEssentials;
    }

    public PoliticalPartyEssentials getPoliticalPartyEssentials() {
        return politicalPartyEssentials;
    }

    public void setPoliticalPartyEssentials(PoliticalPartyEssentials politicalPartyEssentials) {
        this.politicalPartyEssentials = politicalPartyEssentials;
    }
}
