package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.CongressionalDistrictEntity;
import net.yeoman.nmpcaport.entities.CongressionalRepEntity;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class CongressionalRepDto {

    private Long id;
    private String congressionalRepId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private CongressionalDistrictEntity districtEntity;
    private CongressionalDistrictResponse district;
    private String congressionalDistrictIdentifier;
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

    public String getCongressionalDistrictIdentifier() {
        return congressionalDistrictIdentifier;
    }

    public void setCongressionalDistrictIdentifier(String congressionalDistrictIdentifier) {
        this.congressionalDistrictIdentifier = congressionalDistrictIdentifier;
    }

    public CongressionalDistrictEntity getDistrictEntity() {
        return districtEntity;
    }

    public void setDistrictEntity(CongressionalDistrictEntity districtEntity) {
        this.districtEntity = districtEntity;
    }

    public CongressionalDistrictResponse getDistrict() {
        return district;
    }

    public void setDistrict(CongressionalDistrictResponse district) {
        this.district = district;
    }


}
