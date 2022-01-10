package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;

import java.util.Date;
import java.util.List;

public class ContactDto {

    private Long id;
    private String title;
    private String contactId;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private List<String> networkingGroups;
    private String healthCenter;
    private List<PhoneNumberRequest> phoneNumbers;
    private List<PhoneNumberResponse> phoneNumberResponses;
    private HealthCenterNestedResponseModel healthCenterNestedResponse;
    private HealthCenterEntity healthCenterEntity;
    private List<NetworkingGroupEntity> networkingGroupEntities;
    private List<NetworkingGroupResponseModel> networkingGroupResponses;

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

    public List<String> getNetworkingGroups() {
        return networkingGroups;
    }

    public List<PhoneNumberRequest> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberRequest> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setNetworkingGroups(List<String> networkingGroups) {
        this.networkingGroups = networkingGroups;
    }

    public String getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(String healthCenter) {
        this.healthCenter = healthCenter;
    }

    public HealthCenterNestedResponseModel getHealthCenterNestedResponse() {
        return healthCenterNestedResponse;
    }

    public void setHealthCenterNestedResponse(HealthCenterNestedResponseModel healthCenterNestedResponse) {
        this.healthCenterNestedResponse = healthCenterNestedResponse;
    }

    public HealthCenterEntity getHealthCenterEntity() {
        return healthCenterEntity;
    }

    public void setHealthCenterEntity(HealthCenterEntity healthCenterEntity) {
        this.healthCenterEntity = healthCenterEntity;
    }

    public List<NetworkingGroupEntity> getNetworkingGroupEntities() {
        return networkingGroupEntities;
    }

    public void setNetworkingGroupEntities(List<NetworkingGroupEntity> networkingGroupEntities) {
        this.networkingGroupEntities = networkingGroupEntities;
    }

    public List<NetworkingGroupResponseModel> getNetworkingGroupResponses() {
        return networkingGroupResponses;
    }

    public void setNetworkingGroupResponses(List<NetworkingGroupResponseModel> networkingGroupResponses) {
        this.networkingGroupResponses = networkingGroupResponses;
    }

    public List<PhoneNumberResponse> getPhoneNumberResponses() {
        return phoneNumberResponses;
    }

    public void setPhoneNumberResponses(List<PhoneNumberResponse> phoneNumberResponses) {
        this.phoneNumberResponses = phoneNumberResponses;
    }
}
