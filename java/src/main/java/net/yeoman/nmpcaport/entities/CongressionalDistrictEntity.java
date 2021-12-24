package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="congressionalDistricts")
public class CongressionalDistrictEntity implements Serializable {


    private static final long serialVersionUID = -469174334886399408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String congressionalDistrictId;

    @NotBlank(message = "require")
    @Column(unique = true)
    private String name;

    @Size(max = 500)
    private String map;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextElection;

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

    @OneToOne(mappedBy = "districtEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private CongressionalRepEntity repEntity;

    @OneToMany(mappedBy = "congressionalDistrictEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    List<SiteEntity> sites;



    public CongressionalDistrictEntity() {
    }

    public CongressionalDistrictEntity(Long id, String congressionalDistrictId, String name, Date createdAt, Date updatedAt) {
        this.id = id;
        this.congressionalDistrictId = congressionalDistrictId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public CongressionalDistrictEntity(Long id,
                                       String congressionalDistrictId,
                                       String name,
                                       String map,
                                       LocalDate nextElection,
                                       Date createdAt,
                                       Date updatedAt,
                                       CongressionalRepEntity repEntity,
                                       List<SiteEntity> sites) {
        this.id = id;
        this.congressionalDistrictId = congressionalDistrictId;
        this.name = name;
        this.map = map;
        this.nextElection = nextElection;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.repEntity = repEntity;
        this.sites = sites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCongressionalDistrictId() {
        return congressionalDistrictId;
    }

    public void setCongressionalDistrictId(String congressionalDistrictId) {
        this.congressionalDistrictId = congressionalDistrictId;
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

    public LocalDate getNextElection() {
        return nextElection;
    }

    public void setNextElection(LocalDate nextElection) {
        this.nextElection = nextElection;
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

    public CongressionalRepEntity getRepEntity() {
        return repEntity;
    }

    public void setRepEntity(CongressionalRepEntity repEntity) {
        this.repEntity = repEntity;
    }

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }


}
