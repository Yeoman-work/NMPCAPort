package net.yeoman.nmpcaport.io.response.LocationResponse;

import net.yeoman.nmpcaport.io.response.CongressionalRepResponse.CongressionalRepResponse;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;

import java.util.Date;

public class LocationWithCongressionalRepResponse {

    private String name;
    private String description;
    private CityResponse cityResponse;
    private ZipCodeResponse zipCodeResponse;
    private CountyResponse countyResponse;
    private CongressionalRepResponse congressionalRepResponse;
    private Date createdAt;
    private Date updatedAt;

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

    public CityResponse getCityResponse() {
        return cityResponse;
    }

    public void setCityResponse(CityResponse cityResponse) {
        this.cityResponse = cityResponse;
    }

    public ZipCodeResponse getZipCodeResponse() {
        return zipCodeResponse;
    }

    public void setZipCodeResponse(ZipCodeResponse zipCodeResponse) {
        this.zipCodeResponse = zipCodeResponse;
    }

    public CountyResponse getCountyResponse() {
        return countyResponse;
    }

    public void setCountyResponse(CountyResponse countyResponse) {
        this.countyResponse = countyResponse;
    }

    public CongressionalRepResponse getCongressionalRepResponse() {
        return congressionalRepResponse;
    }

    public void setCongressionalRepResponse(CongressionalRepResponse congressionalRepResponse) {
        this.congressionalRepResponse = congressionalRepResponse;
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
}
