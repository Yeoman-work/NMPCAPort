package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="services")
public class ServiceEntity implements Serializable {

    private static final long serialVersionUID = 3542587758129069203L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String serviceId;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String abbr;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sites_services",
            joinColumns = @JoinColumn(name = "service_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "site_entity_id")
    )
    private List<SiteEntity> sites;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="service_healthCenter",
            joinColumns = @JoinColumn(name = "service_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "health_center_entity_id")
    )
    private List<HealthCenterEntity> healthCenterEntities;

    public ServiceEntity() {
    }

    public ServiceEntity(Long id, String serviceId, String name, String abbr) {
        this.id = id;
        this.serviceId = serviceId;
        this.name = name;
        this.abbr = abbr;
    }

    public ServiceEntity(Long id,
                         String serviceId,
                         String name,
                         String abbr,
                         Date createdAt,
                         Date updatedAt,
                         List<SiteEntity> sites) {
        this.id = id;
        this.serviceId = serviceId;
        this.name = name;
        this.abbr = abbr;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sites = sites;
    }

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
        this.name = name.trim().toLowerCase();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
}
