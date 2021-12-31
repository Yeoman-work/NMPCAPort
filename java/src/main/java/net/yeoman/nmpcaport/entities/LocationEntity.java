package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "locations")
public class LocationEntity implements Serializable {

    private static final long serialVersionUID = -1776190010756405683L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String locationId;

    @NotBlank(message = "required")
    @Size(min=3, max=25)
    private String name;

    @Size(min=5, max = 256)
    private String description;

    @NotBlank(message = "required")
    @Size(min=5, max=100)
    private String streetAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_entity_id")
    private CityEntity cityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_entity_id")
    private CountyEntity countyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zip_code_entity_id")
    private ZipCodeEntity zipCodeEntity;

    @OneToMany(mappedBy = "locationEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OfficeAssignmentEntity> officeAssignmentEntities;


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

    public LocationEntity() {
    }

    public LocationEntity(Long id,
                          String locationId,
                          String name,
                          String description,
                          CityEntity cityEntity,
                          CountyEntity county,
                          ZipCodeEntity zipCode,
                          Date createdAt,
                          Date updatedAt) {
        this.id = id;
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.cityEntity = cityEntity;
        this.countyEntity = county;
        this.zipCodeEntity = zipCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public CountyEntity getCountyEntity() {
        return countyEntity;
    }

    public void setCountyEntity(CountyEntity countyEntity) {
        this.countyEntity = countyEntity;
    }

    public ZipCodeEntity getZipCodeEntity() {
        return zipCodeEntity;
    }

    public void setZipCodeEntity(ZipCodeEntity zipCodeEntity) {
        this.zipCodeEntity = zipCodeEntity;
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

    public List<OfficeAssignmentEntity> getOfficeAssignmentEntities() {
        return officeAssignmentEntities;
    }

    public void setOfficeAssignmentEntities(List<OfficeAssignmentEntity> officeAssignmentEntities) {
        this.officeAssignmentEntities = officeAssignmentEntities;
    }
}
