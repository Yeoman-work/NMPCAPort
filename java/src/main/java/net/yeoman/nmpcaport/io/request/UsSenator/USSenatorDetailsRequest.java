package net.yeoman.nmpcaport.io.request.UsSenator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class USSenatorDetailsRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String website;
    private String elected;
    private String nextElection;

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

    public String getElected() {
        return elected;
    }

    public void setElected(String elected) {
        this.elected = elected;
    }

    public String getNextElection() {
        return nextElection;
    }

    public void setNextElection(String nextElection) {
        this.nextElection = nextElection;
    }
}
