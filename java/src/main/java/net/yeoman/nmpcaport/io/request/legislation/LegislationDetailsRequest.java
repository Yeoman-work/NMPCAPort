package net.yeoman.nmpcaport.io.request.legislation;

import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.util.Date;

public class LegislationDetailsRequest {

    private String name;
    private String description;
    private String houseStatus;
    private Date houseStatusDate;
    private String senateStatus;
    private Date senateStatusDate;
    private String governorStatus;
    private Date governorStatusDate;

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

    public Date getGovernorStatusDate() {
        return governorStatusDate;
    }

    public void setGovernorStatusDate(Date governorStatusDate) {
        this.governorStatusDate = governorStatusDate;
    }
}
