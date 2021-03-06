package net.yeoman.nmpcaport.io.response.service;

import net.yeoman.nmpcaport.entities.SiteEntity;

import java.util.List;

public class ServiceResponse {

    private String serviceId;
    private String name;
    private String ABBR;
    private String createdAt;
    private String updatedAt;
    private List<SiteEntity> sites;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getABBR() {
        return ABBR;
    }

    public void setABBR(String ABBR) {
        this.ABBR = ABBR;
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

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }
}
