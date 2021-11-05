package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateSenatorEntity;
import net.yeoman.nmpcaport.shared.dto.StateSenatorDto;

public interface StateSenatorService {

    StateSenatorDto getStateSenator(String senatorId);
    StateSenatorDto createSenator(StateSenatorDto stateSenatorDto);
    StateSenatorDto updateStateSenator(String senatorId, StateSenatorDto stateSenatorDto);
    StateSenatorDto deleteStateSenator(String senatorId);
    StateSenatorEntity getStateSenatorEntity(String senatorId);
}
