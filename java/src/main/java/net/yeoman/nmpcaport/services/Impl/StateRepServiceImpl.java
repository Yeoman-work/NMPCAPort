package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.io.response.stateRep.StateRepEssentials;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepNestedResponse;
import net.yeoman.nmpcaport.services.StateRepService;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.repositories.StateRepRepository;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.shared.dto.StateRepDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateRepServiceImpl implements StateRepService {


    private final StateRepRepository stateRepRepository;
    private final NMHouseDistrictServiceImpl nmHouseDistrictService;
    private final PoliticalPartyServiceImpl politicalPartyService;
    private final  CityServiceImpl cityService;
    private final  ZipCodeServiceImpl zipCodeService;
    private final Utils utils;

    public StateRepServiceImpl(StateRepRepository stateRepRepository,
                               NMHouseDistrictServiceImpl nmHouseDistrictService,
                               PoliticalPartyServiceImpl politicalPartyService,
                               CityServiceImpl cityService,
                               ZipCodeServiceImpl zipCodeService,
                               Utils utils
    ){

        this.stateRepRepository = stateRepRepository;
        this.nmHouseDistrictService = nmHouseDistrictService;
        this.politicalPartyService = politicalPartyService;
        this.cityService = cityService;
        this.zipCodeService = zipCodeService;
        this.utils = utils;
    }




    @Override
    public StateRepDto getStateRep(String stateRepId) {

       StateRepDto stateRepDto = new ModelMapper().map(this.stateRepRepository.findByStateRepId(stateRepId), StateRepDto.class);

       if(stateRepDto.getNmHouseDistrict() != null){

           stateRepDto.setNmHouseDistrictResponse(new ModelMapper().map(stateRepDto.getNmHouseDistrict(), NMHouseDistrictNestedResponse.class));
       }

       return stateRepDto;
    }

    @Override
    public StateRepDto createStateRep(StateRepDto stateRepDto) {

        StateRepEntity stateRepEntity = new ModelMapper().map(stateRepDto, StateRepEntity.class);

        stateRepEntity.setStateRepId(utils.generateRandomID());

        //check for NMHouse district
        if(!stateRepDto.getNmHouseDistrict().isBlank()){

            //get NM House district
            NMHouseDistrictEntity nmHouseDistrictEntity = this.nmHouseDistrictService.findNMHouseDistrictEntity(stateRepDto.getNmHouseDistrict());

            //if null throw error
            if(nmHouseDistrictEntity == null) throw new NMHouseDistrictServiceException(stateRepDto.getNmHouseDistrict() + ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


            //add district response to the dto
            stateRepDto.setNmHouseDistrictResponse(new ModelMapper().map(nmHouseDistrictEntity, NMHouseDistrictNestedResponse.class));


            //add district entity to state rep entity
            stateRepEntity.setNmHouseDistrict(nmHouseDistrictEntity);
        }

        //check if party id exist
        if(!stateRepDto.getParty().isBlank()){

            //get political party
            PoliticalPartyEntity politicalPartyEntity = this.politicalPartyService.politicalPartyEntity(stateRepDto.getParty());

            //if party is null throw error
            if(politicalPartyEntity == null) throw new PoliticalPartyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set political party response to DTO
            stateRepDto.setPoliticalPartyResponse(new ModelMapper().map(politicalPartyEntity, PoliticalPartyResponse.class));

            //set political party entity tp state rep entity
            stateRepEntity.setPoliticalParty(politicalPartyEntity);

        }

        //if city id exist
        if(!stateRepDto.getCity().isBlank()){

            //get city entity
            CityEntity cityEntity = this.cityService.findCity(stateRepDto.getCity());

            // if city is null throw error
            if(cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set city response on dto
            stateRepDto.setCityResponse(new ModelMapper().map(cityEntity, CityResponse.class));

            //set city entity on state rep entity
            stateRepEntity.setCityEntity(cityEntity);
        }

        if(!stateRepDto.getZipCode().isBlank()){

            //get zip code
            ZipCodeEntity zipCodeEntity = this.zipCodeService.getZipCodeEntity(stateRepDto.getZipCode());

            //check if zip code is null
            if(zipCodeEntity == null) throw new ZipCodeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            // add zipCode response
            stateRepDto.setZipCodeResponse(new ModelMapper().map(zipCodeEntity, ZipCodeResponse.class));

            //zipCode response
            stateRepEntity.setZipCodeEntity(zipCodeEntity);

        }

        StateRepEntity savedStateRep = this.stateRepRepository.save(stateRepEntity);


        return stateRepDto;
    }

    @Override
    public StateRepDto updatedStateRep(String stateRepId, StateRepDto stateRepDto) {
        return null;
    }

    @Override
    public StateRepDto deleteStateRep(String stateRepId) {
        return null;
    }

    @Override
    public StateRepEntity findStateRepEntityById(String stateRepId) {
        return null;
    }

    @Override
    public List<StateRepDto> findAllStateReps() {
        List<StateRepDto> returnValue = new ArrayList<>();

        List<StateRepEntity> allStateReps = this.stateRepRepository.findAll();

        for(StateRepEntity rep: allStateReps){


            StateRepDto stateRepDto = new ModelMapper().map(rep, StateRepDto.class);

            if(stateRepDto.getCityEntity() != null){

                stateRepDto.setCityResponse(
                        this.cityService.dtoToResponse(
                                this.cityService.entityToDto(
                                        stateRepDto.getCityEntity()
                                )
                        )
                );
            }

            if(stateRepDto.getZipCodeEntity() != null){

                stateRepDto.setZipCodeResponse(
                        this.zipCodeService.dtoToResponse(
                                this.zipCodeService.entityToDto(
                                        stateRepDto.getZipCodeEntity()
                                )
                        )
                );

            }

            if(stateRepDto.getPoliticalParty() != null){

                stateRepDto.setPoliticalPartyResponse(this.politicalPartyService.dtoToResponse(this.politicalPartyService.entityToDto(stateRepDto.getPoliticalParty())));

            }

            if(stateRepDto.getNmHouseDistrictEntity() != null){

                stateRepDto.setNmHouseDistrictResponse(new ModelMapper().map(stateRepDto.getNmHouseDistrictEntity(), NMHouseDistrictNestedResponse.class));
            }


            returnValue.add(stateRepDto);
        }

        return returnValue;
    }

    @Override
    public List<StateRepEntity> getAllEntities() {
        return this.stateRepRepository.findAll();
    }

    @Override
    public StateRepEssentials getStateRepEssentials(StateRepEntity stateRepEntity) {
        return null;
    }

    @Override
    public List<StateRepEssentials> getStateRepEssentials(List<StateRepEntity> stateRepEntities) {
        return null;
    }

    @Override
    public StateRepNestedResponse getStateRepNestedReps(StateRepEntity stateRepEntity) {

        if(this.entityIsNull(stateRepEntity))
            throw new StateRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        StateRepNestedResponse stateRepNestedResponse = new StateRepNestedResponse();

        stateRepNestedResponse.setFirstName(stateRepEntity.getFirstName());
        stateRepNestedResponse.setLastName(stateRepEntity.getLastName());
        stateRepNestedResponse.setEmail(stateRepEntity.getEmail());
        stateRepNestedResponse.setPicture(stateRepEntity.getPicture());
        stateRepNestedResponse.setCapitolRoom(stateRepEntity.getCapitolRoom());
        stateRepNestedResponse.setStreetAddress(stateRepEntity.getStreetAddress());
        stateRepNestedResponse.setStateRepId(stateRepEntity.getStateRepId());
        stateRepNestedResponse.setCity(stateRepEntity.getCityEntity().getName());
        stateRepNestedResponse.setZipCode(stateRepEntity.getZipCodeEntity().getName());
        stateRepNestedResponse.setNmHouseDistrictEssentialResponse(
                this.nmHouseDistrictService.entityToEssentials(stateRepEntity.getNmHouseDistrict()
                )
        );

        stateRepNestedResponse.setPoliticalPartyEssentials(
                this.politicalPartyService.getPoliticalPartyEssentials(
                        stateRepEntity.getPoliticalParty()
                )
        );

        return stateRepNestedResponse;
    }

    @Override
    public List<StateRepNestedResponse> getStateRepNestedReps(List<StateRepEntity> stateRepEntities) {

        if(this.entityIsNull(stateRepEntities))
            throw new StateRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<StateRepNestedResponse> returnValue = new ArrayList<>();

        for(StateRepEntity stateRepEntity: stateRepEntities){

            returnValue.add(this.getStateRepNestedReps(stateRepEntity));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(StateRepEntity stateRepEntity) {
        return stateRepEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<StateRepEntity> stateRepEntities) {
        return  stateRepEntities == null;
    }
}
