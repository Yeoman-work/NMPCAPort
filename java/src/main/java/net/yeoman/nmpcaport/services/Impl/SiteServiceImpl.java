package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.site.SiteEssentialsResponse;
import net.yeoman.nmpcaport.services.SiteService;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.repositories.SiteRepository;
import net.yeoman.nmpcaport.shared.dto.SiteDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {


    private final SiteRepository siteRepository;
    private final CityServiceImpl cityService;
    private final CountyServiceImpl countyService;
    private final ZipCodeServiceImpl zipCodeService;
    private final FundServiceImpl fundService;
    private final ServiceServiceImpl serviceService;
    private final HealthCenterServiceImpl healthCenterService;
    private final HouseDistrictServiceImpl houseDistrictService;
    private final SenateDistrictServiceImpl senateDistrictService;
    private final CongressionalDistrictServiceImpl congressionalDistrictService;
    private final SiteFundingDetailsServiceImpl siteFundingDetailsService;
    private final SiteServiceDetailsServiceImpl siteServiceDetailsService;
    private final Utils utils;

    public SiteServiceImpl(SiteRepository siteRepository,
                           CityServiceImpl cityService,
                           CountyServiceImpl countyService,
                           ZipCodeServiceImpl zipCodeService,
                           FundServiceImpl fundService,
                           ServiceServiceImpl serviceService,
                           HealthCenterServiceImpl healthCenterService,
                           HouseDistrictServiceImpl houseDistrictService,
                           SenateDistrictServiceImpl senateDistrictService,
                           CongressionalDistrictServiceImpl congressionalDistrictService,
                           SiteFundingDetailsServiceImpl siteFundingDetailsService,
                           SiteServiceDetailsServiceImpl siteServiceDetailsService,
                           Utils utils
    ){
        this.siteRepository = siteRepository;
        this.cityService = cityService;
        this.countyService = countyService;
        this.zipCodeService = zipCodeService;
        this.fundService = fundService;
        this.serviceService = serviceService;
        this.healthCenterService = healthCenterService;
        this.houseDistrictService = houseDistrictService;
        this.senateDistrictService = senateDistrictService;
        this.congressionalDistrictService = congressionalDistrictService;
        this.siteServiceDetailsService = siteServiceDetailsService;
        this.siteFundingDetailsService = siteFundingDetailsService;
        this.utils = utils;

    }


    @Override
    public SiteEntity getSiteEntity(String siteId) {

        return this.siteRepository.findBySiteId(siteId);
    }

    @Override
    public void deleteSite(String siteId) {

        this.siteRepository.delete(
                this.siteRepository.findBySiteId(siteId)
        );
    }

    @Override
    public void createSiteEntity(SiteDetailsRequestModel siteDetailsRequestModel) {

        if(this.requestIsNull(siteDetailsRequestModel))
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //create site entity with unique id
        SiteEntity siteEntity = this.generateUniqueSiteId(new SiteEntity());

        //set name
        siteEntity.setName(siteDetailsRequestModel.getName());

        //set address
        siteEntity.setStreetAddress(siteDetailsRequestModel.getStreetAddress());

        //check for city id
        if(siteDetailsRequestModel.getCity() == null)
            throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //get city from DB
        CityEntity cityEntity = this.cityService.findCity(siteDetailsRequestModel.getCity());

        //check if city is null
        if(cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //set city entity
        siteEntity.setCityEntity(cityEntity);

        //check for county id
        if(siteDetailsRequestModel.getCounty() == null)
            throw new CountyServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //check that county is not null
        CountyEntity countyEntity = this.countyService.findCountyEntity(siteDetailsRequestModel.getCounty());

        //county entity is null
        if(countyEntity == null) throw new CountyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //check county entity
        siteEntity.setCountyEntity(countyEntity);

        //check for zip code id
        if(siteDetailsRequestModel.getZipCode() != null)
            throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //get zipCode from db
        ZipCodeEntity zipCodeEntity = this.zipCodeService.getZipCodeEntity(siteDetailsRequestModel.getZipCode());

        //check if zipcode is null
        if(zipCodeEntity == null) throw new ZipCodeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //set zip code
        siteEntity.setZipCodeEntity(zipCodeEntity);

        //check for health center
        if(siteDetailsRequestModel.getHealthCenter() == null)
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //get health center from db
         HealthCenterEntity healthCenterEntity = this.healthCenterService.getHealthCenterEntity(
                 siteDetailsRequestModel.getHealthCenter()
         );

         if(healthCenterEntity == null)
             throw new HealthCenterServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

         siteEntity.setHealthCenterEntity(healthCenterEntity);

         if(siteDetailsRequestModel.getHouseDistrict() != null){

             HouseDistrictEntity houseDistrictEntity = this.houseDistrictService.getHouseDistrict(
                     siteDetailsRequestModel.getHouseDistrict()
             );

             if(houseDistrictEntity != null){

                 siteEntity.setHouseDistrictEntity(houseDistrictEntity);
             }
         }

         if(siteDetailsRequestModel.getSenateDistrict() != null){

             SenateDistrictEntity senateDistrictEntity = this.senateDistrictService.findSenateDistrictEntity(
                     siteDetailsRequestModel.getSenateDistrict()
             );

             if(senateDistrictEntity != null){

                 siteEntity.setSenateDistrictEntity(senateDistrictEntity);
             }
         }


         if(siteDetailsRequestModel.getCongressionalDistrict() != null){

             CongressionalDistrictEntity congressionalDistrictEntity =
                     this.congressionalDistrictService.getCongressionalDistrictEntity(
                             siteDetailsRequestModel.getCongressionalDistrict()
                     );

             if(congressionalDistrictEntity != null){

                 siteEntity.setCongressionalDistrictEntity(congressionalDistrictEntity);
             }
         }

         SiteEntity savedSiteEntity = this.saveSite(siteEntity);

         if(this.entityIsNull(savedSiteEntity))
             throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

         if(siteDetailsRequestModel.getService() != null ){

             List<ServiceEntity> serviceEntities = this.serviceService.getServices(
                     siteDetailsRequestModel.getService()
             );

             this.siteServiceDetailsService.linkServicesToSites(serviceEntities, savedSiteEntity);
         }

         if(siteDetailsRequestModel.getFunding() != null){

             List<FundEntity> fundEntities = this.fundService.getFundEntities(
                     siteDetailsRequestModel.getService()
             );

             this.siteFundingDetailsService.linkFundingToSites(fundEntities, savedSiteEntity);
         }

    }

    @Override
    public void createSitesBulk(List<SiteDetailsRequestModel> siteDetailsRequestModels, HealthCenterEntity healthCenterEntity) {

        if(this.requestIsNull(siteDetailsRequestModels))
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());



        for(SiteDetailsRequestModel request: siteDetailsRequestModels){

            this.createSiteEntity(request);
        }

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
    public SiteEssentialsResponse entityToEssential(SiteEntity siteEntity) {

        if(this.entityIsNull(siteEntity)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        SiteEssentialsResponse siteEssentialsResponse = new SiteEssentialsResponse();

        siteEssentialsResponse.setName(siteEntity.getName());
        siteEssentialsResponse.setSiteId(siteEntity.getSiteId());
        siteEssentialsResponse.setStreetAddress(siteEntity.getStreetAddress());
        siteEssentialsResponse.setCityEssentials(this.cityService.entityToEssentials(siteEntity.getCityEntity()));
        siteEssentialsResponse.setCountyEssentials(this.countyService.entityToEssentials(siteEntity.getCountyEntity()));
        siteEssentialsResponse.setZipCodeEssentials(this.zipCodeService.entityToEssentials(siteEntity.getZipCodeEntity()));

        List<ServiceEntity> serviceEntities = this.siteServiceDetailsService.getServiceEntities(
                siteEntity.getServiceDetailsEntities()
        );


        return siteEssentialsResponse;
    }

    @Override
    public List<SiteEssentialsResponse> entityToEssential(List<SiteEntity> siteEntities) {

        if(this.entityIsNull(siteEntities)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SiteEssentialsResponse> returnValue = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            returnValue.add(this.entityToEssential(siteEntity));
        }

        return returnValue;
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
