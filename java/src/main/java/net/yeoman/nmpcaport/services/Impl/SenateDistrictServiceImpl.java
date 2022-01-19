package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.SenateDistrictServiceException;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.io.repositories.SenateDistrictRepository;
import net.yeoman.nmpcaport.services.SenateDistrictService;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<SenateDistrictDto> createBulkSenateDistrict(List<SenateDistrictDto> senateDistrictDtoList) {
        List<SenateDistrictDto> returnValue = new ArrayList<>();

        for(SenateDistrictDto senateDistrictDto: senateDistrictDtoList){

            SenateDistrictEntity preSaveEntity = new ModelMapper().map(senateDistrictDto, SenateDistrictEntity.class);

            preSaveEntity.setSenateDistrictId(utils.generateRandomID());
            SenateDistrictEntity savedEntity = this.senateDistrictRepository.save(preSaveEntity);

            returnValue.add(new ModelMapper().map(savedEntity, SenateDistrictDto.class));
        }

        return returnValue;
    }

    @Override
    public SenateDistrictEssentialResponse essentialsToEntity(SenateDistrictEntity senateDistrictEntity) {

        if(this.entityIsNull(senateDistrictEntity))
            throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        return this.utils.objectMapper().map(senateDistrictEntity, SenateDistrictEssentialResponse.class);
    }

    @Override
    public SenateDistrictDto entityToDto(SenateDistrictEntity senateDistrictEntity) {

        if(this.entityIsNull(senateDistrictEntity)) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(senateDistrictEntity, SenateDistrictDto.class);
    }

    @Override
    public List<SenateDistrictDto> entityToDto(List<SenateDistrictEntity> senateDistrictEntities) {

        if(this.entityIsNull(senateDistrictEntities)) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SenateDistrictDto> returnValue = new ArrayList<>();

        for(SenateDistrictEntity senateDistrictEntity: senateDistrictEntities){

            returnValue.add(this.entityToDto(senateDistrictEntity));
        }

        return returnValue;
    }

    @Override
    public SenateDistrictNestedResponse dtoToNestedResponse(SenateDistrictDto senateDistrictDto) {

        if(this.dtoIsNull(senateDistrictDto)) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(senateDistrictDto, SenateDistrictNestedResponse.class);
    }

    @Override
    public List<SenateDistrictNestedResponse> dtoToNestedResponse(List<SenateDistrictDto> senateDistrictDtoList) {

        if(this.dtoIsNull(senateDistrictDtoList)) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SenateDistrictNestedResponse> returnValue = new ArrayList<>();

        for(SenateDistrictDto senateDistrictDto: senateDistrictDtoList){

            returnValue.add(this.dtoToNestedResponse(senateDistrictDto));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(SenateDistrictEntity senateDistrictEntity) {
        return senateDistrictEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<SenateDistrictEntity> senateDistrictEntities) {
        return senateDistrictEntities == null;
    }

    @Override
    public Boolean dtoIsNull(SenateDistrictDto senateDistrictDto) {
        return senateDistrictDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<SenateDistrictDto> senateDistrictDtoList) {
        return senateDistrictDtoList == null;
    }
}
