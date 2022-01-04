package net.yeoman.nmpcaport.io.request.staff;

import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequestList;

import java.util.List;

public class StaffDetailsRequestCongressionalRep {

    private String rep;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private List<PhoneNumberRequest> phoneNumberList;


    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
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
