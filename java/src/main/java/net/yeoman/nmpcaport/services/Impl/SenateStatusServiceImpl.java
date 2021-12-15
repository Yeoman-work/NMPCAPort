package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.SenateStatusEntity;
import net.yeoman.nmpcaport.io.repositories.SenateDistrictRepository;
import net.yeoman.nmpcaport.io.repositories.SenateStatusRepository;
import net.yeoman.nmpcaport.services.SenateStatusService;
import net.yeoman.nmpcaport.shared.dto.SenateStatusDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class SenateStatusServiceImpl implements SenateStatusService {

    @Autowired
    private SenateStatusRepository senateStatusRepository;

    @Autowired
    private Utils utils;


    @Override
    public SenateStatusDto getSenateStatus(String statusId) {

        SenateStatusEntity senateStatusEntity = this.senateStatusRepository.findBySenateStatusId(statusId);

        return new ModelMapper().map(senateStatusEntity, SenateStatusDto.class);
    }

    @Override
    public SenateStatusDto deleteSenateStatus(String statusId) {

        SenateStatusEntity senateStatusEntity = this.senateStatusRepository.findBySenateStatusId(statusId);

        this.senateStatusRepository.delete(senateStatusEntity);

        return new ModelMapper().map(senateStatusEntity, SenateStatusDto.class);
    }

    @Override
    public SenateStatusDto updateSenateStatus(String statusId, SenateStatusDto senateStatusDto) {

        SenateStatusEntity senateStatusEntity = this.senateStatusRepository.findBySenateStatusId(statusId);

        if(!senateStatusDto.getStatus().equals(senateStatusDto.getStatus())){

            senateStatusEntity.setStatus(senateStatusDto.getStatus());
        }

        SenateStatusEntity savedStatusEntity = this.senateStatusRepository.save(senateStatusEntity);

        return new ModelMapper().map(savedStatusEntity, SenateStatusDto.class);
    }

    @Override
    public SenateStatusDto createSenateStatus(SenateStatusDto senateStatusDto) {

        SenateStatusEntity senateStatusEntity = new ModelMapper().map(senateStatusDto, SenateStatusEntity.class);

        SenateStatusEntity savedStatusEntity = this.senateStatusRepository.save(senateStatusEntity);

        return new ModelMapper().map(savedStatusEntity, SenateStatusDto.class);
    }

    @Override
    public List<SenateStatusDto> createSenateStatus(List<SenateStatusDto> senateStatusDtoList) {
        List<SenateStatusDto> returnValue = new ArrayList<>();

        for(SenateStatusDto status: senateStatusDtoList){

            SenateStatusEntity senateStatusEntity = new ModelMapper().map(status, SenateStatusEntity.class);

            senateStatusEntity.setSenateStatusId(utils.generateRandomID());

            SenateStatusEntity savedStatusEntity = this.senateStatusRepository.save(senateStatusEntity);

            returnValue.add(new ModelMapper().map(savedStatusEntity, SenateStatusDto.class));
        }

        return returnValue;
    }
}
