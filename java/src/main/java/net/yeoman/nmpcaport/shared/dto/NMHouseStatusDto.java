package net.yeoman.nmpcaport.shared.dto;


import java.util.Date;

public class NMHouseStatusDto {

    private Long id;
    private String houseStatusId;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseStatusId() {
        return houseStatusId;
    }

    public void setHouseStatusId(String houseStatusId) {
        this.houseStatusId = houseStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
