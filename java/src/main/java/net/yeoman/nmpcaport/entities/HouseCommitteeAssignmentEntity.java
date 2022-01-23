package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="houseCommitteeAssignments")
public class HouseCommitteeAssignmentEntity implements Serializable {


    private static final long serialVersionUID = -2964495333057757567L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String publicId;

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
    @JoinColumn(name = "n_m_house_committee_entity_id")
    private HouseCommitteeEntity nmHouseCommitteeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_rep_entity_id")
    private StateRepEntity stateRepEntity;



    public HouseCommitteeAssignmentEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
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

    public HouseCommitteeEntity getNmHouseCommitteeEntity() {
        return nmHouseCommitteeEntity;
    }

    public void setNmHouseCommitteeEntity(HouseCommitteeEntity nmHouseCommitteeEntity) {
        this.nmHouseCommitteeEntity = nmHouseCommitteeEntity;
    }

    public StateRepEntity getStateRepEntity() {
        return stateRepEntity;
    }

    public void setStateRepEntity(StateRepEntity stateRepEntity) {
        this.stateRepEntity = stateRepEntity;
    }
}
