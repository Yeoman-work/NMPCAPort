package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @OneToMany(mappedBy = "serviceEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SiteServiceDetailsEntity> siteServiceDetailsEntities;



    public ServiceEntity() {
    }

    public ServiceEntity(Long id,
                         String serviceId,
                         String name,
                         String abbr,
                         Date createdAt,
                         Date updatedAt,
                         List<SiteServiceDetailsEntity> siteDetailsList) {
        this.id = id;
        this.serviceId = serviceId;
        this.name = name;
        this.abbr = abbr;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

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

    public List<SiteServiceDetailsEntity> getSiteServiceDetailsEntities() {
        return siteServiceDetailsEntities;
    }

    public void setSiteServiceDetailsEntities(List<SiteServiceDetailsEntity> siteServiceDetailsEntities) {
        this.siteServiceDetailsEntities = siteServiceDetailsEntities;
    }
}
