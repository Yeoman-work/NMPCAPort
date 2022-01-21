package net.yeoman.nmpcaport.io.response.staff;

import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberEssentials;

import java.util.List;

public class StaffEssentials {

    private String staffId;
    private String firstName;
    private String title;
    private String lastName;
    private String email;
    private List<PhoneNumberEssentials> phoneNumberEssentials;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<PhoneNumberEssentials> getPhoneNumberEssentials() {
        return phoneNumberEssentials;
    }

    public void setPhoneNumberEssentials(List<PhoneNumberEssentials> phoneNumberEssentials) {
        this.phoneNumberEssentials = phoneNumberEssentials;
    }
}
