package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "phoneNumbers")
public class PhoneNumberEntity implements Serializable {

    private static final long serialVersionUID = -5880412070465560589L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String phoneNumberId;

    @NotBlank(message = "required")
    @Size(min = 5, max = 25)
    private String description;

    @NotBlank(message = "required")
    @Column(unique = true)
    @Pattern(regexp ="^(\\d{3}[- .]?){2}\\d{4}$", message = "phone number format xxx-xxx-xxxx")
    private String number;

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

    @OneToMany(mappedBy = "phoneNumberEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignedNumberEntity> assignedNumberEntities;


    public PhoneNumberEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumberId() {
        return phoneNumberId;
    }

    public void setPhoneNumberId(String phoneNumberId) {
        this.phoneNumberId = phoneNumberId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public List<AssignedNumberEntity> getAssignedNumberEntities() {
        return assignedNumberEntities;
    }

    public void setAssignedNumberEntities(List<AssignedNumberEntity> assignedNumberEntities) {
        this.assignedNumberEntities = assignedNumberEntities;
    }
}
