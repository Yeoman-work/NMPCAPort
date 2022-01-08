package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
import net.yeoman.nmpcaport.services.HealthCenterService;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterNestedResponseModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsResponse;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.io.repositories.HealthCenterRepository;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import net.yeoman.nmpcaport.shared.dto.SiteDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthCenterServiceImpl implements HealthCenterService {

    @Autowired
    private HealthCenterRepository healthCenterRepository;

    @Autowired
    private SiteServiceImpl siteService;


    @Autowired
    private SiteFundingDetailsServiceImpl siteFundingDetailsService;

    @Autowired
    private FundServiceImpl fundService;

    @Autowired
    private SiteServiceDetailsServiceImpl siteServiceDetailsService;

    @Autowired
    private NMHouseDistrictServiceImpl nmHouseDistrictService;

    @Autowired
    private SenateDistrictServiceImpl senateDistrictService;

    @Autowired
    private CongressionalDistrictServiceImpl congressionalDistrictService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CountyServiceImpl countyService;

    @Autowired
    private ZipCodeServiceImpl zipCodeService;

    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private Utils utils;

    @Override
    public HealthCenterDto getHealthCenter(String healthCenterId) {

        HealthCenterEntity healthCenterEntity = this.healthCenterRepository.findByHealthCenterId(healthCenterId);

        HealthCenterDto healthCenterDto = new ModelMapper().map(healthCenterEntity, HealthCenterDto.class);

        if(healthCenterDto.getUsers() != null){

            List<UserDetailsResponseModel> userResponseList = new ArrayList<>();

            for(UserEntity user: healthCenterDto.getUserEntities()){

                userResponseList.add(new ModelMapper().map(user, UserDetailsResponseModel.class));
            }

            healthCenterDto.setUserDetailsResponseList(userResponseList);
        }

        if(healthCenterDto.getContactEntities() != null){

            List<ContactNestedResponseModel> contactNestedResponseList = new ArrayList<>();

            for(ContactEntity contact: healthCenterDto.getContactEntities()){

                contactNestedResponseList.add(new ModelMapper().map(contact, ContactNestedResponseModel.class));
            }

            healthCenterDto.setContactNestedResponseList(contactNestedResponseList);
        }

        if(healthCenterDto.getSites() != null){

            List<SiteDetailsResponse> siteDetails = new ArrayList<>();

            for(SiteEntity site: healthCenterDto.getSiteEntities()){

                SiteDetailsResponse siteResponse = new ModelMapper().map(site, SiteDetailsResponse.class);

                siteResponse.setCityResponse(new ModelMapper().map(site.getCityEntity(), CityResponse.class));
                siteResponse.setCountyResponse(new ModelMapper().map(site.getCountyEntity(), CountyResponse.class));
                siteResponse.setZipCodeResponse(new ModelMapper().map(site.getZipCodeEntity(), ZipCodeResponse.class));
                siteResponse.setHealthCenterResponse(new ModelMapper().map(site.getHealthCenterEntity(), HealthCenterNestedResponseModel.class));

                siteDetails.add(siteResponse);
            }

            healthCenterDto.setSiteResponse(siteDetails);
        }

        return healthCenterDto;
    }

    @Override
    public HealthCenterResponseModel createHealthCenter(HealthCenterDto healthCenterDto) {
        System.out.println("Inside health care service");
        ModelMapper modelMapper = new ModelMapper();
        HealthCenterEntity healthCenterEntity = new ModelMapper().map(healthCenterDto, HealthCenterEntity.class);

        healthCenterEntity.setHealthCenterId(utils.generateRandomID());
        System.out.println("after health Center public assignment");
        while(this.healthCenterRepository.existsByHealthCenterId(healthCenterEntity.getHealthCenterId())){

            healthCenterEntity.setHealthCenterId(utils.generateRandomID());
        }


        HealthCenterEntity newHealthCenter = this.healthCenterRepository.save(healthCenterEntity);

        HealthCenterDto savedHealthCenterDto = modelMapper.map(newHealthCenter, HealthCenterDto.class);

        if(newHealthCenter == null) throw new HealthCenterServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        if(healthCenterDto.getSitesRequest() != null){
            System.out.println("in here");
            List<SiteDto> siteDtoList = new ArrayList<>();

            for(SiteDetailsRequestModel site: healthCenterDto.getSitesRequest()){

                siteDtoList.add(modelMapper.map(site, SiteDto.class));
            }

            List<SiteDto> savedSiteDtoList = this.siteService.createSiteBulk(siteDtoList, newHealthCenter.getHealthCenterId());

            if(savedSiteDtoList == null) throw new SiteServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            List<SiteDetailsResponse> siteDetailsResponses = new ArrayList<>();

            for(SiteDto siteDto: savedSiteDtoList){

                siteDetailsResponses.add(modelMapper.map(siteDto, SiteDetailsResponse.class));

            }

            savedHealthCenterDto.setSiteResponse(siteDetailsResponses);
        }


        return modelMapper.map(savedHealthCenterDto, HealthCenterResponseModel.class);

    }



    @Override
    public HealthCenterDto updateHealthCenter(String healthCenterId, HealthCenterDto healthCenterDto) {

        //get record
        HealthCenterEntity healthCenterEntity = this.healthCenterRepository.findByHealthCenterId(healthCenterId);

        //health center entity null throw error
        if(healthCenterEntity == null) throw new HealthCenterServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        if(!healthCenterEntity.getName().equals(healthCenterDto.getName())){
            healthCenterEntity.setName(healthCenterDto.getName());
        }

        if(!healthCenterEntity.getNameAbbr().equals(healthCenterDto.getNameAbbr())){
            healthCenterEntity.setNameAbbr(healthCenterDto.getNameAbbr());
        }

        return new ModelMapper().map(healthCenterEntity, HealthCenterDto.class);
    }

    @Override
    public HealthCenterDto deleteHealthCenter(String healthCenterId) {
        return null;
    }

    @Override
    public HealthCenterEntity getHealthCenterEntity(String healthCenterId) {

        return this.healthCenterRepository.findByHealthCenterId(healthCenterId);
    }

    @Override
    public HealthCenterEntity savedHealthCenterEntity(HealthCenterEntity healthCenterEntity) {
        return this.healthCenterRepository.save(healthCenterEntity);
    }


    @Override
    public List<HealthCenterDto> getHealthCenters(int page, int limit) {
        ModelMapper modelMapper = new ModelMapper();
        List<HealthCenterDto> returnValue = new ArrayList<>();
        List<NMHouseDistrictEntity> nmHouseDistrictEntities = new ArrayList<>();
        List<CongressionalDistrictEntity> congressionalDistrictEntities = new ArrayList<>();
        List<SenateDistrictEntity> senateDistrictEntities = new ArrayList<>();
        List<ServiceEntity> serviceEntities = new ArrayList<>();
        List<FundEntity> fundEntities = new ArrayList<>();

        if (page > 0) page -= 1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<HealthCenterEntity> healthCenterPage = this.healthCenterRepository.findAll(pageableRequest);

        List<HealthCenterEntity> healthCenters = healthCenterPage.getContent();


        for (HealthCenterEntity healthCenter : healthCenters) {

            HealthCenterDto healthCenterDto = modelMapper.map(healthCenter, HealthCenterDto.class);


            if (healthCenter.getSiteEntities() != null) {
                List<SiteDetailsNestedResponse> siteDetailsResponses = new ArrayList<>();

                for (SiteEntity site : healthCenter.getSiteEntities()) {

                    SiteDto siteDto = modelMapper.map(site, SiteDto.class);

                    if(site.getCityEntity() != null){

                        siteDto.setCityResponse(modelMapper.map(site.getCityEntity(), CityResponse.class));
                    }

                    if(site.getCountyEntity() != null){

                        siteDto.setCountyResponse(modelMapper.map(site.getCountyEntity(), CountyResponse.class));
                    }

                    if(site.getZipCodeEntity() != null){

                        siteDto.setZipCodeResponse(modelMapper.map(site.getZipCodeEntity(), ZipCodeResponse.class));
                    }


                    if(site.getNmHouseDistrictEntity() != null){

                        siteDto.setNmHouseDistrictResponse(modelMapper.map(site.getNmHouseDistrictEntity(), NMHouseDistrictResponse.class));

                        if(!nmHouseDistrictEntities.contains(site.getNmHouseDistrictEntity())){
                            nmHouseDistrictEntities.add(site.getNmHouseDistrictEntity());
                        }
                    }

                    if(site.getSenateDistrictEntity() != null){

                        siteDto.setSenateDistrictResponseModel(modelMapper.map(site.getSenateDistrictEntity(), SenateDistrictResponseModel.class));

                        if(!senateDistrictEntities.contains(site.getSenateDistrictEntity())){

                            senateDistrictEntities.add(site.getSenateDistrictEntity());
                        }
                    }

                    if(site.getCongressionalDistrictEntity() != null){

                        siteDto.setCongressionalDistrictResponse(modelMapper.map(site.getCongressionalDistrictEntity(), CongressionalDistrictResponse.class));

                        if(!congressionalDistrictEntities.contains(site.getCongressionalDistrictEntity())){

                            congressionalDistrictEntities.add(site.getCongressionalDistrictEntity());
                        }

                    }

                    if(site.getSiteFundingDetailsEntities() != null){
                        List<FundResponseModel> fundResponseModelList = new ArrayList<>();

                        for(SiteFundingDetailsEntity fundDetails: site.getSiteFundingDetailsEntities()){

                            FundResponseModel fundResponseModel = modelMapper.map(fundDetails.getFundEntity(), FundResponseModel.class);

                            fundResponseModelList.add(fundResponseModel);

                            if(!fundEntities.contains(fundDetails.getFundEntity())){

                                fundEntities.add(fundDetails.getFundEntity());
                            }
                        }

                        siteDto.setFundResponseModels(fundResponseModelList);
                    }

                    if(site.getServiceDetailsEntities() != null){

                        List<ServiceResponse> serviceResponseList = new ArrayList<>();

                        for(SiteServiceDetailsEntity serviceDetails: site.getServiceDetailsEntities()){

                            ServiceResponse serviceResponse = modelMapper.map(serviceDetails.getServiceEntity(), ServiceResponse.class);

                            serviceResponseList.add(serviceResponse);

                            if(!serviceEntities.contains(serviceDetails.getServiceEntity())){

                                serviceEntities.add(serviceDetails.getServiceEntity());
                            }
                        }

                        siteDto.setServiceResponses(serviceResponseList);
                    }


                    siteDetailsResponses.add(modelMapper.map(siteDto, SiteDetailsNestedResponse.class));

                }

                healthCenterDto.setSiteDetailsNestedResponseList(siteDetailsResponses);
            }

            if(nmHouseDistrictEntities.size() > 0){

                List<NMHouseDistrictNestedResponse> houseDistrictResponses = new ArrayList<>();

                for(NMHouseDistrictEntity houseDistrict: nmHouseDistrictEntities ){

                    houseDistrictResponses.add(modelMapper.map(houseDistrict, NMHouseDistrictNestedResponse.class));

                }

                healthCenterDto.setNmHouseDistrictNestedResponses(houseDistrictResponses);

            }

            if(senateDistrictEntities.size() > 0){

                List<SenateDistrictResponseModel> senateDistrictResponseModels = new ArrayList<>();

                for(SenateDistrictEntity senateDistrict: senateDistrictEntities){

                    senateDistrictResponseModels.add(modelMapper.map(senateDistrict, SenateDistrictResponseModel.class));
                }

                healthCenterDto.setSenateDistrictResponseModelList(senateDistrictResponseModels);
            }

            if(congressionalDistrictEntities.size() > 0){

                List<CongressionalDistrictResponse> congressionalDistrictResponses = new ArrayList<>();

                for(CongressionalDistrictEntity congressionalDistrict: congressionalDistrictEntities){

                    congressionalDistrictResponses.add(modelMapper.map(congressionalDistrict, CongressionalDistrictResponse.class));

                }

                healthCenterDto.setCongressionalDistrictResponseList(congressionalDistrictResponses);
            }

            if(serviceEntities.size() > 0){

                List<ServiceResponse> serviceResponses = new ArrayList<>();

                for(ServiceEntity service: serviceEntities){

                    ServiceResponse serviceResponse = modelMapper.map(service, ServiceResponse.class);

                    serviceResponses.add(serviceResponse);
                }

                healthCenterDto.setServiceResponses(serviceResponses);
            }

            if(fundEntities.size() > 0){

                List<FundResponseModel> fundResponseModels = new ArrayList<>();

                for(FundEntity fund: fundEntities){

                    FundResponseModel fundResponseModel = modelMapper.map(fund, FundResponseModel.class);

                    fundResponseModels.add(fundResponseModel);
                }

                healthCenterDto.setFundResponseModels(fundResponseModels);

            }

            returnValue.add(healthCenterDto);
        }

        return returnValue;
    }

    @Override
    public List<HealthCenterDto> getAllHealthCenters() {
        ModelMapper modelMapper = new ModelMapper();
        List<HealthCenterDto> returnValue = new ArrayList<>();
        List<HealthCenterEntity> healthCenterEntities = this.healthCenterRepository.findAll();

        for(HealthCenterEntity healthCenterEntity: healthCenterEntities){

            returnValue.add(modelMapper.map(healthCenterEntity, HealthCenterDto.class));
        }

        return returnValue;
    }

}
