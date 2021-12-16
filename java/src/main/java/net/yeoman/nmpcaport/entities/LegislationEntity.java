package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "legislation")
public class LegislationEntity implements Serializable {

    private static final long serialVersionUID = 6898202583569814205L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String legislationId;


    @NotBlank(message = "required")
    @Column(unique = true)
    @Size(min = 3, max = 25, message = "must between 3 and 25 characters")
    private String name;


    @Lob
    @Size(max = 700)
    private String description;

    private String houseStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date houseStatusDate;

    private String senateStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date senateStatusDate;

    private String governorStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date governorStatusDate;


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

    public LegislationEntity() {
    }

    public LegislationEntity(Long id,
                             String legislationId,
                             String name,
                             String description,
                             String houseStatus,
                             Date houseStatusDate,
                             String senateStatus,
                             Date senateStatusDate,
                             String governorStatus,
                             Date governorStatusDate,
                             Date createdAt,
                             Date updatedAt) {
        this.id = id;
        this.legislationId = legislationId;
        this.name = name;
        this.description = description;
        this.houseStatus = houseStatus;
        this.houseStatusDate = houseStatusDate;
        this.senateStatus = senateStatus;
        this.senateStatusDate = senateStatusDate;
        this.governorStatus = governorStatus;
        this.governorStatusDate = governorStatusDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegislationId() {
        return legislationId;
    }

    public void setLegislationId(String legislationId) {
        this.legislationId = legislationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Date getHouseStatusDate() {
        return houseStatusDate;
    }

    public void setHouseStatusDate(Date houseStatusDate) {
        this.houseStatusDate = houseStatusDate;
    }

    public String getSenateStatus() {
        return senateStatus;
    }

    public void setSenateStatus(String senateStatus) {
        this.senateStatus = senateStatus;
    }

    public Date getSenateStatusDate() {
        return senateStatusDate;
    }

    public void setSenateStatusDate(Date senateStatusDate) {
        this.senateStatusDate = senateStatusDate;
    }

    public String getGovernorStatus() {
        return governorStatus;
    }

    public void setGovernorStatus(String governorStatus) {
        this.governorStatus = governorStatus;
    }

    public Date getGovernorStatusDate() {
        return governorStatusDate;
    }

    public void setGovernorStatusDate(Date governorStatusDate) {
        this.governorStatusDate = governorStatusDate;
    }



}
