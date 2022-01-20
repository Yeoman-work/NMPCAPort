package net.yeoman.nmpcaport.io.response.stateRep;

import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictEssentialResponse;

public class StateRepNestedResponse {


    private String stateRepId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String capitolRoom;
    private String streetAddress;
    private String city;
    private String zipCode;
    private NMHouseDistrictEssentialResponse nmHouseDistrictEssentialResponse;


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

    public NMHouseDistrictEssentialResponse getNmHouseDistrictEssentialResponse() {
        return nmHouseDistrictEssentialResponse;
    }

    public void setNmHouseDistrictEssentialResponse(NMHouseDistrictEssentialResponse nmHouseDistrictEssentialResponse) {
        this.nmHouseDistrictEssentialResponse = nmHouseDistrictEssentialResponse;
    }
}
