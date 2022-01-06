package net.yeoman.nmpcaport.io.request.HealthCenter;

import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;

import java.util.List;

public class HealthCenterDetailsRequestModel {

    private String name;
    private String nameAbbr;
    private List<SiteDetailsRequestModel> siteDetailsRequests;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }

    public List<SiteDetailsRequestModel> getSiteDetailsRequests() {
        return siteDetailsRequests;
    }

    public void setSiteDetailsRequests(List<SiteDetailsRequestModel> siteDetailsRequests) {
        this.siteDetailsRequests = siteDetailsRequests;
    }
}
