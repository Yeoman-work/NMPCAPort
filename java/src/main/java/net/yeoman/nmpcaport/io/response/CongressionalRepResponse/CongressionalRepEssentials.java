package net.yeoman.nmpcaport.io.response.CongressionalRepResponse;

import net.yeoman.nmpcaport.io.response.LocationResponse.LocationEssentialsResponse;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberEssentials;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyEssentials;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.staff.StaffEssentials;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;

import java.util.List;

public class CongressionalRepEssentials {

    private String congressionalRepId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String website;
    private CongressionalDistrictEssentialsResponse congressionalDistrictEssentialsResponse;
    private List<PhoneNumberEssentials> phoneNumberEssentials;
    private List<StaffEssentials> staffEssentials;
    private PoliticalPartyEssentials politicalPartyEssentials;
    private List<LocationEssentialsResponse> locationEssentialsResponses;

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

    public CongressionalDistrictEssentialsResponse getCongressionalDistrictEssentialsResponse() {
        return congressionalDistrictEssentialsResponse;
    }

    public void setCongressionalDistrictEssentialsResponse(CongressionalDistrictEssentialsResponse congressionalDistrictEssentialsResponse) {
        this.congressionalDistrictEssentialsResponse = congressionalDistrictEssentialsResponse;
    }

    public List<PhoneNumberEssentials> getPhoneNumberEssentials() {
        return phoneNumberEssentials;
    }

    public void setPhoneNumberEssentials(List<PhoneNumberEssentials> phoneNumberEssentials) {
        this.phoneNumberEssentials = phoneNumberEssentials;
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

    public List<LocationEssentialsResponse> getLocationEssentialsResponses() {
        return locationEssentialsResponses;
    }

    public void setLocationEssentialsResponses(List<LocationEssentialsResponse> locationEssentialsResponses) {
        this.locationEssentialsResponses = locationEssentialsResponses;
    }
}
