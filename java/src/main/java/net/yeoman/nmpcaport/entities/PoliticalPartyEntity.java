package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "politicalParties")
public class PoliticalPartyEntity implements Serializable {

    private static final long serialVersionUID = -4944284707842124842L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(updatable = false)
    private String partyId;

    @NotBlank(message = "required")
    @Column(updatable = false)
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

    @OneToMany(mappedBy = "politicalParty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<StateRepEntity> stateRepEntities;

    @OneToMany(mappedBy = "politicalPartyEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<StateSenatorEntity> stateSenatorEntities;

    public PoliticalPartyEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
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

    public List<StateRepEntity> getStateRepEntities() {
        return stateRepEntities;
    }

    public void setStateRepEntities(List<StateRepEntity> stateRepEntities) {
        this.stateRepEntities = stateRepEntities;
    }

    public List<StateSenatorEntity> getStateSenatorEntities() {
        return stateSenatorEntities;
    }

    public void setStateSenatorEntities(List<StateSenatorEntity> stateSenatorEntities) {
        this.stateSenatorEntities = stateSenatorEntities;
    }
}
