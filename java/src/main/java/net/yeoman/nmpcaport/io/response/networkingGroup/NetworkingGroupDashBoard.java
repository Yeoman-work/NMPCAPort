package net.yeoman.nmpcaport.io.response.networkingGroup;

import net.yeoman.nmpcaport.io.response.contact.ContactDashBoard;
import net.yeoman.nmpcaport.io.response.contact.ContactEssentials;

import java.util.Date;
import java.util.List;

public class NetworkingGroupDashBoard {

    private String networkingGroupId;
    private String name;
    private String description;
    private List<ContactEssentials> contactEssentials;
    private Date createdAt;
    private Date updatedAt;


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

    public List<ContactEssentials> getContactEssentials() {
        return contactEssentials;
    }

    public void setContactEssentials(List<ContactEssentials> contactEssentials) {
        this.contactEssentials = contactEssentials;
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

    public String getNetworkingGroupId() {
        return networkingGroupId;
    }

    public void setNetworkingGroupId(String networkingGroupId) {
        this.networkingGroupId = networkingGroupId;
    }
}
