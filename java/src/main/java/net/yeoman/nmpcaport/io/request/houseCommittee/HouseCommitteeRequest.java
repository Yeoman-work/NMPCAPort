package net.yeoman.nmpcaport.io.request.houseCommittee;

import java.util.List;

public class HouseCommitteeRequest {

    private String name;
    private String description;
    private List<String> repIds;

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

    public List<String> getRepIds() {
        return repIds;
    }

    public void setRepIds(List<String> repIds) {
        this.repIds = repIds;
    }
}
