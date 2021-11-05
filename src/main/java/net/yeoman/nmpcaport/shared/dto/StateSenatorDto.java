package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;

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
    private String senateDistrictIdentifier;
    private Date createdAt;
    private Date updatedAt;
    private SenateDistrictResponseModel senateDistrictResponse;
    private SenateDistrictEntity senateDistrict;

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

    public String getSenateDistrictIdentifier() {
        return senateDistrictIdentifier;
    }

    public void setSenateDistrictIdentifier(String senateDistrictIdentifier) {
        this.senateDistrictIdentifier = senateDistrictIdentifier;
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

    public SenateDistrictEntity getSenateDistrict() {
        return senateDistrict;
    }

    public void setSenateDistrict(SenateDistrictEntity senateDistrict) {
        this.senateDistrict = senateDistrict;
    }

    public SenateDistrictResponseModel getSenateDistrictResponse() {
        return senateDistrictResponse;
    }

    public void setSenateDistrictResponse(SenateDistrictResponseModel senateDistrictResponse) {
        this.senateDistrictResponse = senateDistrictResponse;
    }

}
