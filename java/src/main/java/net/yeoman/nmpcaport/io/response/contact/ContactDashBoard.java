package net.yeoman.nmpcaport.io.response.contact;

import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterEssentials;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupEssentials;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberEssentials;

import java.util.List;

public class ContactDashBoard {

    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private List<NetworkingGroupEssentials> networkingGroupEssentials;
    private HealthCenterEssentials healthCenterEssentials;
    private List<PhoneNumberEssentials> phoneNumbers;


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

    public List<PhoneNumberEssentials> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberEssentials> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
