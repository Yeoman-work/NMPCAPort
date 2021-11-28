package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateHouseCommitteeEntity;
import net.yeoman.nmpcaport.io.request.stateHouseCommittee.StateHouseCommitteeDetailsList;
import net.yeoman.nmpcaport.io.response.stateHouseCommittee.StateHouseCommitteeResponse;
import net.yeoman.nmpcaport.shared.dto.StateHouseCommitteeDto;

import java.util.List;

public interface StateHouseCommitteeService {

    public StateHouseCommitteeDto getStateHouseCommittee(String committeeId);
    public StateHouseCommitteeDto createStateHouseCommittee(StateHouseCommitteeDto stateHouseCommittee);
    public StateHouseCommitteeDto updateStateHouseCommittee(String committeeId, StateHouseCommitteeDto stateHouseCommitteeDto);
    public StateHouseCommitteeDto deleteStateHouseCommittee(String committeeId);
    public StateHouseCommitteeEntity getStateHouseCommitteeEntity(StateHouseCommitteeDto stateHouseCommitteeDto);
    public List<StateHouseCommitteeResponse> createStateHouseCommittees(StateHouseCommitteeDetailsList stateHouseCommitteeDetailsList);

}
