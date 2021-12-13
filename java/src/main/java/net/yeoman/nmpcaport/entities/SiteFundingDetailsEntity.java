package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="siteFundingDetails")
public class SiteFundingDetailsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String siteFundingDetailsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_entity_id")
    private SiteEntity site;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_entity_id")
    private FundEntity fund;




    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    private void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    private void onUpdate(){

        this.updatedAt = new Date();
    }


    public SiteFundingDetailsEntity() {
    }


    public SiteFundingDetailsEntity(Long id,
                                    String siteFundingDetailsId,
                                    SiteEntity site,
                                    FundEntity fund,
                                    HealthCenterEntity healthCenter,
                                    Date createdAt,
                                    Date updatedAt) {
        this.id = id;
        this.siteFundingDetailsId = siteFundingDetailsId;
        this.site = site;
        this.fund = fund;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public FundEntity getFund() {
        return fund;
    }

    public void setFund(FundEntity fund) {
        this.fund = fund;
    }

}
