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
    private CityEntity city;

    @NotNull(message = "required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_entity_id")
    private CountyEntity county;

    @NotNull(message = "required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_center_entity_id")
    @JsonIgnore
    private HealthCenterEntity healthCenter;

    @NotNull(message = "required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zip_code_entity_id")
    private ZipCodeEntity zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "n_m_house_district_entity_id")
    private NMHouseDistrictEntity nmHouseDistrict;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senate_district_entity_id")
    private SenateDistrictEntity senateDistrict;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congressional_district_id")
    private CongressionalDistrictEntity congressionalDistrictEntity;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SiteFundingDetailsEntity> siteFundingDetails;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SiteServiceDetailsEntity> siteServiceDetails;



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
        this.healthCenter = healthCenter;
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


    public HealthCenterEntity getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenterEntity healthCenter) {
        this.healthCenter = healthCenter;
    }


    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }

    public ZipCodeEntity getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCodeEntity zipCode) {
        this.zipCode = zipCode;
    }


    public SenateDistrictEntity getSenateDistrict() {
        return senateDistrict;
    }

    public void setSenateDistrict(SenateDistrictEntity senateDistrict) {
        this.senateDistrict = senateDistrict;
    }

    public NMHouseDistrictEntity getNmHouseDistrict() {
        return nmHouseDistrict;
    }

    public void setNmHouseDistrict(NMHouseDistrictEntity nmHouseDistrict) {
        this.nmHouseDistrict = nmHouseDistrict;
    }

    public CongressionalDistrictEntity getCongressionalDistrictEntity() {
        return congressionalDistrictEntity;
    }

    public void setCongressionalDistrictEntity(CongressionalDistrictEntity congressionalDistrictEntity) {
        this.congressionalDistrictEntity = congressionalDistrictEntity;
    }

    public List<SiteFundingDetailsEntity> getSiteFundingDetails() {
        return siteFundingDetails;
    }

    public void setSiteFundingDetails(List<SiteFundingDetailsEntity> siteFundingDetails) {
        this.siteFundingDetails = siteFundingDetails;
    }

    public List<SiteServiceDetailsEntity> getSiteServiceDetails() {
        return siteServiceDetails;
    }

    public void setSiteServiceDetails(List<SiteServiceDetailsEntity> siteServiceDetails) {
        this.siteServiceDetails = siteServiceDetails;
    }
}
