package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "senateCommittees")
public class SenateCommitteeEntity implements Serializable {

    private static final long serialVersionUID = -6206693048907184517L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String publicId;

    @NotBlank(message = "required")
    @Size(min= 3, max=75)
    private String name;

    @Size(max=150)
    private String description;

    @Column(updatable = false)
    private Date updatedAt;
    private Date createdAt;

    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){

        this.updatedAt = new Date();

    }

    @OneToMany(mappedBy = "senateCommitteeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SenateCommitteeAssignmentEntity> senateCommitteeAssignments;

    public SenateCommitteeEntity() {
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<SenateCommitteeAssignmentEntity> getSenateCommitteeAssignments() {
        return senateCommitteeAssignments;
    }

    public void setSenateCommitteeAssignments(List<SenateCommitteeAssignmentEntity> senateCommitteeAssignments) {
        this.senateCommitteeAssignments = senateCommitteeAssignments;
    }
}
