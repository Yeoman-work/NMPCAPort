package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.CityEntity;
import net.yeoman.nmpcaport.entities.PoliticalPartyEntity;
import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

import java.util.Date;

public class StateSenatorDto {

    private Long id;
    private String stateSenatorId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String capitolRoom;
    private String streetAddress;
    private String senateDistrict;
    private String zipCode;
    private String city;
    private String party;
    private ZipCodeResponse zipCodeResponse;
    private CityResponse cityResponse;
    private PoliticalPartyResponse politicalPartyResponse;
    private SenateDistrictResponseModel senateDistrictResponse;
    private Date createdAt;
    private Date updatedAt;
    private SenateDistrictEntity senateDistrictEntity;
    private PoliticalPartyEntity politicalPartyEntity;
    private CityEntity cityEntity;
    private ZipCodeEntity zipCodeEntity;

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

    public String getSenateDistrict() {
        return senateDistrict;
    }

    public void setSenateDistrict(String senateDistrict) {
        this.senateDistrict = senateDistrict;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public SenateDistrictEntity getSenateDistrictEntity() {
        return senateDistrictEntity;
    }

    public void setSenateDistrictEntity(SenateDistrictEntity senateDistrictEntity) {
        this.senateDistrictEntity = senateDistrictEntity;
    }

    public SenateDistrictResponseModel getSenateDistrictResponse() {
        return senateDistrictResponse;
    }

    public void setSenateDistrictResponse(SenateDistrictResponseModel senateDistrictResponse) {
        this.senateDistrictResponse = senateDistrictResponse;
    }

    public ZipCodeResponse getZipCodeResponse() {
        return zipCodeResponse;
    }

    public void setZipCodeResponse(ZipCodeResponse zipCodeResponse) {
        this.zipCodeResponse = zipCodeResponse;
    }

    public CityResponse getCityResponse() {
        return cityResponse;
    }

    public void setCityResponse(CityResponse cityResponse) {
        this.cityResponse = cityResponse;
    }

    public PoliticalPartyResponse getPoliticalPartyResponse() {
        return politicalPartyResponse;
    }

    public void setPoliticalPartyResponse(PoliticalPartyResponse politicalPartyResponse) {
        this.politicalPartyResponse = politicalPartyResponse;
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
