package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.entities.PoliticalPartyEntity;
import net.yeoman.nmpcaport.io.request.politicalParty.PoliticalPartyDetailsRequest;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyEssentials;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
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

    //generate entity with unique id
    public PoliticalPartyEntity generateUniqueId(PoliticalPartyEntity politicalPartyEntity);

    //political party essentials
    public PoliticalPartyEssentials getPoliticalPartyEssentials(PoliticalPartyEntity politicalPartyEntity);

    //save political party
    public PoliticalPartyEntity savePoliticalEntity(PoliticalPartyEntity politicalPartyEntity);

    //convert dto
    public PoliticalPartyEntity dtoToEntity(PoliticalPartyDto politicalPartyDto);
    public List<PoliticalPartyEntity> dtoToEntity(List<PoliticalPartyDto> politicalPartyDtoList);
    public PoliticalPartyResponse dtoToResponse(PoliticalPartyDto politicalPartyDto);
    public List<PoliticalPartyResponse> dtoToResponse(List<PoliticalPartyDto> politicalPartyDtoList);

    //convert entity
    public PoliticalPartyDto entityToDto(PoliticalPartyEntity politicalPartyEntity);
    public List<PoliticalPartyDto> entityToDto(List<PoliticalPartyEntity> politicalParty);

    //convert request
    public PoliticalPartyDto requestToDto(PoliticalPartyDetailsRequest politicalPartyDetailsRequest);
    public List<PoliticalPartyDto> requestToDto(List<PoliticalPartyDetailsRequest> politicalPartyDetailsRequestList);


    public Boolean entityIsNull(PoliticalPartyEntity politicalPartyEntity);
    public Boolean entityIsNull(List<PoliticalPartyEntity> politicalPartyEntityList);
    public Boolean dtoIsNull(PoliticalPartyDto politicalPartyDto);
    public Boolean dtoIsNull(List<PoliticalPartyDto> politicalPartyDtoList);
    public Boolean requestIsNull(PoliticalPartyDetailsRequest politicalPartyDetailsRequest);
    public Boolean requestIsNull(List<PoliticalPartyDetailsRequest> politicalPartyDetailsRequests);
}
