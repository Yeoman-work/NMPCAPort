package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.errormessages.StateRepErrorMessage;
import net.yeoman.nmpcaport.io.request.stateRep.StateRepDetailsRequest;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepEssentials;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepNestedResponse;
import net.yeoman.nmpcaport.services.StateRepService;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
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
    private final HouseDistrictServiceImpl nmHouseDistrictService;
    private final PoliticalPartyServiceImpl politicalPartyService;
    private final  CityServiceImpl cityService;
    private final  ZipCodeServiceImpl zipCodeService;
    private final Utils utils;

    public StateRepServiceImpl(StateRepRepository stateRepRepository,
                               HouseDistrictServiceImpl nmHouseDistrictService,
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
    public StateRepEntity findStateRepEntityById(String stateRepId) {

        return this.stateRepRepository.findByStateRepId(stateRepId);
    }

    @Override
    public StateRepEntity saveStateRepEntity(StateRepEntity stateRepEntity) {

        return this.stateRepRepository.save(stateRepEntity);
    }

    @Override
    public StateRepEntity getStateRepEntity(String publicId) {

        return this.stateRepRepository.findByStateRepId(publicId);
    }


    @Override
    public List<StateRepEntity> getAllEntities() {

        return this.stateRepRepository.findAll();

    }

    @Override
    public StateRepEntity generateUniqueId(StateRepEntity stateRepEntity) {

        if(this.entityIsNull(stateRepEntity))
            throw new StateRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        stateRepEntity.setStateRepId(this.utils.generateRandomID());

        while(this.stateRepRepository.existsByStateRepId(stateRepEntity.getStateRepId())){

            stateRepEntity.setStateRepId(this.utils.generateRandomID());
        }

        return stateRepEntity;
    }

    @Override
    public StateRepEntity requestToEntity(StateRepDetailsRequest stateRepDetailsRequest) {

        if(this.requestIsNull(stateRepDetailsRequest))
            throw new StateRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        StateRepEntity stateRepEntity = this.generateUniqueId(new StateRepEntity());

        if(stateRepDetailsRequest.getFirstName().length() < 3)
            throw new StateRepServiceException(StateRepErrorMessage.FIRST_NAME_LENGTH.getErrorMessage());


        if(stateRepDetailsRequest.getFirstName().length() > 50)
            throw new StateRepServiceException(StateRepErrorMessage.FIRST_NAME_LENGTH.getErrorMessage());


        stateRepEntity.setFirstName(stateRepDetailsRequest.getFirstName());

        if(stateRepDetailsRequest.getLastName().length() < 3)
            throw new StateRepServiceException(StateRepErrorMessage.LAST_NAME_LENGTH.getErrorMessage());

        if(stateRepDetailsRequest.getLastName().length() > 50)
            throw new StateRepServiceException(StateRepErrorMessage.LAST_NAME_LENGTH.getErrorMessage());


        stateRepEntity.setLastName(stateRepDetailsRequest.getLastName());

        if(stateRepDetailsRequest.getEmail().length() > 150)
            throw new StateRepServiceException(ErrorMessages.EMAIL_CHARACTER_LENGTH.getErrorMessage());


        stateRepEntity.setEmail(stateRepDetailsRequest.getEmail());

        if(stateRepDetailsRequest.getPicture().length() > 250)
            throw new StateRepServiceException(ErrorMessages.PICTURE_LENGTH.getErrorMessage());


        stateRepEntity.setPicture(stateRepDetailsRequest.getPicture());

        if(stateRepDetailsRequest.getCapitolRoom().length() <= 8)
            throw new StateRepServiceException(ErrorMessages.CAPITOL_ROOM.getErrorMessage());


        stateRepEntity.setCapitolRoom(stateRepDetailsRequest.getCapitolRoom());

        if(stateRepDetailsRequest.getStreetAddress() != null){

            if(stateRepDetailsRequest.getStreetAddress().length() < 5)
                throw new StateRepServiceException(ErrorMessages.ADDRESS_LENGTH.getErrorMessage());

            if(stateRepDetailsRequest.getStreetAddress().length() > 150)
                throw new StateRepServiceException(ErrorMessages.ADDRESS_LENGTH.getErrorMessage());

            stateRepEntity.setStreetAddress(stateRepDetailsRequest.getStreetAddress());
        }


        if(stateRepDetailsRequest.getCity() != null){

            CityEntity cityEntity = this.cityService.findCity(stateRepDetailsRequest.getCity());

            if(cityEntity == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

            stateRepEntity.setCityEntity(this.cityService.findCity(stateRepDetailsRequest.getCity()));
        }


        if(stateRepDetailsRequest.getZipCode() != null){

            ZipCodeEntity zipCode = this.zipCodeService.getZipCodeEntity(stateRepDetailsRequest.getZipCode());

            if(zipCode == null) throw new StateRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

            stateRepEntity.setZipCodeEntity(this.zipCodeService.getZipCodeEntity(stateRepDetailsRequest.getZipCode()));
        }


        if(stateRepDetailsRequest.getParty() != null){

            stateRepEntity.setPoliticalParty(
                    this.politicalPartyService.politicalPartyEntity(
                            stateRepDetailsRequest.getParty()
                    )
            );

        }

        if(stateRepDetailsRequest.getHouseDistrict() != null){

            stateRepEntity.setHouseDistrict(
                    this.nmHouseDistrictService.getHouseDistrict(
                            stateRepDetailsRequest.getHouseDistrict()
                    )
            );
        }


        return this.saveStateRepEntity(stateRepEntity);
    }

    @Override
    public StateRepEssentials getStateRepEssentials(StateRepEntity stateRepEntity) {

        if(this.entityIsNull(stateRepEntity))
            throw new StateSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        StateRepEssentials stateRepEssentials = new StateRepEssentials();

        stateRepEssentials.setFirstName(stateRepEntity.getFirstName());
        stateRepEssentials.setLastName(stateRepEntity.getLastName());
        stateRepEssentials.setStateRepId(stateRepEntity.getStateRepId());
        stateRepEssentials.setPicture(stateRepEntity.getPicture());
        stateRepEssentials.setEmail(stateRepEntity.getEmail());
        stateRepEssentials.setNmHouseDistrictEssentialResponse(
                this.nmHouseDistrictService.entityToEssentials(stateRepEntity.getHouseDistrict()
                )
        );
        stateRepEssentials.setPoliticalPartyEssentials(
                this.politicalPartyService.getPoliticalPartyEssentials(stateRepEntity.getPoliticalParty()
                )
        );

        return stateRepEssentials;
    }

    @Override
    public List<StateRepEssentials> getStateRepEssentials(List<StateRepEntity> stateRepEntities) {

        if(this.entityIsNull(stateRepEntities))
            throw new StateSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<StateRepEssentials> returnValue = new ArrayList<>();

        for(StateRepEntity stateRepEntity: stateRepEntities){

            returnValue.add(this.getStateRepEssentials(stateRepEntity));
        }

        return returnValue;
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
                this.nmHouseDistrictService.entityToEssentials(stateRepEntity.getHouseDistrict()
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
        return null;
    }


    @Override
    public Boolean entityIsNull(StateRepEntity stateRepEntity) {
        return stateRepEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<StateRepEntity> stateRepEntities) {
        return  stateRepEntities == null;
    }

    @Override
    public Boolean requestIsNull(StateRepDetailsRequest stateRepDetailsRequest) {

        return stateRepDetailsRequest == null;
    }
}
