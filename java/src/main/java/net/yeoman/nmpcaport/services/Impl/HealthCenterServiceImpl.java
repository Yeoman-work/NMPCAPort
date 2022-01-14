package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.services.HealthCenterService;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseBaseModel;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
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

    @Override
    public HealthCenterDto entityToDto(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(healthCenterEntity, HealthCenterDto.class);
    }

    @Override
    public List<HealthCenterDto> entityToDto(List<HealthCenterEntity> healthCenterEntityList) {

        if(this.entityArrayIsNull(healthCenterEntityList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HealthCenterDto> returnValue = new ArrayList<>();

        for(HealthCenterEntity healthCenter: healthCenterEntityList){

            returnValue.add(this.entityToDto(healthCenter));
        }

        return returnValue;
    }

    @Override
    public HealthCenterEntity dtoToHealthCenterEntity(HealthCenterDto healthCenterDto) {


        HealthCenterEntity healthCenterEntity = utils.objectMapper().map(healthCenterDto, HealthCenterEntity.class);

        if(healthCenterEntity.getHealthCenterId() == null || healthCenterEntity.getHealthCenterId().length() < 30){

            healthCenterEntity = this.generateHealthCenterWithUniqueHealthCenterId(healthCenterEntity);
        }

        return healthCenterEntity;
    }

    @Override
    public List<HealthCenterEntity> dtoArrayToEntityArray(List<HealthCenterDto> healthCenterDtoList) {

        List<HealthCenterEntity> returnValue = new ArrayList<>();

        for(HealthCenterDto healthCenterDto: healthCenterDtoList){

            returnValue.add(this.dtoToHealthCenterEntity(healthCenterDto));
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

        if(this.dtoArrayIsNull(healthCenterDtoList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

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

        if(this.dtoArrayIsNull(healthCenterDtoList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

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

        if(this.dtoArrayIsNull(healthCenterDtoList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

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

        if(dtoArrayIsNull(healthCenterDtoList)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

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
    public Boolean entityArrayIsNull(List<HealthCenterEntity> healthCenterEntityList) {
        return healthCenterEntityList == null;
    }

    @Override
    public Boolean dtoIsNull(HealthCenterDto healthCenterDto) {
        return healthCenterDto == null;
    }

    @Override
    public Boolean dtoArrayIsNull(List<HealthCenterDto> healthCenterDtoList) {
        return healthCenterDtoList == null;
    }

    @Override
    public Boolean responseArrayIsNull(List<HealthCenterResponseModel> healthCenterResponseModelList) {
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
    public Boolean requestArrayIsNull(List<HealthCenterDetailsRequestModel> healthCenterDetailsRequestModelList) {
        return healthCenterDetailsRequestModelList == null;
    }

}
