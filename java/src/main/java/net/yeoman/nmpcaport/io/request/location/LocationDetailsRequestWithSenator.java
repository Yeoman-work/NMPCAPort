package net.yeoman.nmpcaport.io.request.location;

public class LocationDetailsRequestWithSenator {

    private String name;
    private String description;
    private String city;
    private String county;
    private String zipCode;
    private String usSenator;


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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getUsSenator() {
        return usSenator;
    }

    public void setUsSenator(String usSenator) {
        this.usSenator = usSenator;
    }
}
