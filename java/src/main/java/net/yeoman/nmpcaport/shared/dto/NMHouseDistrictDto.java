package net.yeoman.nmpcaport.shared.dto;

import java.time.LocalDate;
import java.util.Date;

public class NMHouseDistrictDto {

    private Long id;
    private String houseDistrictId;
    private String name;
    private String map;
    private LocalDate reelection;
    private Date createdAt;
    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseDistrictId() {
        return houseDistrictId;
    }

    public void setHouseDistrictId(String houseDistrictId) {
        this.houseDistrictId = houseDistrictId;
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

    public LocalDate getReelection() {
        return reelection;
    }

    public void setReelection(LocalDate reelection) {
        this.reelection = reelection;
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
