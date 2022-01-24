package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.io.request.stateRep.StateRepDetailsRequest;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepEssentials;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepNestedResponse;
import net.yeoman.nmpcaport.shared.dto.StateRepDto;

import java.util.List;

public interface StateRepService {




    public StateRepEntity findStateRepEntityById(String stateRepId);


    //save entity
    public StateRepEntity saveStateRepEntity(StateRepEntity stateRepEntity);

    //get all state rep entities
    public StateRepEntity getStateRepEntity(String publicId);
    public List<StateRepEntity> getAllEntities();

    //generate entity with unique ID
    public StateRepEntity generateUniqueId(StateRepEntity stateRepEntity);

    //get state rep
    public StateRepEntity requestToEntity(StateRepDetailsRequest stateRepDetailsRequest);

    //State rep essentials
    public StateRepEssentials getStateRepEssentials(StateRepEntity stateRepEntity);
    public List<StateRepEssentials> getStateRepEssentials(List<StateRepEntity> stateRepEntities);

    //state Rep dashboard
    public StateRepNestedResponse getStateRepNestedReps(StateRepEntity stateRepEntity);
    public List<StateRepNestedResponse> getStateRepNestedReps(List<StateRepEntity> stateRepEntities);

    //check for objects
    public Boolean entityIsNull(StateRepEntity stateRepEntity);
    public Boolean entityIsNull(List<StateRepEntity> stateRepEntities);

    //check if request is null
    public Boolean requestIsNull(StateRepDetailsRequest stateRepDetailsRequest);



}
