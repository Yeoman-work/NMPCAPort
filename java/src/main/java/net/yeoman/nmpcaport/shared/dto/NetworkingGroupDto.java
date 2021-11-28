package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;

import java.util.Date;
import java.util.List;

public class NetworkingGroupDto {

    private Long id;
    private String networkingGroupId;
    private String name;
    private String description;
    private List<UserEntity> users;
    private List<UserDetailsResponseModel> userResponse;
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
}
