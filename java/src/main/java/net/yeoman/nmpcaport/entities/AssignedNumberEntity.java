package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "assignedNumbers")
public class AssignedNumberEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String assignmentId;



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
    @JoinColumn(name = "state_rep_entity_id")
    private StateRepEntity stateRepEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_number_entity_id")
    private PhoneNumberEntity phoneNumberEntity;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "state_senator_entity_id")
    private StateSenatorEntity stateSenatorEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congressional_rep_entity_id")
    private CongressionalDistrictEntity congressionalDistrictEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_entity_id")
    private ContactEntity contactEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_entity_id")
    private StaffEntity staffEntity;


    public AssignedNumberEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
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

    public StateRepEntity getStateRepEntity() {
        return stateRepEntity;
    }

    public void setStateRepEntity(StateRepEntity stateRepEntity) {
        this.stateRepEntity = stateRepEntity;
    }

    public PhoneNumberEntity getPhoneNumberEntity() {
        return phoneNumberEntity;
    }

    public void setPhoneNumberEntity(PhoneNumberEntity phoneNumberEntity) {
        this.phoneNumberEntity = phoneNumberEntity;
    }

    public StateSenatorEntity getStateSenatorEntity() {
        return stateSenatorEntity;
    }

    public void setStateSenatorEntity(StateSenatorEntity stateSenatorEntity) {
        this.stateSenatorEntity = stateSenatorEntity;
    }

    public CongressionalDistrictEntity getCongressionalDistrictEntity() {
        return congressionalDistrictEntity;
    }

    public void setCongressionalDistrictEntity(CongressionalDistrictEntity congressionalDistrictEntity) {
        this.congressionalDistrictEntity = congressionalDistrictEntity;
    }

    public ContactEntity getContactEntity() {
        return contactEntity;
    }

    public void setContactEntity(ContactEntity contactEntity) {
        this.contactEntity = contactEntity;
    }

    public StaffEntity getStaffEntity() {
        return staffEntity;
    }

    public void setStaffEntity(StaffEntity staffEntity) {
        this.staffEntity = staffEntity;
    }
}
