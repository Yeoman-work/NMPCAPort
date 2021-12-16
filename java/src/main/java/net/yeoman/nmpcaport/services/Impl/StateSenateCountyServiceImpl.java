package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.StateRepCountyEntity;
import net.yeoman.nmpcaport.entities.StateSenateCountyEntity;
import net.yeoman.nmpcaport.io.repositories.StateSenateCountyRepository;
import net.yeoman.nmpcaport.services.StateSenatorCountyService;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

public class StateSenateCountyServiceImpl implements StateSenatorCountyService {

    @Autowired
    private StateSenateCountyRepository stateSenateCountyRepository;

    @Autowired
    private Utils utils;

    @Override
    public StateSenateCountyEntity getStateSenateCountyEntity(String stateCountyEntityId) {

        return this.stateSenateCountyRepository.findBySenateCountyId(stateCountyEntityId);

    }

    @Override
    public StateSenateCountyEntity createStateSenateCountyEntity(StateSenateCountyEntity senateCountyEntity) {

        StateSenateCountyEntity stateSenateCountyEntity = this.stateSenateCountyRepository.save(senateCountyEntity);

        stateSenateCountyEntity.setSenateCountyId(utils.generateRandomID());

        return stateSenateCountyEntity;
    }

    @Override
    public StateSenateCountyEntity deleteStateSenateCountyEntity(String stateSenateCountyEntityId) {

        StateSenateCountyEntity senateCountyEntity = this.stateSenateCountyRepository.findBySenateCountyId(stateSenateCountyEntityId);

        this.stateSenateCountyRepository.delete(senateCountyEntity);

        return senateCountyEntity;
    }
}
