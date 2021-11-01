package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.repositories.SiteRepository;
import net.yeoman.nmpcaport.services.SiteService;
import net.yeoman.nmpcaport.shared.dto.SiteDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CountyServiceImpl countyService;

    @Autowired
    private ZipCodeServiceImpl zipCodeService;

    @Autowired HealthCenterServiceImpl healthCenterService;

    @Autowired
    private Utils utils;

    @Override
    public SiteDto getSite(String siteId) {

        SiteEntity siteEntity = this.siteRepository.findBySiteId(siteId);

        return new ModelMapper().map(siteEntity, SiteDto.class);
    }

    @Override
    public SiteDto createSite(SiteDetailsRequestModel site) {

        SiteDto siteDto = convertFromRequestToDto(site);

        siteDto.setSiteId(utils.generateRandomID());
        siteDto.setCityResponse(new ModelMapper().map(siteDto.getCity(), CityResponse.class));
        siteDto.setCountyResponse(new ModelMapper().map(siteDto.getCounty(), CountyResponse.class));
        siteDto.setZipCodeResponse(new ModelMapper().map(siteDto.getZipCode(), ZipCodeResponse.class));
        siteDto.setHealthCenterResponse(new ModelMapper().map(siteDto.getHealthCenter(), HealthCenterResponseModel.class));

        SiteEntity siteEntity = new ModelMapper().map(siteDto, SiteEntity.class);

        this.siteRepository.save(siteEntity);

        return  siteDto;
    }



    @Override
    public SiteDto updatedSite(SiteDto site) {
        return null;
    }

    @Override
    public SiteEntity getSiteEntity(String siteId) {
        return null;
    }

    @Override
    public void deleteSite(String siteId) {

    }

    @Override
    public SiteDto convertFromRequestToDto(SiteDetailsRequestModel siteRequest) {

        SiteDto siteDto = new SiteDto();

        siteDto.setName(siteRequest.getName());

        if(siteRequest.getCityId() != null){

            siteDto.setCity(this.cityService.findCity(siteRequest.getCityId()));
        }

        if(siteRequest.getCountyId() != null){

            siteDto.setCounty(this.countyService.findCountyEntity(siteRequest.getCountyId()));
        }

        if(siteRequest.getZipCodeId() != null){

            siteDto.setZipCode(this.zipCodeService.getZipCodeEntity(siteRequest.getZipCodeId()));
        }

        if(siteRequest.getHealthCenterId() != null){

            siteDto.setHealthCenter(this.healthCenterService.getHealthCenterEntity(siteRequest.getHealthCenterId()));
        }


        return siteDto;
    }
}
