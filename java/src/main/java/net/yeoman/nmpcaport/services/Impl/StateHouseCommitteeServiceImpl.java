package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.services.StateHouseCommitteeService;
import net.yeoman.nmpcaport.entities.StateHouseCommitteeEntity;
import net.yeoman.nmpcaport.io.request.stateHouseCommittee.StateHouseCommitteeDetailsList;
import net.yeoman.nmpcaport.io.response.stateHouseCommittee.StateHouseCommitteeResponse;
import net.yeoman.nmpcaport.io.repositories.StateHouseCommitteeRepository;
import net.yeoman.nmpcaport.shared.dto.StateHouseCommitteeDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateHouseCommitteeServiceImpl implements StateHouseCommitteeService {

    @Autowired
    private StateHouseCommitteeRepository stateHouseCommitteeRepository;

    @Autowired
    private Utils utils;


    @Override
    public StateHouseCommitteeDto getStateHouseCommittee(String committeeId) {
        return null;
    }

    @Override
    public StateHouseCommitteeDto createStateHouseCommittee(StateHouseCommitteeDto stateHouseCommittee) {
        return null;
    }


    @Override
    public StateHouseCommitteeDto updateStateHouseCommittee(String committeeId, StateHouseCommitteeDto stateHouseCommitteeDto) {
        return null;
    }

    @Override
    public StateHouseCommitteeDto deleteStateHouseCommittee(String committeeId) {
        return null;
    }

    @Override
    public StateHouseCommitteeEntity getStateHouseCommitteeEntity(StateHouseCommitteeDto stateHouseCommitteeDto) {
        return null;
    }



    @Override
    public List<StateHouseCommitteeResponse> createStateHouseCommittees(StateHouseCommitteeDetailsList stateHouseCommitteeDetailsList) {

        List<StateHouseCommitteeResponse> returnValue = new ArrayList<>();

        for(String name: stateHouseCommitteeDetailsList.getCommitteesNames()){

            if(!this.stateHouseCommitteeRepository.existsByName(name)){

                StateHouseCommitteeEntity stateHouseCommitteeEntity = new StateHouseCommitteeEntity();

                stateHouseCommitteeEntity.setName(name);

                stateHouseCommitteeEntity.setHouseCommitteeId(utils.generateRandomID());

                while(this.stateHouseCommitteeRepository.existsByHouseCommitteeId(stateHouseCommitteeEntity.getHouseCommitteeId())){

                    stateHouseCommitteeEntity.setHouseCommitteeId(utils.generateRandomID());
                }

                StateHouseCommitteeEntity storedStateHouseCommittee = this.stateHouseCommitteeRepository.save(stateHouseCommitteeEntity);

                returnValue.add(new ModelMapper().map(storedStateHouseCommittee, StateHouseCommitteeResponse.class));
            }

        }

        return returnValue;
    }
}
