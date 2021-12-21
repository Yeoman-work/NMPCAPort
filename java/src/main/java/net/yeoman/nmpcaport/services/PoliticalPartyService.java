package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.PoliticalPartyEntity;
import net.yeoman.nmpcaport.shared.dto.PoliticalPartyDto;

import java.util.List;

public interface PoliticalPartyService {

    public PoliticalPartyDto getPoliticalParty(String partyId);
    public PoliticalPartyDto createPoliticalParty(PoliticalPartyDto politicalPartyDto);
    public PoliticalPartyDto updatePoliticalParty(String partyId, PoliticalPartyDto partyDto);
    public List<PoliticalPartyDto> getAllPoliticalParities();
    public List<PoliticalPartyDto> createPoliticalParty(List<PoliticalPartyDto> politicalPartyDtoList);
    public PoliticalPartyDto deletePoliticalParty(String partyId);
    public PoliticalPartyEntity politicalPartyEntity(String partyId);
}
