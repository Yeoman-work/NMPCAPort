package net.yeoman.nmpcaport.shared.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

public class GovernorDto {

    private Long id;
    private String governorId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String website;
    private LocalDate firstTerm;
    private LocalDate secondTerm;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGovernorId() {
        return governorId;
    }

    public void setGovernorId(String governorId) {
        this.governorId = governorId;
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

    public LocalDate getFirstTerm() {
        return firstTerm;
    }

    public void setFirstTerm(LocalDate firstTerm) {
        this.firstTerm = firstTerm;
    }

    public LocalDate getSecondTerm() {
        return secondTerm;
    }

    public void setSecondTerm(LocalDate secondTerm) {
        this.secondTerm = secondTerm;
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
}
