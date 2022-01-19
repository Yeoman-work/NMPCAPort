package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.location.LocationDetailsRequestWithRep;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationEssentialsResponse;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.repositories.LocationRepository;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorResponse;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
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
    private USSenatorServiceImpl usSenatorService;

    @Autowired
    OfficeAssignmentServiceImpl officeAssignmentService;

    @Autowired
    CongressionalRepServiceImpl congressionalRepService;

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

        ModelMapper modelMapper = new ModelMapper();
        LocationEntity locationEntity = this.locationRepository.findByLocationId(locationId);

        if(locationEntity == null) throw new LocationServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        LocationDto locationDto = modelMapper.map(locationEntity, LocationDto.class);

        if(locationDto.getCityEntity() != null){

            locationDto.setCityResponse(modelMapper.map(locationDto.getCityEntity(), CityResponse.class));
        }

        if(locationDto.getCountyEntity() != null){

            locationDto.setCountyResponse(modelMapper.map(locationDto.getCountyEntity(), CountyResponse.class));
        }

        if(locationDto.getZipCodeEntity() != null){

            locationDto.setZipCodeResponse(modelMapper.map(locationDto.getZipCodeResponse(), ZipCodeResponse.class));
        }

        return locationDto;
    }


    @Override
    public List<LocationDto> createLocationCongressionalRep(List<LocationDto> locationDtoList) {
        ModelMapper modelMapper = new ModelMapper();
        List<LocationDto> returnValue = new ArrayList<>();

        for(LocationDto locationDto: locationDtoList){

            LocationEntity locationEntity = modelMapper.map(locationDto, LocationEntity.class);

            locationEntity.setLocationId(utils.generateRandomID());

            while(this.locationRepository.existsByLocationId(locationEntity.getLocationId())){

                locationEntity.setLocationId(utils.generateRandomID());
            }

            if(locationDto.getCity() != null){

                CityEntity cityEntity = this.cityService.findCity(locationDto.getCity());

                if(cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                locationEntity.setCityEntity(cityEntity);
            }

            if(locationDto.getCounty() != null){

                CountyEntity countyEntity = this.countyService.findCountyEntity(locationDto.getCounty());

                if(countyEntity == null) throw new CountyServiceException(ErrorMessages.NO_PARTY_AFFILIATION.getErrorMessage());

                locationEntity.setCountyEntity(countyEntity);
            }

            if(locationDto.getZipCode() != null){

                ZipCodeEntity zipCodeEntity = this.zipCodeService.getZipCodeEntity(locationDto.getZipCode());

                if(zipCodeEntity == null) throw new ZipCodeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                locationEntity.setZipCodeEntity(zipCodeEntity);
            }

            System.out.println(locationDto.getRep());
            CongressionalRepEntity congressionalRepEntity = this.congressionalRepService.getCongressionalRepEntity(locationDto.getRep());
            //System.out.println(congressionalRepEntity);
            if(congressionalRepEntity == null) throw new CongressionalRepServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            OfficeAssignmentEntity officeAssignmentEntity = new OfficeAssignmentEntity();

            officeAssignmentEntity.setPublicId(utils.generateRandomID());

            while (this.officeAssignmentService.officePublicIdExist(officeAssignmentEntity.getPublicId())){

                officeAssignmentEntity.setPublicId(utils.generateRandomID());
            }

            LocationEntity savedLocation = this.locationRepository.save(locationEntity);

            officeAssignmentEntity.setLocationEntity(savedLocation);

            officeAssignmentEntity.setCongressionalRepEntity(congressionalRepEntity);

            OfficeAssignmentEntity savedOfficeAssignmentEntity = this.officeAssignmentService.createOfficeAssignment(officeAssignmentEntity);

            LocationDto savedLocationDto = modelMapper.map(savedLocation, LocationDto.class);

            returnValue.add(savedLocationDto);
        }

        return returnValue;
    }

    @Override
    public List<LocationDto> createLocationUsSenator(List<LocationDto> locationDtoList, String senatorId ) {

        List<LocationDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for(LocationDto locationDto: locationDtoList){

            //convert dto to entity
            LocationEntity preSavedLocation = modelMapper.map(locationDto, LocationEntity.class);

            System.out.println("read here");
            System.out.println(preSavedLocation.getStreetAddress());

            preSavedLocation.setLocationId(utils.generateRandomID());

            //get US Senator
            USSenatorEntity usSenatorEntity = this.usSenatorService.getUSSenatorEntity(senatorId);

            //throw error if senator doesn't exist
            if(usSenatorEntity == null) throw new UsSenatorServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //office assignment list
            List<OfficeAssignmentEntity> officeAssignments = new ArrayList<>();

            //check for office entities
            if(preSavedLocation.getOfficeAssignmentEntities() != null){

                officeAssignments = preSavedLocation.getOfficeAssignmentEntities();

            }


            // new office assignment entity
            OfficeAssignmentEntity officeAssignmentEntity = new OfficeAssignmentEntity();

            //generate public id
            officeAssignmentEntity.setPublicId(utils.generateRandomID());

            //assign senator to office assignment
            officeAssignmentEntity.setUsSenatorEntity(usSenatorEntity);

            //set assignments to list
            officeAssignments.add(officeAssignmentEntity);

            //assign to location
            preSavedLocation.setOfficeAssignmentEntities(officeAssignments);


            //get county entity
            CountyEntity countyEntity = this.countyService.findCountyEntity(locationDto.getCounty());

            //throw error if county doesn't exist
            if(countyEntity == null) throw new CountyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set county entity for location
            preSavedLocation.setCountyEntity(countyEntity);

            //get city entity
            CityEntity cityEntity = this.cityService.findCity(locationDto.getCity());

            //if city doesn't exist throw error
            if(cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set city for location
            preSavedLocation.setCityEntity(cityEntity);


            //get zip code
            ZipCodeEntity zipCodeEntity = this.zipCodeService.getZipCodeEntity(locationDto.getZipCode());

            //throw error if zip code exist
            if(zipCodeEntity == null) throw new ZipCodeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set zip code for location
            preSavedLocation.setZipCodeEntity(zipCodeEntity);

            //save location
            LocationEntity savedLocation = this.locationRepository.save(preSavedLocation);

            officeAssignmentEntity.setLocationEntity(savedLocation);

            this.officeAssignmentService.createOfficeAssignment(officeAssignmentEntity);

            //add to list that will be returned
            returnValue.add(modelMapper.map(preSavedLocation, LocationDto.class));

        }

        for(LocationDto locationDto: returnValue){

            //check for county entity
            if(locationDto.getCountyEntity() != null){

                locationDto.setCountyResponse(modelMapper.map(locationDto.getCountyEntity(), CountyResponse.class));
            }

            //check for city entity
            if(locationDto.getCityEntity() != null){

                locationDto.setCityResponse(modelMapper.map(locationDto.getCityEntity(), CityResponse.class));
            }

            //check for zip code entity
            if(locationDto.getZipCodeEntity() != null){

                locationDto.setZipCodeResponse(modelMapper.map(locationDto.getZipCodeEntity(), ZipCodeResponse.class));
            }

            //check for usSenator
            if(locationDto.getUsSenatorEntity() != null){

                locationDto.setUsSenatorResponse(modelMapper.map(locationDto.getUsSenatorEntity(), USSenatorResponse.class));
            }
        }

        return returnValue;
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

        if(!oldLocation.getCityEntity().getCityId().equals(locationDto.getCity())){

            CityEntity city = this.cityService.findCity(locationDto.getCity());

            oldLocation.setCityEntity(city);

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
    public List<LocationResponse> createLocationsBulk(List<LocationDetailsRequestWithRep> locationDetailsRequests) {
        List<LocationResponse> returnValue = new ArrayList<>();
        for(LocationDetailsRequestWithRep item: locationDetailsRequests){
            LocationEntity location = new LocationEntity();

            location.setLocationId(utils.generateRandomID());

            location.setName(item.getName());

            if(!item.getDescription().isEmpty()){

                location.setDescription(item.getDescription());
            }



            LocationEntity savedLocation = this.locationRepository.save(location);

            LocationDto locationDto = new ModelMapper().map(savedLocation, LocationDto.class);

            returnValue.add(new ModelMapper().map(locationDto, LocationResponse.class));

        }
        return returnValue;
    }

    @Override
    public LocationEssentialsResponse entityToEssential(LocationEntity locationEntity) {

        if(this.entityIsNull(locationEntity))
            throw new LocationServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(locationEntity, LocationEssentialsResponse.class);
    }

    @Override
    public List<LocationEssentialsResponse> entityToEssential(List<LocationEntity> locationEntities) {

        if(this.entityIsNull(locationEntities))
            throw new LocationServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<LocationEssentialsResponse> returnValue = new ArrayList<>();

        for(LocationEntity locationEntity: locationEntities){

            returnValue.add(this.entityToEssential(locationEntity));
        }
        return returnValue;
    }

    @Override
    public Boolean entityIsNull(LocationEntity locationEntity) {
        return locationEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<LocationEntity> locationEntities) {
        return locationEntities == null;
    }
}
