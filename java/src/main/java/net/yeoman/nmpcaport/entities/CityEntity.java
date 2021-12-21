package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "cities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "sites"})
public class CityEntity implements Serializable {


    private static final long serialVersionUID = -634507472277662911L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String cityId;

    @NotBlank(message = "require")
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



    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SiteEntity> site;

    @OneToMany(mappedBy = "cityEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StateRepEntity> stateReps;

    @OneToMany(mappedBy = "cityEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StateSenatorEntity> stateSenators;



    public CityEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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

    public List<SiteEntity> getSite() {
        return site;
    }

    public void setSite(List<SiteEntity> site) {
        this.site = site;
    }

    public List<StateRepEntity> getStateReps() {
        return stateReps;
    }

    public void setStateReps(List<StateRepEntity> stateReps) {
        this.stateReps = stateReps;
    }

    public List<StateSenatorEntity> getStateSenators() {
        return stateSenators;
    }

    public void setStateSenators(List<StateSenatorEntity> stateSenators) {
        this.stateSenators = stateSenators;
    }
}
