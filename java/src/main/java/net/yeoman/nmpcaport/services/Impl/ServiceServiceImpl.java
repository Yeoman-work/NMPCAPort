package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.ServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.io.response.service.ServiceNestedResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
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
    private SiteServiceImpl siteService;

    @Autowired
    private SiteServiceDetailsServiceImpl siteServiceDetailsService;

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
    public ServiceResponse dtoToResponse(ServiceDto serviceDto) {

        if(this.dtoIsNull(serviceDto)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(serviceDto, ServiceResponse.class);
    }

    @Override
    public List<ServiceResponse> dtoToResponse(List<ServiceDto> serviceDtoList) {

        if(this.dtoIsNull(serviceDtoList)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceResponse> returnValue = new ArrayList<>();

        for(ServiceDto serviceDto: serviceDtoList){

            returnValue.add(this.dtoToResponse(serviceDto));
        }


        return returnValue;
    }

    @Override
    public ServiceNestedResponse dtoToNestedResponse(ServiceDto serviceDto) {

        if(this.dtoIsNull(serviceDto)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return utils.objectMapper().map(serviceDto, ServiceNestedResponse.class) ;
    }



    @Override
    public List<ServiceNestedResponse> dtoToNestedResponse(List<ServiceDto> serviceDtoList) {

        if(this.dtoIsNull(serviceDtoList)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceNestedResponse> returnValue = new ArrayList<>();

        for(ServiceDto serviceDto: serviceDtoList){

            returnValue.add(this.dtoToNestedResponse(serviceDto));
        }

        return returnValue;
    }

    @Override
    public ServiceDto entityToDto(ServiceEntity serviceEntity) {

        if(this.entityIsNull(serviceEntity)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ServiceDto returnValue = this.utils.objectMapper().map(serviceEntity, ServiceDto.class);

        if(returnValue.getSiteServiceDetailsEntities() != null){

            returnValue.setSiteDetailsNestedResponses(this.siteService.dtoNestedResponse(
                    this.siteService.entityToDto(this.siteServiceDetailsService.getSiteEntities(
                            returnValue.getSiteServiceDetailsEntities()))));
        }

        return returnValue;
    }

    @Override
    public List<ServiceDto> entityToDto(List<ServiceEntity> serviceEntities) {

        if(this.entityIsNull(serviceEntities)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceDto> returnValue = new ArrayList<>();

        for(ServiceEntity serviceEntity: serviceEntities){

            returnValue.add(this.entityToDto(serviceEntity));
        }

        return returnValue;
    }

    @Override
    public ServiceDto entityToDtoBase(ServiceEntity serviceEntity) {

        if(entityIsNull(serviceEntity)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(serviceEntity, ServiceDto.class);
    }

    @Override
    public List<ServiceDto> entityToDtoBase(List<ServiceEntity> serviceEntities) {

        if(entityIsNull(serviceEntities)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceDto> returnValue = new ArrayList<>();

        for(ServiceEntity serviceEntity: serviceEntities){

            returnValue.add(entityToDtoBase(serviceEntity));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(ServiceEntity serviceEntity) {
        return null;
    }

    @Override
    public Boolean entityIsNull(List<ServiceEntity> serviceEntities) {
        return null;
    }

    @Override
    public Boolean dtoIsNull(ServiceDto serviceDto) {
        return null;
    }

    @Override
    public Boolean dtoIsNull(List<ServiceDto> serviceDtoList) {
        return null;
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
