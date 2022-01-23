package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "houseCommittee")
public class HouseCommitteeEntity implements Serializable {


    private static final long serialVersionUID = 3988256856852878426L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String publicId;


    @NotBlank(message = "required")
    @Size(min=3, max=75)
    private String name;

    @NotBlank(message = "required")
    @Size(max=150)
    private String description;


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

    @OneToMany(mappedBy = "nmHouseCommitteeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HouseCommitteeAssignmentEntity> houseCommitteeAssignments;

    public HouseCommitteeEntity() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<HouseCommitteeAssignmentEntity> getHouseCommitteeAssignments() {
        return houseCommitteeAssignments;
    }

    public void setHouseCommitteeAssignments(List<HouseCommitteeAssignmentEntity> houseCommitteeAssignments) {
        this.houseCommitteeAssignments = houseCommitteeAssignments;
    }
}
