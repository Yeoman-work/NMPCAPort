package net.yeoman.nmpcaport.io.response.stateSenator;

import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyEssentials;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;

import java.util.Date;

public class StateSenatorEssentials {

    private String stateSenatorId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private PoliticalPartyEssentials politicalPartyEssentials;
    private SenateDistrictEssentialResponse senateDistrictEssentialResponse;

    public String getStateSenatorId() {
        return stateSenatorId;
    }

    public void setStateSenatorId(String stateSenatorId) {
        this.stateSenatorId = stateSenatorId;
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

    public PoliticalPartyEssentials getPoliticalPartyEssentials() {
        return politicalPartyEssentials;
    }

    public void setPoliticalPartyEssentials(PoliticalPartyEssentials politicalPartyEssentials) {
        this.politicalPartyEssentials = politicalPartyEssentials;
    }

    public SenateDistrictEssentialResponse getSenateDistrictEssentialResponse() {
        return senateDistrictEssentialResponse;
    }

    public void setSenateDistrictEssentialResponse(SenateDistrictEssentialResponse senateDistrictEssentialResponse) {
        this.senateDistrictEssentialResponse = senateDistrictEssentialResponse;
    }
}
