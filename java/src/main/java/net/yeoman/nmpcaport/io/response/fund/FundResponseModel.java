package net.yeoman.nmpcaport.io.response.fund;

import net.yeoman.nmpcaport.entities.SiteEntity;

import java.util.Date;
import java.util.List;

public class FundResponseModel {

    private String fundId;
    private String name;
    private List<SiteEntity> siteEntities;
    private Date createdAt;
    private Date updatedAt;


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

    public List<SiteEntity> getSiteEntities() {
        return siteEntities;
    }

    public void setSiteEntities(List<SiteEntity> siteEntities) {
        this.siteEntities = siteEntities;
    }
}
