package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.USSenatorEntity;
import net.yeoman.nmpcaport.shared.dto.USSenatorDto;

public interface USSenatorService {

    public USSenatorDto getSenator(String senatorId);
    public USSenatorDto createSenator(USSenatorDto senatorDto);
    public USSenatorDto updateSenator(String senatorId, USSenatorDto usSenatorDto);
    public USSenatorDto deleteSenator(String senatorId);
    public USSenatorEntity getUSSenatorEntity(String senatorId);
}
