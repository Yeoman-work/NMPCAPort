package net.yeoman.nmpcaport.io.request.stateRep;

public class StateRepDetailsRequest {


    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String capitolRoom;
    private String streetAddress;
    private String nmHouseDistrictIdentifier;


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
}
