package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "stateSenateCounty")
public class StateSenateCountyEntity implements Serializable {


    private static final long serialVersionUID = 561149628631499362L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String senateCountyId;

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
    @JoinColumn(name = "senate_district_entity_id")
    private SenateDistrictEntity senateDistrict;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_entity_id")
    private CountyEntity countyEntity;


    public StateSenateCountyEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenateCountyId() {
        return senateCountyId;
    }

    public void setSenateCountyId(String senateCountyId) {
        this.senateCountyId = senateCountyId;
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

    public SenateDistrictEntity getSenateDistrict() {
        return senateDistrict;
    }

    public void setSenateDistrict(SenateDistrictEntity senateDistrict) {
        this.senateDistrict = senateDistrict;
    }

    public CountyEntity getCountyEntity() {
        return countyEntity;
    }

    public void setCountyEntity(CountyEntity countyEntity) {
        this.countyEntity = countyEntity;
    }
}
