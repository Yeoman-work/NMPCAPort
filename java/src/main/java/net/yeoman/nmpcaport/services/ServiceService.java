package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ServiceEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteServiceDetailsEntity;
import net.yeoman.nmpcaport.io.request.service.ServiceRequestListModel;
import net.yeoman.nmpcaport.io.response.service.ServiceEssentialsResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceNestedResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
import net.yeoman.nmpcaport.shared.dto.ServiceDto;

import java.util.List;

public interface ServiceService {

    public List<ServiceDto> allServices();

    public ServiceDto createService(ServiceDto serviceDetails);

    //convert to response
    public ServiceResponse dtoToResponse(ServiceDto serviceDto);
    public List<ServiceResponse> dtoToResponse(List<ServiceDto> serviceDtoList);

    //get service essentials from site(s)
    public ServiceEssentialsResponse getServiceEssentialsFromSite(String serviceId, String serviceName);
    public List<ServiceEssentialsResponse> getServiceEssentialsFromSite(List<SiteEntity> siteEntities);

    //convert to nested Response
    public ServiceNestedResponse dtoToNestedResponse(ServiceDto serviceDto);
    public List<ServiceNestedResponse> dtoToNestedResponse(List<ServiceDto> serviceDtoList);

    //entity to nestedResponse
    public ServiceNestedResponse entityToNestedResponse(ServiceEntity serviceEntity);
    public List<ServiceNestedResponse> entityToNestedResponse(List<ServiceEntity> serviceEntities);

    //convert entity to essential
    public ServiceEssentialsResponse entityToEssential(ServiceEntity serviceEntity);
    public List<ServiceEssentialsResponse> entityToEssential(List<ServiceEntity> serviceEntities);

    //convert entity to dto
    public ServiceDto entityToDto(ServiceEntity serviceEntity);
    public List<ServiceDto> entityToDto(List<ServiceEntity> serviceEntities);

    public ServiceDto entityToDtoBase(ServiceEntity serviceEntity);
    public List<ServiceDto> entityToDtoBase(List<ServiceEntity> serviceEntities);

    //check if null
    public Boolean entityIsNull(ServiceEntity serviceEntity);
    public Boolean entityIsNull(List<ServiceEntity> serviceEntities);
    public Boolean dtoIsNull(ServiceDto serviceDto);
    public Boolean dtoIsNull(List<ServiceDto> serviceDtoList);
    public Boolean essentialIsNull(ServiceEssentialsResponse serviceEssentialsResponse);
    public Boolean essentialIsNull(List<ServiceEssentialsResponse> serviceEssentialsResponses);

    //find service by id
    public ServiceEntity getService(String serviceId);
    public List<ServiceEntity> getServices(List<String> serviceIds);
    public ServiceEntity deleteUser(String serviceId);

    public List<ServiceDto> createServiceBulk(ServiceRequestListModel serviceRequestList);

    public ServiceEntity getServiceEntity(String serviceId);
}
