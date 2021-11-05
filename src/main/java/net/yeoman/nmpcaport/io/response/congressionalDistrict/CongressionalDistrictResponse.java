package net.yeoman.nmpcaport.io.response.congressionalDistrict;

import java.util.Date;

public class CongressionalDistrictResponse {

    private String congressionalDistrictId;
    private String name;
    private String map;
    private Date createdAt;
    private Date updatedAt;

    public String getCongressionalDistrictId() {
        return congressionalDistrictId;
    }

    public void setCongressionalDistrictId(String congressionalDistrictId) {
        this.congressionalDistrictId = congressionalDistrictId;
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
