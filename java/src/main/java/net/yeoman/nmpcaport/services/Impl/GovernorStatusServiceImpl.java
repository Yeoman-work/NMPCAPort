package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.GovernorStatusEntity;
import net.yeoman.nmpcaport.io.request.governorStatus.GovernorStatusRequest;
import net.yeoman.nmpcaport.io.response.GovernorStatus.GovernorStatusResponse;
import net.yeoman.nmpcaport.io.repositories.GovernorStatusRepository;
import net.yeoman.nmpcaport.services.GovernorStatusService;
import net.yeoman.nmpcaport.shared.dto.GovernorStatusDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GovernorStatusServiceImpl implements GovernorStatusService {

    @Autowired
    private GovernorStatusRepository governorStatusRepository;

    @Autowired
    private Utils utils;


    @Override
    public GovernorStatusDto findStatusById(String publicId) {

        return new ModelMapper().map(governorStatusRepository.findByGovStatusId(publicId), GovernorStatusDto.class);
    }

    @Override
    public GovernorStatusDto createStatus(GovernorStatusDto newStatus) {

        newStatus.setGovStatusId(utils.generateRandomID());

        GovernorStatusEntity savedStatus =  this.governorStatusRepository.save(new ModelMapper().map(newStatus, GovernorStatusEntity.class));

        return new ModelMapper().map(savedStatus, GovernorStatusDto.class);
    }

    @Override
    public GovernorStatusDto updateStatus(GovernorStatusDto updatedStatus, String publicId) {

        GovernorStatusEntity oldStatus = governorStatusRepository.findByGovStatusId(publicId);

        if(!oldStatus.getName().equals(updatedStatus.getName())){

            oldStatus.setName(updatedStatus.getName());
        }

        GovernorStatusEntity savedStatus = this.governorStatusRepository.save(oldStatus);

        return new ModelMapper().map(savedStatus, GovernorStatusDto.class);
    }

    @Override
    public GovernorStatusEntity findGovernorStatusEntity(String id) {

        return this.governorStatusRepository.findByGovStatusId(id);
    }

    @Override
    public List<GovernorStatusResponse> createStatusBulk(List<GovernorStatusRequest> list) {
        List<GovernorStatusResponse> returnValue = new ArrayList<>();
        for(GovernorStatusRequest item: list){

            GovernorStatusEntity newStatus = new GovernorStatusEntity();

            newStatus.setName(item.getName());
            newStatus.setGovStatusId(utils.generateRandomID());

            GovernorStatusEntity savedStatus = this.governorStatusRepository.save(newStatus);
            GovernorStatusDto savedStatusDto = new ModelMapper().map(savedStatus, GovernorStatusDto.class);
            returnValue.add(new ModelMapper().map(savedStatusDto, GovernorStatusResponse.class));

        }
        return returnValue;
    }

    @Override
    public GovernorStatusDto deleteGovernorStatus(String publicId) {

        GovernorStatusEntity deletedStatus = this.governorStatusRepository.findByGovStatusId(publicId);
        this.governorStatusRepository.delete(deletedStatus);

        return new ModelMapper().map(deletedStatus, GovernorStatusDto.class );
    }
}
