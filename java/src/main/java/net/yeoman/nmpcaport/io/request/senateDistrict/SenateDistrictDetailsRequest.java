package net.yeoman.nmpcaport.io.request.senateDistrict;

import java.time.LocalDate;
import java.util.List;

public class SenateDistrictDetailsRequest {

    private String name;
    private String map;
    private LocalDate electionDate;

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

    public LocalDate getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(LocalDate electionDate) {
        this.electionDate = electionDate;
    }
}
