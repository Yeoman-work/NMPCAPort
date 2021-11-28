package net.yeoman.nmpcaport.io.response.senateDistrict;

import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorResponse;

import java.time.LocalDate;
import java.util.Date;

public class SenateDistrictResponseModel {

    private String senateDistrictId;
    private String name;
    private String map;
    private LocalDate electionDate;
    private Date createdAt;
    private Date updatedAt;
    private StateSenatorNestedResponse stateSenatorNestedResponse;

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

    public StateSenatorNestedResponse getStateSenatorNestedResponse() {
        return stateSenatorNestedResponse;
    }

    public void setStateSenatorNestedResponse(StateSenatorNestedResponse stateSenatorNestedResponse) {
        this.stateSenatorNestedResponse = stateSenatorNestedResponse;
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


}
