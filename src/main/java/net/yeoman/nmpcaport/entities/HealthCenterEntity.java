package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "healthCenters")
public class HealthCenterEntity implements Serializable {

    private static final long serialVersionUID = -4247886660345569161L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String healthCenterId;

    @NotBlank(message = "required")
    @Size(min = 4, max = 50, message = "must be between 4 and 50 characters")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "required")
    @Size(min=3, max = 8, message = "must be between 3 and 8 characters")
    @Column(unique = true)
    private String nameAbbr;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "healthCenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UserEntity> users;

    @OneToMany(mappedBy = "healthCenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ContactEntity> contacts;

    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdated(){

        this.updatedAt = new Date();

    }

    public HealthCenterEntity() {
    }

    public HealthCenterEntity(Long id, String healthCenterId, String name, String nameAbbr, Date createdAt, Date updatedAt, List<UserEntity> users, List<ContactEntity> contacts) {
        this.id = id;
        this.healthCenterId = healthCenterId;
        this.name = name;
        this.nameAbbr = nameAbbr;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.users = users;
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(String healthCenterId) {
        this.healthCenterId = healthCenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactEntity> contacts) {
        this.contacts = contacts;
    }
}
