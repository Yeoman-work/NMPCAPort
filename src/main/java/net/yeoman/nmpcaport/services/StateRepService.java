package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.shared.dto.StateRepDto;

public interface StateRepService {

    public StateRepDto getStateRep(String stateRepId);
    public StateRepDto createStateRep(StateRepDto stateRepDto);
    public StateRepDto updatedStateRep(String stateRepId, StateRepDto stateRepDto);
    public StateRepDto deleteStateRep(String stateRepId);
    public StateRepEntity findStateRepEntityById(String stateRepId);
}
