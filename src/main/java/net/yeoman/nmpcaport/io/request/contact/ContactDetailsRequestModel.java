package net.yeoman.nmpcaport.io.request.contact;

import java.util.List;

public class ContactDetailsRequestModel {

    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> networkingGroupIds;


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

    public List<String> getNetworkingGroupIds() {
        return networkingGroupIds;
    }

    public void setNetworkingGroupIds(List<String> networkingGroupIds) {
        this.networkingGroupIds = networkingGroupIds;
    }

}
