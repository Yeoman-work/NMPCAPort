package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StateSenatorEntity;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepNestedResponse;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.shared.dto.StateSenatorDto;

import java.util.List;

public interface StateSenatorService {

    public StateSenatorDto getStateSenator(String senatorId);
    public StateSenatorDto createSenator(StateSenatorDto stateSenatorDto);
    public StateSenatorDto updateStateSenator(String senatorId, StateSenatorDto stateSenatorDto);
    public StateSenatorDto deleteStateSenator(String senatorId);
    public StateSenatorEntity getStateSenatorEntity(String senatorId);
    public List<StateSenatorDto> getAllStateSenators();
    public List<StateSenatorEntity> getStateSenatorEntities();

    //get senator dashboard
    public StateSenatorNestedResponse stateSenatorDashboardData(StateSenatorEntity stateSenatorEntity);
    public List<StateSenatorNestedResponse> stateSenatorDashboardData(List<StateSenatorEntity> stateSenatorEntities);

    //check object is null
    public Boolean entityIsNull(StateSenatorEntity stateSenatorEntity);
    public Boolean entityIsNull(List<StateSenatorEntity> stateSenatorEntities);

}
