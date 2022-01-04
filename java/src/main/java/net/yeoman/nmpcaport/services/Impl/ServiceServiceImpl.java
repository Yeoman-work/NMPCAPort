package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.services.ServiceService;
import net.yeoman.nmpcaport.entities.ServiceEntity;
import net.yeoman.nmpcaport.io.request.service.ServiceDetailsRequestModel;
import net.yeoman.nmpcaport.io.request.service.ServiceRequestListModel;
import net.yeoman.nmpcaport.io.repositories.ServiceRepository;
import net.yeoman.nmpcaport.shared.dto.ServiceDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private Utils utils;

    @Override
    public List<ServiceDto> allServices() {
        List<ServiceDto> returnValue = new ArrayList<>();

        List<ServiceEntity> serviceEntityList = this.serviceRepository.findAll();

        for(ServiceEntity serviceEntity: serviceEntityList){

            returnValue.add(new ModelMapper().map(serviceEntity, ServiceDto.class));
        }

        return returnValue;
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

    @Override
    public List<ServiceDto> createServiceBulk(ServiceRequestListModel serviceDetailsRequestModelList) {
        List<ServiceDto> converted = new ArrayList<>();
        List<ServiceEntity> serviceEntities = new ArrayList<>();

        for(ServiceDetailsRequestModel service: serviceDetailsRequestModelList.getServiceRequestList()){


            converted.add(new ModelMapper().map(service, ServiceDto.class));
        }

        for(ServiceDto serviceDto: converted ){

            serviceEntities.add(new ModelMapper().map(serviceDto, ServiceEntity.class));
        }

        converted.clear();

        for(ServiceEntity serviceEntity: serviceEntities){

            serviceEntity.setServiceId(utils.generateRandomID());
            ServiceEntity service = this.serviceRepository.save(serviceEntity);

            converted.add(new ModelMapper().map(service, ServiceDto.class));
        }

        return converted;

    }

    @Override
    public ServiceEntity getServiceEntity(String serviceId) {

        return this.serviceRepository.findByServiceId(serviceId);
    }


}
