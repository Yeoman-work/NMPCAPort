package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.USSenatorEntity;
import net.yeoman.nmpcaport.io.repositories.USSenatorRepository;
import net.yeoman.nmpcaport.services.USSenatorService;
import net.yeoman.nmpcaport.shared.dto.USSenatorDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class USSenatorServiceImpl implements USSenatorService {

    @Autowired
    private USSenatorRepository usSenatorRepository;

    @Autowired
    private Utils utils;

    @Override
    public USSenatorDto getSenator(String senatorId){

        return new ModelMapper().map(this.usSenatorRepository.findBySenatorId(senatorId), USSenatorDto.class);
    }

    @Override
    public USSenatorDto createSenator(USSenatorDto senatorDto) {

        USSenatorEntity senatorEntity = new ModelMapper().map(senatorDto, USSenatorEntity.class);

        senatorEntity.setSenatorId(utils.generateRandomID());

        while(this.usSenatorRepository.existsBySenatorId(senatorEntity.getSenatorId())){

            senatorEntity.setSenatorId(utils.generateRandomID());
        }

        USSenatorEntity storedSenatorEntity = this.usSenatorRepository.save(senatorEntity);

        return new ModelMapper().map(storedSenatorEntity, USSenatorDto.class);
    }

    @Override
    public USSenatorDto updateSenator(String senatorId, USSenatorDto usSenatorDto) {
        return null;
    }

    @Override
    public USSenatorDto deleteSenator(String senatorId) {
        return null;
    }

    @Override
    public USSenatorEntity getUSSenatorEntity(String senatorId) {
        return null;
    }
}
