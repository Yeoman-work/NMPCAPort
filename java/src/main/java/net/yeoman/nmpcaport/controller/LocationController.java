package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.location.LocationDetailsRequestWithRep;
import net.yeoman.nmpcaport.io.request.location.LocationDetailsRequestList;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationWithCongressionalRepResponse;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationWithUSSenatorResponse;
import net.yeoman.nmpcaport.services.Impl.LocationServiceImpl;
import net.yeoman.nmpcaport.shared.dto.LocationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {


    @Autowired
    private LocationServiceImpl locationService;



    @GetMapping(path="/{locationId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public LocationResponse getLocation(@PathVariable("locationId") String locationId){

        LocationDto locationDto = this.locationService.getOneLocation(locationId);


        return new ModelMapper().map(locationDto, LocationResponse.class);

    }

    @PostMapping(path="/usSenator/{senatorId}",
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<LocationWithUSSenatorResponse> createLocationWithSenator(@PathVariable("senatorId") String senatorId, @RequestBody LocationDetailsRequestList locationDetailsRequestList){

        List<LocationDto> serviceInput = new ArrayList<>();
        List<LocationWithUSSenatorResponse> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for(LocationDetailsRequestWithRep location: locationDetailsRequestList.getLocationDetailsRequestWithRepList()){

            serviceInput.add(modelMapper.map(location, LocationDto.class));
        }

        List<LocationDto> locationDtoList = this.locationService.createLocationUsSenator(serviceInput, senatorId);

        for(LocationDto locationDto: locationDtoList){

            returnValue.add(modelMapper.map(locationDto, LocationWithUSSenatorResponse.class));
        }

        return returnValue;
    }

    @PostMapping(path = "/congressionalRep",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<LocationWithCongressionalRepResponse> createLocationWithCongressionalRep(@RequestBody List<LocationDetailsRequestWithRep> locationDetailsRequestWithRepList){
        ModelMapper modelMapper = new ModelMapper();
        List<LocationWithCongressionalRepResponse> returnValue = new ArrayList<>();
        List<LocationDto> serviceInput = new ArrayList<>();

        for(LocationDetailsRequestWithRep locationDetailsRequestWithRep: locationDetailsRequestWithRepList){

            LocationDto locationDto = modelMapper.map(locationDetailsRequestWithRep, LocationDto.class);
            serviceInput.add(locationDto);
        }

        List<LocationDto> savedLocationDtoList = this.locationService.createLocationCongressionalRep(serviceInput);

        for(LocationDto locationDto: savedLocationDtoList){

            LocationWithCongressionalRepResponse locationWithCongressionalRepResponse = modelMapper.map(locationDto, LocationWithCongressionalRepResponse.class);
            returnValue.add(locationWithCongressionalRepResponse);
        }

        return returnValue;
    }
}
