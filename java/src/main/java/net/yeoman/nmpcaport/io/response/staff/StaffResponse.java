package net.yeoman.nmpcaport.io.response.staff;

import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;

import java.util.Date;
import java.util.List;

public class StaffResponse {

    private String staffId;
    private String firstName;
    private String title;
    private String lastName;
    private String email;
    private List<PhoneNumberResponse> phoneNumberResponses;
    private Date createdAt;
    private Date updatedAt;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public List<PhoneNumberResponse> getPhoneNumberResponses() {
        return phoneNumberResponses;
    }

    public void setPhoneNumberResponses(List<PhoneNumberResponse> phoneNumberResponses) {
        this.phoneNumberResponses = phoneNumberResponses;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
