package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "NMHouseDistricts")
public class NMHouseDistrictEntity implements Serializable {

    private static final long serialVersionUID = 2340860745973567245L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String houseDistrictId;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String name;

    private String map;

    private LocalDate reelection;

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

    @OneToMany(mappedBy = "nmHouseDistrict", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SiteEntity> sites;

    @OneToOne(mappedBy = "nmHouseDistrict", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private StateRepEntity stateRep;




    public NMHouseDistrictEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseDistrictId() {
        return houseDistrictId;
    }

    public void setHouseDistrictId(String houseDistrictId) {
        this.houseDistrictId = houseDistrictId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public LocalDate getReelection() {
        return reelection;
    }

    public void setReelection(LocalDate reelection) {
        this.reelection = reelection;
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

    public StateRepEntity getStateRep() {
        return stateRep;
    }

    public void setStateRep(StateRepEntity stateRep) {
        this.stateRep = stateRep;
    }

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }


}
