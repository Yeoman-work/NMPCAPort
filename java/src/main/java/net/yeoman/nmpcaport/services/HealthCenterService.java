package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.*;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.fund.FundEssentialsResponse;
import net.yeoman.nmpcaport.io.response.fund.FundNestedResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceEssentialsResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceNestedResponse;
import net.yeoman.nmpcaport.io.response.site.SiteEssentialsResponse;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;

import java.util.List;

public interface HealthCenterService {



    void deleteHealthCenter(String healthCenterId);

    List<HealthCenterDto> getAllHealthCenters();

    //create healthCenter
    public void createHealthCenter(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel);

    //health Center response
    HealthCenterResponseModel getHealthCenterResponse(HealthCenterEntity healthCenterEntity);

    //return entity
    HealthCenterEntity generateHealthCenterWithUniqueId(HealthCenterEntity healthCenter);
    HealthCenterEntity getHealthCenterEntity(String healthCenterId);
    HealthCenterEntity saveHealthCenterEntity(HealthCenterEntity healthCenterEntity);
    List<HealthCenterEntity> getHealthCenterEntities(int page, int limit);

    //entity to dashboard view
    HealthCenterDashBoard entityToDashBoardData(HealthCenterEntity healthCenterEntity);
    List<HealthCenterDashBoard> entityToDashBoardData(List<HealthCenterEntity> healthCenterEntityList);

    //health center essentials
    HealthCenterEssentials healthCenterEssentials(HealthCenterEntity healthEntity);

    //get dashboard data
    List<HealthCenterDashBoard> healthCenterDashBoard(int page, int limit);

    //check if object is null
    Boolean entityIsNull(HealthCenterEntity healthCenter);
    Boolean entityIsNull(List<HealthCenterEntity> healthCenterEntityList);
    Boolean responseIsNull(List<HealthCenterResponseModel> healthCenterResponseModelList);
    Boolean responseIsNull(HealthCenterResponseModel healthCenterResponseModel);
    Boolean requestIsNull(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel);
    Boolean requestIsNull(List<HealthCenterDetailsRequestModel> healthCenterDetailsRequestModelList);

}
