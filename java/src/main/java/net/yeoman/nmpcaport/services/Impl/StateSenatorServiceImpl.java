package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.repositories.CityRepository;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.repositories.StateSenatorRepository;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.services.StateSenatorService;
import net.yeoman.nmpcaport.shared.dto.StateSenatorDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateSenatorServiceImpl implements StateSenatorService {

    @Autowired
    private StateSenatorRepository stateSenatorRepository;

    @Autowired
    private SenateDistrictServiceImpl senateDistrictService;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CountyServiceImpl countyService;

    @Autowired
    private ZipCodeServiceImpl zipCodeService;

    @Autowired
    private PoliticalPartyServiceImpl politicalPartyService;

    @Autowired
    private Utils utils;


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
}
