package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.repositories.SiteRepository;
import net.yeoman.nmpcaport.services.SiteFundingDetailsService;
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
    public List<SiteDto> createSiteBulk(List<SiteDto> siteDtoList, String healthCenterId) {


        //return value list
        List<SiteDto> returnValue = new ArrayList<>();

//        //list of service from all sites
//        List<ServiceEntity> serviceEntitiesList = new ArrayList<>();
//
//        //list of Entities from all sites
//        List<FundEntity> fundEntityList = new ArrayList<>();

        //get all congressional districts
        List<String> congressionalDistrictIdList = new ArrayList<>();
        List<CongressionalDistrictEntity> congressionalDistrictEntities = new ArrayList<>();

        //get all senate district sites
        List<String> senateDistrictIdList = new ArrayList<>();
        List<SenateDistrictEntity> senateDistrictEntities = new ArrayList<>();

        //get all state house districts
        List<String> houseDistrictIdList = new ArrayList<>();
        List<NMHouseDistrictEntity> nmHouseDistrictEntities = new ArrayList<>();

        //get healthCenter
        HealthCenterEntity healthCenterEntity = this.healthCenterService.getHealthCenterEntity(healthCenterId);

        // check if health center is null, if so throw error
        if (healthCenterEntity == null)
            throw new HealthCenterServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //gather all districts
        for (SiteDto site : siteDtoList) {
            // get all congressional districts
            if (!congressionalDistrictIdList.contains(site.getCongressionalDistrict())) {

                //congressional district
                CongressionalDistrictEntity congressionalDistrict = this.congressionalDistrictService.getCongressionalDistrictEntity(site.getCongressionalDistrict());

                if (congressionalDistrict == null)
                    throw new CongressionalDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                congressionalDistrictEntities.add(congressionalDistrict);
                congressionalDistrictIdList.add(site.getCongressionalDistrict());
            }

            //get all senate districts
            if (!senateDistrictIdList.contains(site.getSenateDistrict())) {

                SenateDistrictEntity senateDistrict = this.senateDistrictService.findSenateDistrictEntity(site.getSenateDistrict());

                if (senateDistrict == null)
                    throw new SenateDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                senateDistrictEntities.add(senateDistrict);
                senateDistrictIdList.add(site.getSenateDistrict());
            }
            //get all house districts
            if (!houseDistrictIdList.contains(site.getNmHouseDistrict())) {

                NMHouseDistrictEntity nmHouseDistrict = this.nmHouseDistrictService.findNMHouseDistrictEntity(site.getNmHouseDistrict());

                if (nmHouseDistrict == null)
                    throw new NMHouseDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                nmHouseDistrictEntities.add(nmHouseDistrict);
                houseDistrictIdList.contains(site.getNmHouseDistrict());
            }
        }


//        //gather all services
//        for (SiteDto site : siteDtoList) {
//            //loop through service ids
//            for (String serviceId : site.getService()) {
//                //get service from db
//                ServiceEntity service = this.serviceService.getServiceEntity(serviceId);
//                //if service is null throw error
//                if (service == null) throw new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
//                //if service not in array
//                if (!serviceEntitiesList.contains(service)) {
//                    //add to array
//                    serviceEntitiesList.add(service);
//                }
//            }
//        }

//        //gather all funds from data transfer objects
//        for (SiteDto site : siteDtoList) {
//
//            // loop through fund ids
//            for (String fundId : site.getFund()) {
//                //get fund from db
//                FundEntity fundEntity = this.fundService.getFundEntity(fundId);
//                //if fund is null throw error
//                if (fundEntity == null) throw new FundServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
//
//                //if fund not in array
//                if (!fundEntityList.contains(fundEntity)) {
//
//                    //add to array
//                    fundEntityList.add(fundEntity);
//                }
//            }
//        }


        //cycle throw list of unsaved site data transfer objects
        for (SiteDto site : siteDtoList) {

            //convert the data transfer object into Site Entity
            SiteEntity siteEntity = new ModelMapper().map(site, SiteEntity.class);

            //generate site Id
            siteEntity.setSiteId(utils.generateRandomID());
            //get county
            CountyEntity countyEntity = this.countyService.findCountyEntity(site.getCounty());

            // check if county is null throw error
            if (countyEntity == null) throw new CountyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set county for site
            siteEntity.setCounty(countyEntity);

            //get city
            CityEntity cityEntity = this.cityService.findCity(site.getCity());

            //check if county is null and throw error
            if (cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set city for site
            siteEntity.setCity(cityEntity);

            //get zipcode for site
            ZipCodeEntity zipCodeEntity = this.zipCodeService.getZipCodeEntity(site.getZipCode());

            // check if zip code is null if so throw error
            if (zipCodeEntity == null)
                throw new ZipCodeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set zip code for site
            siteEntity.setZipCode(this.zipCodeService.getZipCodeEntity(site.getZipCode()));

            //set health center for site
            siteEntity.setHealthCenter(healthCenterEntity);

            // senate district field is not required
            // check for senate district id
            if (!site.getSenateDistrict().isEmpty()) {

                //get senate district
                SenateDistrictEntity senateDistrictEntity = this.senateDistrictService.findSenateDistrictEntity(site.getSenateDistrict());

                //check if senate district is null, if so throw error
                if (senateDistrictEntity == null)
                    throw new SenateDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                //set senate district to site
                siteEntity.setSenateDistrict(senateDistrictEntity);
            }
            //field not required
            // check for house district id
            if (!site.getNmHouseDistrict().isEmpty()) {

                //get nmHouseDistrict
                NMHouseDistrictEntity nmHouseDistrictEntity = this.nmHouseDistrictService.findNMHouseDistrictEntity(site.getNmHouseDistrict());

                //check if house district is null, if so throw error
                if (nmHouseDistrictEntity == null)
                    throw new NMHouseDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                //set nm house district to site
                siteEntity.setNmHouseDistrict(nmHouseDistrictEntity);
            }

            //field is not required
            // check for congressional district
            if (!site.getCongressionalDistrict().isEmpty()) {

                //get congressional district
                CongressionalDistrictEntity congressionalDistrictEntity = this.congressionalDistrictService.getCongressionalDistrictEntity(site.getCongressionalDistrict());

                //check if congressional district is null if so throw error
                if (congressionalDistrictEntity == null)
                    throw new CongressionalDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                //set congressional district to site
                siteEntity.setCongressionalDistrictEntity(congressionalDistrictEntity);

            }

            SiteEntity savedSiteEntity = this.siteRepository.save(siteEntity);
            //assigns fund to the site entity

            for (String fundId : site.getFund()) {

                FundEntity fundEntity = this.fundService.getFundEntity(fundId);

                SiteFundingDetailsEntity siteFundingDetails = new SiteFundingDetailsEntity();

                siteFundingDetails.setSiteFundingDetailsId(utils.generateRandomID());

                siteFundingDetails.setFund(fundEntity);

                siteFundingDetails.setSite(savedSiteEntity);

                SiteFundingDetailsEntity savedSiteFunding = this.siteFundingDetailsService.createSiteFundingEntity(siteFundingDetails);

            }

            //assigns service to all entities;
            for(String serviceId: site.getService()){

                ServiceEntity serviceEntity = this.serviceService.getServiceEntity(serviceId);

                SiteServiceDetailsEntity siteServiceDetails = new SiteServiceDetailsEntity();

                siteServiceDetails.setSiteServiceDetailsId(utils.generateRandomID());

                siteServiceDetails.setService(serviceEntity);

                siteServiceDetails.setSite(savedSiteEntity);

                SiteServiceDetailsEntity siteServiceDetailsEntity = this.siteServiceDetailsService.createSiteServiceEntity(siteServiceDetails);

            }

            returnValue.add(new ModelMapper().map(savedSiteEntity, SiteDto.class));
        }

        return returnValue;
    }
}
