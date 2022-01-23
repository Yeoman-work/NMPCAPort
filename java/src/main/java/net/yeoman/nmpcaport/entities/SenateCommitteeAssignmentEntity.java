package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="senateCommitteeAssignments")
public class SenateCommitteeAssignmentEntity implements Serializable {

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
    @JoinColumn(name= "senate_committee_Entity_id")
    private SenateCommitteeEntity senateCommitteeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="state_senator_entity_id")
    private StateSenatorEntity stateSenatorEntity;

    public SenateCommitteeAssignmentEntity() {
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

    public SenateCommitteeEntity getSenateCommitteeEntity() {
        return senateCommitteeEntity;
    }

    public void setSenateCommitteeEntity(SenateCommitteeEntity senateCommitteeEntity) {
        this.senateCommitteeEntity = senateCommitteeEntity;
    }

    public StateSenatorEntity getStateSenatorEntity() {
        return stateSenatorEntity;
    }

    public void setStateSenatorEntity(StateSenatorEntity stateSenatorEntity) {
        this.stateSenatorEntity = stateSenatorEntity;
    }
}
