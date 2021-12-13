package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nmSenateCommittees")
public class NMSenateCommitteeEntity implements Serializable {

    private static final long serialVersionUID = -6206693048907184517L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="required")
    private String nmSenateCommitteeId;

    @NotBlank(message = "required")
    @Size(min=4, max=100, message = "must be between 4 and 100 characters")
    @Column(unique = true)
    private String name;

    @Size(min=8, max=200, message = "must be between 8 and 200 characters")
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


    public NMSenateCommitteeEntity() {
    }

    public NMSenateCommitteeEntity(Long id, String nmSenateCommitteeId, String name, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nmSenateCommitteeId = nmSenateCommitteeId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmSenateCommitteeId() {
        return nmSenateCommitteeId;
    }

    public void setNmSenateCommitteeId(String nmSenateCommitteeId) {
        this.nmSenateCommitteeId = nmSenateCommitteeId;
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


}
