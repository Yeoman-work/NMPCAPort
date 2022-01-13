package net.yeoman.nmpcaport.io.response.networkingGroup;

import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.Date;
import java.util.List;

public class NetworkingGroupFormResponseModel {

    private String networkingGroupId;
    private String name;
    private String description;
    private List<UserDetailsResponseModel> userResponses;
    private List<ContactNestedResponseModel> members;
    private List<ContactNestedResponseModel> nonMembers;
    private List<String> memberIds;
    private List<String> nonMemberIds;
    private Date createdAt;
    private Date updatedAt;

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

    public List<UserDetailsResponseModel> getUserResponses() {
        return userResponses;
    }

    public void setUserResponses(List<UserDetailsResponseModel> userResponses) {
        this.userResponses = userResponses;
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

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }

    public List<String> getNonMemberIds() {
        return nonMemberIds;
    }

    public void setNonMemberIds(List<String> nonMemberIds) {
        this.nonMemberIds = nonMemberIds;
    }
}
