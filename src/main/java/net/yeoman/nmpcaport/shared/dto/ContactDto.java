package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;

import java.util.List;

public class ContactDto {

    private Long id;
    private String title;
    private String contactId;
    private String firstName;
    private String lastName;
    private String email;
    private String createdAt;
    private String updatedAt;
    private List<String> networkingGroupIds;
    private String healthCenterId;
    private HealthCenterNestedResponseModel healthCenterNestedResponse;
    private HealthCenterEntity healthCenter;
    private List<NetworkingGroupEntity> networkingGroups;
    private List<NetworkingGroupResponseModel> networkingGroupResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getNetworkingGroupIds() {
        return networkingGroupIds;
    }

    public void setNetworkingGroupIds(List<String> networkingGroupIds) {
        this.networkingGroupIds = networkingGroupIds;
    }

    public List<NetworkingGroupEntity> getNetworkingGroups() {
        return networkingGroups;
    }

    public void setNetworkingGroups(List<NetworkingGroupEntity> networkingGroups) {
        this.networkingGroups = networkingGroups;
    }

    public List<NetworkingGroupResponseModel> getNetworkingGroupResponse() {
        return networkingGroupResponse;
    }

    public void setNetworkingGroupResponse(List<NetworkingGroupResponseModel> networkingGroupResponse) {
        this.networkingGroupResponse = networkingGroupResponse;
    }

    public HealthCenterNestedResponseModel getHealthCenterNestedResponse() {
        return healthCenterNestedResponse;
    }

    public void setHealthCenterNestedResponse(HealthCenterNestedResponseModel healthCenterNestedResponse) {
        this.healthCenterNestedResponse = healthCenterNestedResponse;
    }

    public HealthCenterEntity getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenterEntity healthCenter) {
        this.healthCenter = healthCenter;
    }

    public String getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(String healthCenterId) {
        this.healthCenterId = healthCenterId;
    }
}
