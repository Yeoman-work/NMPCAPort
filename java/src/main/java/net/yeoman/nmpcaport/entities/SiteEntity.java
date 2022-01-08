package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sites")
public class SiteEntity implements Serializable {

    private static final long serialVersionUID = 2813213657366374297L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String siteId;

    @NotBlank(message = "required")
    @Column(unique = true)
    @Size(min = 3, max = 25, message = "must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "required")
    @Size(min=5, max=50, message = "must be between 5 and 100 characters" )
    private String streetAddress;

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


    @NotNull(message = "required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_entity_id")
    private CityEntity cityEntity;

    @NotNull(message = "required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_entity_id")
    private CountyEntity countyEntity;

    @NotNull(message = "required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_center_entity_id")
    @JsonIgnore
    private HealthCenterEntity healthCenterEntity;

    @NotNull(message = "required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zip_code_entity_id")
    private ZipCodeEntity zipCodeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "n_m_house_district_entity_id")
    private NMHouseDistrictEntity nmHouseDistrictEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senate_district_entity_id")
    private SenateDistrictEntity senateDistrictEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congressional_district_id")
    private CongressionalDistrictEntity congressionalDistrictEntity;

    @OneToMany(mappedBy = "siteEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SiteFundingDetailsEntity> siteFundingDetailsEntities;

    @OneToMany(mappedBy = "siteEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SiteServiceDetailsEntity> serviceDetailsEntities;



    public SiteEntity() {
    }

    public SiteEntity(Long id,
                      String name,
                      Date createdAt,
                      Date updatedAt,
                      HealthCenterEntity healthCenter) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.healthCenterEntity = healthCenterEntity;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress.trim().toLowerCase();
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


    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public CountyEntity getCountyEntity() {
        return countyEntity;
    }

    public void setCountyEntity(CountyEntity countyEntity) {
        this.countyEntity = countyEntity;
    }

    public HealthCenterEntity getHealthCenterEntity() {
        return healthCenterEntity;
    }

    public void setHealthCenterEntity(HealthCenterEntity healthCenterEntity) {
        this.healthCenterEntity = healthCenterEntity;
    }

    public ZipCodeEntity getZipCodeEntity() {
        return zipCodeEntity;
    }

    public void setZipCodeEntity(ZipCodeEntity zipCodeEntity) {
        this.zipCodeEntity = zipCodeEntity;
    }

    public NMHouseDistrictEntity getNmHouseDistrictEntity() {
        return nmHouseDistrictEntity;
    }

    public void setNmHouseDistrictEntity(NMHouseDistrictEntity nmHouseDistrictEntity) {
        this.nmHouseDistrictEntity = nmHouseDistrictEntity;
    }

    public SenateDistrictEntity getSenateDistrictEntity() {
        return senateDistrictEntity;
    }

    public void setSenateDistrictEntity(SenateDistrictEntity senateDistrictEntity) {
        this.senateDistrictEntity = senateDistrictEntity;
    }

    public List<SiteFundingDetailsEntity> getSiteFundingDetailsEntities() {
        return siteFundingDetailsEntities;
    }

    public void setSiteFundingDetailsEntities(List<SiteFundingDetailsEntity> siteFundingDetailsEntities) {
        this.siteFundingDetailsEntities = siteFundingDetailsEntities;
    }

    public List<SiteServiceDetailsEntity> getServiceDetailsEntities() {
        return serviceDetailsEntities;
    }

    public void setServiceDetailsEntities(List<SiteServiceDetailsEntity> serviceDetailsEntities) {
        this.serviceDetailsEntities = serviceDetailsEntities;
    }

    public CongressionalDistrictEntity getCongressionalDistrictEntity() {
        return congressionalDistrictEntity;
    }

    public void setCongressionalDistrictEntity(CongressionalDistrictEntity congressionalDistrictEntity) {
        this.congressionalDistrictEntity = congressionalDistrictEntity;
    }


}
