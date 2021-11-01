package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "counties")
public class CountyEntity implements Serializable {


    private static final long serialVersionUID = 3510969843448259854L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(updatable = false)
    private String countyId;

    @NotBlank(message = "required")
    @Column(updatable = false)
    private String name;

    @Column(updatable = true)
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "county", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SiteEntity> sites;

    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){

        this.updatedAt = new Date();
    }

    public CountyEntity() {
    }

    public CountyEntity(Long id, String countyId, String name, Date createdAt, Date updatedAt, List<SiteEntity> sites) {
        this.id = id;
        this.countyId = countyId;
        this.name = name;
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

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
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

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }
}
