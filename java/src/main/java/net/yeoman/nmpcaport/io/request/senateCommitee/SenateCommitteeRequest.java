package net.yeoman.nmpcaport.io.request.senateCommitee;

import java.util.List;

public class SenateCommitteeRequest {

    private String name;
    private String description;

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

    public List<String> getSenatorIds() {
        return senatorIds;
    }

    public void setSenatorIds(List<String> senatorIds) {
        this.senatorIds = senatorIds;
    }
}
