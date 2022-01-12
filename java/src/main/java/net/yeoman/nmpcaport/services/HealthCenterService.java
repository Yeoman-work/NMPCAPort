package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseBaseModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;

import java.util.List;

public interface HealthCenterService {

    public HealthCenterDto getHealthCenter(String healthCenterId);
    public HealthCenterResponseModel createHealthCenter(HealthCenterDto healthCenterDto);
    public HealthCenterDto updateHealthCenter(String healthCenterId, HealthCenterDto healthCenterDto);
    public HealthCenterDto deleteHealthCenter(String healthCenterId);
    public HealthCenterEntity getHealthCenterEntity(String healthCenterId);
    public HealthCenterEntity savedHealthCenterEntity(HealthCenterEntity healthCenterEntity);
    public List<HealthCenterDto> getHealthCenters(int page, int limit);
    public List<HealthCenterDto> getAllHealthCenters();
    public HealthCenterEntity generateHealthCenterWithUniqueHealthCenterId(HealthCenterEntity healthCenter);

    //convert entity to DTO
    public HealthCenterDto entityToHealthCenterDto(HealthCenterEntity healthCenterEntity);
    public List<HealthCenterDto> entityArrayToHealthCenterArray(List<HealthCenterEntity> healthCenterEntityList);

    //convert dto to entity
    public HealthCenterEntity dtoToHealthCenterEntity(HealthCenterDto healthCenterDto);
    public List<HealthCenterEntity> dtoArrayToEntityArray(List<HealthCenterDto> healthCenterDtoList);

    //convert dto to health center response
    public HealthCenterResponseModel dtoToHealthCenterResponse(HealthCenterDto healthCenterDto);
    public List<HealthCenterResponseModel> dtoToHealthCenterResponseModel(List<HealthCenterDto> healthCenterDtoList);

    public HealthCenterResponseBaseModel dtoToHealthCenterResponseBaseModel(HealthCenterDto healthCenterDto);
    public List<HealthCenterResponseBaseModel> dtoToHealthCenterResponseBaseModel(List<HealthCenterDto> healthCenterDtoList);
    public HealthCenterNestedResponseModel dtoToHealthCenterNestedResponseModel(HealthCenterDto healthCenterDto);
    public List<HealthCenterNestedResponseModel> dtoToHealthCenterNestedResponseModelArray(List<HealthCenterDto> healthCenterDtoList);
    public HealthCenterDetailsRequestModel dtoToHealthCenterDetailsRequestModel(HealthCenterDto healthCenterDto);
    public List<HealthCenterDetailsRequestModel> dtoToHealthCenterDetailsRequestModel(List<HealthCenterDto> healthCenterDtoList);
    public Boolean entityIsNull(HealthCenterEntity healthCenter);
    public Boolean entityArrayIsNull(List<HealthCenterEntity> healthCenterEntityList);
    public Boolean dtoIsNull(HealthCenterDto healthCenterDto);
    public Boolean dtoArrayIsNull(List<HealthCenterDto> healthCenterDtoList);
    public Boolean responseArrayIsNull(List<HealthCenterResponseModel> healthCenterResponseModelList);
    public Boolean responseIsNull(HealthCenterResponseModel healthCenterResponseModel);
    public Boolean requestIsNull(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel);
    public Boolean requestArrayIsNull(List<HealthCenterDetailsRequestModel> healthCenterDetailsRequestModelList);

}
