package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "senateDistricts")
public class SenateDistrictEntity implements Serializable {


    private static final long serialVersionUID = 5003780263868621335L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String senateDistrictId;

    @NotBlank(message = "required")
    @Column(unique = true)
    @Size(min=1, max = 2)
    private String name;

    @Size(min = 500)
    private String map;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate electionDate;

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

    @OneToMany(mappedBy = "senateDistrict", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SiteEntity> sites;

    @OneToOne(mappedBy = "senateDistrictEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private StateSenatorEntity stateSenator;

    @OneToMany(mappedBy = "senateDistrict", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StateSenateCountyEntity> senateCountyEntities;




    public SenateDistrictEntity() {
    }

    public SenateDistrictEntity(Long id,
                                String senateDistrictId,
                                String name,
                                String map,
                                LocalDate electionDate,
                                Date createdAt,
                                Date updatedAt,
                                List<SiteEntity> sites,
                                StateSenatorEntity stateSenator,
                                List<StateSenateCountyEntity> senateCountyEntities) {
        this.id = id;
        this.senateDistrictId = senateDistrictId;
        this.name = name;
        this.map = map;
        this.electionDate = electionDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sites = sites;
        this.stateSenator = stateSenator;
        this.senateCountyEntities = senateCountyEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenateDistrictId() {
        return senateDistrictId;
    }

    public void setSenateDistrictId(String senateDistrictId) {
        this.senateDistrictId = senateDistrictId;
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

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public LocalDate getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(LocalDate electionDate) {
        this.electionDate = electionDate;
    }

    public StateSenatorEntity getStateSenator() {
        return stateSenator;
    }

    public void setStateSenator(StateSenatorEntity stateSenator) {
        this.stateSenator = stateSenator;
    }


    public List<StateSenateCountyEntity> getSenateCountyEntities() {
        return senateCountyEntities;
    }

    public void setSenateCountyEntities(List<StateSenateCountyEntity> senateCountyEntities) {
        this.senateCountyEntities = senateCountyEntities;
    }
}
