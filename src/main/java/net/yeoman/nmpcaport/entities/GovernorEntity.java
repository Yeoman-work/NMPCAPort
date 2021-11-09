package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Governor")
public class GovernorEntity implements Serializable {

    private static final long serialVersionUID = 3176290052787268633L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String governorId;

    @NotBlank(message = "required")
    private String firstName;

    @NotBlank(message = "required")
    private String lastName;

    @Email(message = "please enter a valid email")
    private String email;

    private String picture;

    private String website;

    private LocalDate firstTerm;

    private LocalDate secondTerm;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){

        this.updatedAt = new Date();
    }

    public GovernorEntity() {
    }

    public GovernorEntity(Long id,
                          String governorId,
                          String firstName,
                          String lastName,
                          String email,
                          String picture,
                          String website,
                          LocalDate firstTerm,
                          LocalDate secondTerm,
                          Date createdAt,
                          Date updatedAt) {
        this.id = id;
        this.governorId = governorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.picture = picture;
        this.website = website;
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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
        this.firstName = firstName.trim().toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim().toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website.trim();
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
