package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.io.response.HealthCenter.*;
import net.yeoman.nmpcaport.services.HealthCenterService;
import net.yeoman.nmpcaport.exception.*;
import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.io.repositories.HealthCenterRepository;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthCenterServiceImpl implements HealthCenterService {


    private final HealthCenterRepository healthCenterRepository;

    private final SiteServiceImpl siteService;

    private final ServiceServiceImpl serviceService;

    private final FundServiceImpl fundService;
    private final HouseDistrictServiceImpl houseDistrictService;
    private final SenateDistrictServiceImpl senateDistrictService;
    private final CongressionalDistrictServiceImpl congressionalDistrictService;

    private final ContactServiceImpl contactService;

    private final Utils utils;

    public HealthCenterServiceImpl(HealthCenterRepository healthCenterRepository,
                                   @Lazy SiteServiceImpl siteService,
                                   ServiceServiceImpl serviceService,
                                   FundServiceImpl fundService,
                                   @Lazy HouseDistrictServiceImpl houseDistrictService,
                                   @Lazy SenateDistrictServiceImpl senateDistrictService,
                                   @Lazy CongressionalDistrictServiceImpl congressionalDistrictService,
                                   @Lazy ContactServiceImpl contactService,
                                   Utils utils
                                   ){

        this.healthCenterRepository = healthCenterRepository;
        this.siteService = siteService;
        this.serviceService = serviceService;
        this.fundService = fundService;
        this.houseDistrictService = houseDistrictService;
        this.senateDistrictService = senateDistrictService;
        this.congressionalDistrictService = congressionalDistrictService;
        this.contactService = contactService;
        this.utils = utils;

    }



    @Override
    public void createHealthCenter(HealthCenterDetailsRequestModel healthCenterDetailsRequestModel) {

        if(this.requestIsNull(healthCenterDetailsRequestModel))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterEntity healthCenterEntity = this.generateHealthCenterWithUniqueId(new HealthCenterEntity());

        healthCenterEntity.setName(healthCenterDetailsRequestModel.getName());
        healthCenterEntity.setNameAbbr(healthCenterDetailsRequestModel.getNameAbbr());

        HealthCenterEntity savedHealthCenter = this.saveHealthCenterEntity(healthCenterEntity);

        if(!this.siteService.requestIsNull(
                healthCenterDetailsRequestModel.getSitesRequest())
        ){
            this.siteService.createSitesBulk(healthCenterDetailsRequestModel.getSitesRequest(), savedHealthCenter);
        }

    }

    @Override
    public HealthCenterResponseModel getHealthCenterResponse(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterResponseModel healthCenterResponseModel = new HealthCenterResponseModel();

        healthCenterResponseModel.setName(healthCenterEntity.getName());
        healthCenterResponseModel.setNameAbbr(healthCenterEntity.getNameAbbr());
        healthCenterResponseModel.setCreatedAt(healthCenterEntity.getCreatedAt());
        healthCenterResponseModel.setUpdatedAt(healthCenterEntity.getUpdatedAt());
        healthCenterResponseModel.setHealthCenterId(healthCenterEntity.getHealthCenterId());

        if(healthCenterEntity.getContacts() != null){

            healthCenterResponseModel.setContactEssentials(
                    this.contactService.getContactEssentials(healthCenterEntity.getContacts()
                    )
            );
        }

        if(healthCenterEntity.getSiteEntities() != null){

            healthCenterResponseModel.setSiteEssentialsResponses(
                    this.siteService.entityToEssential(
                            healthCenterEntity.getSiteEntities()
                    )
            );

            healthCenterResponseModel.setHouseDistrictEssentialResponses(
                    this.houseDistrictService.entityToEssentials(
                            this.houseDistrictService.getHouseDistrictsFromSites(
                                    healthCenterEntity.getSiteEntities()
                            )
                    )
            );

            healthCenterResponseModel.setSenateDistrictEssentialResponses(
                    this.senateDistrictService.entitiesToEssentials(
                            this.senateDistrictService.getSenateDistrictEntitiesFromSites(
                                    healthCenterEntity.getSiteEntities())
                    )
            );

            healthCenterResponseModel.setCongressionalDistrictEssentialsResponses(
                    this.congressionalDistrictService.entityToEssentials(
                            this.congressionalDistrictService.getCongressionalDistrictFromSites(
                                    healthCenterEntity.getSiteEntities()
                            )
                    )
            );



        }

        return healthCenterResponseModel;
    }


    @Override
    public void deleteHealthCenter(String healthCenterId) {

        this.healthCenterRepository.delete(this.healthCenterRepository.findByHealthCenterId(healthCenterId));
    }

    @Override
    public HealthCenterEntity getHealthCenterEntity(String healthCenterId) {

        return this.healthCenterRepository.findByHealthCenterId(healthCenterId);
    }


    @Override
    public HealthCenterEntity saveHealthCenterEntity(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.healthCenterRepository.save(healthCenterEntity);
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
    public HealthCenterEntity generateHealthCenterWithUniqueId(HealthCenterEntity healthCenter) {

        //check if health center is null
        if(healthCenter == null) throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //set health center id
        healthCenter.setHealthCenterId(utils.generateRandomID());

        while(this.healthCenterRepository.existsByHealthCenterId(healthCenter.getHealthCenterId())){

            healthCenter.setHealthCenterId(utils.generateRandomID());
        }

        return healthCenter;
    }



    //get health center for dashboard
    @Override
    public HealthCenterDashBoard entityToDashBoardData(HealthCenterEntity healthCenterEntity) {

        if(this.entityIsNull(healthCenterEntity))
            throw new HealthCenterServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HealthCenterDashBoard healthCenterDashBoard = new HealthCenterDashBoard();

        healthCenterDashBoard.setHealthCenterId(healthCenterEntity.getHealthCenterId());

        healthCenterDashBoard.setName(healthCenterEntity.getName());

        healthCenterDashBoard.setNameAbbr(healthCenterEntity.getNameAbbr());

        healthCenterDashBoard.setSiteEssentialsResponses(
                this.siteService.entityToEssential(
                        healthCenterEntity.getSiteEntities()
                )
        );

        healthCenterDashBoard.setServiceEssentialsResponses(
                this.serviceService.getServiceEssentialsFromSite(
                        healthCenterEntity.getSiteEntities()
                )
        );

        healthCenterDashBoard.setFundEssentialsResponses(
                this.fundService.getEssentialsFromSite(
                        healthCenterEntity.getSiteEntities()
                )
        );


        healthCenterDashBoard.setHouseDistrictEssentialResponses(
                this.houseDistrictService.entityToEssentials(
                        this.houseDistrictService.getHouseDistrictsFromSites(
                                healthCenterEntity.getSiteEntities()
                        )
                )
        );

        healthCenterDashBoard.setSenateDistrictEssentialResponses(
                this.senateDistrictService.entitiesToEssentials(
                        this.senateDistrictService.getSenateDistrictEntitiesFromSites(
                                healthCenterEntity.getSiteEntities()
                        )
                )
        );

        healthCenterDashBoard.setCongressionalDistrictEssentialResponses(
                this.congressionalDistrictService.entityToEssentials(
                        this.congressionalDistrictService.getCongressionalDistrictFromSites(
                                healthCenterEntity.getSiteEntities()
                        )
                )
        );


        return healthCenterDashBoard;
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
    public List<HealthCenterDashBoard> healthCenterDashBoard(int page, int limit) {

        List<HealthCenterEntity> healthCenterDtoList = this.getHealthCenterEntities(page, limit);


        return this.entityToDashBoardData(healthCenterDtoList);
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
