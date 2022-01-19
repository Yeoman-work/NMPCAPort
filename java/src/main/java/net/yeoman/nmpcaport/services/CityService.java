package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.CityEntity;
import net.yeoman.nmpcaport.io.response.city.CityEssentials;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.shared.dto.CityDto;

import java.util.List;

public interface CityService {


    public CityEntity findCity(String cityId);

    public List<CityEntity> findAllCities();

    public CityDto findByCityId(String cityId);

    public List<CityResponse> createCitiesFromList(List<String> cityName);

    public CityDto updateCity(String cityId, CityDto cityDto);

    public List<CityResponse> allCities();

    //convert entity to essentials
    public CityEssentials entityToEssentials(CityEntity cityEntity);
    public List<CityEssentials> entityToEssentials(List<CityEntity> cityEntityList);

    public CityDto entityToDto(CityEntity cityDto);
    public List<CityDto> entityToDto(List<CityEntity> cityEntityList);

    public CityResponse dtoToResponse(CityDto cityDto);
    public List<CityResponse> dtoToResponse(List<CityDto> cityDtoList);

    public Boolean entityIsNull(CityEntity cityEntity);
    public Boolean entityIsNull(List<CityEntity> cityEntityList);
    public Boolean dtoIsNull(CityDto cityDto);
    public Boolean dtoIsNull(List<CityDto> cityDtoList);

}
