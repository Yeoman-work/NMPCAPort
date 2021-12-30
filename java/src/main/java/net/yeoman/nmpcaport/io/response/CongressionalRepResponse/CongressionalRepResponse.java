package net.yeoman.nmpcaport.io.response.CongressionalRepResponse;

import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;

import java.util.Date;

public class CongressionalRepResponse {

    private String congressionalRepId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String website;
    private CongressionalDistrictResponse districtResponse;
    private PhoneNumberResponse phoneNumberResponse;
    private PoliticalPartyResponse politicalPartyResponse;
    private Date createdAt;
    private Date updatedAt;

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

    public CongressionalDistrictResponse getDistrictResponse() {
        return districtResponse;
    }

    public void setDistrictResponse(CongressionalDistrictResponse districtResponse) {
        this.districtResponse = districtResponse;
    }

    public PhoneNumberResponse getPhoneNumberResponse() {
        return phoneNumberResponse;
    }

    public void setPhoneNumberResponse(PhoneNumberResponse phoneNumberResponse) {
        this.phoneNumberResponse = phoneNumberResponse;
    }

    public PoliticalPartyResponse getPoliticalPartyResponse() {
        return politicalPartyResponse;
    }

    public void setPoliticalPartyResponse(PoliticalPartyResponse politicalPartyResponse) {
        this.politicalPartyResponse = politicalPartyResponse;
    }
}
