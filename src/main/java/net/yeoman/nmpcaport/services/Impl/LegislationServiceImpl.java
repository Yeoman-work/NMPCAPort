package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.LegislationEntity;
import net.yeoman.nmpcaport.repositories.LegislationRepository;
import net.yeoman.nmpcaport.services.LegislationService;
import net.yeoman.nmpcaport.shared.dto.LegislationDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
}
