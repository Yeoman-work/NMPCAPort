package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.repositories.SiteRepository;
import net.yeoman.nmpcaport.services.HealthCenterService;
import net.yeoman.nmpcaport.services.SiteService;
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

    @Override
    public List<SiteDto> createSiteBulk(List<SiteDto> siteDtoList) {
        List<SiteDto> returnValue = new ArrayList<>();
        List<SiteEntity> savedSites = new ArrayList<>();
        List<ServiceEntity> serviceEntitiesList = new ArrayList<>();
        List<FundEntity> fundEntityList = new ArrayList<>();

        //gather all services
        for(SiteDto site: siteDtoList){

            for(String serviceId: site.getServiceIds()){

                ServiceEntity service = this.serviceService.getServiceEntity(serviceId);

                if(service == null) throw new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                if(!serviceEntitiesList.contains(service)){

                    serviceEntitiesList.add(service);
                }
            }
        }

        //gather all funds
        for(SiteDto site: siteDtoList){

            for(String fundId: site.getFundIds()){

                FundEntity fundEntity = this.fundService.getFundEntity(fundId);

                if(fundEntity == null) throw new FundServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                if(!fundEntityList.contains(fundEntity)){

                    fundEntityList.add(fundEntity);
                }
            }
        }


        for(SiteDto site: siteDtoList){

            SiteEntity siteEntity = new ModelMapper().map(site, SiteEntity.class);

            //get county
            CountyEntity countyEntity = this.countyService.findCountyEntity(site.getCountyId());

            // check if county is null throw error
            if(countyEntity == null) throw new CountyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set county for site
            siteEntity.setCounty(countyEntity);

            //get city
            CityEntity cityEntity = this.cityService.findCity(site.getCityId());

            //check if county is null and throw error
            if(cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set city for site
            siteEntity.setCity(cityEntity);

            //get zipcode for site
            ZipCodeEntity zipCodeEntity = this.zipCodeService.getZipCodeEntity(site.getZipCodeId());

            // check if zip code is null if so throw error
            if(zipCodeEntity == null) throw new ZipCodeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set zip code for site
            siteEntity.setZipCode(this.zipCodeService.getZipCodeEntity(site.getZipCodeId()));

            //get healthCenter
            HealthCenterEntity healthCenterEntity = this.healthCenterService.getHealthCenterEntity(site.getHealthCenterId());

            // check if health center is null, if so throw error
            if(healthCenterEntity == null) throw new HealthCenterServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set health center for site
            siteEntity.setHealthCenter(healthCenterEntity);

            // senate district field is not required
            // check for senate district id
            if(!site.getSenateDistrictId().isEmpty()){

                //get senate district
                SenateDistrictEntity senateDistrictEntity = this.senateDistrictService.findSenateDistrictEntity(site.getSenateDistrictId());

                //check if senate district is null, if so throw error
                if(senateDistrictEntity == null) throw new SenateDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                //set senate district to site
                siteEntity.setSenateDistrict(senateDistrictEntity);
            }
            //field not required
            // check for house district id
            if(!site.getNmHouseDistrictId().isEmpty()){

                //get nmHouseDistrict
                NMHouseDistrictEntity nmHouseDistrictEntity = this.nmHouseDistrictService.findNMHouseDistrictEntity(site.getNmHouseDistrictId());

                //check if house district is null, if so throw error
                if(nmHouseDistrictEntity == null) throw new NMHouseDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                //set nm house district to site
                siteEntity.setNmHouseDistrict(nmHouseDistrictEntity);
            }

            //field is not required
            // check for congressional district
            if(!site.getCongressionalDistrictId().isEmpty()){

                //get congressional district
                CongressionalDistrictEntity congressionalDistrictEntity = this.congressionalDistrictService.getCongressionalDistrictEntity(site.getCongressionalDistrictId());

                //check if congressional district is null if so throw error
                if(congressionalDistrictEntity == null) throw new CongressionalDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                //set congressional district to site
                siteEntity.setCongressionalDistrictEntity(congressionalDistrictEntity);

            }
            //assigns fund to the site entity
            if(site.getFundIds().size() > 0){
                List<FundEntity> fundingEntities = siteEntity.getFundEntities();
                for(String fundId: site.getFundIds()){

                    for(FundEntity fundEntity: fundEntityList){

                        if(fundEntity.getFundId() == fundId){

                            fundingEntities.add(fundEntity);
                            break;

                        }
                    }

                }
                siteEntity.setFundEntities(fundingEntities);
            }

            //assigns service to all entities;
            if(site.getServiceIds().size() > 0){
                List<ServiceEntity> serviceEntities = siteEntity.getServices();
                for(String serviceId: site.getServiceIds()){

                    for(ServiceEntity serviceEntity: serviceEntitiesList){

                        if(serviceEntity.getServiceId() == serviceId){

                            serviceEntities.add(serviceEntity);
                            break;

                        }

                    }
                }

                siteEntity.setServices(serviceEntities);
            }

            SiteEntity savedSiteEntity = this.siteRepository.save(siteEntity);

            savedSites.add(savedSiteEntity);

        }

        for(SiteEntity site: savedSites){
            returnValue.add(new ModelMapper().map(site, SiteDto.class));
        }

        return returnValue;
    }
}
