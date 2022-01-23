package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.CityEntity;
import net.yeoman.nmpcaport.entities.HouseDistrictEntity;
import net.yeoman.nmpcaport.entities.PoliticalPartyEntity;
import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;


import java.util.Date;

public class StateRepDto {

    private long id;
    private String stateRepId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String capitolRoom;
    private String streetAddress;
    private String nmHouseDistrict;
    private String county;
    private String city;
    private String zipCode;
    private String party;
    private Date createdAt;
    private Date updatedAt;
    private HouseDistrictEntity nmHouseDistrictEntity;
    private HouseDistrictNestedResponse nmHouseDistrictResponse;
    private CountyResponse countyResponse;
    private CityResponse cityResponse;
    private ZipCodeResponse zipCodeResponse;
    private PoliticalPartyEntity politicalParty;
    private PoliticalPartyResponse politicalPartyResponse;
    private ZipCodeEntity zipCodeEntity;
    private CityEntity cityEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    public void setNmHouseDistrict(String nmHouseDistrict) {
        this.nmHouseDistrict = nmHouseDistrict;
    }

    public HouseDistrictEntity getNmHouseDistrictEntity() {
        return nmHouseDistrictEntity;
    }

    public void setNmHouseDistrictEntity(HouseDistrictEntity nmHouseDistrictEntity) {
        this.nmHouseDistrictEntity = nmHouseDistrictEntity;
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

    public String getNmHouseDistrict() {
        return nmHouseDistrict;
    }

    public HouseDistrictNestedResponse getNmHouseDistrictResponse() {
        return nmHouseDistrictResponse;
    }

    public void setNmHouseDistrictResponse(HouseDistrictNestedResponse nmHouseDistrictResponse) {
        this.nmHouseDistrictResponse = nmHouseDistrictResponse;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public PoliticalPartyEntity getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(PoliticalPartyEntity politicalParty) {
        this.politicalParty = politicalParty;
    }

    public PoliticalPartyResponse getPoliticalPartyResponse() {
        return politicalPartyResponse;
    }

    public void setPoliticalPartyResponse(PoliticalPartyResponse politicalPartyResponse) {
        this.politicalPartyResponse = politicalPartyResponse;
    }

    public CityResponse getCityResponse() {
        return cityResponse;
    }

    public void setCityResponse(CityResponse cityResponse) {
        this.cityResponse = cityResponse;
    }

    public ZipCodeEntity getZipCodeEntity() {
        return zipCodeEntity;
    }

    public void setZipCodeEntity(ZipCodeEntity zipCodeEntity) {
        this.zipCodeEntity = zipCodeEntity;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }


}
