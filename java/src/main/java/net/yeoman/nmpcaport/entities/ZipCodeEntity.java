package net.yeoman.nmpcaport.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "zipCodes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "sites"})
public class ZipCodeEntity implements Serializable {

    private static final long serialVersionUID = 2918813411729527072L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="required")
    @Column(unique = true)
    private String zipCodeId;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String name;

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

    @OneToMany(mappedBy = "zipCodeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SiteEntity> sites;

    @OneToMany(mappedBy = "zipCodeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StateRepEntity> stateRepEntityList;

    @OneToMany(mappedBy = "zipCodeEntity", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<StateSenatorEntity> stateSenatorEntityList;

    public ZipCodeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCodeId() {
        return zipCodeId;
    }

    public void setZipCodeId(String zipCodeId) {
        this.zipCodeId = zipCodeId;
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

    public List<StateRepEntity> getStateRepEntityList() {
        return stateRepEntityList;
    }

    public void setStateRepEntityList(List<StateRepEntity> stateRepEntityList) {
        this.stateRepEntityList = stateRepEntityList;
    }


}
