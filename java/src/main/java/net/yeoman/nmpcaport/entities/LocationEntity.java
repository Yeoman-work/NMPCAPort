package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

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
    private String name;

    @Size(max = 256)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_entity_id")
    private CityEntity cityEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "county_entity_id")
    private CountyEntity county;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zip_code_entity_id")
    private ZipCodeEntity zipCode;

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
        this.county = county;
        this.zipCode = zipCode;
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

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }

    public ZipCodeEntity getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCodeEntity zipCode) {
        this.zipCode = zipCode;
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
