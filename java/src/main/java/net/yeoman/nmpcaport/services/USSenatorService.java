package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.USSenatorEntity;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorEssentials;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorResponse;
import net.yeoman.nmpcaport.shared.dto.USSenatorDto;

import java.util.List;

public interface USSenatorService {


    public USSenatorDto createSenator(USSenatorDto senatorDto);
    public USSenatorDto updateSenator(String senatorId, USSenatorDto usSenatorDto);
    public USSenatorDto deleteSenator(String senatorId);
    public USSenatorEntity getUSSenatorEntity(String senatorId);



    //get US senator response
    public USSenatorResponse getUsSenatorResponse(USSenatorEntity usSenator);
    public List<USSenatorResponse> getUsSenatorResponse(List<USSenatorEntity> usSenatorEntities);

    //get all entities
    public List<USSenatorEntity> getUSSenatorEntities();




    //get us senator essentials
    public USSenatorEssentials getUSSenatorEssentials(USSenatorEntity usSenatorEntity);
    public List<USSenatorEssentials> getUSSenatorEssentials(List<USSenatorEntity> usSenatorEntities);

    //check if object is null
    public Boolean entityIsNull(USSenatorEntity usSenatorEntity);
    public Boolean entityIsNull(List<USSenatorEntity> usSenatorEntities);
}
