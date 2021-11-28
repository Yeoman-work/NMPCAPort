package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;

public interface HealthCenterService {

    public HealthCenterDto getHealthCenter(String healthCenterId);
    public HealthCenterResponseModel createHealthCenter(HealthCenterDto healthCenterDto);
    public HealthCenterDto updateHealthCenter(String healthCenterId, HealthCenterDto healthCenterDto);
    public HealthCenterDto deleteHealthCenter(String healthCenterId);
    public HealthCenterEntity getHealthCenterEntity(String healthCenterId);

}
