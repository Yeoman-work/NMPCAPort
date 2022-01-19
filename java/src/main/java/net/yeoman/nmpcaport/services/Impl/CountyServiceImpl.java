package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.CountyServiceException;
import net.yeoman.nmpcaport.io.response.County.CountyEssentials;
import net.yeoman.nmpcaport.services.CountyService;
import net.yeoman.nmpcaport.entities.CountyEntity;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.repositories.CountyRepository;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.dto.CountyDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountyServiceImpl implements CountyService {

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private Utils utils;

    @Override
    public List<CountyEntity> findAll() {
        return this.countyRepository.findAll();
    }

    @Override
    public CountyEntity findCountyEntity(String countyId) {

        return this.countyRepository.findByCountyId(countyId);
    }

    @Override
    public ContactDto findCountyById(String countyId) {
        return null;
    }

    @Override
    public List<CountyResponse> createCounties(List<String> countiesList) {

        List<CountyResponse> counties = new ArrayList<>();

        for(String county: countiesList){

            if(!this.countyRepository.existsByName(county)){

                CountyEntity countyEntity = new CountyEntity();

                countyEntity.setCountyId(utils.generateRandomID());
                countyEntity.setName(county);

                CountyEntity storedCounty = this.countyRepository.save(countyEntity);

                counties.add(new ModelMapper().map(storedCounty, CountyResponse.class));
            }
        }
        return counties;
    }

    @Override
    public List<CountyResponse> countyResponse() {
        List<CountyResponse> returnValue = new ArrayList<>();

        List<CountyEntity> counties =this.countyRepository.findAll();

        for(CountyEntity county: counties){

            returnValue.add(new ModelMapper().map(county, CountyResponse.class));
        }

        return returnValue;
    }

    @Override
    public CountyEssentials entityToEssentials(CountyEntity countyEntity) {

        if(this.entityIsNull(countyEntity))
            throw new CountyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(countyEntity, CountyEssentials.class);
    }

    @Override
    public List<CountyEssentials> entityToEssentials(List<CountyEntity> countyEntities) {

        if(this.entityIsNull(countyEntities))
            throw new CountyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CountyEssentials> returnValue = new ArrayList<>();

        for(CountyEntity county: countyEntities){

            returnValue.add(this.entityToEssentials(county));
        }

        return returnValue;
    }

    @Override
    public CountyDto entityToDto(CountyEntity countyEntity) {

        if(this.entityIsNull(countyEntity)) throw new CountyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        return this.utils.objectMapper().map(countyEntity, CountyDto.class);
    }

    @Override
    public List<CountyDto> entityToDto(List<CountyEntity> countyEntities) {

        if(this.entityIsNull(countyEntities)) throw new CountyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CountyDto> returnValue = new ArrayList<>();

        for(CountyEntity countyEntity: countyEntities){

            returnValue.add(this.entityToDto(countyEntity));

        }

        return returnValue;
    }

    @Override
    public CountyResponse dtoToResponse(CountyDto countyDto) {

        if(this.dtoIsNull(countyDto)) throw new CountyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(countyDto, CountyResponse.class);
    }

    @Override
    public List<CountyResponse> dtoToResponse(List<CountyDto> countyDtoList) {

        if(dtoIsNull(countyDtoList)) throw new CountyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CountyResponse> returnValue = new ArrayList<>();

        for(CountyDto countyDto: countyDtoList){

            returnValue.add(this.dtoToResponse(countyDto));

        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(CountyEntity countyEntity) {
        return  countyEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<CountyEntity> countyEntities) {
        return countyEntities == null;
    }

    @Override
    public Boolean dtoIsNull(CountyDto countyDto) {
        return countyDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<CountyDto> countyDtoList) {
        return countyDtoList == null;
    }


}
