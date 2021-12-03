package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.fund.FundRequestModel;

import java.util.Date;
import java.util.List;

public class FundDto {

    private Long id;
    private String fundId;
    private String name;
    private String fundCode;
    private List<String> siteIds;
    private List<SiteEntity> siteEntities;
    private List<String> healthCenterIds;
    private List<HealthCenterEntity> healthCenterEntities;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
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

    public List<String> getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(List<String> siteIds) {
        this.siteIds = siteIds;
    }

    public List<SiteEntity> getSiteEntities() {
        return siteEntities;
    }

    public void setSiteEntities(List<SiteEntity> siteEntities) {
        this.siteEntities = siteEntities;
    }

    public List<String> getHealthCenterIds() {
        return healthCenterIds;
    }

    public void setHealthCenterIds(List<String> healthCenterIds) {
        this.healthCenterIds = healthCenterIds;
    }

    public List<HealthCenterEntity> getHealthCenterEntities() {
        return healthCenterEntities;
    }

    public void setHealthCenterEntities(List<HealthCenterEntity> healthCenterEntities) {
        this.healthCenterEntities = healthCenterEntities;
    }
}
