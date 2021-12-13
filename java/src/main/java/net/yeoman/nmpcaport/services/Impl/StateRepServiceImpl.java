package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.repositories.StateRepRepository;
import net.yeoman.nmpcaport.services.StateRepService;
import net.yeoman.nmpcaport.shared.dto.StateRepDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateRepServiceImpl implements StateRepService {

    @Autowired
    private StateRepRepository stateRepRepository;

    @Autowired
    private NMHouseDistrictServiceImpl nmHouseDistrictService;

    @Autowired
    private Utils utils;

    @Override
    public StateRepDto getStateRep(String stateRepId) {

       StateRepDto stateRepDto = new ModelMapper().map(this.stateRepRepository.findByStateRepId(stateRepId), StateRepDto.class);

       if(stateRepDto.getNmHouseDistrict() != null){

           stateRepDto.setNmHouseDistrictResponse(new ModelMapper().map(stateRepDto.getNmHouseDistrict(), NMHouseDistrictNestedResponse.class));
       }

       return stateRepDto;
    }

    @Override
    public StateRepDto createStateRep(StateRepDto stateRepDto) {

        stateRepDto.setStateRepId(utils.generateRandomID());
        if(stateRepDto.getNmHouseDistrictIdentifier() != null){

            stateRepDto.setNmHouseDistrict(this.nmHouseDistrictService.findNMHouseDistrictEntity(stateRepDto.getNmHouseDistrictIdentifier()));

            stateRepDto.setNmHouseDistrictResponse(new ModelMapper().map(stateRepDto.getNmHouseDistrict(), NMHouseDistrictNestedResponse.class));
        }
        StateRepEntity stateRepEntity = this.stateRepRepository.save(new ModelMapper().map(stateRepDto, StateRepEntity.class));

        return stateRepDto;
    }

    @Override
    public StateRepDto updatedStateRep(String stateRepId, StateRepDto stateRepDto) {
        return null;
    }

    @Override
    public StateRepDto deleteStateRep(String stateRepId) {
        return null;
    }

    @Override
    public StateRepEntity findStateRepEntityById(String stateRepId) {
        return null;
    }
}
