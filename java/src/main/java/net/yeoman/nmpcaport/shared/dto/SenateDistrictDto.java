package net.yeoman.nmpcaport.shared.dto;

import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.StateSenatorEntity;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorResponse;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SenateDistrictDto {

    private Long id;
    private String senateDistrictId;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private String map;
    private LocalDate electionDate;
    private List<String> siteIdentifiers;
    private List<SiteEntity> sites;
    private StateSenatorEntity stateSenator;
    private StateSenatorNestedResponse stateSenatorNestedResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getSiteIdentifiers() {
        return siteIdentifiers;
    }

    public void setSiteIdentifiers(List<String> siteIdentifiers) {
        this.siteIdentifiers = siteIdentifiers;
    }

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
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

    public StateSenatorEntity getStateSenator() {
        return stateSenator;
    }

    public void setStateSenator(StateSenatorEntity stateSenator) {
        this.stateSenator = stateSenator;
    }

    public StateSenatorNestedResponse getStateSenatorNestedResponse() {
        return stateSenatorNestedResponse;
    }

    public void setStateSenatorNestedResponse(StateSenatorNestedResponse stateSenatorNestedResponse) {
        this.stateSenatorNestedResponse = stateSenatorNestedResponse;
    }
}
