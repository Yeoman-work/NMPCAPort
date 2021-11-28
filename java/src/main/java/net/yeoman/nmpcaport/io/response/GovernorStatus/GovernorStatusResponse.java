package net.yeoman.nmpcaport.io.response.GovernorStatus;

import java.util.Date;

public class GovernorStatusResponse {

    private String govStatusId;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public String getGovStatusId() {
        return govStatusId;
    }

    public void setGovStatusId(String govStatusId) {
        this.govStatusId = govStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
