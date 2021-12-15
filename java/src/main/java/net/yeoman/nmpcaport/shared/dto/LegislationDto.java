package net.yeoman.nmpcaport.shared.dto;

import java.util.Date;

public class LegislationDto {

    private Long id;
    private String legislationId;
    private String name;
    private String description;
    private String houseStatus;
    private Date houseStatusDate;
    private String senateStatus;
    private Date senateStatusDate;
    private String governorStatus;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegislationId() {
        return legislationId;
    }

    public void setLegislationId(String legislationId) {
        this.legislationId = legislationId;
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

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Date getHouseStatusDate() {
        return houseStatusDate;
    }

    public void setHouseStatusDate(Date houseStatusDate) {
        this.houseStatusDate = houseStatusDate;
    }

    public String getSenateStatus() {
        return senateStatus;
    }

    public void setSenateStatus(String senateStatus) {
        this.senateStatus = senateStatus;
    }

    public Date getSenateStatusDate() {
        return senateStatusDate;
    }

    public void setSenateStatusDate(Date senateStatusDate) {
        this.senateStatusDate = senateStatusDate;
    }

    public String getGovernorStatus() {
        return governorStatus;
    }

    public void setGovernorStatus(String governorStatus) {
        this.governorStatus = governorStatus;
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
