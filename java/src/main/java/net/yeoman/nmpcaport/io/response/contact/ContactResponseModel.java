package net.yeoman.nmpcaport.io.response.contact;

import java.util.List;

import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;

public class ContactResponseModel {

    private String contactId;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String createdAt;
    private String updatedAt;
    private List<NetworkingGroupResponseModel> networkingGroupResponse;
    private HealthCenterNestedResponseModel healthCenterNestedResponseModel;


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

	public List<NetworkingGroupResponseModel> getNetworkingGroupResponse() {
		return networkingGroupResponse;
	}

	public void setNetworkingGroupResponse(List<NetworkingGroupResponseModel> networkingGroupResponse) {
		this.networkingGroupResponse = networkingGroupResponse;
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

    public HealthCenterNestedResponseModel getHealthCenterNestedResponseModel() {
        return healthCenterNestedResponseModel;
    }

    public void setHealthCenterNestedResponseModel(HealthCenterNestedResponseModel healthCenterNestedResponseModel) {
        this.healthCenterNestedResponseModel = healthCenterNestedResponseModel;
    }
}
