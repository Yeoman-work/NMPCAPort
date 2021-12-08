package net.yeoman.nmpcaport.io.request.congressionalDistrict;

import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;

import java.util.List;

public class CongressionalDistrictDetailsResponseList {

    List<CongressionalDistrictResponse> congressionalDistrictResponseList;

    public List<CongressionalDistrictResponse> getCongressionalDistrictResponseList() {
        return congressionalDistrictResponseList;
    }

    public void setCongressionalDistrictResponseList(List<CongressionalDistrictResponse> congressionalDistrictResponseList) {
        this.congressionalDistrictResponseList = congressionalDistrictResponseList;
    }
}
