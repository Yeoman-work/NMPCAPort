package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="officeAssignments")
public class OfficeAssignmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="required")
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
    @JoinColumn(name = "u_s_senator_entity_id")
    private USSenatorEntity usSenatorEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_entity_id")
    private LocationEntity locationEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congressional_rep_entity_id")
    private CongressionalRepEntity congressionalRepEntity;


    public OfficeAssignmentEntity() {
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

    public USSenatorEntity getUsSenatorEntity() {
        return usSenatorEntity;
    }

    public void setUsSenatorEntity(USSenatorEntity usSenatorEntity) {
        this.usSenatorEntity = usSenatorEntity;
    }

    public LocationEntity getLocationEntity() {
        return locationEntity;
    }

    public void setLocationEntity(LocationEntity locationEntity) {
        this.locationEntity = locationEntity;
    }

    public CongressionalRepEntity getCongressionalRepEntity() {
        return congressionalRepEntity;
    }

    public void setCongressionalRepEntity(CongressionalRepEntity congressionalRepEntity) {
        this.congressionalRepEntity = congressionalRepEntity;
    }
}
