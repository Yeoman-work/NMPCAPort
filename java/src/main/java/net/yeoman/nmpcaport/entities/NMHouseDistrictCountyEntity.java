package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "stateRepCounty")
public class NMHouseDistrictCountyEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String stateRepCountyId;


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

    public NMHouseDistrictCountyEntity() {
    }

    public NMHouseDistrictCountyEntity(Long id,
                                       String stateRepCountyId,
                                       Date createdAt,
                                       Date updatedAt,
                                       HouseDistrictEntity nmHouseDistrictEntity,
                                       CountyEntity countyEntity) {
        this.id = id;
        this.stateRepCountyId = stateRepCountyId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nmHouseDistrictEntity = nmHouseDistrictEntity;
        this.countyEntity = countyEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "n_m_house_district_entity_id")
    private HouseDistrictEntity nmHouseDistrictEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_entity_id")
    private CountyEntity countyEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateRepCountyId() {
        return stateRepCountyId;
    }

    public void setStateRepCountyId(String stateRepCountyId) {
        this.stateRepCountyId = stateRepCountyId;
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

    public HouseDistrictEntity getNmHouseDistrictEntity() {
        return nmHouseDistrictEntity;
    }

    public void setNmHouseDistrictEntity(HouseDistrictEntity nmHouseDistrictEntity) {
        this.nmHouseDistrictEntity = nmHouseDistrictEntity;
    }

    public CountyEntity getCountyEntity() {
        return countyEntity;
    }

    public void setCountyEntity(CountyEntity countyEntity) {
        this.countyEntity = countyEntity;
    }


}
