package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepNestedResponse;
import net.yeoman.nmpcaport.shared.dto.StateRepDto;

import java.util.List;

public interface StateRepService {

    public StateRepDto getStateRep(String stateRepId);

    public StateRepDto createStateRep(StateRepDto stateRepDto);

    public StateRepDto updatedStateRep(String stateRepId, StateRepDto stateRepDto);

    public StateRepDto deleteStateRep(String stateRepId);

    public StateRepEntity findStateRepEntityById(String stateRepId);

    public List<StateRepDto> findAllStateReps();

    //get all state rep entities
    public List<StateRepEntity> getAllEntities();

    //state Rep dashboard
    public StateRepNestedResponse getStateRepNestedReps(StateRepEntity stateRepEntity);
    public List<StateRepNestedResponse> getStateRepNestedReps(List<StateRepEntity> stateRepEntities);

    //check for objects
    public Boolean entityIsNull(StateRepEntity stateRepEntity);
    public Boolean entityIsNull(List<StateRepEntity> stateRepEntities);



}
