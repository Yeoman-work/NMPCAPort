package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.NMHouseStatusEntity;
import net.yeoman.nmpcaport.io.repositories.NMHouseStatusRepository;
import net.yeoman.nmpcaport.services.NMHouseStatusService;
import net.yeoman.nmpcaport.shared.dto.NMHouseStatusDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NMHouseStatusServiceImpl implements NMHouseStatusService {

    
    private final NMHouseStatusRepository nmHouseStatusRepository;

    private final Utils utils;
    
    public NMHouseStatusServiceImpl(NMHouseStatusRepository nmHouseStatusRepository,
    			                    Utils utils
	) {
    	this.nmHouseStatusRepository = nmHouseStatusRepository;
    	this.utils = utils;
    }

    @Override
    public NMHouseStatusDto getNMHouseStatusDto(String houseStatusId) {

        NMHouseStatusEntity nmHouseStatusEntity = this.nmHouseStatusRepository.findByHouseStatusId(houseStatusId);

        return new ModelMapper().map(nmHouseStatusEntity, NMHouseStatusDto.class);
    }

    @Override
    public NMHouseStatusDto createNMHouseStatus(NMHouseStatusDto nmHouseStatusDto) {

        NMHouseStatusEntity nmHouseStatusEntity = new ModelMapper().map(nmHouseStatusDto, NMHouseStatusEntity.class);

        nmHouseStatusEntity.setHouseStatusId(utils.generateRandomID());

        NMHouseStatusEntity savedHouseStatusEntity = this.nmHouseStatusRepository.save(nmHouseStatusEntity);

        return new ModelMapper().map(savedHouseStatusEntity, NMHouseStatusDto.class);
    }

    @Override
    public NMHouseStatusDto updateNMHouseStatus(String statusId, NMHouseStatusDto nmHouseStatusDto) {

        NMHouseStatusEntity nmHouseStatusEntity = this.nmHouseStatusRepository.findByHouseStatusId(statusId);

        if(nmHouseStatusEntity.getStatus().equals(nmHouseStatusDto.getStatus())){

            nmHouseStatusEntity.setStatus(nmHouseStatusDto.getStatus());

        }

        NMHouseStatusEntity savedHouseStatusEntity = this.nmHouseStatusRepository.save(nmHouseStatusEntity);

        return new ModelMapper().map(savedHouseStatusEntity, NMHouseStatusDto.class);
    }

    @Override
    public NMHouseStatusDto deleteNMHouseStatus(String statusId) {

        NMHouseStatusEntity nmHouseStatusEntity = this.nmHouseStatusRepository.findByHouseStatusId(statusId);

        this.nmHouseStatusRepository.delete(nmHouseStatusEntity);

        return new ModelMapper().map(nmHouseStatusEntity, NMHouseStatusDto.class);
    }

    @Override
    public List<NMHouseStatusDto> getAllNMHouseStatus() {

        List<NMHouseStatusDto> returnValue = new ArrayList<>();

        List<NMHouseStatusEntity> nmHouseStatusEntityList = this.nmHouseStatusRepository.findAll();

        for(NMHouseStatusEntity status: nmHouseStatusEntityList){

                returnValue.add(new ModelMapper().map(status, NMHouseStatusDto.class));
        }

        return returnValue;
    }

    @Override
    public List<NMHouseStatusDto> createdNMHouseStatusBulk(List<NMHouseStatusDto> nmHouseStatusDtoList) {

        List<NMHouseStatusDto> returnValue = new ArrayList<>();

        for(NMHouseStatusDto status: nmHouseStatusDtoList){

            NMHouseStatusEntity nmHouseStatusEntity = new ModelMapper().map(status, NMHouseStatusEntity.class);

            nmHouseStatusEntity.setHouseStatusId(utils.generateRandomID());

            NMHouseStatusEntity savedHouseStatus = this.nmHouseStatusRepository.save(nmHouseStatusEntity);

            returnValue.add(new ModelMapper().map(savedHouseStatus, NMHouseStatusDto.class));
        }

        return returnValue;
    }
}
