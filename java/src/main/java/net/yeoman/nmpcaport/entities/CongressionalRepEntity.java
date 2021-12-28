package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @Size(min = 3, max = 25, message = "Must be between 3 and 25 characters")
    private String firstName;

    @NotBlank(message = "required")
    @Size(min = 3, max = 25, message = "Must be between 3 and 25 characters")
    private String lastName;


    @Email(message = "Please enter a valid email")
    @Size(max=150)
    private String email;

    @Size(max = 250)
    private String picture;

    @Size(max = 300, message = "Must be 300 characters or less")
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

    @OneToMany(mappedBy = "congressionalRepEntity" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<PersonnelLocationEntity> personnelLocationEntities;

    @OneToMany(mappedBy = "congressionalRepEntity" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<AssignedNumberEntity> assignedNumberEntities ;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congressional_district_entity_id")
    private CongressionalDistrictEntity districtEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "political_party_entity_id")
    private PoliticalPartyEntity politicalPartyEntity;


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

    public CongressionalDistrictEntity getDistrictEntity() {
        return districtEntity;
    }

    public void setDistrictEntity(CongressionalDistrictEntity districtEntity) {
        this.districtEntity = districtEntity;
    }

    public PoliticalPartyEntity getPoliticalPartyEntity() {
        return politicalPartyEntity;
    }

    public void setPoliticalPartyEntity(PoliticalPartyEntity politicalPartyEntity) {
        this.politicalPartyEntity = politicalPartyEntity;
    }

    public List<PersonnelLocationEntity> getPersonnelLocationEntities() {
        return personnelLocationEntities;
    }

    public void setPersonnelLocationEntities(List<PersonnelLocationEntity> personnelLocationEntities) {
        this.personnelLocationEntities = personnelLocationEntities;
    }

    public List<AssignedNumberEntity> getAssignedNumberEntities() {
        return assignedNumberEntities;
    }

    public void setAssignedNumberEntities(List<AssignedNumberEntity> assignedNumberEntities) {
        this.assignedNumberEntities = assignedNumberEntities;
    }
}
