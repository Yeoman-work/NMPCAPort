package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.CongressionalRepEntity;
import net.yeoman.nmpcaport.shared.dto.CongressionalRepDto;

public interface CongressionalRepService {

    public CongressionalRepDto getCongressionalRep(String congressionalRepId);
    public CongressionalRepDto createCongressionalRep(CongressionalRepDto congressionalRepDto);
    public CongressionalRepDto updateCongressionalRep(String RepId, CongressionalRepDto congressionalRepDto);
    public CongressionalRepDto deleteCongressionalRep(String repId);
    public CongressionalRepEntity getCongressionalRepEntity(String congressionalRepId);


}
