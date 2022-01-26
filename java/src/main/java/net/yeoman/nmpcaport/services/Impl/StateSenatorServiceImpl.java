package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorEssentials;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.services.StateSenatorService;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.repositories.StateSenatorRepository;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.shared.dto.StateSenatorDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateSenatorServiceImpl implements StateSenatorService {



    private final StateSenatorRepository stateSenatorRepository;

    private final SenateDistrictServiceImpl senateDistrictService;

    private final CityServiceImpl cityService;

    private final ZipCodeServiceImpl zipCodeService;

    private final PoliticalPartyServiceImpl politicalPartyService;

    private final Utils utils;

    public StateSenatorServiceImpl(StateSenatorRepository stateSenatorRepository,
                                   SenateDistrictServiceImpl senateDistrictService,
                                   CityServiceImpl cityService,
                                   ZipCodeServiceImpl zipCodeService,
                                   PoliticalPartyServiceImpl politicalPartyService,
                                   Utils utils
                                   ){
        this.stateSenatorRepository = stateSenatorRepository;
        this.senateDistrictService = senateDistrictService;
        this.cityService = cityService;
        this.zipCodeService = zipCodeService;
        this.politicalPartyService = politicalPartyService;
        this.utils = utils;
    }


    @Override
    public StateSenatorDto getStateSenator(String senatorId) {

        return null;
    }

    @Override
    public StateSenatorDto createSenator(StateSenatorDto stateSenatorDto) {

       StateSenatorEntity stateSenatorEntity = new ModelMapper().map(stateSenatorDto, StateSenatorEntity.class);

       stateSenatorEntity.setStateSenatorId(utils.generateRandomID());

       if(!stateSenatorDto.getSenateDistrict().isBlank()){

           SenateDistrictEntity senateDistrict = this.senateDistrictService.findSenateDistrictEntity(stateSenatorDto.getSenateDistrict());

           if(senateDistrict == null) throw new SenateDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

           stateSenatorEntity.setSenateDistrictEntity(senateDistrict);
       }

       if(!stateSenatorDto.getParty().isBlank()){

           PoliticalPartyEntity politicalParty = this.politicalPartyService.politicalPartyEntity(stateSenatorDto.getParty());

           if(politicalParty == null) throw new PoliticalPartyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

           stateSenatorEntity.setPoliticalPartyEntity(politicalParty);
       }

       if(!stateSenatorDto.getCity().isBlank()){

           CityEntity cityEntity = this.cityService.findCity(stateSenatorDto.getCity());

           if(cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

           stateSenatorEntity.setCityEntity(cityEntity);
       }

       if(!stateSenatorDto.getZipCode().isBlank()){

           ZipCodeEntity zipCodeEntity = this.zipCodeService.getZipCodeEntity(stateSenatorDto.getZipCode());

           if(zipCodeEntity == null) throw new ZipCodeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

           stateSenatorEntity.setZipCodeEntity(zipCodeEntity);

       }

       StateSenatorEntity saveStateSenator = this.stateSenatorRepository.save(stateSenatorEntity);

        return new ModelMapper().map(saveStateSenator, StateSenatorDto.class);
    }



    @Override
    public StateSenatorDto updateStateSenator(String senatorId, StateSenatorDto stateSenatorDto) {
        return null;
    }

    @Override
    public StateSenatorDto deleteStateSenator(String senatorId) {
        return null;
    }

    @Override
    public StateSenatorEntity getStateSenatorEntity(String senatorId) {

        return this.stateSenatorRepository.findByStateSenatorId(senatorId);
    }

    @Override
    public List<StateSenatorDto> getAllStateSenators() {
        List<StateSenatorDto> returnValue = new ArrayList<>();

        List<StateSenatorEntity> stateSenatorEntities = this.stateSenatorRepository.findAll();

        for(StateSenatorEntity stateSenator: stateSenatorEntities){

            StateSenatorDto stateSenatorDto = new ModelMapper().map(stateSenator, StateSenatorDto.class);

            if(stateSenatorDto.getSenateDistrictEntity() != null){
                stateSenatorDto.setSenateDistrictResponse(new ModelMapper().map(stateSenatorDto.getSenateDistrictEntity(), SenateDistrictResponseModel.class));
            }

            if(stateSenatorDto.getCityEntity() != null){

                stateSenatorDto.setCityResponse(new ModelMapper().map(stateSenatorDto.getCityEntity(), CityResponse.class));
            }

            if(stateSenatorDto.getZipCodeEntity() != null){

                stateSenatorDto.setZipCodeResponse(new ModelMapper().map(stateSenatorDto.getZipCodeEntity(), ZipCodeResponse.class));
            }

            if(stateSenatorDto.getPoliticalPartyEntity() != null){

                stateSenatorDto.setPoliticalPartyResponse(new ModelMapper().map(stateSenator.getPoliticalPartyEntity(), PoliticalPartyResponse.class));
            }

            returnValue.add(stateSenatorDto);

        }

        return returnValue;
    }

    @Override
    public List<StateSenatorEntity> getStateSenatorEntities() {
        return this.stateSenatorRepository.findAll();
    }

    @Override
    public StateSenatorEssentials getStateSenatorEssentials(StateSenatorEntity stateSenatorEntity) {

        if(this.entityIsNull(stateSenatorEntity))
            throw new StateSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        StateSenatorEssentials stateSenatorEssentials = new StateSenatorEssentials();

        stateSenatorEssentials.setStateSenatorId(stateSenatorEntity.getStateSenatorId());
        stateSenatorEssentials.setEmail(stateSenatorEntity.getEmail());

        stateSenatorEssentials.setPoliticalPartyEssentials(
                this.politicalPartyService.getPoliticalPartyEssentials(
                        stateSenatorEntity.getPoliticalPartyEntity()
                )
        );

        stateSenatorEssentials.setSenateDistrictEssentialResponse(
                this.senateDistrictService.entityToEssentials(
                        stateSenatorEntity.getSenateDistrictEntity()
                )
        );

        stateSenatorEssentials.setPicture(stateSenatorEntity.getPicture());
        stateSenatorEssentials.setLastName(stateSenatorEntity.getLastName());
        stateSenatorEssentials.setFirstName(stateSenatorEntity.getFirstName());

        return stateSenatorEssentials;
    }

    @Override
    public List<StateSenatorEssentials> getStateSenatorEssentials(List<StateSenatorEntity> stateSenatorEntities) {

        if(this.entityIsNull(stateSenatorEntities))
            throw new StateSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<StateSenatorEssentials> returnValue = new ArrayList<>();

        for(StateSenatorEntity stateSenatorEntity: stateSenatorEntities){

            returnValue.add(this.getStateSenatorEssentials(stateSenatorEntity));
        }

        return returnValue;
    }

    @Override
    public StateSenatorNestedResponse stateSenatorDashboardData(StateSenatorEntity stateSenatorEntity) {

        if(this.entityIsNull(stateSenatorEntity))
            throw new StateSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        StateSenatorNestedResponse stateSenatorNestedResponse = new StateSenatorNestedResponse();

        stateSenatorNestedResponse.setFirstName(stateSenatorEntity.getFirstName());
        stateSenatorNestedResponse.setLastName(stateSenatorEntity.getLastName());
        stateSenatorNestedResponse.setStateSenatorId(stateSenatorEntity.getStateSenatorId());
        stateSenatorNestedResponse.setStreetAddress(stateSenatorEntity.getStreetAddress());
        stateSenatorNestedResponse.setEmail(stateSenatorEntity.getEmail());
        stateSenatorNestedResponse.setCapitolRoom(stateSenatorEntity.getCapitolRoom());
        stateSenatorNestedResponse.setPicture(stateSenatorEntity.getPicture());
        stateSenatorNestedResponse.setCity(stateSenatorEntity.getCityEntity().getName());
        stateSenatorNestedResponse.setZipCode(stateSenatorEntity.getZipCodeEntity().getName());
        stateSenatorNestedResponse.setPoliticalPartyEssentials(
                this.politicalPartyService.getPoliticalPartyEssentials(
                        stateSenatorEntity.getPoliticalPartyEntity()
                )
        );

        stateSenatorNestedResponse.setSenateDistrictEssentialResponse(
                this.senateDistrictService.entityToEssentials(
                        stateSenatorEntity.getSenateDistrictEntity())
        );

        return stateSenatorNestedResponse;
    }

    @Override
    public List<StateSenatorNestedResponse> stateSenatorDashboardData(List<StateSenatorEntity> stateSenatorEntities) {

        if(this.entityIsNull(stateSenatorEntities))
            throw new StateSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<StateSenatorNestedResponse> returnValue = new ArrayList<>();

        for(StateSenatorEntity stateSenator: stateSenatorEntities){

            returnValue.add(this.stateSenatorDashboardData(stateSenator));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(StateSenatorEntity stateSenatorEntity) {
        return stateSenatorEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<StateSenatorEntity> stateSenatorEntities) {
        return stateSenatorEntities == null;
    }
}
