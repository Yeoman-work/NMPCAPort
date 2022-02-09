package net.yeoman.nmpcaport.io.response.networkingGroup;

import java.util.Date;
import java.util.HashMap;

public class NetworkingGroupFormResponseModel {
    private String name;
    private String description;
    private HashMap<String, String> memberIds;
    private Date createdAt;
    private Date updatedAt;


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

	public HashMap<String, String> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(HashMap<String, String> memberIds) {
		this.memberIds = memberIds;
	}

  

}
