package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;

import java.util.Date;


public class SiteFundingDetailsDto {

    private Long id;
    private String siteFundingDetailsId;
    private SiteEntity site;
    private SiteDetailsNestedResponse siteDetailsNestedResponse;
    private FundEntity fund;
    private FundResponseModel fundResponseModel;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteFundingDetailsId() {
        return siteFundingDetailsId;
    }

    public void setSiteFundingDetailsId(String siteFundingDetailsId) {
        this.siteFundingDetailsId = siteFundingDetailsId;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public FundEntity getFund() {
        return fund;
    }

    public void setFund(FundEntity fund) {
        this.fund = fund;
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

    public FundResponseModel getFundResponseModel() {
        return fundResponseModel;
    }

    public void setFundResponseModel(FundResponseModel fundResponseModel) {
        this.fundResponseModel = fundResponseModel;
    }
}
