package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.Date;
import java.util.List;

public class NetworkingGroupDto {

    private Long id;
    private String networkingGroupId;
    private String name;
    private String description;
    private List<UserEntity> users;
    private List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities;
    private List<ContactEntity> nonMemberContactEntities;
    private List<ContactEntity> memberContactEntities;
    private List<UserDetailsResponseModel> userResponse;
    private List<ContactNestedResponseModel> contactNestedResponses;
    private List<ContactNestedResponseModel> members;
    private List<ContactNestedResponseModel> nonMembers;
    private List<String> userIds;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetworkingGroupId() {
        return networkingGroupId;
    }

    public void setNetworkingGroupId(String networkingGroupId) {
        this.networkingGroupId = networkingGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
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

    public List<UserDetailsResponseModel> getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(List<UserDetailsResponseModel> userResponse) {
        this.userResponse = userResponse;
    }

    public List<ContactNestedResponseModel> getContactNestedResponses() {
        return contactNestedResponses;
    }

    public void setContactNestedResponses(List<ContactNestedResponseModel> contactNestedResponses) {
        this.contactNestedResponses = contactNestedResponses;
    }

    public List<ContactNestedResponseModel> getMembers() {
        return members;
    }

    public void setMembers(List<ContactNestedResponseModel> members) {
        this.members = members;
    }

    public List<ContactNestedResponseModel> getNonMembers() {
        return nonMembers;
    }

    public void setNonMembers(List<ContactNestedResponseModel> nonMembers) {
        this.nonMembers = nonMembers;
    }

    public List<AssignedNetworkingGroupEntity> getAssignedNetworkingGroupEntities() {
        return assignedNetworkingGroupEntities;
    }

    public void setAssignedNetworkingGroupEntities(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities) {
        this.assignedNetworkingGroupEntities = assignedNetworkingGroupEntities;
    }

    public List<ContactEntity> getNonMemberContactEntities() {
        return nonMemberContactEntities;
    }

    public void setNonMemberContactEntities(List<ContactEntity> nonMemberContactEntities) {
        this.nonMemberContactEntities = nonMemberContactEntities;
    }

    public List<ContactEntity> getMemberContactEntities() {
        return memberContactEntities;
    }

    public void setMemberContactEntities(List<ContactEntity> memberContactEntities) {
        this.memberContactEntities = memberContactEntities;
    }
}
