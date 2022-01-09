package net.yeoman.nmpcaport.io.request.contact;

import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;

import java.util.List;

public class ContactDetailsRequestModel {

    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> networkingGroups;
    private String healthCenter;
    private List<PhoneNumberRequest> phoneNumbers;


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

    public List<String> getNetworkingGroups() {
        return networkingGroups;
    }

    public void setNetworkingGroups(List<String> networkingGroups) {
        this.networkingGroups = networkingGroups;
    }

    public List<PhoneNumberRequest> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberRequest> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(String healthCenter) {
        this.healthCenter = healthCenter;
    }
}
