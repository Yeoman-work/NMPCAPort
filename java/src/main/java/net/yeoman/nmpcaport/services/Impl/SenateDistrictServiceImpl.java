package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorResponse;
import net.yeoman.nmpcaport.repositories.SenateDistrictRepository;
import net.yeoman.nmpcaport.services.SenateDistrictService;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SenateDistrictServiceImpl implements SenateDistrictService {

    @Autowired
    private SenateDistrictRepository senateDistrictRepository;

    @Autowired
    private StateSenatorServiceImpl stateSenatorService;

    @Autowired
    private Utils utils;


    @Override
    public SenateDistrictDto getDistrict(String districtId) {

        SenateDistrictEntity senateDistrictEntity = this.senateDistrictRepository.findBySenateDistrictId(districtId);
        SenateDistrictDto senateDistrictDto = new ModelMapper().map(senateDistrictEntity, SenateDistrictDto.class);
        if(senateDistrictDto.getStateSenator() != null){

            senateDistrictDto.setStateSenatorNestedResponse(new ModelMapper().map(senateDistrictDto.getStateSenator(), StateSenatorNestedResponse.class));
        }

        return senateDistrictDto;
    }


    @Override
    public SenateDistrictDto createDistrict(SenateDistrictDto senateDistrictDto) {

        SenateDistrictEntity senateDistrict = new ModelMapper().map(senateDistrictDto, SenateDistrictEntity.class);

        senateDistrict.setSenateDistrictId(utils.generateRandomID());
        senateDistrict.setElectionDate(utils.initialStateSenateTerm());

        if(utils.isSenateDistrict(senateDistrict.getName())){
            if(this.senateDistrictRepository.existsByName(senateDistrict.getName())){
                throw new RuntimeException("Senate District already exist");
            }

            this.senateDistrictRepository.save(senateDistrict);

        }else{

            throw new RuntimeException(senateDistrict.getName() + " is not a valid district");
        }

        return new ModelMapper().map(senateDistrict, SenateDistrictDto.class);
    }




    @Override
    public SenateDistrictDto updateDistrict(String senateDistrictId, SenateDistrictDto senateDistrictDto) {
        return null;
    }


    @Override
    public SenateDistrictDto deleteDistrict(String senateDistrictId) {
        return null;
    }

    @Override
    public SenateDistrictEntity findSenateDistrictEntity(String senateDistrictId){

        return this.senateDistrictRepository.findBySenateDistrictId(senateDistrictId);
    }

    @Override
    public List<SenateDistrictDto> getAllSenateDistricts() {
        List<SenateDistrictDto> returnValue = new ArrayList<>();
        List<SenateDistrictEntity> senateDistrictEntities = this.senateDistrictRepository.findAll();

        for(SenateDistrictEntity district: senateDistrictEntities){

            returnValue.add(new ModelMapper().map(district, SenateDistrictDto.class));
        }

        return returnValue;
    }
}
