package net.yeoman.nmpcaport.io.request.interimCommittee;

import java.util.List;

public class InterimCommitteeRequest {

    private String name;
    private String description;
    private List<String> stateRepIds;
    private List<String> stateSenator;

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

    public List<String> getStateRepIds() {
        return stateRepIds;
    }

    public void setStateRepIds(List<String> stateRepIds) {
        this.stateRepIds = stateRepIds;
    }

    public List<String> getStateSenator() {
        return stateSenator;
    }

    public void setStateSenator(List<String> stateSenator) {
        this.stateSenator = stateSenator;
    }
}
