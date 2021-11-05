package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.ServiceEntity;
import net.yeoman.nmpcaport.io.request.service.ServiceDetailsRequestModel;
import net.yeoman.nmpcaport.repositories.ServiceRepository;
import net.yeoman.nmpcaport.services.ServiceService;
import net.yeoman.nmpcaport.shared.dto.ServiceDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private Utils utils;

    @Override
    public List<ServiceDto> allService() {
        return null;
    }

    @Override
    public ServiceDto createService(ServiceDto serviceDto) {

        serviceDto.setServiceId(utils.generateRandomID());

        this.serviceRepository.save(new ModelMapper().map(serviceDto, ServiceEntity.class));

        return serviceDto;
    }

    @Override
    public ServiceDto getService(String serviceId) {

        ServiceEntity serviceEntity = this.serviceRepository.findByServiceId(serviceId);

        return new ModelMapper().map(serviceEntity, ServiceDto.class);
    }


    @Override
    public ServiceDto deleteUser(String serviceId) {
        return null;
    }
}
