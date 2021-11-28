package net.yeoman.nmpcaport.io.request.NMSenateCommittee;

import java.util.Date;

public class NMSenateCommitteeDetailsRequest {

    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private String stateSenatorIdentifier;

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

    public String getStateSenatorIdentifier() {
        return stateSenatorIdentifier;
    }

    public void setStateSenatorIdentifier(String stateSenatorIdentifier) {
        this.stateSenatorIdentifier = stateSenatorIdentifier;
    }
}
