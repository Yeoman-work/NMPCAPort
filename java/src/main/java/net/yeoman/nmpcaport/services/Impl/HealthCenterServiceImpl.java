package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.io.response.HealthCenter.*;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.fund.FundEssentialsResponse;
import net.yeoman.nmpcaport.io.response.fund.FundNestedResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceEssentialsResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceNestedResponse;
import net.yeoman.nmpcaport.io.response.site.SiteEssentialsResponse;
import net.yeoman.nmpcaport.services.HealthCenterService;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
import net.yeoman.nmpcaport.io.repositories.HealthCenterRepository;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthCenterServiceImpl implements HealthCenterService {

    @Autowired
    private HealthCenterRepository healthCenterRepository;

    @Autowired
    private SiteServiceImpl siteService;

    @Autowired
    private ServiceServiceImpl serviceService;

    @Autowired
    private SiteFundingDetailsServiceImpl siteFundingDetailsService;

    @Autowired
    private FundServiceImpl fundService;

    @Autowired
    private SiteServiceDetailsServiceImpl siteServiceDetailsService;

    @Autowired
    private HouseDistrictServiceImpl nmHouseDistrictService;

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


        return this.entityToDto(healthCenterEntity);
    }

    @Override
    public HealthCenterDto requestToDto(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel) {

        if(this.requestIsNull(healthCenterDetailsRequestModel))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(healthCenterDetailsRequestModel, HealthCenterDto.class);
    }

    @Override
    public List<HealthCenterDto> requestToDto(List<HealthCenterDetailsRequestModel> healthCenterDetailsRequestModelList) {

        if(this.requestIsNull(healthCenterDetailsRequestModelList))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return null;
    }

    @Override
    public HealthCenterResponseFull dtoToResponseFull(HealthCenterDto healthCenterDto) {

        if(this.dtoIsNull(healthCenterDto))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(healthCenterDto, HealthCenterResponseFull.class);
    }

    @Override
    public List<HealthCenterResponseFull> dtoToResponseFull(List<HealthCenterDto> healthCenterDtoList) {

        if(this.dtoIsNull(healthCenterDtoList))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterResponseFull> returnValue = new ArrayList<>();

        for(HealthCenterDto healthCenterDto: healthCenterDtoList){

            returnValue.add(this.dtoToResponseFull(healthCenterDto));
        }

        return returnValue;
    }

    @Override
    public void createHealthCenter(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel) {

        if(this.requestIsNull(healthCenterDetailsRequestModel))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterEntity healthCenterEntity = this.savedHealthCenterEntityWithReturn(
                this.dtoToEntity(
                        this.requestToDto(
                                healthCenterDetailsRequestModel)
                )
        );

        if(!this.siteService.requestIsNull(
                healthCenterDetailsRequestModel.getSitesRequest())
        ){
            this.siteService.createSiteBulk(healthCenterDetailsRequestModel.getSitesRequest(), healthCenterEntity);
        }

        return ;

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
    public void savedHealthCenterEntity(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        this.healthCenterRepository.save(healthCenterEntity);

         return;
    }

    @Override
    public HealthCenterEntity savedHealthCenterEntityWithReturn(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.healthCenterRepository.save(healthCenterEntity);
    }


    @Override
    public List<HealthCenterDto> getHealthCenters(int page, int limit) {


        if (page > 0) page -= 1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<HealthCenterEntity> healthCenterPage = this.healthCenterRepository.findAll(pageableRequest);

        List<HealthCenterEntity> healthCenterEntities = healthCenterPage.getContent();

        return this.entityToDto(healthCenterEntities);
    }

    @Override
    public List<HealthCenterEntity> getHealthCenterEntities(int page, int limit) {

        if (page > 0) page -= 1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<HealthCenterEntity> healthCenterPage = this.healthCenterRepository.findAll(pageableRequest);


        return healthCenterPage.getContent();
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

    @Override
    public List<ServiceEssentialsResponse> getServiceEssentials(List<SiteEssentialsResponse> siteEssentialsResponses) {

        List<ServiceEssentialsResponse> returnValue = new ArrayList<>();

        for(SiteEssentialsResponse site: siteEssentialsResponses){

            for(ServiceEssentialsResponse service: site.getServiceEssentialsResponses()){

                if(!returnValue.contains(service)){

                    returnValue.add(service);
                }
            }
        }

        return returnValue;
    }

    @Override
    public List<FundEssentialsResponse> getFundingEssentials(List<SiteEssentialsResponse> siteEssentialsResponses) {

        List<FundEssentialsResponse> returnValue = new ArrayList<>();

        for(SiteEssentialsResponse site: siteEssentialsResponses){

            for(FundEssentialsResponse fund: site.getFundEssentialsResponses()){

                if(!returnValue.contains(fund)){

                    returnValue.add(fund);
                }

            }
        }
        return returnValue;
    }

    @Override
    public List<HouseDistrictEssentialResponse> getNMHouseDistrictEssentials(List<SiteEssentialsResponse> siteEssentialsResponses) {

        List<HouseDistrictEssentialResponse> returnValue = new ArrayList<>();

        for(SiteEssentialsResponse site: siteEssentialsResponses){

            returnValue.add(site.getNmHouseDistrictEssentialResponse());
        }


        return returnValue.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<SenateDistrictEssentialResponse> getSenateDistrictEssentials(List<SiteEssentialsResponse> siteEssentialsResponses) {
        return null;
    }

    @Override
    public List<CongressionalDistrictEssentialsResponse> congressionalDistrictEssentials(List<CongressionalDistrictEssentialsResponse> congressionalDistrictEssentialsResponses) {
        return null;
    }


    @Override
    public HealthCenterEntity generateHealthCenterWithUniqueHealthCenterId(HealthCenterEntity healthCenter) {

        //check if health center is null
        if(healthCenter == null) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //set health center id
        healthCenter.setHealthCenterId(utils.generateRandomID());

        while(this.healthCenterRepository.existsByHealthCenterId(healthCenter.getHealthCenterId())){

            healthCenter.setHealthCenterId(utils.generateRandomID());
        }

        return healthCenter;
    }

    //get NM house districts from sites
    @Override
    public List<HouseDistrictNestedResponse> getHealthCenterNMHouseDistrictsFromSites(List<SiteEntity> siteEntities) {

        if(this.siteService.entityIsNull(siteEntities)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HouseDistrictEntity> districtEntities = new ArrayList<>();
        List<HouseDistrictNestedResponse> returnValue = new ArrayList<>();


        for(SiteEntity siteEntity: siteEntities){

            if(!this.nmHouseDistrictService.entityIsNull(siteEntity.getNmHouseDistrictEntity())){

                if(!districtEntities.contains(siteEntity.getNmHouseDistrictEntity())){

                    districtEntities.add(siteEntity.getNmHouseDistrictEntity());
                }
            }
        }


        if(districtEntities.size() > 0){

            returnValue = this.nmHouseDistrictService.dtoToNestedResponse(this.nmHouseDistrictService.entityToDto(districtEntities));

        }

        return returnValue;
    }

    //get senate district from sites
    @Override
    public List<SenateDistrictNestedResponse> getHealthCenterSenateDistrictFromSites(List<SiteEntity> siteEntities) {

        if(this.siteService.entityIsNull(siteEntities)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SenateDistrictNestedResponse> returnValue = new ArrayList<>();
        List<SenateDistrictEntity> senateDistrictEntities = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            if(!this.senateDistrictService.entityIsNull(siteEntity.getSenateDistrictEntity())){

                if(!senateDistrictEntities.contains(siteEntity.getSenateDistrictEntity())){

                    senateDistrictEntities.add(siteEntity.getSenateDistrictEntity());
                }
            }

        }

        if(siteEntities != null && siteEntities.size() > 0){

            returnValue = this.senateDistrictService.dtoToNestedResponse(this.senateDistrictService.entityToDto(senateDistrictEntities));
        }

        return returnValue;
    }

    @Override
    public List<CongressionalDistrictNestedResponse> getHealthCenterCongressionalDistrictFromSites(List<SiteEntity> siteEntities) {

        if(this.siteService.entityIsNull(siteEntities)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CongressionalDistrictNestedResponse> returnValue = new ArrayList<>();
        List<CongressionalDistrictEntity> congressionalDistrictEntities = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            if(!this.congressionalDistrictService.entityIsNull(siteEntity.getCongressionalDistrictEntity())){

                if(!congressionalDistrictEntities.contains(siteEntity.getCongressionalDistrictEntity())){


                    System.out.println(siteEntity.getCongressionalDistrictEntity().getName());
                    congressionalDistrictEntities.add(siteEntity.getCongressionalDistrictEntity());
                }
            }
        }


        if(congressionalDistrictEntities != null && congressionalDistrictEntities.size() > 0){

            System.out.println(" where are in here");
            returnValue = this.congressionalDistrictService.dtoToNestedResponse(this.congressionalDistrictService.entityToDto(congressionalDistrictEntities));
        }


        return returnValue;
    }

    @Override
    public List<ServiceNestedResponse> getHealthCenterServicesFromSites(List<SiteEntity> siteEntities) {

        if(this.siteService.entityIsNull(siteEntities)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceEntity> serviceEntityList =  new ArrayList<>();

        List<ServiceNestedResponse> returnValue = new ArrayList<>();

        for(SiteEntity site: siteEntities){

            if(!this.siteService.entityIsNull(site)){

                if(!this.siteServiceDetailsService.entityIsNull(site.getServiceDetailsEntities())){


                    List<ServiceEntity> serviceEntities = this.siteServiceDetailsService.getServiceEntities(
                            site.getServiceDetailsEntities());

                    for(ServiceEntity serviceEntity: serviceEntities){

                        if(!serviceEntityList.contains(serviceEntity)){

                            serviceEntityList.add(serviceEntity);
                        }
                    }
                }
            }
        }

        if(serviceEntityList != null && serviceEntityList.size() > 0){

            returnValue = this.serviceService.entityToNestedResponse(serviceEntityList);
        }

        return returnValue;
    }



    @Override
    public List<FundNestedResponse> getHealthCenterFundingFromSites(List<SiteEntity> siteEntities) {

        if(this.siteService.entityIsNull(siteEntities)) throw new  SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<FundNestedResponse> returnValue = new ArrayList<>();
        List<FundEntity> fundEntityList = new ArrayList<>();


        for(SiteEntity siteEntity: siteEntities){

            if(this.siteService.entityIsNull(siteEntities)){
                if(!this.siteFundingDetailsService.entityIsNull(siteEntity.getSiteFundingDetailsEntities())){

                    for(FundEntity fundEntity: this.siteFundingDetailsService.getFundEntities(siteEntity.getSiteFundingDetailsEntities())){

                        if(!fundEntityList.contains(fundEntity)){

                            fundEntityList.add(fundEntity);
                        }
                    }
                }
            }

            if(fundEntityList != null && fundEntityList.size() > 0){

                returnValue =  this.fundService.dtoToNestedResponse(this.fundService.entityToDto(fundEntityList));
            }

        }

        return returnValue;
    }

    @Override
    public HealthCenterDto entityToDto(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterDto healthCenterDto = this.utils.objectMapper().map(healthCenterEntity, HealthCenterDto.class);

        if(!this.contactService.entityIsNull(healthCenterDto.getContactEntities())){

            healthCenterDto.setContactNestedResponseList(this.contactService.dtoToNestedResponse(
                    this.contactService.entityToDto(
                            healthCenterEntity.getContacts())));
        }

        if(!this.siteService.entityIsNull(healthCenterDto.getSiteEntities())){

            //get sites
            healthCenterDto.setSiteDetailsNestedResponseList(
                    this.siteService.entityToNestedResponse(
                            healthCenterDto.getSiteEntities()
                    )
            );

            //get services from sites
            healthCenterDto.setServiceNestedResponses(
                    this.getHealthCenterServicesFromSites(
                            healthCenterDto.getSiteEntities()));

            //get funding from sites
            healthCenterDto.setFundNestedResponses(
                    this.getHealthCenterFundingFromSites(
                            healthCenterDto.getSiteEntities()));

            healthCenterDto.setNmHouseDistrictNestedResponses(
                    this.getHealthCenterNMHouseDistrictsFromSites(
                            healthCenterDto.getSiteEntities()));


            healthCenterDto.setSenateDistrictNestedResponseList(
                    this.getHealthCenterSenateDistrictFromSites(
                            healthCenterDto.getSiteEntities()));


            healthCenterDto.setCongressionalDistrictNestedResponseList(
                    this.getHealthCenterCongressionalDistrictFromSites(
                            healthCenterDto.getSiteEntities()));


        }

        return healthCenterDto;
    }

    @Override
    public List<HealthCenterDto> entityToDto(List<HealthCenterEntity> healthCenterEntityList) {

        if(this.entityIsNull(healthCenterEntityList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterDto> returnValue = new ArrayList<>();

        for(HealthCenterEntity healthCenter: healthCenterEntityList){

            returnValue.add(this.entityToDto(healthCenter));
        }

        return returnValue;
    }

    //get health center for dashboard
    @Override
    public HealthCenterDashBoard entityToDashBoardData(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterDto healthCenterDto = this.utils.objectMapper().map(healthCenterEntity, HealthCenterDto.class);

        List<SiteEssentialsResponse> siteEssentialsResponses = this.siteService.entityToEssential(healthCenterDto.getSiteEntities());

        healthCenterDto.setSiteEssentialsResponses(siteEssentialsResponses);

          healthCenterDto.setServiceEssentialsResponses(this.healthCenterServiceEssentials(healthCenterEntity.getSiteEntities()));
        healthCenterDto.setFundEssentialsResponses(this.healthCenterFundEssentials(healthCenterEntity.getSiteEntities()));
        healthCenterDto.setNmHouseDistrictEssentialResponses(this.healthCenterNMDistrictEssentials(healthCenterEntity.getSiteEntities()));
        healthCenterDto.setSenateDistrictEssentialResponses(this.healthCenterSenateDistrictEssentials(healthCenterEntity.getSiteEntities()));
        healthCenterDto.setCongressionalEssentialsResponses(this.healthCenterCongressionalEssentials(healthCenterEntity.getSiteEntities()));


        return this.utils.objectMapper().map(healthCenterDto, HealthCenterDashBoard.class);
    }

    @Override
    public List<HealthCenterDashBoard> entityToDashBoardData(List<HealthCenterEntity> healthCenterEntityList) {

        if(this.entityIsNull(healthCenterEntityList))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterDashBoard> returnValue = new ArrayList<>();

        for(HealthCenterEntity healthCenterEntity: healthCenterEntityList){

            returnValue.add(this.entityToDashBoardData(healthCenterEntity));
        }

        return returnValue;
    }




    @Override
    public List<ServiceEssentialsResponse> healthCenterServiceEssentials(List<SiteEntity> siteEntities) {

        List<ServiceEntity> serviceEntities = new ArrayList<>();
        List<ServiceEssentialsResponse> returnValue = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            for(SiteServiceDetailsEntity serviceDetailsEntity: siteEntity.getServiceDetailsEntities()){

                if(!serviceEntities.contains(serviceDetailsEntity.getServiceEntity())){

                    serviceEntities.add(serviceDetailsEntity.getServiceEntity());
                }
            }
        }

        for(ServiceEntity service: serviceEntities){

            ServiceEssentialsResponse serviceEssentialsResponse = new ServiceEssentialsResponse();

            serviceEssentialsResponse.setName(service.getName());
            serviceEssentialsResponse.setServiceId(service.getServiceId());
            returnValue.add(serviceEssentialsResponse);
        }

        return returnValue.stream()
                .sorted(Comparator.comparing(ServiceEssentialsResponse::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<FundEssentialsResponse> healthCenterFundEssentials(List<SiteEntity> siteEntities) {

        List<FundEntity> fundEntities = new ArrayList<>();
        List<FundEssentialsResponse> returnValue = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            for(SiteFundingDetailsEntity fund: siteEntity.getSiteFundingDetailsEntities()){

                if(!fundEntities.contains(fund.getFundEntity())){

                    fundEntities.add(fund.getFundEntity());
                }
            }
        }

        for(FundEntity fund: fundEntities){

            FundEssentialsResponse fundEssentialsResponse = new FundEssentialsResponse();

            fundEssentialsResponse.setFundId(fund.getFundId());
            fundEssentialsResponse.setName(fund.getName());

            returnValue.add(fundEssentialsResponse);
        }

        return returnValue.stream()
                .sorted(Comparator.comparing(FundEssentialsResponse :: getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<HouseDistrictEssentialResponse> healthCenterNMDistrictEssentials(List<SiteEntity> siteEntities) {

        List<HouseDistrictEntity> nmHouseDistrictEntities = new ArrayList<>();
        List<HouseDistrictEssentialResponse> returnValue = new ArrayList<>();

        for(SiteEntity site: siteEntities){

            if(!nmHouseDistrictEntities.contains(site.getNmHouseDistrictEntity())){

                nmHouseDistrictEntities.add(site.getNmHouseDistrictEntity());
            }
        }

        for(HouseDistrictEntity district: nmHouseDistrictEntities){

            HouseDistrictEssentialResponse nmHouseDistrictEssentialResponse = new HouseDistrictEssentialResponse();

            nmHouseDistrictEssentialResponse.setName(district.getName());
            nmHouseDistrictEssentialResponse.setHouseDistrictId(district.getHouseDistrictId());

            returnValue.add(nmHouseDistrictEssentialResponse);

        }

        return returnValue.stream()
                .sorted(Comparator.comparing(HouseDistrictEssentialResponse:: getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<SenateDistrictEssentialResponse> healthCenterSenateDistrictEssentials(List<SiteEntity> siteEntities) {

        List<SenateDistrictEntity> senateDistrictEntities = new ArrayList<>();
        List<SenateDistrictEssentialResponse> returnValue = new ArrayList<>();

        for(SiteEntity site: siteEntities){

            if(!senateDistrictEntities.contains(site.getSenateDistrictEntity())){

                senateDistrictEntities.add(site.getSenateDistrictEntity());
            }
        }

        for(SenateDistrictEntity district: senateDistrictEntities){

            SenateDistrictEssentialResponse senateDistrictEssentialResponse = new SenateDistrictEssentialResponse();

            senateDistrictEssentialResponse.setSenateDistrictId(district.getSenateDistrictId());
            senateDistrictEssentialResponse.setName(district.getName());

            returnValue.add(senateDistrictEssentialResponse);
        }

        return returnValue.stream()
                .sorted(Comparator.comparing(SenateDistrictEssentialResponse :: getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<CongressionalDistrictEssentialsResponse> healthCenterCongressionalEssentials(List<SiteEntity> siteEntities) {

        List<CongressionalDistrictEntity> congressionalDistrictEntities = new ArrayList<>();
        List<CongressionalDistrictEssentialsResponse> returnValue = new ArrayList<>();

        for(SiteEntity site: siteEntities){

            if(!congressionalDistrictEntities.contains(site.getCongressionalDistrictEntity())){

                congressionalDistrictEntities.add(site.getCongressionalDistrictEntity());
            }

        }

        for(CongressionalDistrictEntity district: congressionalDistrictEntities){

            CongressionalDistrictEssentialsResponse congressionalDistrictEssentialsResponse =
                    new CongressionalDistrictEssentialsResponse();

            congressionalDistrictEssentialsResponse.setCongressionalDistrictId(district.getCongressionalDistrictId());
            congressionalDistrictEssentialsResponse.setName(district.getName());

            returnValue.add(congressionalDistrictEssentialsResponse);
        }

        return returnValue.stream()
                .sorted(Comparator.comparing(CongressionalDistrictEssentialsResponse :: getName))
                .collect(Collectors.toList());
    }

    @Override
    public HealthCenterResponseFull entityToResponseFull(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterDto healthCenterDto = this.entityToDto(healthCenterEntity);

        return this.dtoToResponseFull(healthCenterDto);
    }

    @Override
    public List<HealthCenterResponseFull> entityToResponseFull(List<HealthCenterEntity> healthCenterEntityList) {

        if(this.entityIsNull(healthCenterEntityList))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterResponseFull> returnValue = new ArrayList<>();

        for(HealthCenterEntity healthCenterEntity: healthCenterEntityList){

            HealthCenterResponseFull healthCenterResponseFull = this.entityToResponseFull(healthCenterEntity);
            returnValue.add(healthCenterResponseFull);
        }

        return returnValue;
    }

    @Override
    public HealthCenterEssentials healthCenterEssentials(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterEssentials healthCenterEssentials = new HealthCenterEssentials();

        healthCenterEssentials.setName(healthCenterEntity.getName());
        healthCenterEssentials.setNameAbbr(healthCenterEntity.getNameAbbr());
        healthCenterEssentials.setHealthCenterId(healthCenterEntity.getHealthCenterId());

        return healthCenterEssentials;
    }

    @Override
    public List<HealthCenterResponseFull> getHealthCenterResponse(int page, int index) {

        List<HealthCenterDto> healthCenterEntities = this.getHealthCenters(page, index);

        return this.dtoToResponseFull(healthCenterEntities);
    }

    @Override
    public List<HealthCenterDashBoard> healthCenterDashBoard(int page, int limit) {

        List<HealthCenterEntity> healthCenterDtoList = this.getHealthCenterEntities(page, limit);


        return this.entityToDashBoardData(healthCenterDtoList);
    }

    @Override
    public HealthCenterEntity dtoToEntity(HealthCenterDto healthCenterDto) {


        HealthCenterEntity healthCenterEntity = utils.objectMapper().map(healthCenterDto, HealthCenterEntity.class);

        if(healthCenterEntity.getHealthCenterId() == null || healthCenterEntity.getHealthCenterId().length() < 30){

            healthCenterEntity = this.generateHealthCenterWithUniqueHealthCenterId(healthCenterEntity);
        }

        return healthCenterEntity;
    }

    @Override
    public List<HealthCenterEntity> dtoToEntity(List<HealthCenterDto> healthCenterDtoList) {

        List<HealthCenterEntity> returnValue = new ArrayList<>();

        for(HealthCenterDto healthCenterDto: healthCenterDtoList){

            returnValue.add(this.dtoToEntity(healthCenterDto));
        }

        return returnValue;
    }

    @Override
    public HealthCenterResponseModel dtoToHealthCenterResponse(HealthCenterDto healthCenterDto) {

        if(this.dtoIsNull(healthCenterDto)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(healthCenterDto, HealthCenterResponseModel.class);

    }

    @Override
    public List<HealthCenterResponseModel> dtoToHealthCenterResponseModel(List<HealthCenterDto> healthCenterDtoList) {

        if(this.dtoIsNull(healthCenterDtoList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterResponseModel> returnValue = new ArrayList<>();

        for(HealthCenterDto healthCenterDto: healthCenterDtoList){

            returnValue.add(this.dtoToHealthCenterResponse(healthCenterDto));
        }

        return returnValue;
    }



    @Override
    public HealthCenterResponseBaseModel dtoToHealthCenterResponseBaseModel(HealthCenterDto healthCenterDto) {

        if(this.dtoIsNull(healthCenterDto)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(healthCenterDto, HealthCenterResponseBaseModel.class);
    }

    @Override
    public List<HealthCenterResponseBaseModel> dtoToHealthCenterResponseBaseModel(List<HealthCenterDto> healthCenterDtoList) {

        if(this.dtoIsNull(healthCenterDtoList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterResponseBaseModel> returnValue = new ArrayList<>();

        for(HealthCenterDto healthCenterDto: healthCenterDtoList){

            returnValue.add(this.utils.objectMapper().map(healthCenterDto, HealthCenterResponseBaseModel.class));
        }
        return null;
    }

    @Override
    public HealthCenterNestedResponseModel dtoToHealthCenterNestedResponseModel(HealthCenterDto healthCenterDto) {

        if(this.dtoIsNull(healthCenterDto)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(healthCenterDto, HealthCenterNestedResponseModel.class);
    }




    @Override
    public List<HealthCenterNestedResponseModel> dtoToHealthCenterNestedResponseModelArray(List<HealthCenterDto> healthCenterDtoList) {

        if(this.dtoIsNull(healthCenterDtoList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterNestedResponseModel> returnValue = new ArrayList<>();

        for(HealthCenterDto healthCenterDto: healthCenterDtoList){

            returnValue.add(this.dtoToHealthCenterNestedResponseModel(healthCenterDto));
        }

        return returnValue;
    }

    @Override
    public HealthCenterDetailsRequestModel dtoToHealthCenterDetailsRequestModel(HealthCenterDto healthCenterDto) {

        if(dtoIsNull(healthCenterDto)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(healthCenterDto, HealthCenterDetailsRequestModel.class);
    }

    @Override
    public List<HealthCenterDetailsRequestModel> dtoToHealthCenterDetailsRequestModel(List<HealthCenterDto> healthCenterDtoList) {

        if(dtoIsNull(healthCenterDtoList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterDetailsRequestModel> returnValue = new ArrayList<>();

        for(HealthCenterDto healthCenterDto: healthCenterDtoList){

            returnValue.add(this.utils.objectMapper().map(healthCenterDto, HealthCenterDetailsRequestModel.class));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(HealthCenterEntity healthCenter) {
        return healthCenter == null;
    }

    @Override
    public Boolean entityIsNull(List<HealthCenterEntity> healthCenterEntityList) {
        return healthCenterEntityList == null;
    }

    @Override
    public Boolean dtoIsNull(HealthCenterDto healthCenterDto) {
        return healthCenterDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<HealthCenterDto> healthCenterDtoList) {
        return healthCenterDtoList == null;
    }

    @Override
    public Boolean responseIsNull(List<HealthCenterResponseModel> healthCenterResponseModelList) {
        return healthCenterResponseModelList == null;
    }

    @Override
    public Boolean responseIsNull(HealthCenterResponseModel healthCenterResponseModel) {
        return healthCenterResponseModel == null;
    }

    @Override
    public Boolean requestIsNull(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel) {
        return healthCenterDetailsRequestModel == null;
    }


    @Override
    public Boolean requestIsNull(List<HealthCenterDetailsRequestModel> healthCenterDetailsRequestModelList) {
        return healthCenterDetailsRequestModelList == null;
    }

}
