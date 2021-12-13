package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.CityEntity;
import net.yeoman.nmpcaport.entities.CountyEntity;
import net.yeoman.nmpcaport.entities.LocationEntity;
import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.request.location.LocationDetailsRequest;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.repositories.LocationRepository;
import net.yeoman.nmpcaport.services.LocationService;
import net.yeoman.nmpcaport.shared.dto.LocationDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CountyServiceImpl countyService;

    @Autowired
    private ZipCodeServiceImpl zipCodeService;

    @Autowired
    private Utils utils;

    @Override
    public LocationDto getOneLocation(String locationId) {

        return new ModelMapper().map(this.locationRepository.findByLocationId(locationId), LocationDto.class);
    }

    @Override
    public LocationDto createLocation(LocationDto locationDto) {

        LocationEntity preSavedLocation = new ModelMapper().map(locationDto, LocationEntity.class);
        LocationEntity saved = this.locationRepository.save(preSavedLocation);
        return new ModelMapper().map(saved, LocationDto.class);
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto, String locationId) {

        LocationEntity oldLocation = this.locationRepository.findByLocationId(locationId);

        if(!oldLocation.getName().equals(locationDto.getName())){

            oldLocation.setName(locationDto.getName());
        }

        if(!oldLocation.getDescription().equals(locationDto.getDescription())){

            oldLocation.setDescription(locationDto.getDescription());
        }

        if(!oldLocation.getCityEntity().getCityId().equals(locationDto.getCityId())){

            CityEntity city = this.cityService.findCity(locationDto.getCityId());

            oldLocation.setCityEntity(city);

        }

        if(!oldLocation.getCounty().getCountyId().equals(locationDto.getCountyId())){

            CountyEntity county = this.countyService.findCountyEntity(locationDto.getCountyId());

            oldLocation.setCounty(county);
        }

        if(!oldLocation.getZipCode().getZipCodeId().equals(locationDto.getZipCodeId())){

            ZipCodeEntity zipCode = this.zipCodeService.getZipCodeEntity(locationDto.getZipCodeId());

            oldLocation.setZipCode(zipCode);

        }


        LocationEntity saved = this.locationRepository.save(oldLocation);

        return new ModelMapper().map(saved, LocationDto.class);
    }

    @Override
    public LocationDto deleteLocation(String locationId) {

        LocationEntity deletedLocation = this.locationRepository.findByLocationId(locationId);

        this.locationRepository.delete(deletedLocation);

        return new ModelMapper().map(deletedLocation, LocationDto.class);
    }

    @Override
    public List<LocationResponse> createLocationsBulk(List<LocationDetailsRequest> locationDetailsRequests) {
        List<LocationResponse> returnValue = new ArrayList<>();
        for(LocationDetailsRequest item: locationDetailsRequests){
            LocationEntity location = new LocationEntity();

            location.setLocationId(utils.generateRandomID());

            location.setName(item.getName());

            if(!item.getDescription().isEmpty()){

                location.setDescription(item.getDescription());
            }

            location.setCityEntity(this.cityService.findCity(item.getCityId()));

            location.setCounty(this.countyService.findCountyEntity(item.getCountyId()));

            location.setZipCode(this.zipCodeService.getZipCodeEntity(item.getZipCodeId()));

            LocationEntity savedLocation = this.locationRepository.save(location);

            LocationDto locationDto = new ModelMapper().map(savedLocation, LocationDto.class);

            returnValue.add(new ModelMapper().map(locationDto, LocationResponse.class));

        }
        return returnValue;
    }
}
