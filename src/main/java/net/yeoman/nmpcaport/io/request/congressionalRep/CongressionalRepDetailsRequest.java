package net.yeoman.nmpcaport.io.request.congressionalRep;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class CongressionalRepDetailsRequest {


    private String firstName;
    private String lastName;
    private String email;
    private String congressionalDistrictIdentifier;
    private String picture;
    private String website;
    


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

    public String getCongressionalDistrictIdentifier() {

        return congressionalDistrictIdentifier;
    }

    public void setCongressionalDistrictIdentifier(String congressionalDistrictIdentifier) {
        this.congressionalDistrictIdentifier = congressionalDistrictIdentifier;
    }
}
