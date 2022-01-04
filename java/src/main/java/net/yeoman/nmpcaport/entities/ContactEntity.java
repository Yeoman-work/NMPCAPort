package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="contacts")
public class ContactEntity implements Serializable {

    private static final long serialVersionUID = -5964979469095991442L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String contactId;

    @Size(min = 2, max = 25, message = "must be between 2 and 25 characters")
    private String title;

    @NotBlank(message = "required")
    @Size(min=3, max=25, message = "must be between 3 and 25 characters")
    private String firstName;

    @NotBlank(message = "required")
    @Size(min=3, max=25, message = "must be between 3 and 25 characters")
    private String lastName;

    @NotBlank(message = "required")
    @Email(message = "Please enter a valid email")
    private String email;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;


    @OneToMany(mappedBy = "contactEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_center_entity_id")
    HealthCenterEntity healthCenter;

    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){

        this.updatedAt = new Date();
    }

    public ContactEntity() {
    }

    public ContactEntity(Long id, String contactId, String title, String firstName, String lastName, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.contactId = contactId;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public HealthCenterEntity getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenterEntity healthCenter) {
        this.healthCenter = healthCenter;
    }

    public List<AssignedNetworkingGroupEntity> getAssignedNetworkingGroupEntities() {
        return assignedNetworkingGroupEntities;
    }

    public void setAssignedNetworkingGroupEntities(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities) {
        this.assignedNetworkingGroupEntities = assignedNetworkingGroupEntities;
    }
}
