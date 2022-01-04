package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequestList;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class StaffDto {

    private Long id;
    private String staffId;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String rep;
    private String senator;
    private List<PhoneNumberRequest> phoneNumberList;
    private List<AssignedNumberEntity> assignedNumberEntities;
    private List<PhoneNumberResponse> phoneNumberResponses;
    private List<PhoneNumberEntity> phoneNumberEntities;
    private Date createdAt;
    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<PhoneNumberResponse> getPhoneNumberResponses() {
        return phoneNumberResponses;
    }

    public void setPhoneNumberResponses(List<PhoneNumberResponse> phoneNumberResponses) {
        this.phoneNumberResponses = phoneNumberResponses;
    }

    public List<PhoneNumberEntity> getPhoneNumberEntities() {
        return phoneNumberEntities;
    }

    public void setPhoneNumberEntities(List<PhoneNumberEntity> phoneNumberEntities) {
        this.phoneNumberEntities = phoneNumberEntities;
    }

    public String getSenator() {
        return senator;
    }

    public void setSenator(String senator) {
        this.senator = senator;
    }

    public List<AssignedNumberEntity> getAssignedNumberEntities() {
        return assignedNumberEntities;
    }

    public void setAssignedNumberEntities(List<AssignedNumberEntity> assignedNumberEntities) {
        this.assignedNumberEntities = assignedNumberEntities;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }
}
