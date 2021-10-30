package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;

public interface HealthCenterService {

    HealthCenterDto getHealthCenter(String healthCenterId);
    HealthCenterResponseModel createHealthCenter(HealthCenterDto healthCenterDto);
    HealthCenterDto updateHealthCenter(String healthCenterId, HealthCenterDto healthCenterDto);
    HealthCenterDto deleteHealthCenter(String healthCenterId);

}
