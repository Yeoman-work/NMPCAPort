package net.yeoman.nmpcaport.io.response.stateHouseCommittee;

import java.util.Date;

public class StateHouseCommitteeResponse {

    private String houseCommitteeId;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;


    public String getHouseCommitteeId() {
        return houseCommitteeId;
    }

    public void setHouseCommitteeId(String houseCommitteeId) {
        this.houseCommitteeId = houseCommitteeId;
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
}
