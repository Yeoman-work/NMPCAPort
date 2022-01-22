package net.yeoman.nmpcaport.io.request.interimCommittee;

import java.util.List;

public class InterimCommitteeRequest {

    private String name;
    private String description;
    private List<String> repIds;
    private List<String> senatorIds;

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

    public List<String> getSenatorIds() {
        return senatorIds;
    }

    public void setSenatorIds(List<String> senatorIds) {
        this.senatorIds = senatorIds;
    }
}
