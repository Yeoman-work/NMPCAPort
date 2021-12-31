package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "staff")
public class StaffEntity implements Serializable {


    private static final long serialVersionUID = -4716111834274837051L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String staffId;

    @NotBlank(message = "required")
    @Size(min = 3, max = 25, message = "must be between 3 and 25 characters")
    private String firstName;

    @NotBlank(message = "required")
    @Size(min = 3, max = 25, message = "must be between 3 and 25 characters")
    private String lastName;

    @Size(min=3, max = 50)
    private String title;

    @Email(message = "Please enter a valid email address")
    @Size(max = 150)
    private String email;

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

    @OneToMany(mappedBy = "staffEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignedNumberEntity> assignedNumberEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_s_senator_entity_id")
    private USSenatorEntity usSenatorEntity;

    public StaffEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim().toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim().toLowerCase();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public USSenatorEntity getUsSenatorEntity() {
        return usSenatorEntity;
    }

    public void setUsSenatorEntity(USSenatorEntity usSenatorEntity) {
        this.usSenatorEntity = usSenatorEntity;
    }

    public List<AssignedNumberEntity> getAssignedNumberEntities() {
        return assignedNumberEntities;
    }

    public void setAssignedNumberEntities(List<AssignedNumberEntity> assignedNumberEntities) {
        this.assignedNumberEntities = assignedNumberEntities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
