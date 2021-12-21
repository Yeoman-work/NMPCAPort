package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.NMHouseDistrictCountyEntity;
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
    public NMHouseDistrictCountyEntity getStateRepCountyEntity(String stateRepCountyId) {

        return this.stateRepCountyRepository.findByStateRepCountyId(stateRepCountyId);
    }

    @Override
    public NMHouseDistrictCountyEntity createStateRepCountyEntity(NMHouseDistrictCountyEntity stateRepCountyEntity) {

        stateRepCountyEntity.setStateRepCountyId(utils.generateRandomID());

        NMHouseDistrictCountyEntity stateRepCounty = this.stateRepCountyRepository.save(stateRepCountyEntity);

        return stateRepCounty ;
    }

    @Override
    public NMHouseDistrictCountyEntity deleteStateRepCounty(String stateRepCountyId) {

        NMHouseDistrictCountyEntity stateRepCountyEntity = this.stateRepCountyRepository.findByStateRepCountyId(stateRepCountyId);

        this.stateRepCountyRepository.delete(stateRepCountyEntity);

        return stateRepCountyEntity;
    }
}
