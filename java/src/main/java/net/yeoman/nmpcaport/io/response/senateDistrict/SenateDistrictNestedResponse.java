package net.yeoman.nmpcaport.io.response.senateDistrict;

import java.time.LocalDate;
import java.util.Date;

public class SenateDistrictNestedResponse {

    private String senateDistrictId;
    private String name;
    private String map;
    private LocalDate electionDate;
    private Date createdAt;
    private Date updatedAt;

    public String getSenateDistrictId() {
        return senateDistrictId;
    }

    public void setSenateDistrictId(String senateDistrictId) {
        this.senateDistrictId = senateDistrictId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public LocalDate getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(LocalDate electionDate) {
        this.electionDate = electionDate;
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
