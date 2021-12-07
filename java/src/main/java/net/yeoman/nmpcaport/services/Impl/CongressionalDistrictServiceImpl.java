package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.CongressionalDistrictEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.CongressionalDistrictServiceException;
import net.yeoman.nmpcaport.repositories.CongressionalDistrictRepository;
import net.yeoman.nmpcaport.services.CongressionalDistrictService;
import net.yeoman.nmpcaport.shared.dto.CongressionalDistrictDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CongressionalDistrictServiceImpl implements CongressionalDistrictService {

    @Autowired
    private CongressionalDistrictRepository congressionalDistrictRepository;

    @Autowired
    private Utils utils;


    @Override
    public CongressionalDistrictDto getCongressionalDistrict(String congressionalDistrictId) {
        return null;
    }

    @Override
    public CongressionalDistrictDto createCongressionalDistrict(CongressionalDistrictDto congressionalDistrictDto) {

        if(!utils.isCongressionalDistrict(congressionalDistrictDto.getName())) throw new CongressionalDistrictServiceException(ErrorMessages.DISTRICT_NOT_VALID.getErrorMessage());

        if(this.congressionalDistrictRepository.existsByName(congressionalDistrictDto.getName())) throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        CongressionalDistrictEntity congressionalDistrict = new ModelMapper().map(congressionalDistrictDto, CongressionalDistrictEntity.class);

        congressionalDistrict.setCongressionalDistrictId(utils.generateRandomID());

        congressionalDistrict.setNextElection(utils.initial2yearCycle());

        CongressionalDistrictEntity storedCongressionalDistrict = this.congressionalDistrictRepository.save(congressionalDistrict);

        return new ModelMapper().map(storedCongressionalDistrict, CongressionalDistrictDto.class);
    }

    @Override
    public CongressionalDistrictDto updateCongressionalDistrict(String congressionalDistrictId, CongressionalDistrictDto congressionalDistrictDto) {
        return null;
    }

    @Override
    public CongressionalDistrictDto deleteCongressionalDistrict(String congressionalDistrictId) {
        return null;
    }

    @Override
    public List<CongressionalDistrictDto> findAllCongressionalDistricts(){
        List<CongressionalDistrictDto> returnValue = new ArrayList<>();

        List<CongressionalDistrictEntity> congressionalDistrictEntities = this.congressionalDistrictRepository.findAll();

        for(CongressionalDistrictEntity district: congressionalDistrictEntities){

            returnValue.add(new ModelMapper().map(district, CongressionalDistrictDto.class));
        }
        return returnValue;
    }

    public CongressionalDistrictEntity getCongressionalDistrictEntity(String congressionalId){

        return this.congressionalDistrictRepository.findByCongressionalDistrictId(congressionalId);
    }
}
