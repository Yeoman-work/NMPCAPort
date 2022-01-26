package net.yeoman.nmpcaport.io.response.contact;

import java.util.Date;
import java.util.List;

import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterEssentials;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupEssentials;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberEssentials;


public class ContactResponseModel {

    private String contactId;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private List<NetworkingGroupEssentials> networkingGroupEssentials;
    private HealthCenterEssentials healthCenterEssentials;
    private List<PhoneNumberEssentials> phoneNumberEssentials;


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

    public List<NetworkingGroupEssentials> getNetworkingGroupEssentials() {
        return networkingGroupEssentials;
    }

    public void setNetworkingGroupEssentials(List<NetworkingGroupEssentials> networkingGroupEssentials) {
        this.networkingGroupEssentials = networkingGroupEssentials;
    }

    public HealthCenterEssentials getHealthCenterEssentials() {
        return healthCenterEssentials;
    }

    public void setHealthCenterEssentials(HealthCenterEssentials healthCenterEssentials) {
        this.healthCenterEssentials = healthCenterEssentials;
    }

    public List<PhoneNumberEssentials> getPhoneNumberEssentials() {
        return phoneNumberEssentials;
    }

    public void setPhoneNumberEssentials(List<PhoneNumberEssentials> phoneNumberEssentials) {
        this.phoneNumberEssentials = phoneNumberEssentials;
    }
}
