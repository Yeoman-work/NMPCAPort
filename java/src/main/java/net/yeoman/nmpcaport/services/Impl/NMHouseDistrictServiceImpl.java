package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.NMHouseDistrictEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.NMHouseDistrictServiceException;
import net.yeoman.nmpcaport.io.repositories.NMHouseDistrictRepository;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.services.NMHouseDistrictService;
import net.yeoman.nmpcaport.shared.dto.NMHouseDistrictDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NMHouseDistrictServiceImpl implements NMHouseDistrictService {


    @Autowired
    private NMHouseDistrictRepository nmHouseDistrictRepository;

    @Autowired
    private Utils utils;

    @Override
    public NMHouseDistrictDto getNMHouseDistrict(String houseDistrictId) {

        NMHouseDistrictEntity nmHouseDistrictEntity = this.nmHouseDistrictRepository.findByHouseDistrictId(houseDistrictId);

        if(nmHouseDistrictEntity == null) throw new NMHouseDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return new ModelMapper().map(nmHouseDistrictEntity, NMHouseDistrictDto.class);
    }

    @Override
    public NMHouseDistrictDto createNMHouseDistrict(NMHouseDistrictDto nmHouseDistrictDto) {

        if(this.nmHouseDistrictRepository.existsByName(nmHouseDistrictDto.getName())) throw new NMHouseDistrictServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        NMHouseDistrictEntity nmHouseDistrictEntity = new ModelMapper().map(nmHouseDistrictDto, NMHouseDistrictEntity.class);

        nmHouseDistrictEntity.setHouseDistrictId(utils.generateRandomID());

        nmHouseDistrictEntity.setReelection(utils.initial2yearCycle());

        NMHouseDistrictEntity storedNMHouseDistrict = this.nmHouseDistrictRepository.save(nmHouseDistrictEntity);

        return new ModelMapper().map(storedNMHouseDistrict, NMHouseDistrictDto.class);
    }

    @Override
    public NMHouseDistrictDto updatedNMHouseDistrict(String nmHouseDistrictId, NMHouseDistrictDto nmHouseDistrictDto) {
        return null;
    }

    @Override
    public NMHouseDistrictDto deleteNMHouseDistrict(String nmHouseDistrictId) {
        return null;
    }

    @Override
    public List<NMHouseDistrictDto> bulkCreateHouseDistrict(List<NMHouseDistrictDto> nmHouseDistrictDtoList) {

        List<NMHouseDistrictDto> returnValue = new ArrayList<>();
        for(NMHouseDistrictDto nmHouseDistrictDto: nmHouseDistrictDtoList){

            NMHouseDistrictEntity nmHouseDistrictEntity = new ModelMapper().map(nmHouseDistrictDto, NMHouseDistrictEntity.class);

            nmHouseDistrictEntity.setHouseDistrictId(utils.generateRandomID());

            NMHouseDistrictEntity saveNMHouseDistrict = this.nmHouseDistrictRepository.save(nmHouseDistrictEntity);

            returnValue.add(new ModelMapper().map(saveNMHouseDistrict, NMHouseDistrictDto.class));

        }

        return returnValue;
    }

    @Override
    public NMHouseDistrictEntity findNMHouseDistrictEntity(String nmHouseDistrictId) {

        return this.nmHouseDistrictRepository.findByHouseDistrictId(nmHouseDistrictId);
    }

    @Override
    public NMHouseDistrictEssentialResponse entityToEssentials(NMHouseDistrictEntity nmHouseDistrictEntity) {

        if(this.entityIsNull(nmHouseDistrictEntity))
            throw new NMHouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(nmHouseDistrictEntity, NMHouseDistrictEssentialResponse.class);
    }

    @Override
    public NMHouseDistrictDto entityToDto(NMHouseDistrictEntity nmHouseDistrictEntity) {

        if(this.entityIsNull(nmHouseDistrictEntity)) throw new NMHouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        return this.utils.objectMapper().map(nmHouseDistrictEntity, NMHouseDistrictDto.class);
    }

    @Override
    public List<NMHouseDistrictDto> entityToDto(List<NMHouseDistrictEntity> nmHouseDistrictEntities) {

        if(this.entityIsNull(nmHouseDistrictEntities)) throw new NMHouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<NMHouseDistrictDto> returnValue = new ArrayList<>();

        for(NMHouseDistrictEntity nmHouseDistrictEntity: nmHouseDistrictEntities){

            returnValue.add(this.entityToDto(nmHouseDistrictEntity));
        }

        return returnValue;
    }

    @Override
    public NMHouseDistrictNestedResponse dtoToNestedResponse(NMHouseDistrictDto nmHouseDistrictDto) {

        if(this.dtoIsNull(nmHouseDistrictDto)) throw new NMHouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(nmHouseDistrictDto, NMHouseDistrictNestedResponse.class);
    }

    @Override
    public List<NMHouseDistrictNestedResponse> dtoToNestedResponse(List<NMHouseDistrictDto> nmHouseDistrictDtoList) {

        if(this.dtoIsNull(nmHouseDistrictDtoList)) throw new NMHouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<NMHouseDistrictNestedResponse> returnValue = new ArrayList<>();

        for(NMHouseDistrictDto nmHouseDistrictDto: nmHouseDistrictDtoList){

            returnValue.add(this.dtoToNestedResponse(nmHouseDistrictDto));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(NMHouseDistrictEntity nmHouseDistrictEntity) {
        return nmHouseDistrictEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<NMHouseDistrictEntity> nmHouseDistrictEntities) {
        return nmHouseDistrictEntities == null;
    }

    @Override
    public Boolean dtoIsNull(NMHouseDistrictDto nmHouseDistrictDto) {
        return nmHouseDistrictDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<NMHouseDistrictDto> houseDistrictDtoList) {
        return houseDistrictDtoList == null;
    }

    @Override
    public List<NMHouseDistrictDto> getAllNMHouseDistrictResponses() {

        List<NMHouseDistrictDto> returnValue = new ArrayList<>();

        List<NMHouseDistrictEntity> allDistricts = this.nmHouseDistrictRepository.findAll();

        for(NMHouseDistrictEntity district: allDistricts){

            returnValue.add(new ModelMapper().map(district, NMHouseDistrictDto.class));
        }

        return returnValue;
    }


}
