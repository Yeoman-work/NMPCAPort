package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -128727207048747504L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String userId;

    @NotBlank(message = "required")
    @Size(min = 3, max = 25, message = "must be between 3 and 25 characters")
    private String firstName;

    @NotBlank(message = "required")
    @Size(min = 3, max = 25, message = "must be between 3 and 25 characters")
    private String lastName;


    @NotBlank(message = "required")
    @Email(message = "enter a valid email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "required")
    private String encryptedPassword;


    private String emailVerificationToken;

    @NotNull(message = "required")
    private Boolean emailVerificationStatus = false;


    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usersNetworkingGroups",
            joinColumns = @JoinColumn(name = "user_entity_id" ),
            inverseJoinColumns = @JoinColumn(name = "networking_group_entity_id")
    )
    private List<NetworkingGroupEntity> networkingGroupEntities;

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

    public UserEntity() {
    }

    public UserEntity(Long id,
                      String userId,
                      String firstName,
                      String lastName,
                      String email,
                      String encryptedPassword,
                      String emailVerificationToken,
                      Boolean emailVerificationStatus,
                      Date createdAt,
                      Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.emailVerificationToken = emailVerificationToken;
        this.emailVerificationStatus = emailVerificationStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
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

    public List<NetworkingGroupEntity> getNetworkingGroups() {
        return networkingGroupEntities;
    }

    public void setNetworkingGroups(List<NetworkingGroupEntity> networkingGroupEntities) {
        this.networkingGroupEntities = networkingGroupEntities;
    }

    public List<NetworkingGroupEntity> getNetworkingGroupEntities() {
        return networkingGroupEntities;
    }

    public void setNetworkingGroupEntities(List<NetworkingGroupEntity> networkingGroupEntities) {
        this.networkingGroupEntities = networkingGroupEntities;
    }

    public HealthCenterEntity getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenterEntity healthCenter) {
        this.healthCenter = healthCenter;
    }
}
