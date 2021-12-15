package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="HouseDistrictStatus")
public class NMHouseStatusEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String houseStatusId;

    @NotBlank(message = "required")
    private String status;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void OnUpdate(){

        this.updatedAt = new Date();
    }

    public NMHouseStatusEntity() {
    }

    public NMHouseStatusEntity(Long id,
                               String houseStatusId,
                               String status,
                               Date createdAt,
                               Date updatedAt) {
        this.id = id;
        this.houseStatusId = houseStatusId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseStatusId() {
        return houseStatusId;
    }

    public void setHouseStatusId(String houseStatusId) {
        this.houseStatusId = houseStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
