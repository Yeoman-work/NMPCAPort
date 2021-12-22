package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateSenatorEntity;
import net.yeoman.nmpcaport.shared.dto.StateSenatorDto;

import java.util.List;

public interface StateSenatorService {

    public StateSenatorDto getStateSenator(String senatorId);
    public StateSenatorDto createSenator(StateSenatorDto stateSenatorDto);
    public StateSenatorDto updateStateSenator(String senatorId, StateSenatorDto stateSenatorDto);
    public StateSenatorDto deleteStateSenator(String senatorId);
    public StateSenatorEntity getStateSenatorEntity(String senatorId);
    public List<StateSenatorDto> getAllStateSenators();

}
