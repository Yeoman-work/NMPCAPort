package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.CountyEntity;
import net.yeoman.nmpcaport.exception.CountyServiceException;
import net.yeoman.nmpcaport.io.response.County.CountyEssentials;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.dto.CountyDto;

import java.util.List;

public interface CountyService {

    public List<CountyEntity> findAll();
    public CountyEntity findCountyEntity(String countyId);
    public ContactDto findCountyById(String countyId);
    public List<CountyResponse> createCounties(List<String> countiesList);
    public List<CountyResponse> countyResponse();


    public CountyEssentials entityToEssentials(CountyEntity countyEntity);
    public List<CountyEssentials> entityToEssentials(List<CountyEntity> countyEntities);

    public CountyDto entityToDto(CountyEntity countyEntity);
    public List<CountyDto> entityToDto(List<CountyEntity> countyEntities);

    public CountyResponse dtoToResponse(CountyDto countyDto);
    public List<CountyResponse> dtoToResponse(List<CountyDto> countyDtoList);

    public Boolean entityIsNull(CountyEntity countyEntity);
    public Boolean entityIsNull(List<CountyEntity> countyEntities);

    public Boolean dtoIsNull(CountyDto countyDto);
    public Boolean dtoIsNull(List<CountyDto> countyDtoList);

}
