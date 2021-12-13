package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.ServiceEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsResponse;


import java.util.Date;

public class SiteServiceDetailsDto {


    private Long id;
    private String siteServiceDetailsId;
    private SiteEntity site;
    private SiteDetailsNestedResponse siteDetailsNestedResponse;
    private SiteDetailsResponse siteDetailsResponse;
    private ServiceEntity service;
    private ServiceResponse serviceResponse;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteServiceDetailsId() {
        return siteServiceDetailsId;
    }

    public void setSiteServiceDetailsId(String siteServiceDetailsId) {
        this.siteServiceDetailsId = siteServiceDetailsId;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
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

    public SiteDetailsNestedResponse getSiteDetailsNestedResponse() {
        return siteDetailsNestedResponse;
    }

    public void setSiteDetailsNestedResponse(SiteDetailsNestedResponse siteDetailsNestedResponse) {
        this.siteDetailsNestedResponse = siteDetailsNestedResponse;
    }

    public SiteDetailsResponse getSiteDetailsResponse() {
        return siteDetailsResponse;
    }

    public void setSiteDetailsResponse(SiteDetailsResponse siteDetailsResponse) {
        this.siteDetailsResponse = siteDetailsResponse;
    }

    public ServiceResponse getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(ServiceResponse serviceResponse) {
        this.serviceResponse = serviceResponse;
    }
}
