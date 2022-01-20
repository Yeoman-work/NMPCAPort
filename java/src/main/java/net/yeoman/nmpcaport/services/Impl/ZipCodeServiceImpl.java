package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.ZipCodeServiceException;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeEssentials;
import net.yeoman.nmpcaport.services.ZipCodeService;
import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.io.repositories.ZipCodeRepository;
import net.yeoman.nmpcaport.shared.dto.ZipCodeDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    @Autowired
    private Utils utils;

    @Override
    public List<ZipCodeDto> findALl() {

        List<ZipCodeDto> returnValue = new ArrayList<>();

        List<ZipCodeEntity> zipCodes = this.zipCodeRepository.findAll();

        for(ZipCodeEntity zipCode: zipCodes){

            returnValue.add(new ModelMapper().map(zipCode, ZipCodeDto.class));
        }
        return returnValue;
    }

    @Override
    public ZipCodeEntity getZipCodeEntity(String zipCodeId) {

        return this.zipCodeRepository.findByZipCodeId(zipCodeId);
    }

    @Override
    public ZipCodeDto findZipCodeById(String zipCodeId) {

        ZipCodeEntity zipCode = this.zipCodeRepository.findByZipCodeId(zipCodeId);

        return new ModelMapper().map(zipCode, ZipCodeDto.class);
    }

    @Override
    public List<ZipCodeResponse> createZipCodesFromList(List<String> zipCodes) {
        List<ZipCodeResponse> zipCodeResponseList = new ArrayList<>();

        for(String zipCode: zipCodes){

            if(!this.zipCodeRepository.existsByName(zipCode)){

                ZipCodeEntity zipCodeEntity = new ZipCodeEntity();

                zipCodeEntity.setZipCodeId(utils.generateRandomID());
                zipCodeEntity.setName(zipCode);

                ZipCodeEntity storedZipCode = this.zipCodeRepository.save(zipCodeEntity);

                zipCodeResponseList.add(new ModelMapper().map(storedZipCode, ZipCodeResponse.class));
            }

        }

        return zipCodeResponseList;
    }

    @Override
    public ZipCodeEssentials entityToEssentials(ZipCodeEntity zipCodeEntity) {

        if(entityIsNull(zipCodeEntity))
            throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ZipCodeEssentials zipCodeEssentials = new ZipCodeEssentials();

        zipCodeEssentials.setName(zipCodeEntity.getName());
        zipCodeEssentials.setZipCodeId(zipCodeEntity.getZipCodeId());

        return zipCodeEssentials;
    }

    @Override
    public List<ZipCodeEssentials> entityToEssentials(List<ZipCodeEntity> zipCodeEntities) {

        if(this.entityIsNull(zipCodeEntities))
            throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ZipCodeEssentials> returnValue = new ArrayList<>();

        for(ZipCodeEntity zipCode: zipCodeEntities){

            returnValue.add(this.entityToEssentials(zipCode));
        }

        return returnValue;
    }

    @Override
    public ZipCodeDto entityToDto(ZipCodeEntity zipCodeEntity) {

        if(this.entityIsNull(zipCodeEntity)) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(zipCodeEntity, ZipCodeDto.class);
    }

    @Override
    public List<ZipCodeDto> entityToDto(List<ZipCodeEntity> zipCodeDtoEntityList) {

        if(this.entityIsNull(zipCodeDtoEntityList)) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ZipCodeDto> returnValue = new ArrayList<>();

        for(ZipCodeEntity zipCodeEntity: zipCodeDtoEntityList){

            returnValue.add(this.entityToDto(zipCodeEntity));
        }

        return returnValue;
    }

    @Override
    public ZipCodeResponse dtoToResponse(ZipCodeDto zipCodeDto) {

        if(this.dtoIsNull(zipCodeDto)) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(zipCodeDto, ZipCodeResponse.class);
    }

    @Override
    public List<ZipCodeResponse> dtoToResponse(List<ZipCodeDto> zipCodeDtoList) {

        if(this.dtoIsNull(zipCodeDtoList)) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ZipCodeResponse> returnValue = new ArrayList<>();

        for(ZipCodeDto zipCodeDto: zipCodeDtoList){

            returnValue.add(this.dtoToResponse(zipCodeDto));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(ZipCodeEntity zipCodeEntity) {
        return  zipCodeEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<ZipCodeEntity> zipCodeEntities) {
        return zipCodeEntities == null;
    }

    @Override
    public Boolean dtoIsNull(ZipCodeDto zipCodeDto) {
        return zipCodeDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<ZipCodeDto> zipCodeDtoList) {
        return zipCodeDtoList == null;
    }


}
