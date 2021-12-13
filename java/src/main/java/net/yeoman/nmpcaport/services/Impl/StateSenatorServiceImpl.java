package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.entities.StateSenatorEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.StateSenatorServiceException;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.repositories.StateSenatorRepository;
import net.yeoman.nmpcaport.services.StateSenatorService;
import net.yeoman.nmpcaport.shared.dto.StateSenatorDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateSenatorServiceImpl implements StateSenatorService {

    @Autowired
    private StateSenatorRepository stateSenatorRepository;

    @Autowired
    private SenateDistrictServiceImpl senateDistrictService;

    @Autowired
    private Utils utils;


    @Override
    public StateSenatorDto getStateSenator(String senatorId) {
        return null;
    }

    @Override
    public StateSenatorDto createSenator(StateSenatorDto stateSenatorDto) {

        if(this.stateSenatorRepository.existsByEmail(stateSenatorDto.getEmail())) throw new StateSenatorServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        StateSenatorEntity stateSenatorEntity = new ModelMapper().map(stateSenatorDto, StateSenatorEntity.class);

        if(stateSenatorDto.getSenateDistrictIdentifier() != null){
            SenateDistrictEntity senateDistrictEntity = this.senateDistrictService.findSenateDistrictEntity(stateSenatorDto.getSenateDistrictIdentifier());
            stateSenatorEntity.setSenateDistrict(senateDistrictEntity);
        }

        StateSenatorEntity storedStateSenatorEntity = this.stateSenatorRepository.save(stateSenatorEntity);


        StateSenatorDto newSenateDto =  new ModelMapper().map(storedStateSenatorEntity, StateSenatorDto.class);

        if(newSenateDto.getSenateDistrict() != null){

            newSenateDto.setSenateDistrictResponse(new ModelMapper().map(newSenateDto.getSenateDistrict(), SenateDistrictResponseModel.class));
        }

        return newSenateDto;
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
}
