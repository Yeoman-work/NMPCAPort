package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateSenateCountyEntity;

public interface StateSenatorCountyService {

    public StateSenateCountyEntity getStateSenateCountyEntity(String stateCountyEntity);
    public StateSenateCountyEntity createStateSenateCountyEntity(StateSenateCountyEntity senateCountyEntity);
    public StateSenateCountyEntity deleteStateSenateCountyEntity(String stateCountyId);
}
