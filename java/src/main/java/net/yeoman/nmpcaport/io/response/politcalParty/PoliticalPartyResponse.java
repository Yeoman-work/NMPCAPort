package net.yeoman.nmpcaport.io.response.politcalParty;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class PoliticalPartyResponse {


    private String partyId;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
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
}
