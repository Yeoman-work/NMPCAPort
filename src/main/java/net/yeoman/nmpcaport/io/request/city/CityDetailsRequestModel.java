package net.yeoman.nmpcaport.io.request.city;

import net.yeoman.nmpcaport.entities.SiteEntity;

import java.util.Date;
import java.util.List;

public class CityDetailsRequestModel {

    private String cityId;
    private String name;
    private List<String> siteId;
    private List<SiteEntity> site;


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSiteId() {
        return siteId;
    }

    public void setSiteId(List<String> siteId) {
        this.siteId = siteId;
    }

    public List<SiteEntity> getSite() {
        return site;
    }

    public void setSite(List<SiteEntity> site) {
        this.site = site;
    }
}
