package net.yeoman.nmpcaport.io.request.staff;


import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;

import java.util.List;

public class StaffDetailsRequestSenator {

    private String senator;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private List<PhoneNumberRequest> phoneNumberList;


    public String getSenator() {
        return senator;
    }

    public void setSenator(String senator) {
        this.senator = senator;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PhoneNumberRequest> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<PhoneNumberRequest> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }
}
