package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "counties")
@JsonIgnoreProperties({"hibernateLazyInitializer", "sites"})
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

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "county", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SiteEntity> sites;

    @OneToMany(mappedBy = "countyEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StateSenateCountyEntity> stateSenateCountyEntityList;

    @OneToMany(mappedBy = "countyEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NMHouseDistrictCountyEntity> stateRepCountyEntityList;

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

    public CountyEntity(Long id,
                        String countyId,
                        String name,
                        Date createdAt,
                        Date updatedAt,
                        List<SiteEntity> sites,
                        List<StateSenateCountyEntity> stateSenateCountyEntityList,
                        List<NMHouseDistrictCountyEntity> stateRepCountyEntityList) {
        this.id = id;
        this.countyId = countyId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sites = sites;
        this.stateSenateCountyEntityList = stateSenateCountyEntityList;
        this.stateRepCountyEntityList = stateRepCountyEntityList;
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

    public List<StateSenateCountyEntity> getStateSenateCountyEntityList() {
        return stateSenateCountyEntityList;
    }

    public void setStateSenateCountyEntityList(List<StateSenateCountyEntity> stateSenateCountyEntityList) {
        this.stateSenateCountyEntityList = stateSenateCountyEntityList;

    }

    public List<NMHouseDistrictCountyEntity> getStateRepCountyEntityList() {
        return stateRepCountyEntityList;
    }

    public void setStateRepCountyEntityList(List<NMHouseDistrictCountyEntity> stateRepCountyEntityList) {
        this.stateRepCountyEntityList = stateRepCountyEntityList;
    }


}
