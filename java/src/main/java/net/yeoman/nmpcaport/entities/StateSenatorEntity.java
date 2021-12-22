package net.yeoman.nmpcaport.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "stateSenators")
public class StateSenatorEntity implements Serializable {

    private static final long serialVersionUID = 657089847650751807L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String stateSenatorId;


    @NotBlank(message = "required")
    @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
    private String firstName;

    @NotBlank(message = "required")
    @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
    private String lastName;

    @Size(max = 150, message = "must be 150 characters or less")
    @Email(message = "please enter a valid email")
    private String email;

    @Size(min=5, max = 250)
    private String picture;

    @Size(max = 8, message = "must be 9 character or less")
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
    protected void onUpdate(){

        this.updatedAt = new Date();
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senate_district_entity_id")
    private SenateDistrictEntity senateDistrictEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "political_party_entity_id")
    private PoliticalPartyEntity politicalPartyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_entity_id")
    private CityEntity cityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zip_code_entity_id")
    private ZipCodeEntity zipCodeEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "nmSenatorsCommittees",
            joinColumns = @JoinColumn(name = "state_senator_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "n_m_senate_committee_entity_id")
    )
    private List<NMSenateCommitteeEntity> committees;

    public StateSenatorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.email = email.trim();
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

    public SenateDistrictEntity getSenateDistrictEntity() {
        return senateDistrictEntity;
    }

    public void setSenateDistrictEntity(SenateDistrictEntity senateDistrictEntity) {
        this.senateDistrictEntity = senateDistrictEntity;
    }

    public List<NMSenateCommitteeEntity> getCommittees() {
        return committees;
    }

    public void setCommittees(List<NMSenateCommitteeEntity> committees) {
        this.committees = committees;
    }

    public PoliticalPartyEntity getPoliticalPartyEntity() {
        return politicalPartyEntity;
    }

    public void setPoliticalPartyEntity(PoliticalPartyEntity politicalPartyEntity) {
        this.politicalPartyEntity = politicalPartyEntity;
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



}
