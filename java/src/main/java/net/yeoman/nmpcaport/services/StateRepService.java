package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.exception.StateRepServiceException;
import net.yeoman.nmpcaport.io.request.stateRep.StateRepDetailsRequest;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepEssentials;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepNestedResponse;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepResponse;
import net.yeoman.nmpcaport.shared.dto.StateRepDto;

import java.util.List;

public interface StateRepService {

    //find state rep entity
    StateRepEntity findStateRepEntityById(String stateRepId);


    //save entity
    StateRepEntity saveStateRepEntity(StateRepEntity stateRepEntity);

    //get all state rep entities
    StateRepEntity getStateRepEntity(String d);
    List<StateRepEntity> getAllEntities();

    StateRepResponse getStateRepResponse(StateRepEntity stateRepEntity);

    //generate entity with unique ID
    StateRepEntity generateUniqueId(StateRepEntity stateRepEntity);

    //get state rep
    StateRepEntity createStateRep(StateRepDetailsRequest stateRepDetailsRequest);

    //State rep essentials
    StateRepEssentials getStateRepEssentials(StateRepEntity stateRepEntity);
    List<StateRepEssentials> getStateRepEssentials(List<StateRepEntity> stateRepEntities);

    //state Rep dashboard
    StateRepNestedResponse getStateRepNestedReps(StateRepEntity stateRepEntity);
    List<StateRepNestedResponse> getStateRepNestedReps(List<StateRepEntity> stateRepEntities);

    //check for objects
    Boolean entityIsNull(StateRepEntity stateRepEntity);
    Boolean entityIsNull(List<StateRepEntity> stateRepEntities);

    //check if request is null
    Boolean requestIsNull(StateRepDetailsRequest stateRepDetailsRequest);


}
