package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.CityEntity;
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
}
