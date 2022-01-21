package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="interimCommitteeAssignments")
public class InterimCommitteeAssignmentEntity implements Serializable {


    private static final long serialVersionUID = 4879310149805551690L;

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
    @JoinColumn(name="interim_committee_entity_id")
    private InterimCommitteeEntity interimCommitteeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="state_rep_entity_id")
    private StateRepEntity stateRepEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="state_senator_entity_id")
    private StateSenatorEntity stateSenatorEntity;

    public InterimCommitteeAssignmentEntity() {
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

    public InterimCommitteeEntity getInterimCommitteeEntity() {
        return interimCommitteeEntity;
    }

    public void setInterimCommitteeEntity(InterimCommitteeEntity interimCommitteeEntity) {
        this.interimCommitteeEntity = interimCommitteeEntity;
    }

    public StateRepEntity getStateRepEntity() {
        return stateRepEntity;
    }

    public void setStateRepEntity(StateRepEntity stateRepEntity) {
        this.stateRepEntity = stateRepEntity;
    }

    public StateSenatorEntity getStateSenatorEntity() {
        return stateSenatorEntity;
    }

    public void setStateSenatorEntity(StateSenatorEntity stateSenatorEntity) {
        this.stateSenatorEntity = stateSenatorEntity;
    }
}
