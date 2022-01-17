package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.services.SiteService;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.repositories.SiteRepository;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import net.yeoman.nmpcaport.shared.dto.SiteDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private FundServiceImpl fundService;

    @Autowired
    private ServiceServiceImpl serviceService;

    @Autowired HealthCenterServiceImpl healthCenterService;

    @Autowired
    private NMHouseDistrictServiceImpl nmHouseDistrictService;

    @Autowired
    private SenateDistrictServiceImpl senateDistrictService;

    @Autowired
    private CongressionalDistrictServiceImpl congressionalDistrictService;

    @Autowired
    private SiteFundingDetailsServiceImpl siteFundingDetailsService;

    @Autowired
    private SiteServiceDetailsServiceImpl siteServiceDetailsService;

    @Autowired
    private Utils utils;

    @Override
    public SiteDto getSite(String siteId) {

        SiteEntity siteEntity = this.siteRepository.findBySiteId(siteId);

        return new ModelMapper().map(siteEntity, SiteDto.class);
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
    public void createSiteBulk(List<SiteDetailsRequestModel> siteDetailsRequestModels,
                                        HealthCenterEntity healthCenterEntity) {

        if(this.healthCenterService.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(this.requestIsNull(siteDetailsRequestModels))
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        this.dtoToEntity(this.requestToDto(siteDetailsRequestModels), healthCenterEntity);

    }

    @Override
    public SiteEntity generateUniqueSiteId(SiteEntity siteEntity) {

        if(this.entityIsNull(siteEntity)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        siteEntity.setSiteId(utils.generateRandomID());

        while(this.siteRepository.existsBySiteId(siteEntity.getSiteId())){

            siteEntity.setSiteId(utils.generateRandomID());
        }

        return siteEntity;
    }

    @Override
    public SiteEntity saveSite(SiteEntity siteEntity) {
        return this.siteRepository.save(siteEntity);
    }

    @Override
    public SiteDto entityToDto(SiteEntity siteEntity) {

        if(entityIsNull(siteEntity)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //convert entity to DTO
        SiteDto siteDto = this.utils.objectMapper().map(siteEntity, SiteDto.class);

        //check for services
        if(siteDto.getSiteServiceDetailsEntities() != null) {

            //convert services from entity to response
            siteDto.setServiceNestedResponses(this.serviceService.dtoToNestedResponse(
                    this.serviceService.entityToDto(
                            this.siteServiceDetailsService.getServiceEntities(
                                    siteDto.getSiteServiceDetailsEntities()))));
        }

        //check for funding
        if(!this.siteFundingDetailsService.entityIsNull(siteDto.getSiteFundingDetailsEntities())){

            //convert fund entity to response
            siteDto.setFundNestedResponses(this.fundService.dtoToNestedResponse(
                    this.fundService.entityToDto(
                            this.siteFundingDetailsService.getFundEntities(
                                    siteDto.getSiteFundingDetailsEntities()))));
        }

        //check for city entity
        if(!this.cityService.entityIsNull(siteDto.getCityEntity())){

            //convert entity to response
            siteDto.setCityResponse(this.cityService.dtoToResponse(
                    this.cityService.entityToDto(siteDto.getCityEntity())));
        }

        //check for county entity
        if(!this.countyService.entityIsNull(siteDto.getCountyEntity())){

            //convert entity to response
            siteDto.setCountyResponse(this.countyService.dtoToResponse(
                    this.countyService.entityToDto(siteDto.getCountyEntity())));
        }

        //check for zip code entity
        if(!this.zipCodeService.entityIsNull(siteDto.getZipCodeEntity())){

            //convert entity to response
            siteDto.setZipCodeResponse(this.zipCodeService.dtoToResponse(
                    this.zipCodeService.entityToDto(siteDto.getZipCodeEntity())));
        }

        //check for nm house entity
        if(!this.nmHouseDistrictService.entityIsNull(siteDto.getNmHouseDistrictEntity())){
            //convert entity to nested response
            siteDto.setNmHouseDistrictNestedResponse(this.nmHouseDistrictService.dtoToNestedResponse(
                    this.nmHouseDistrictService.entityToDto(
                            siteDto.getNmHouseDistrictEntity())));
        }

        //check for senate entity
        if(!this.senateDistrictService.entityIsNull(siteDto.getSenateDistrictEntity())){
            //convert entity to nested response
            siteDto.setSenateDistrictNestedResponse(this.senateDistrictService.dtoToNestedResponse(
                    this.senateDistrictService.entityToDto(
                            siteDto.getSenateDistrictEntity())));
        }

        //check for congressional entity
        if(!this.congressionalDistrictService.entityIsNull(siteDto.getCongressionalDistrictEntity())){
            //convert entity to nested response
            siteDto.setCongressionalDistrictNestedResponse(this.congressionalDistrictService.dtoToNestedResponse(
                    this.congressionalDistrictService.entityToDto(
                            siteDto.getCongressionalDistrictEntity())));
        }

        return siteDto;
    }

    @Override
    public List<SiteDto> entityToDto(List<SiteEntity> siteEntities) {

        if(this.entityIsNull(siteEntities)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SiteDto> returnValue = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            returnValue.add(this.entityToDto(siteEntity));
        }

        return returnValue;
    }


    @Override
    public void dtoToEntity(SiteDto siteDto, HealthCenterEntity healthCenterEntity) {

        if(this.dtoIsNull(siteDto)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(this.healthCenterService.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        SiteEntity siteEntity = this.generateUniqueSiteId(
                this.utils.objectMapper().map(siteDto, SiteEntity.class)
        );

        if(siteDto.getCity() != null){

            siteEntity.setCityEntity(
                    this.cityService.findCity(
                            siteDto.getCity()));
        }

        if(siteDto.getCounty() != null){

            siteEntity.setCountyEntity(
                    this.countyService.findCountyEntity(
                            siteDto.getCounty()
                    )
            );
        }

        if(siteDto.getZipCode() != null){

            siteEntity.setZipCodeEntity(
                    this.zipCodeService.getZipCodeEntity(
                            siteDto.getZipCode()
                    )
            );
        }

        if(siteDto.getNmHouseDistrict() != null){

            siteEntity.setNmHouseDistrictEntity(
                    this.nmHouseDistrictService.findNMHouseDistrictEntity(
                            siteDto.getNmHouseDistrict()
                    )
            );
        }

        if(siteDto.getSenateDistrict() != null){

            siteEntity.setSenateDistrictEntity(
                    this.senateDistrictService.findSenateDistrictEntity(
                            siteDto.getSenateDistrict()
                    )
            );
        }

        if(siteDto.getCongressionalDistrict() != null){

            siteEntity.setCongressionalDistrictEntity(
                    this.congressionalDistrictService.getCongressionalDistrictEntity(
                            siteDto.getCongressionalDistrict()
                    )
            );
        }

        siteEntity.setHealthCenterEntity(healthCenterEntity);

        SiteEntity savedSiteEntity = this.saveSite(siteEntity);

        if(siteDto.getService() != null){

            this.siteServiceDetailsService.linkServicesToSites(
                    this.serviceService.getServices(siteDto.getService()), savedSiteEntity
            );
        }

        if(siteDto.getFund() != null){

            this.siteFundingDetailsService.linkFundingToSites(
                    this.fundService.getFunds(siteDto.getFund()), savedSiteEntity
            );
        }


        return;
    }


    @Override
    public void dtoToEntity(List<SiteDto> siteDtoList, HealthCenterEntity healthCenterEntity) {

        if(this.dtoIsNull(siteDtoList)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        for(SiteDto siteDto: siteDtoList){

            this.dtoToEntity(siteDto, healthCenterEntity);
        }

        return;
    }


    @Override
    public SiteDto requestToDto(SiteDetailsRequestModel siteDetailsRequestModel) {

        if(this.requestIsNull(siteDetailsRequestModel)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(siteDetailsRequestModel, SiteDto.class);
    }


    @Override
    public List<SiteDto> requestToDto(List<SiteDetailsRequestModel> siteDetailsRequestModels) {

        if(this.requestIsNull(siteDetailsRequestModels)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SiteDto> returnValue = new ArrayList<>();

        for(SiteDetailsRequestModel siteRequest: siteDetailsRequestModels){

            returnValue.add(this.requestToDto(siteRequest));
        }


        return returnValue;
    }

    @Override
    public SiteDetailsNestedResponse dtoToNestedResponse(SiteDto siteDto) {

        if(this.dtoIsNull(siteDto)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return utils.objectMapper().map(siteDto, SiteDetailsNestedResponse.class);
    }

    @Override
    public List<SiteDetailsNestedResponse> dtoNestedResponse(List<SiteDto> siteDtoList) {

        if(this.dtoIsNull(siteDtoList)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SiteDetailsNestedResponse> returnValue = new ArrayList<>();

        for(SiteDto siteDto: siteDtoList){

            returnValue.add(this.dtoToNestedResponse(siteDto));
        }

        return returnValue;
    }

    @Override
    public List<SiteDetailsNestedResponse> entityToNestedResponse(List<SiteEntity> siteEntities) {

        if(this.entityIsNull(siteEntities))
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SiteDto> siteDtoList = this.entityToDto(siteEntities);

        return this.dtoNestedResponse(siteDtoList);
    }

    @Override
    public Boolean entityIsNull(SiteEntity siteEntity) {
        return siteEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<SiteEntity> siteEntities) {
        return siteEntities == null;
    }

    @Override
    public Boolean dtoIsNull(SiteDto siteDto) {
        return siteDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<SiteDto> siteDtoList) {
        return siteDtoList == null;
    }

    @Override
    public Boolean requestIsNull(SiteDetailsRequestModel siteDetailsRequestModel) {
        return siteDetailsRequestModel == null;
    }

    @Override
    public Boolean requestIsNull(List<SiteDetailsRequestModel> siteDetailsRequestModels) {
        return siteDetailsRequestModels == null;
    }

    @Override
    public Boolean siteExist(String publicId) {

        return this.siteRepository.existsBySiteId(publicId);
    }
}
