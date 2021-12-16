package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.StateRepCountyEntity;
import net.yeoman.nmpcaport.io.repositories.StateRepCountyRepository;
import net.yeoman.nmpcaport.services.StateRepCountyService;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

public class StateRepCountyServiceImpl implements StateRepCountyService {

    @Autowired
    private StateRepCountyRepository stateRepCountyRepository;

    @Autowired
    private Utils utils;

    @Override
    public StateRepCountyEntity getStateRepCountyEntity(String stateRepCountyId) {

        return this.stateRepCountyRepository.findStateRepCountyId(stateRepCountyId);
    }

    @Override
    public StateRepCountyEntity createStateRepCountyEntity(StateRepCountyEntity stateRepCountyEntity) {

        stateRepCountyEntity.setStateRepCountyId(utils.generateRandomID());

        StateRepCountyEntity stateRepCounty = this.stateRepCountyRepository.save(stateRepCountyEntity);

        return stateRepCounty ;
    }

    @Override
    public StateRepCountyEntity deleteStateRepCounty(String stateRepCountyId) {

        StateRepCountyEntity stateRepCountyEntity = this.stateRepCountyRepository.findStateRepCountyId(stateRepCountyId);

        this.stateRepCountyRepository.delete(stateRepCountyEntity);

        return stateRepCountyEntity;
    }
}
