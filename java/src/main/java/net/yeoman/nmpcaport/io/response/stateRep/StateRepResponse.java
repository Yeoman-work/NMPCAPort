package net.yeoman.nmpcaport.io.response.stateRep;

import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;

public class StateRepResponse {

    private String stateRepId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String capitolRoom;
    private String streetAddress;
    private NMHouseDistrictNestedResponse nmHouseDistrictResponse;


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

    public NMHouseDistrictNestedResponse getNmHouseDistrictResponse() {
        return nmHouseDistrictResponse;
    }

    public void setNmHouseDistrictResponse(NMHouseDistrictNestedResponse nmHouseDistrictResponse) {
        this.nmHouseDistrictResponse = nmHouseDistrictResponse;
    }

}
