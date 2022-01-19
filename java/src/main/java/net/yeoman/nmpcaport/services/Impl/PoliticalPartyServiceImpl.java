package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.PoliticalPartyServiceException;
import net.yeoman.nmpcaport.io.request.politicalParty.PoliticalPartyDetailsRequest;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.services.PoliticalPartyService;
import net.yeoman.nmpcaport.entities.PoliticalPartyEntity;
import net.yeoman.nmpcaport.io.repositories.PoliticalPartyRepository;
import net.yeoman.nmpcaport.shared.dto.PoliticalPartyDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {

    @Autowired
    private PoliticalPartyRepository partyRepository;

    @Autowired
    private Utils utils;

    @Override
    public PoliticalPartyDto getPoliticalParty(String partyId) {
        return new ModelMapper().map(this.partyRepository.findByPartyId(partyId), PoliticalPartyDto.class);
    }

    @Override
    public PoliticalPartyDto createPoliticalParty(PoliticalPartyDto politicalPartyDto) {

        PoliticalPartyEntity politicalParty = new ModelMapper().map(politicalPartyDto, PoliticalPartyEntity.class);

        politicalParty.setPartyId(utils.generateRandomID());

        while(this.partyRepository.existsByPartyId(politicalParty.getPartyId())){

            politicalParty.setPartyId(utils.generateRandomID());
        }

        PoliticalPartyEntity storedPoliticalParty = this.partyRepository.save(politicalParty);

        return new ModelMapper().map(storedPoliticalParty, PoliticalPartyDto.class);
    }

    @Override
    public PoliticalPartyDto updatePoliticalParty(String partyId, PoliticalPartyDto partyDto) {
        return null;
    }

    @Override
    public List<PoliticalPartyDto> getAllPoliticalParities() {
        List<PoliticalPartyDto> returnValue = new ArrayList<>();
        List<PoliticalPartyEntity> politicalPartyEntityList = this.partyRepository.findAll();

        for(PoliticalPartyEntity party: politicalPartyEntityList){

            returnValue.add(new ModelMapper().map(party, PoliticalPartyDto.class));
        }

        return returnValue;
    }

    @Override
    public List<PoliticalPartyDto> createPoliticalParty(List<PoliticalPartyDto> politicalPartyDtoList) {
        List<PoliticalPartyDto> returnValue = new ArrayList<>();

        for(PoliticalPartyDto party: politicalPartyDtoList){

            PoliticalPartyEntity politicalPartyEntity = new ModelMapper().map(party, PoliticalPartyEntity.class);

            politicalPartyEntity.setPartyId(utils.generateRandomID());

            PoliticalPartyEntity savedPoliticalParty = this.partyRepository.save(politicalPartyEntity);

            returnValue.add(new ModelMapper().map(savedPoliticalParty, PoliticalPartyDto.class));
        }

        return returnValue;
    }

    @Override
    public PoliticalPartyDto deletePoliticalParty(String partyId) {
        return null;
    }

    @Override
    public PoliticalPartyEntity politicalPartyEntity(String partyId) {

        return this.partyRepository.findByPartyId(partyId);
    }

    @Override
    public PoliticalPartyEntity generateUniqueId(PoliticalPartyEntity politicalPartyEntity) {

        if(this.entityIsNull(politicalPartyEntity))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        politicalPartyEntity.setPartyId(this.utils.generateRandomID());

        while(this.partyRepository.existsByPartyId(politicalPartyEntity.getPartyId())){

            politicalPartyEntity.setPartyId(this.utils.generateRandomID());
        }

        return politicalPartyEntity;
    }

    @Override
    public PoliticalPartyEntity savePoliticalEntity(PoliticalPartyEntity politicalPartyEntity) {

        if(this.entityIsNull(politicalPartyEntity))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.partyRepository.save(politicalPartyEntity);
    }

    @Override
    public PoliticalPartyEntity dtoToEntity(PoliticalPartyDto politicalPartyDto) {

        if(this.dtoIsNull(politicalPartyDto))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        PoliticalPartyEntity politicalPartyEntity = this.generateUniqueId(
                this.utils.objectMapper().map(
                        politicalPartyDto, PoliticalPartyEntity.class)
        );

        return this.savePoliticalEntity(politicalPartyEntity);
    }

    @Override
    public List<PoliticalPartyEntity> dtoToEntity(List<PoliticalPartyDto> politicalPartyDtoList) {

        if(this.dtoIsNull(politicalPartyDtoList))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<PoliticalPartyEntity> returnValue = new ArrayList<>();

        for(PoliticalPartyDto politicalPartyDto: politicalPartyDtoList){

            returnValue.add(this.dtoToEntity(politicalPartyDto));
        }

        return returnValue;
    }

    @Override
    public PoliticalPartyResponse dtoToResponse(PoliticalPartyDto politicalPartyDto) {

        if(this.dtoIsNull(politicalPartyDto))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(politicalPartyDto, PoliticalPartyResponse.class);
    }

    @Override
    public List<PoliticalPartyResponse> dtoToResponse(List<PoliticalPartyDto> politicalPartyDtoList) {

        if(this.dtoIsNull(politicalPartyDtoList))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<PoliticalPartyResponse> returnValue = new ArrayList<>();

        for(PoliticalPartyDto partyDto: politicalPartyDtoList){

            returnValue.add(this.dtoToResponse(partyDto));
        }

        return returnValue;
    }

    @Override
    public PoliticalPartyDto entityToDto(PoliticalPartyEntity politicalPartyEntity) {

        if(this.entityIsNull(politicalPartyEntity))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(politicalPartyEntity, PoliticalPartyDto.class);
    }

    @Override
    public List<PoliticalPartyDto> entityToDto(List<PoliticalPartyEntity> politicalPartyEntityList) {

        if(this.entityIsNull(politicalPartyEntityList))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        List<PoliticalPartyDto> returnValue = new ArrayList<>();

        for(PoliticalPartyEntity party: politicalPartyEntityList){

            returnValue.add(this.entityToDto(party));
        }

        return returnValue;
    }

    @Override
    public PoliticalPartyDto requestToDto(PoliticalPartyDetailsRequest politicalPartyDetailsRequest) {

        if(this.requestIsNull(politicalPartyDetailsRequest))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(politicalPartyDetailsRequest, PoliticalPartyDto.class);
    }

    @Override
    public List<PoliticalPartyDto> requestToDto(List<PoliticalPartyDetailsRequest> politicalPartyDetailsRequestList) {

        if(this.requestIsNull(politicalPartyDetailsRequestList))
            throw new PoliticalPartyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<PoliticalPartyDto> returnValue = new ArrayList<>();

        for(PoliticalPartyDetailsRequest politicalPartyDetailsRequest: politicalPartyDetailsRequestList){

            returnValue.add(this.requestToDto(politicalPartyDetailsRequest));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(PoliticalPartyEntity politicalPartyEntity) {
        return politicalPartyEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<PoliticalPartyEntity> politicalPartyEntityList) {
        return politicalPartyEntityList == null;
    }

    @Override
    public Boolean dtoIsNull(PoliticalPartyDto politicalPartyDto) {
        return politicalPartyDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<PoliticalPartyDto> politicalPartyDtoList) {
        return politicalPartyDtoList == null;
    }

    @Override
    public Boolean requestIsNull(PoliticalPartyDetailsRequest politicalPartyDetailsRequest) {
        return politicalPartyDetailsRequest == null;
    }

    @Override
    public Boolean requestIsNull(List<PoliticalPartyDetailsRequest> politicalPartyDetailsRequests) {
        return politicalPartyDetailsRequests == null;
    }

}
