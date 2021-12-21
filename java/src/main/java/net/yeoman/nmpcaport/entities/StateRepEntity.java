package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="stateReps")
public class StateRepEntity implements Serializable {

    private static final long serialVersionUID = -5353765383313897869L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String stateRepId;


    @NotBlank(message = "required")
    @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
    private String firstName;

    @NotBlank(message = "required")
    @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
    private String lastName;

    @Size(max = 150, message = "must be 150 characters or less")
    @Email(message = "please enter a valid email")
    @Column(unique = true)
    private String email;

    @Size(min=5, max = 250)
    private String picture;

    @Size(max=8, message = "Must be 8 characters or less")
    private String capitolRoom;

    @Size(min = 5, max= 150, message = "must be between 5 and 150 characters")
    private String streetAddress;


    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {

        this.updatedAt = new Date();

    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nm_house_district_entity_id")
    private NMHouseDistrictEntity nmHouseDistrict;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_entity_id")
    private CityEntity cityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zip_code_entity_id")
    private ZipCodeEntity zipCodeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "political_party_entity_id")
    private PoliticalPartyEntity politicalParty;

    @OneToMany(mappedBy = "stateRepEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AssignedNumberEntity> assignedNumberEntities;



    public StateRepEntity() {
    }

    public StateRepEntity(Long id,
                          String stateRepId,
                          String firstName,
                          String lastName,
                          String email,
                          String picture,
                          String capitolRoom,
                          String streetAddress,
                          Date createdAt,
                          Date updatedAt,
                          NMHouseDistrictEntity nmHouseDistrict,
                          CityEntity cityEntity,
                          ZipCodeEntity zipCodeEntity,
                          List<AssignedNumberEntity> assignedNumberEntities) {
        this.id = id;
        this.stateRepId = stateRepId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.picture = picture;
        this.capitolRoom = capitolRoom;
        this.streetAddress = streetAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nmHouseDistrict = nmHouseDistrict;
        this.cityEntity = cityEntity;
        this.zipCodeEntity = zipCodeEntity;
        this.assignedNumberEntities = assignedNumberEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateRepId() {
        return stateRepId;
    }

    public void setStateRepId(String stateRepId) {
        this.stateRepId = stateRepId;
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
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCapitolRoom() {
        return capitolRoom;
    }

    public void setCapitolRoom(String capitolRoom) {
        this.capitolRoom = capitolRoom;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public NMHouseDistrictEntity getNmHouseDistrict() {
        return nmHouseDistrict;
    }

    public void setNmHouseDistrict(NMHouseDistrictEntity nmHouseDistrict) {
        this.nmHouseDistrict = nmHouseDistrict;
    }


    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public ZipCodeEntity getZipCodeEntity() {
        return zipCodeEntity;
    }

    public void setZipCodeEntity(ZipCodeEntity zipCodeEntity) {
        this.zipCodeEntity = zipCodeEntity;
    }

    public List<AssignedNumberEntity> getAssignedNumberEntities() {
        return assignedNumberEntities;
    }

    public void setAssignedNumberEntities(List<AssignedNumberEntity> assignedNumberEntities) {
        this.assignedNumberEntities = assignedNumberEntities;
    }

    public PoliticalPartyEntity getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(PoliticalPartyEntity politicalParty) {
        this.politicalParty = politicalParty;
    }

}
