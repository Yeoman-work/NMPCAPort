package net.yeoman.nmpcaport.io.request.location;

public class LocationDetailsRequest {

    private String name;
    private String description;
    private String cityId;
    private String countyId;
    private String zipCodeId;

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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getZipCodeId() {
        return zipCodeId;
    }

    public void setZipCodeId(String zipCodeId) {
        this.zipCodeId = zipCodeId;
    }
}
