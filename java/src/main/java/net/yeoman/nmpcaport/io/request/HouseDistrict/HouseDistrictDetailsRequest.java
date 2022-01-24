package net.yeoman.nmpcaport.io.request.HouseDistrict;

import net.yeoman.nmpcaport.io.request.stateRep.StateRepDetailsRequest;

import java.util.List;

public class HouseDistrictDetailsRequest {

    private String name;
    private String map;
    private String repId;
    private StateRepDetailsRequest stateRepDetailsRequest;

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

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public StateRepDetailsRequest getStateRepDetailsRequest() {
        return stateRepDetailsRequest;
    }

    public void setStateRepDetailsRequest(StateRepDetailsRequest stateRepDetailsRequest) {
        this.stateRepDetailsRequest = stateRepDetailsRequest;
    }
}
