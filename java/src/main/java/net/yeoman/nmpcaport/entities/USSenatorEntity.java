package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usSenators")
public class USSenatorEntity implements Serializable {

    private static final long serialVersionUID = -1623707452547126154L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String senatorId;


    @NotBlank(message = "required")
    @Size(min = 3, max = 25, message = "must be between 3 and 25 characters")
    private String firstName;

    @NotBlank(message = "required")
    @Size(min = 3, max = 25, message = "must be between 3 and 25 characters")
    private String lastName;

    @Email(message = "please enter a valid email address")
    @Size(max = 120, message = "must be 120 characters and below")
    private String email;

    @Size(max=300)
    private String website;

    @Size(max = 250)
    private String picture;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate elected;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextElection;

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

    @NotNull(message = "required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "political_party_entity_id")
    private PoliticalPartyEntity politicalPartyEntity;

    @OneToMany(mappedBy = "usSenatorEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OfficeAssignmentEntity> officeAssignmentEntities;

    @OneToMany(mappedBy = "usSenatorEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StaffEntity> staffEntities;

    @OneToMany(mappedBy = "usSenatorEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignedNumberEntity> assignedNumberEntities;

    public USSenatorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenatorId() {
        return senatorId;
    }

    public void setSenatorId(String senatorId) {
        this.senatorId = senatorId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDate getElected() {
        return elected;
    }

    public void setElected(LocalDate elected) {
        this.elected = elected;
    }

    public LocalDate getNextElection() {
        return nextElection;
    }

    public void setNextElection(LocalDate nextElection) {
        this.nextElection = nextElection;
    }

    public PoliticalPartyEntity getPoliticalPartyEntity() {
        return politicalPartyEntity;
    }

    public void setPoliticalPartyEntity(PoliticalPartyEntity politicalPartyEntity) {
        this.politicalPartyEntity = politicalPartyEntity;
    }

    public List<OfficeAssignmentEntity> getOfficeAssignmentEntities() {
        return officeAssignmentEntities;
    }

    public void setOfficeAssignmentEntities(List<OfficeAssignmentEntity> officeAssignmentEntities) {
        this.officeAssignmentEntities = officeAssignmentEntities;
    }

    public List<StaffEntity> getStaffEntities() {
        return staffEntities;
    }

    public void setStaffEntities(List<StaffEntity> staffEntities) {
        this.staffEntities = staffEntities;
    }

    public List<AssignedNumberEntity> getAssignedNumberEntities() {
        return assignedNumberEntities;
    }

    public void setAssignedNumberEntities(List<AssignedNumberEntity> assignedNumberEntities) {
        this.assignedNumberEntities = assignedNumberEntities;
    }
}
