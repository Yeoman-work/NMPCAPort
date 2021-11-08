package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.StateSenatorEntity;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorResponse;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class NMSenateCommitteeDto {

    private Long id;
    private String nmSenateCommitteeId;
    private String name;
    private String description;
    private List<StateSenatorEntity> senatorEntity;
    private List<StateSenatorNestedResponse> senators;
    private List<String> committeeNames;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<StateSenatorEntity> getSenatorEntity() {
        return senatorEntity;
    }

    public void setSenatorEntity(List<StateSenatorEntity> senatorEntity) {
        this.senatorEntity = senatorEntity;
    }

    public List<StateSenatorNestedResponse> getSenators() {
        return senators;
    }

    public void setSenators(List<StateSenatorNestedResponse> senators) {
        this.senators = senators;
    }

    public List<String> getCommitteeNames() {
        return committeeNames;
    }

    public void setCommitteeNames(List<String> committeeNames) {
        this.committeeNames = committeeNames;
    }
}
