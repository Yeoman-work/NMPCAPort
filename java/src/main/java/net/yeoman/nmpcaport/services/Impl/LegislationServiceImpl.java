package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.LegislationEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.errormessages.LegislationErrorMessages;
import net.yeoman.nmpcaport.exception.LegislationServiceException;
import net.yeoman.nmpcaport.io.repositories.LegislationRepository;
import net.yeoman.nmpcaport.services.LegislationService;
import net.yeoman.nmpcaport.shared.dto.LegislationDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LegislationServiceImpl implements LegislationService {

    @Autowired
    private LegislationRepository legislationRepository;

    @Autowired
    private Utils utils;

    @Override
    public LegislationDto getLegislation(String legislationId) {
        return null;
    }

    @Override
    public LegislationDto createLegislation(LegislationDto legislationDto) {

        LegislationEntity legislation = new ModelMapper().map(legislationDto, LegislationEntity.class);

        if(legislation.getName().length() > 25) throw new LegislationServiceException(LegislationErrorMessages.LEGISLATION_NAME_LENGTH.getLegislationErrorMessage());

        legislation.setLegislationId(utils.generateRandomID());

        while(this.legislationRepository.existsByLegislationId(legislation.getLegislationId())){

            legislation.setLegislationId(utils.generateRandomID());
        }

        LegislationEntity storedLegislation = this.legislationRepository.save(legislation);

        return new ModelMapper().map(storedLegislation, LegislationDto.class);
    }

    @Override
    public LegislationDto updateLegislation(String legislationId, LegislationDto legislationDto) {
        return null;
    }

    @Override
    public LegislationDto deleteLegislation(String legislationId) {
        return null;
    }

    @Override
    public LegislationEntity getLegislativeEntity(String legislationId) {
        return null;
    }

    @Override
    public List<LegislationDto> getLegislationList() {
        List<LegislationDto> returnValue = new ArrayList<>();

        List<LegislationEntity> legislationEntityList = this.legislationRepository.findAll();

        for(LegislationEntity legislation: legislationEntityList){

            returnValue.add(new ModelMapper().map(legislation, LegislationDto.class));
        }

        return returnValue;
    }
}
