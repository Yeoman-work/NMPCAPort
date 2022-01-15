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
    public List<SiteDto> createSiteBulk(List<SiteDetailsRequestModel> siteDetailsRequestModels,
                                        HealthCenterEntity healthCenterEntity) {

        if(this.healthCenterService.entityIsNull(healthCenterEntity)) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(this.requestIsNull(siteDetailsRequestModels)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

            //loop through site DTO
            for(SiteDto siteDto: this.requestToDto(siteDetailsRequestModels)){




                System.out.println("read this");
                System.out.println(siteDto.getCity());
                //check for city id
                if(siteDto.getCity() != null){

                    //fetch city
                    CityEntity cityEntity = this.cityService.findCity(siteDto.getCity());
                    // check is entity is present
                    if(cityEntity == null) throw new CityServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                    //set city entity
                    siteEntity.setCityEntity(cityEntity);
                }
                //check for county id
                if(siteDto.getCounty() != null){
                    //get county entity
                    CountyEntity countyEntity = this.countyService.findCountyEntity(siteDto.getCounty());
                    //check for county entity
                    if(countyEntity == null) throw new CountyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                    //set county entity
                    siteEntity.setCountyEntity(countyEntity);
                }
                //check zip code id
                if(siteDto.getZipCode() != null){
                    //get the zip code entity
                    ZipCodeEntity zipCodeEntity = this.zipCodeService.getZipCodeEntity(siteDto.getZipCode());
                    //check if zip code eneity is present
                    if(zipCodeEntity == null) throw new ZipCodeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                    //set zip code entity
                    siteEntity.setZipCodeEntity(zipCodeEntity);
                }

                //check for nm house district id
                if(siteDto.getNmHouseDistrict() != null){

                    //get district
                    NMHouseDistrictEntity nmHouseDistrict = this.nmHouseDistrictService.findNMHouseDistrictEntity(siteDto.getNmHouseDistrict());
                    //check if entity is present
                    if(nmHouseDistrict == null) throw new NMHouseDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                    //set nm house district entity
                    siteEntity.setNmHouseDistrictEntity(nmHouseDistrict);
                }

                //set senate district entity
                if(siteDto.getSenateDistrict() != null){
                    //check if senate district
                    SenateDistrictEntity senateDistrict = this.senateDistrictService.findSenateDistrictEntity(siteDto.getSenateDistrict());
                    //check if senate district is present
                    if(senateDistrict == null) throw new SenateDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                    //set senate district entity
                    siteEntity.setSenateDistrictEntity(senateDistrict);
                }

                //check for congressional district id
                if(siteDto.getCongressionalDistrict() != null){
                    // get congressional district entity
                    CongressionalDistrictEntity congressionalDistrictEntity = this.congressionalDistrictService.getCongressionalDistrictEntity(siteDto.getCongressionalDistrict());
                    //get congressional district is present
                    if(congressionalDistrictEntity == null) throw new CongressionalRepServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                    //set congressional district
                    siteEntity.setCongressionalDistrictEntity(congressionalDistrictEntity);
                }


                //set health center
                siteEntity.setHealthCenterEntity(healthCenterEntity);
                //save site entity
                SiteEntity savedSiteEntity = this.siteRepository.save(siteEntity);

                SiteDto savedSiteDto = modelMapper.map(savedSiteEntity, SiteDto.class);


                //check for site funding
                if(siteDto.getFund() != null){
                    List<FundResponseModel> fundResponseModelList = new ArrayList<>();
                    //loop through funding Ids
                    for(String id: siteDto.getFund()){
                        //get fund entity
                        FundEntity fundEntity = this.fundService.getFundEntity(id);
                        //check if fund entity is present
                        if(fundEntity == null) throw new FundServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                        //create siteFunding details entity
                        SiteFundingDetailsEntity siteFundingDetailsEntity = new SiteFundingDetailsEntity();
                        //set public id
                        siteFundingDetailsEntity.setSiteFundingDetailsId(utils.generateRandomID());
                        //check public id is unique
                        while(this.siteFundingDetailsService.existByPublicId(siteFundingDetailsEntity.getSiteFundingDetailsId())){

                            siteFundingDetailsEntity.setSiteFundingDetailsId(utils.generateRandomID());
                        }
                        //assign site
                        siteFundingDetailsEntity.setSiteEntity(savedSiteEntity);
                        //assign fund
                        siteFundingDetailsEntity.setFundEntity(fundEntity);
                        //convert fund to fund response model
                        fundResponseModelList.add(modelMapper.map(fundEntity, FundResponseModel.class));
                    }
                    //fund response
                    savedSiteDto.setFundResponseModels(fundResponseModelList);

                }
                //check for service(s)
                if(siteDto.getService() != null){

                    //list to collect Service responses
                    List<ServiceResponse> serviceResponses = new ArrayList<>();

                    //loop through service Id
                    for(String id: siteDto.getService()){
                        //get service entity
                        ServiceEntity serviceEntity = this.serviceService.getServiceEntity(id);
                        //check service entity exist
                        if(serviceEntity == null) throw new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                        //create site service entity
                        SiteServiceDetailsEntity siteServiceDetailsEntity = new SiteServiceDetailsEntity();
                        //create public id
                        siteServiceDetailsEntity.setSiteServiceDetailsId(utils.generateRandomID());

                        //check if public id is unique
                        while(this.siteServiceDetailsService.existByPublicId(siteServiceDetailsEntity.getSiteServiceDetailsId())){

                            siteServiceDetailsEntity.setSiteServiceDetailsId(utils.generateRandomID());
                        }
                        //save site and service to entity
                        siteServiceDetailsEntity.setServiceEntity(serviceEntity);
                        siteServiceDetailsEntity.setSiteEntity(savedSiteEntity);

                        //save entity
                        SiteServiceDetailsEntity savedSiteServiceDetailsEntity = this.siteServiceDetailsService.createSiteServiceEntity(siteServiceDetailsEntity);

                        //convert service response to service class
                        serviceResponses.add(modelMapper.map(savedSiteServiceDetailsEntity.getServiceEntity(), ServiceResponse.class));
                    }

                    //add list of responses to saved dto
                    savedSiteDto.setServiceResponses(serviceResponses);
                }

                returnValue.add(savedSiteDto);
            }

        return returnValue;
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
        if(this.zipCodeService.entityIsNull(siteDto.getZipCodeEntity())){

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
