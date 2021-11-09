package net.yeoman.nmpcaport.io.response.legislation;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class LegislationResponse {


    private String legislationId;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

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
