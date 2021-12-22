package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.repositories.StateRepRepository;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.services.StateRepService;
import net.yeoman.nmpcaport.shared.dto.StateRepDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateRepServiceImpl implements StateRepService {

    @Autowired
    private StateRepRepository stateRepRepository;

    @Autowired
    private NMHouseDistrictServiceImpl nmHouseDistrictService;

    @Autowired
    private PoliticalPartyServiceImpl politicalPartyService;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CountyServiceImpl countyService;

    @Autowired
    private ZipCodeServiceImpl zipCodeService;



    @Autowired
    private Utils utils;

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
        if(stateRepDto.getNmHouseDistrict() != null){

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
        if(stateRepDto.getParty() != null){

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
        if(stateRepDto.getCity() != null){

            //get city entity
            CityEntity cityEntity = this.cityService.findCity(stateRepDto.getCity());

            // if city is null throw error
            if(cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set city response on dto
            stateRepDto.setCityResponse(new ModelMapper().map(cityEntity, CityResponse.class));

            //set city entity on state rep entity
            stateRepEntity.setCityEntity(cityEntity);
        }

        if(stateRepDto.getZipCode() != null){

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

                stateRepDto.setCityResponse(new ModelMapper().map(stateRepDto.getCityEntity(), CityResponse.class));
            }

            if(stateRepDto.getZipCodeEntity() != null){

                stateRepDto.setZipCodeResponse(new ModelMapper().map(stateRepDto.getZipCodeEntity(), ZipCodeResponse.class));

            }

            if(stateRepDto.getPoliticalParty() != null){

                stateRepDto.setPoliticalPartyResponse(new ModelMapper().map(stateRepDto.getPoliticalParty(), PoliticalPartyResponse.class));

            }

            if(stateRepDto.getNmHouseDistrictEntity() != null){

                stateRepDto.setNmHouseDistrictResponse(new ModelMapper().map(stateRepDto.getNmHouseDistrictEntity(), NMHouseDistrictNestedResponse.class));
            }


            returnValue.add(stateRepDto);
        }

        return returnValue;
    }
}
