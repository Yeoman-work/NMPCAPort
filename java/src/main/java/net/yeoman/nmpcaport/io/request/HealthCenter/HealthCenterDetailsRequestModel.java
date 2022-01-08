package net.yeoman.nmpcaport.io.request.HealthCenter;

import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;

import java.util.List;

public class HealthCenterDetailsRequestModel {

    private String name;
    private String nameAbbr;
    private List<SiteDetailsRequestModel> sitesRequest;


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

    public List<SiteDetailsRequestModel> getSitesRequest() {
        return sitesRequest;
    }

    public void setSitesRequest(List<SiteDetailsRequestModel> sitesRequest) {
        this.sitesRequest = sitesRequest;
    }
}
