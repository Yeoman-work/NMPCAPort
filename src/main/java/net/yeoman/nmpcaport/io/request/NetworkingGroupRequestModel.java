package net.yeoman.nmpcaport.io.request;

import net.yeoman.nmpcaport.entities.UserEntity;

import java.util.List;

public class NetworkingGroupRequestModel {

    private String name;
    private String description;
    private List<String> userIds;

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

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
