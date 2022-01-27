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
    private SiteEntity siteEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_entity_id")
    private FundEntity fundEntity;




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
        this.siteEntity = site;
        this.fundEntity = fund;
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

    public SiteEntity getSiteEntity() {
        return siteEntity;
    }

    public void setSiteEntity(SiteEntity siteEntity) {
        this.siteEntity = siteEntity;
    }

    public FundEntity getFundEntity() {
        return fundEntity;
    }

    public void setFundEntity(FundEntity fundEntity) {
        this.fundEntity = fundEntity;
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


}
