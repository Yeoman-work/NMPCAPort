package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.CityEntity;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.repositories.CityRepository;
import net.yeoman.nmpcaport.services.CityService;
import net.yeoman.nmpcaport.shared.dto.CityDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private Utils utils;

    @Override
    public List<CityEntity> findAllCities() {

        return this.cityRepository.findAll();
    }



    @Override
    public CityEntity findCity(String cityId) {

        return this.cityRepository.findByCityId(cityId);
    }

    @Override
    public CityDto findByCityId(String cityId) {

        CityEntity cityEntity = this.cityRepository.findByCityId(cityId);

        return new ModelMapper().map(cityEntity, CityDto.class);
    }

    @Override
    public List<CityResponse> createCitiesFromList(List<String> cityList) {
        List<CityResponse> cities = new ArrayList<>();

        for(String cityName: cityList){

            CityEntity city = new CityEntity();

            city.setCityId(utils.generateRandomID());
            city.setName(cityName);

            CityEntity storedCity = this.cityRepository.save(city);

            cities.add(new ModelMapper().map(storedCity, CityResponse.class));
        }
        return cities;
    }

    @Override
    public CityDto updateCity(String cityId, CityDto cityDto) {

        CityEntity cityEntity = this.cityRepository.findByCityId(cityId);

        if(!cityEntity.getName().equals(cityDto.getName())){

            cityEntity.setName(cityDto.getName());
        }

        CityEntity storedCity = this.cityRepository.save(cityEntity);

        return new ModelMapper().map(storedCity, CityDto.class);
    }

    @Override
    public List<CityResponse> allCities() {
        List<CityResponse> returnValue = new ArrayList<>();

        List<CityEntity> cities = this.cityRepository.findAll().stream()
                .sorted(Comparator.comparing(CityEntity::getName))
                .collect(Collectors.toList());

        for(CityEntity city: cities){

            returnValue.add(new ModelMapper().map(city, CityResponse.class));

        }

        return returnValue;
    }


}
