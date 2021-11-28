package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="stateHouseCommittees")
public class StateHouseCommitteeEntity implements Serializable {

    private static final long serialVersionUID = 7022762573239065359L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String houseCommitteeId;

    @NotBlank(message = "required")
    @Column(unique = true)
    @Size(min = 4, max = 100, message = "must be between 4 and 100 characters")
    private String name;

    @Size(min = 8, max = 200, message = "must be between 8 and 200 characters")
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

    public StateHouseCommitteeEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouseCommitteeId() {
        return houseCommitteeId;
    }

    public void setHouseCommitteeId(String houseCommitteeId) {
        this.houseCommitteeId = houseCommitteeId;
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
