package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.ServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.io.response.service.ServiceEssentialsResponse;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {


    private final ServiceRepository serviceRepository;

    private final SiteServiceImpl siteService;

    private final SiteServiceDetailsServiceImpl siteServiceDetailsService;

    private final Utils utils;

    public ServiceServiceImpl(ServiceRepository serviceRepository,
                              SiteServiceImpl siteService,
                              SiteServiceDetailsServiceImpl siteServiceDetailsService,
                              Utils utils
    ){
        this.serviceRepository = serviceRepository;
        this.siteService = siteService;
        this.siteServiceDetailsService = siteServiceDetailsService;
        this.utils = utils;
    }

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
    public ServiceEssentialsResponse getServiceEssentialsFromSite(SiteEntity siteEntity) {

        if(this.siteService.entityIsNull(siteEntity))
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ServiceEssentialsResponse serviceEssentialsResponse = new ServiceEssentialsResponse();

        serviceEssentialsResponse.setName(siteEntity.getName());
        serviceEssentialsResponse.setServiceId(siteEntity.getSiteId());

        return serviceEssentialsResponse;
    }

    @Override
    public List<ServiceEssentialsResponse> getServiceEssentialsFromSite(List<SiteEntity> siteEntities) {

        if(this.siteService.entityIsNull(siteEntities))
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceEssentialsResponse> returnValue = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            returnValue.add(this.getServiceEssentialsFromSite(siteEntity));
        }

        return returnValue.stream()
                .sorted(Comparator.comparing(ServiceEssentialsResponse :: getName))
                .collect(Collectors.toList());
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
    public ServiceNestedResponse entityToNestedResponse(ServiceEntity serviceEntity) {

        if(this.entityIsNull(serviceEntity)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ServiceDto serviceDto = this.entityToDto(serviceEntity);

        return this.dtoToNestedResponse(serviceDto);
    }

    @Override
    public List<ServiceNestedResponse> entityToNestedResponse(List<ServiceEntity> serviceEntities) {

        if(this.entityIsNull(serviceEntities))
            throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceNestedResponse> returnValue = new ArrayList<>();

        for(ServiceEntity serviceEntity: serviceEntities){

            returnValue.add(this.entityToNestedResponse(serviceEntity));
        }

        return returnValue;
    }

    @Override
    public ServiceEssentialsResponse entityToEssential(ServiceEntity serviceEntity) {

        if(this.entityIsNull(serviceEntity))
            throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(serviceEntity, ServiceEssentialsResponse.class);
    }

    @Override
    public List<ServiceEssentialsResponse> entityToEssential(List<ServiceEntity> serviceEntities) {

        if(this.entityIsNull(serviceEntities))
            throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceEssentialsResponse> returnValue = new ArrayList<>();

        for(ServiceEntity serviceEntity: serviceEntities){

            returnValue.add(this.entityToEssential(serviceEntity));
        }

        return returnValue;
    }

    @Override
    public ServiceDto entityToDto(ServiceEntity serviceEntity) {

        if(this.entityIsNull(serviceEntity)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ServiceDto returnValue = this.utils.objectMapper().map(serviceEntity, ServiceDto.class);

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
        return serviceEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<ServiceEntity> serviceEntities) {
        return serviceEntities == null;
    }

    @Override
    public Boolean dtoIsNull(ServiceDto serviceDto) {
        return serviceDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<ServiceDto> serviceDtoList) {
        return serviceDtoList == null;
    }

    @Override
    public Boolean essentialIsNull(ServiceEssentialsResponse serviceEssentialsResponse) {
        return  serviceEssentialsResponse == null;
    }

    @Override
    public Boolean essentialIsNull(List<ServiceEssentialsResponse> serviceEssentialsResponses) {
        return serviceEssentialsResponses == null;
    }

    @Override
    public ServiceEntity getService(String serviceId)   {

        if(serviceId == null) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.serviceRepository.findByServiceId(serviceId);
    }

    @Override
    public List<ServiceEntity> getServices(List<String> serviceIds) {

        if(serviceIds == null) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceEntity> returnValue = new ArrayList<>();

        for(String serviceId: serviceIds){

            returnValue.add(this.getService(serviceId));
        }

        return returnValue;
    }


    @Override
    public ServiceEntity deleteUser(String serviceId) {
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
