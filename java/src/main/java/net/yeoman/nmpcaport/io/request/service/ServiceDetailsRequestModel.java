package net.yeoman.nmpcaport.io.request.service;

import java.util.List;

public class ServiceDetailsRequestModel {

    private String name;
    private String abbr;
    private List<String> siteIdentifier;
    private String createdAt;
    private String updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getSiteIdentifier() {
        return siteIdentifier;
    }

    public void setSiteIdentifier(List<String> siteIdentifier) {
        this.siteIdentifier = siteIdentifier;
    }
}
