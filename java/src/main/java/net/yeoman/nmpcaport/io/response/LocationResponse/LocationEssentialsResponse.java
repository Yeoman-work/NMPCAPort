package net.yeoman.nmpcaport.io.response.LocationResponse;

import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorResponse;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

public class LocationEssentialsResponse {

    private String name;
    private String description;
    private String streetAddress;
    private String city;
    private String zipCode;
    private String county;

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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
