package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.io.request.location.LocationDetailsRequestWithRep;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.shared.dto.LocationDto;

import java.util.List;

public interface LocationService {

    public LocationDto getOneLocation(String locationId);

    public List<LocationDto> createLocationUsSenator(List<LocationDto> locationDtoList, String senatorId);

    public List<LocationDto> createLocationCongressionalRep(List<LocationDto> locationDtoList);

    public LocationDto updateLocation(LocationDto locationDto, String locationId);

    public LocationDto deleteLocation(String locationId);

    public List<LocationResponse> createLocationsBulk(List<LocationDetailsRequestWithRep> locationDetailsRequests);

}
