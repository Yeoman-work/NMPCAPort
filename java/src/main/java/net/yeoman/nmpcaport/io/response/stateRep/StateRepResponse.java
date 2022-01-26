package net.yeoman.nmpcaport.io.response.stateRep;

import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.city.CityEssentials;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyEssentials;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeEssentials;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

public class StateRepResponse {

    private String stateRepId;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String capitolRoom;
    private String streetAddress;
    private PoliticalPartyEssentials politicalPartyEssentials;
    private HouseDistrictEssentialResponse houseDistrictEssentialResponse;
    private CityEssentials cityEssentials;
    private ZipCodeEssentials zipCodeEssentials;



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

    public PoliticalPartyEssentials getPoliticalPartyEssentials() {
        return politicalPartyEssentials;
    }

    public void setPoliticalPartyEssentials(PoliticalPartyEssentials politicalPartyEssentials) {
        this.politicalPartyEssentials = politicalPartyEssentials;
    }

    public HouseDistrictEssentialResponse getHouseDistrictEssentialResponse() {
        return houseDistrictEssentialResponse;
    }

    public void setHouseDistrictEssentialResponse(HouseDistrictEssentialResponse houseDistrictEssentialResponse) {
        this.houseDistrictEssentialResponse = houseDistrictEssentialResponse;
    }

    public CityEssentials getCityEssentials() {
        return cityEssentials;
    }

    public void setCityEssentials(CityEssentials cityEssentials) {
        this.cityEssentials = cityEssentials;
    }

    public ZipCodeEssentials getZipCodeEssentials() {
        return zipCodeEssentials;
    }

    public void setZipCodeEssentials(ZipCodeEssentials zipCodeEssentials) {
        this.zipCodeEssentials = zipCodeEssentials;
    }
}
