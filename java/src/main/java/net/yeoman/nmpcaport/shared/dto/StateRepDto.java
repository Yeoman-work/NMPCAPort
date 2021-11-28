package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.NMHouseDistrictEntity;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private String nmHouseDistrictIdentifier;
    private Date createdAt;
    private Date updatedAt;
    private NMHouseDistrictEntity nmHouseDistrict;
    private NMHouseDistrictNestedResponse nmHouseDistrictResponse;

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

    public String getNmHouseDistrictIdentifier() {
        return nmHouseDistrictIdentifier;
    }

    public void setNmHouseDistrictIdentifier(String nmHouseDistrictIdentifier) {
        this.nmHouseDistrictIdentifier = nmHouseDistrictIdentifier;
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

    public NMHouseDistrictNestedResponse getNmHouseDistrictResponse() {
        return nmHouseDistrictResponse;
    }

    public void setNmHouseDistrictResponse(NMHouseDistrictNestedResponse nmHouseDistrictResponse) {
        this.nmHouseDistrictResponse = nmHouseDistrictResponse;
    }

}
