package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="congressionalReps")
public class CongressionalRepEntity implements Serializable {

    private static final long serialVersionUID = 8243999536221926775L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String congressionalRepId;

    @NotBlank(message = "required")
    @Size(min = 3, max = 45, message = "Must be between 3 and 45 characters")
    private String firstName;

    @NotBlank(message = "required")
    @Size(min = 3, max = 45, message = "Must be between 3 and 45 characters")
    private String lastName;

    @NotBlank(message = "required")
    @Email(message = "Please enter a valid email")
    private String email;

    @Size(max = 250)
    private String picture;

    @Size(min = 4, max = 300, message = "Must be between 4 and 300 characters")
    private String website;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "congressional_district_entity_id")
    private CongressionalDistrictEntity district;

    public CongressionalRepEntity() {
    }

    public CongressionalRepEntity(Long id,
                                  String congressionalRepId,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String website,
                                  Date createdAt,
                                  Date updatedAt) {
        this.id = id;
        this.congressionalRepId = congressionalRepId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.website = website;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CongressionalRepEntity(Long id,
                                  String congressionalRepId,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String picture,
                                  String website,
                                  Date createdAt,
                                  Date updatedAt) {
        this.id = id;
        this.congressionalRepId = congressionalRepId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.picture = picture;
        this.website = website;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



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

    public CongressionalDistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(CongressionalDistrictEntity district) {
        this.district = district;
    }


}
