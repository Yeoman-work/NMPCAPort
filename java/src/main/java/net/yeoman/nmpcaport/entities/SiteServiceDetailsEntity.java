package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "siteServiceDetails")
public class SiteServiceDetailsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String siteServiceDetailsId;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;


    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){

        this.updatedAt = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_entity_id")
    private SiteEntity site;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_entity_id")
    private ServiceEntity service;

    public SiteServiceDetailsEntity() {
    }

    public SiteServiceDetailsEntity(Long id,
                                    String siteServiceDetailsId,
                                    Date createdAt,
                                    Date updatedAt,
                                    SiteEntity site,
                                    HealthCenterEntity healthCenterEntity,
                                    ServiceEntity service) {
        this.id = id;
        this.siteServiceDetailsId = siteServiceDetailsId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.site = site;
        this.service = service;
    }

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

}
