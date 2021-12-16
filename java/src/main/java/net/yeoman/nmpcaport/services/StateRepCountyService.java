package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateRepCountyEntity;

public interface StateRepCountyService {

    public StateRepCountyEntity getStateRepCountyEntity(String stateRepCountyId);
    public StateRepCountyEntity createStateRepCountyEntity(StateRepCountyEntity stateRepCountyEntity);
    public StateRepCountyEntity deleteStateRepCounty(String stateRepCountyId);

}
