package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.CityEntity;
import net.yeoman.nmpcaport.entities.CountyEntity;
import net.yeoman.nmpcaport.entities.ZipCodeEntity;

import java.util.Date;

public class LocationDto {

    private Long id;
    private String locationId;
    private String name;
    private String description;
    private String cityId;
    private String countyId;
    private String zipCodeId;
    private CityEntity cityEntity;
    private ZipCodeEntity zipCodeEntity;
    private CountyEntity countyEntity;
    private Date createdAt;
    private Date updatedAt;


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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getZipCodeId() {
        return zipCodeId;
    }

    public void setZipCodeId(String zipCodeId) {
        this.zipCodeId = zipCodeId;
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

    public CountyEntity getCountyEntity() {
        return countyEntity;
    }

    public void setCountyEntity(CountyEntity countyEntity) {
        this.countyEntity = countyEntity;
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
