package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.SiteEntity;

import java.util.List;

public class ServiceDto {

    private Long id;
    private String serviceId;
    private String name;
    private String ABBR;
    private String createdAt;
    private String updatedAt;
    private List<String> siteIdentifier;
    private List<SiteEntity> sites;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getSiteIdentifier() {
        return siteIdentifier;
    }

    public void setSiteIdentifier(List<String> siteIdentifier) {
        this.siteIdentifier = siteIdentifier;
    }

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }
}
