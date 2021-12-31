package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.io.response.CongressionalRepResponse.CongressionalRepResponse;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorResponse;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

import java.util.Date;

public class LocationDto {

    private Long id;
    private String locationId;
    private String name;
    private String streetAddress;
    private String description;
    private String city;
    private String county;
    private String zipCode;
    private String usSenator;
    private USSenatorEntity usSenatorEntity;
    private USSenatorResponse usSenatorResponse;
    private CongressionalRepEntity congressionalRepEntity;
    private CongressionalRepResponse congressionalRepResponse;
    private CityEntity cityEntity;
    private ZipCodeEntity zipCodeEntity;
    private CountyEntity countyEntity;
    private CityResponse cityResponse;
    private CountyResponse countyResponse;
    private ZipCodeResponse zipCodeResponse;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public CityResponse getCityResponse() {
        return cityResponse;
    }

    public void setCityResponse(CityResponse cityResponse) {
        this.cityResponse = cityResponse;
    }

    public CountyResponse getCountyResponse() {
        return countyResponse;
    }

    public void setCountyResponse(CountyResponse countyResponse) {
        this.countyResponse = countyResponse;
    }

    public ZipCodeResponse getZipCodeResponse() {
        return zipCodeResponse;
    }

    public void setZipCodeResponse(ZipCodeResponse zipCodeResponse) {
        this.zipCodeResponse = zipCodeResponse;
    }

    public USSenatorEntity getUsSenatorEntity() {
        return usSenatorEntity;
    }

    public void setUsSenatorEntity(USSenatorEntity usSenatorEntity) {
        this.usSenatorEntity = usSenatorEntity;
    }

    public USSenatorResponse getUsSenatorResponse() {
        return usSenatorResponse;
    }

    public void setUsSenatorResponse(USSenatorResponse usSenatorResponse) {
        this.usSenatorResponse = usSenatorResponse;
    }

    public CongressionalRepEntity getCongressionalRepEntity() {
        return congressionalRepEntity;
    }

    public void setCongressionalRepEntity(CongressionalRepEntity congressionalRepEntity) {
        this.congressionalRepEntity = congressionalRepEntity;
    }

    public CongressionalRepResponse getCongressionalRepResponse() {
        return congressionalRepResponse;
    }

    public void setCongressionalRepResponse(CongressionalRepResponse congressionalRepResponse) {
        this.congressionalRepResponse = congressionalRepResponse;
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

    public String getUsSenator() {
        return usSenator;
    }

    public void setUsSenator(String usSenator) {
        this.usSenator = usSenator;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
