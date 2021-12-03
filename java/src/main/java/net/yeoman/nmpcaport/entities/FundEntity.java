package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="funds")
public class FundEntity implements Serializable {

    private static final long serialVersionUID = -8744722534402007944L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String fundId;

    @NotBlank(message = "required")
    private String name;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "fundsSites",
            joinColumns = @JoinColumn(name = "fund_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "site_entity_id")
    )
    private List<SiteEntity> sites;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "fundsHealthCenters",
            joinColumns = @JoinColumn(name = "fund_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "health_center_entity_id")
    )
    private List<HealthCenterEntity> healthCenterEntities;


    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdated(){

        this.updatedAt = new Date();
    }

    public FundEntity() {
    }

    public FundEntity(Long id,
                      String fundId,
                      String name,
                      String fundCode,
                      Date createdAt,
                      Date updatedAt) {

        this.id = id;
        this.fundId = fundId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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
        this.name = name.trim().toLowerCase();
    }


    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }

    public List<HealthCenterEntity> getHealthCenterEntities() {
        return healthCenterEntities;
    }

    public void setHealthCenterEntities(List<HealthCenterEntity> healthCenterEntities) {
        this.healthCenterEntities = healthCenterEntities;
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
