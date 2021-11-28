package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.io.request.location.LocationDetailsRequest;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.shared.dto.LocationDto;

import java.util.List;

public interface LocationService {

    public LocationDto getOneLocation(String locationId);

    public LocationDto createLocation(LocationDto locationDto);

    public LocationDto updateLocation(LocationDto locationDto, String locationId);

    public LocationDto deleteLocation(String locationId);

    public List<LocationResponse> createLocationsBulk(List<LocationDetailsRequest> locationDetailsRequests);

}
