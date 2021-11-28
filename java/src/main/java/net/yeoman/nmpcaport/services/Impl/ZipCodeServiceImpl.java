package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.repositories.ZipCodeRepository;
import net.yeoman.nmpcaport.services.ZipCodeService;
import net.yeoman.nmpcaport.shared.dto.ZipCodeDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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


}
