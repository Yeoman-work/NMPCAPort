package net.yeoman.nmpcaport.entities;

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

    @OneToOne(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CongressionalRepEntity rep;

    @OneToMany(mappedBy = "congressionalDistrictEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<SiteEntity> sites;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_center_entity_id")
    private HealthCenterEntity healthCenter;

    public CongressionalDistrictEntity() {
    }


    public CongressionalDistrictEntity(Long id,
                                       String congressionalDistrictId,
                                       String name, String map,
                                       LocalDate nextElection,
                                       Date createdAt,
                                       Date updatedAt) {
        this.id = id;
        this.congressionalDistrictId = congressionalDistrictId;
        this.name = name;
        this.map = map;
        this.nextElection = nextElection;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public CongressionalRepEntity getRep() {
        return rep;
    }

    public void setRep(CongressionalRepEntity rep) {
        this.rep = rep;
    }

    public HealthCenterEntity getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenterEntity healthCenter) {
        this.healthCenter = healthCenter;
    }
}
