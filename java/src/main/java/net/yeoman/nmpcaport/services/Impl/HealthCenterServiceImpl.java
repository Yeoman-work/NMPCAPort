package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
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

                siteResponse.setCityResponse(new ModelMapper().map(site.getCity(), CityResponse.class));
                siteResponse.setCountyResponse(new ModelMapper().map(site.getCounty(), CountyResponse.class));
                siteResponse.setZipCodeResponse(new ModelMapper().map(site.getZipCode(), ZipCodeResponse.class));
                siteResponse.setHealthCenterResponse(new ModelMapper().map(site.getHealthCenter(), HealthCenterNestedResponseModel.class));

                siteDetails.add(siteResponse);
            }

            healthCenterDto.setSiteResponse(siteDetails);
        }

        return healthCenterDto;
    }

    @Override
    public HealthCenterResponseModel createHealthCenter(HealthCenterDto healthCenterDto) {
        ModelMapper modelMapper = new ModelMapper();
        HealthCenterEntity healthCenterEntity = new ModelMapper().map(healthCenterDto, HealthCenterEntity.class);

        healthCenterEntity.setHealthCenterId(utils.generateRandomID());

        while(this.healthCenterRepository.existsByHealthCenterId(healthCenterEntity.getHealthCenterId())){

            healthCenterEntity.setHealthCenterId(utils.generateRandomID());
        }

        HealthCenterEntity newHealthCenter = this.healthCenterRepository.save(healthCenterEntity);

        if(healthCenterDto.getSenateDistrictResponseModelList() != null){


        }




        return healthCenterResponse;
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

        if(page > 0) page -=1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<HealthCenterEntity> healthCenterPage = this.healthCenterRepository.findAll(pageableRequest);

        List<HealthCenterEntity> healthCenters = healthCenterPage.getContent();



        for(HealthCenterEntity healthCenter: healthCenters){

            HealthCenterDto healthCenterDto = new ModelMapper().map(healthCenter, HealthCenterDto.class);



            if(healthCenter.getSites().size() > 0){

                List<SiteDetailsNestedResponse> siteDetailsNestedResponses = new ArrayList<>();
                List<NMHouseDistrictNestedResponse> nmHouseDistrictNestedResponses = new ArrayList<>();
                List<CongressionalDistrictResponse> congressionalDistrictResponseList = new ArrayList<>();
                List<SenateDistrictResponseModel> senateDistrictResponseModelList = new ArrayList<>();
                for(SiteEntity site: healthCenter.getSites()){

                    SiteDetailsNestedResponse siteDetailsNestedResponse = new ModelMapper().map(site, SiteDetailsNestedResponse.class);

                    siteDetailsNestedResponse.setCityResponse(new ModelMapper().map(site.getCity(), CityResponse.class));
                    siteDetailsNestedResponse.setCountyResponse(new ModelMapper().map(site.getCounty(), CountyResponse.class));
                    siteDetailsNestedResponse.setZipCodeResponse(new ModelMapper().map(site.getZipCode(), ZipCodeResponse.class));

                    NMHouseDistrictNestedResponse nmHouseDistrictNestedResponse = new ModelMapper().map(site.getNmHouseDistrict(), NMHouseDistrictNestedResponse.class);
                    CongressionalDistrictResponse congressionalDistrictResponse = new ModelMapper().map(site.getCongressionalDistrictEntity(), CongressionalDistrictResponse.class);
                    SenateDistrictResponseModel senateDistrictResponseModel = new ModelMapper().map(site.getSenateDistrict(), SenateDistrictResponseModel.class);

                    //add a district Boolean
                    Boolean addNMHouseDistrict = true;
                    Boolean addCongressionalDistrict = true;
                    Boolean addSenateDistrict = true;

                    for(NMHouseDistrictNestedResponse districtResponse: nmHouseDistrictNestedResponses){

                        if(districtResponse.getHouseDistrictId() == site.getNmHouseDistrict().getHouseDistrictId()){
                            addNMHouseDistrict = false;
                        }
                    }

                    for(CongressionalDistrictResponse districtResponse: congressionalDistrictResponseList){

                        if(districtResponse.getCongressionalDistrictId() == site.getNmHouseDistrict().getHouseDistrictId()){

                            addCongressionalDistrict = false;
                        }
                    }

                    for(SenateDistrictResponseModel districtResponse: senateDistrictResponseModelList){

                        if(districtResponse.getSenateDistrictId() == site.getSenateDistrict().getSenateDistrictId()){

                            addSenateDistrict = false;
                        }
                    }

                    if(addNMHouseDistrict){

                        nmHouseDistrictNestedResponses.add(new ModelMapper().map(site.getNmHouseDistrict(), NMHouseDistrictNestedResponse.class));
                    }

                    if(addCongressionalDistrict){

                        congressionalDistrictResponseList.add(new ModelMapper().map(site.getCongressionalDistrictEntity(), CongressionalDistrictResponse.class));
                    }

                    if(addSenateDistrict){

                        senateDistrictResponseModelList.add(new ModelMapper().map(site.getSenateDistrict(), SenateDistrictResponseModel.class ));
                    }

                    siteDetailsNestedResponses.add(siteDetailsNestedResponse);
                }

                healthCenterDto.setSiteDetailsNestedResponseList(siteDetailsNestedResponses);
                healthCenterDto.setNmHouseDistrictNestedResponses(nmHouseDistrictNestedResponses);
                healthCenterDto.setSenateDistrictResponseModelList(senateDistrictResponseModelList);
                healthCenterDto.setCongressionalDistrictResponseList(congressionalDistrictResponseList);
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