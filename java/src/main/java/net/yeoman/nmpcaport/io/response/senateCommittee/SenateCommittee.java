package net.yeoman.nmpcaport.io.response.senateCommittee;

import net.yeoman.nmpcaport.io.response.stateRep.StateRepEssentials;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorEssentials;

import java.util.Date;
import java.util.List;

public class SenateCommittee {

    private String publicId;
    private String name;
    private String description;
    private List<StateSenatorEssentials> stateSenatorEssentials;
    private Date updatedAt;
    private Date createdAt;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
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

    public List<StateSenatorEssentials> getStateSenatorEssentials() {
        return stateSenatorEssentials;
    }

    public void setStateSenatorEssentials(List<StateSenatorEssentials> stateSenatorEssentials) {
        this.stateSenatorEssentials = stateSenatorEssentials;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
