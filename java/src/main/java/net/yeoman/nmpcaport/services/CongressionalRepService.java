package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.CongressionalRepEntity;
import net.yeoman.nmpcaport.io.response.CongressionalRepResponse.CongressionalRepEssentials;
import net.yeoman.nmpcaport.shared.dto.CongressionalRepDto;

import java.util.List;

public interface CongressionalRepService {

    public CongressionalRepDto getCongressionalRep(String congressionalRepId);
    public CongressionalRepDto createCongressionalRep(CongressionalRepDto congressionalRepDto);
    public CongressionalRepDto updateCongressionalRep(String RepId, CongressionalRepDto congressionalRepDto);
    public CongressionalRepDto deleteCongressionalRep(String repId);
    public CongressionalRepEntity getCongressionalRepEntity(String congressionalRepId);

    //get congressional rep entities
    public List<CongressionalRepEntity> getAllCongressionalRepEntities();

    //congressional rep dashboard
    public CongressionalRepEssentials getAllCongressionalRepEssentials(CongressionalRepEntity congressionalRepEntity);
    public List<CongressionalRepEssentials> getAllCongressionalRepEssentials(List<CongressionalRepEntity> congressionalRepEntities);


    //check if object is null
    public Boolean entityIsNull(CongressionalRepEntity congressionalRepEntity);
    public Boolean entityIsNull(List<CongressionalRepEntity> congressionalRepEntity);



}
