package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.io.request.service.ServiceDetailsRequestModel;
import net.yeoman.nmpcaport.shared.dto.ServiceDto;

import java.util.List;

public interface ServiceService {

    public List<ServiceDto> allService();

    public ServiceDto createService(ServiceDto serviceDetails);

    public ServiceDto getService(String serviceId);

    public ServiceDto deleteUser(String serviceId);
}
