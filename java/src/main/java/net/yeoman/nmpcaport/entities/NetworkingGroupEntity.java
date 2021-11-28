package net.yeoman.nmpcaport.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="networkingGroups")
public class NetworkingGroupEntity implements Serializable {


    private static final long serialVersionUID = 5738074360164530156L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String networkingGroupId;

    @NotBlank(message = "required")
    @Column(unique = true)
    private String name;

    @Size(min=5, max = 100, message = "must be between 5 and 100 characters")
    private String description;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usersNetworkingGroups",
            joinColumns = @JoinColumn(name = "networking_group_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "user_entity_id")
    )
    private List<UserEntity> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "contactsNetworkingGroups",
            joinColumns = @JoinColumn(name = "networking_group_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_entity_id")
    )
    public List<ContactEntity> contacts;

    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){

        this.updatedAt = new Date();
    }

    public NetworkingGroupEntity() {
    }

    public NetworkingGroupEntity(Long id, String networkingGroupId, String name, String description, Date createdAt, Date updatedAt, List<UserEntity> users) {
        this.id = id;
        this.networkingGroupId = networkingGroupId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetworkingGroupId() {
        return networkingGroupId;
    }

    public void setNetworkingGroupId(String networkingGroupId) {
        this.networkingGroupId = networkingGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim().toLowerCase();
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
