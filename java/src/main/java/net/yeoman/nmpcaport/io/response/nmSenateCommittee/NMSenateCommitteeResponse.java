package net.yeoman.nmpcaport.io.response.nmSenateCommittee;

import net.yeoman.nmpcaport.entities.StateSenatorEntity;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;

import java.util.Date;
import java.util.List;

public class NMSenateCommitteeResponse {

    private String nmSenateCommitteeId;
    private String name;
    private String description;
    private List<StateSenatorNestedResponse> stateSenators;
    private Date createdAt;
    private Date updatedAt;


    public String getNmSenateCommitteeId() {
        return nmSenateCommitteeId;
    }

    public void setNmSenateCommitteeId(String nmSenateCommitteeId) {
        this.nmSenateCommitteeId = nmSenateCommitteeId;
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

    public List<StateSenatorNestedResponse> getStateSenators() {
        return stateSenators;
    }

    public void setStateSenators(List<StateSenatorNestedResponse> stateSenators) {
        this.stateSenators = stateSenators;
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
