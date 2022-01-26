package net.yeoman.nmpcaport.io.response.networkingGroup;

import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.io.response.contact.ContactEssentials;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.Date;
import java.util.List;


public class NetworkingGroupResponseModel {

    private String networkingGroupId;
    private String name;
    private String description;
    private List<ContactEssentials> contactEssentials;
    private Date createdAt;
    private Date updatedAt;

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

    public List<ContactEssentials> getContactEssentials() {
        return contactEssentials;
    }

    public void setContactEssentials(List<ContactEssentials> contactEssentials) {
        this.contactEssentials = contactEssentials;
    }
}
