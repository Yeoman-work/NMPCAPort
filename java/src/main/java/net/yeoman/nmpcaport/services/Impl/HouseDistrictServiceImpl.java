package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.HouseDistrictEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.HouseDistrictServiceException;
import net.yeoman.nmpcaport.exception.SiteFundingServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.exception.StateRepServiceException;
import net.yeoman.nmpcaport.io.repositories.HouseDistrictRepository;
import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
import net.yeoman.nmpcaport.services.HouseDistrictService;
import net.yeoman.nmpcaport.shared.dto.NMHouseDistrictDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseDistrictServiceImpl implements HouseDistrictService {



    private final HouseDistrictRepository houseDistrictRepository;
    private final StateRepServiceImpl stateRepService;
    private final SiteServiceImpl siteService;

    private final Utils utils;

    public HouseDistrictServiceImpl(HouseDistrictRepository houseCommitteeRepository,
                                    StateRepServiceImpl stateRepService,
                                    SiteServiceImpl siteService,
                                    Utils utils
                                    ){

        this.houseDistrictRepository = houseCommitteeRepository;
        this.stateRepService = stateRepService;
        this.siteService = siteService;
        this.utils = utils;
    }


    @Override
    public void deleteHouseDistrict(String publicId) {

        this.houseDistrictRepository.delete(this.getHouseDistrict(publicId));
    }

    @Override
    public void updateHouseDistrict(String publicId, HouseDistrictDetailsRequest houseDistrictDetailsRequest) {

    }

    @Override
    public HouseDistrictEntity convertRequestToEntity(HouseDistrictDetailsRequest houseDistrictDetailsRequest) {

        if(this.requestIsNull(houseDistrictDetailsRequest))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HouseDistrictEntity houseDistrict = this.generateUniqueId(new HouseDistrictEntity());

        houseDistrict.setName(houseDistrictDetailsRequest.getName());
        houseDistrict.setMap(houseDistrictDetailsRequest.getMap());

        if(houseDistrictDetailsRequest.getRepId() != null){

            houseDistrict.setStateRep(this.stateRepService.getStateRepEntity(houseDistrictDetailsRequest.getRepId()));
        }

        if(houseDistrictDetailsRequest.getStateRepDetailsRequest() != null){

            StateRepEntity stateRepEntity = this.stateRepService.requestToEntity(
                    houseDistrictDetailsRequest.getStateRepDetailsRequest()
            );

            if(stateRepEntity == null)
                throw new StateRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

            houseDistrict.setStateRep(stateRepEntity);
        }

        return this.saveHouseDistrict(houseDistrict);
    }

    @Override
    public HouseDistrictEntity saveHouseDistrict(HouseDistrictEntity houseDistrictEntity) {

        return this.houseDistrictRepository.save(houseDistrictEntity);
    }

    @Override
    public HouseDistrictEntity getHouseDistrict(String publicId) {

        return this.houseDistrictRepository.findByHouseDistrictId(publicId);
    }

    @Override
    public List<HouseDistrictEntity> getAllHouseDistrictEntities() {

        return this.houseDistrictRepository.findAll();
    }

    @Override
    public HouseDistrictEntity generateUniqueId(HouseDistrictEntity houseDistrictEntity) {

        if(entityIsNull(houseDistrictEntity))
           throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        houseDistrictEntity.setHouseDistrictId(this.utils.generateRandomID());

        while(this.houseDistrictRepository.existsByHouseDistrictId(houseDistrictEntity.getHouseDistrictId())){

            houseDistrictEntity.setHouseDistrictId(this.utils.generateRandomID());
        }

        return houseDistrictEntity;
    }

    @Override
    public HouseDistrictEntity createHouseDistrict(HouseDistrictDetailsRequest houseDistrictDetailsRequest) {

        if(requestIsNull(houseDistrictDetailsRequest))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HouseDistrictEntity houseDistrict = this.generateUniqueId(new HouseDistrictEntity());

        houseDistrict.setName(houseDistrictDetailsRequest.getName());
        houseDistrict.setMap(houseDistrictDetailsRequest.getMap());

        if(houseDistrictDetailsRequest.getRepId() != null){

            StateRepEntity stateRep = this.stateRepService.getStateRepEntity(houseDistrictDetailsRequest.getRepId());

            if(this.stateRepService.entityIsNull(stateRep))
                throw new StateRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

            houseDistrict.setStateRep(stateRep);
        }

        if(houseDistrictDetailsRequest.getStateRepDetailsRequest() != null){

            houseDistrict.setStateRep(
                    this.stateRepService.requestToEntity(
                            houseDistrictDetailsRequest.getStateRepDetailsRequest()
                    )
            );
        }

        return this.saveHouseDistrict(houseDistrict);
    }

    @Override
    public HouseDistrictEssentialResponse entityToEssentials(HouseDistrictEntity nmHouseDistrictEntity) {

        if(this.entityIsNull(nmHouseDistrictEntity))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HouseDistrictEssentialResponse nmHouseDistrictEssentialResponse = new HouseDistrictEssentialResponse();

        nmHouseDistrictEssentialResponse.setHouseDistrictId(nmHouseDistrictEntity.getHouseDistrictId());
        nmHouseDistrictEssentialResponse.setName(nmHouseDistrictEntity.getName());

        return nmHouseDistrictEssentialResponse;
    }

    @Override
    public List<HouseDistrictEssentialResponse> entityToEssentials(List<HouseDistrictEntity> houseDistrictEntities) {

        if(this.entityIsNull(houseDistrictEntities))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HouseDistrictEssentialResponse> returnValue = new ArrayList<>();

        for(HouseDistrictEntity houseDistrictEntity: houseDistrictEntities){

            returnValue.add(this.entityToEssentials(houseDistrictEntity));
        }
        return returnValue.stream()
                .sorted(Comparator.comparing(HouseDistrictEssentialResponse::getName))
                .collect(Collectors.toList());
    }

    @Override
    public HouseDistrictEntity getHouseDistrictsFromSites(SiteEntity siteEntity) {

        if(this.siteService.entityIsNull(siteEntity))
            throw new SiteFundingServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return siteEntity.getHouseDistrictEntity();
    }

    @Override
    public List<HouseDistrictEntity> getHouseDistrictsFromSites(List<SiteEntity> siteEntities) {

        if(this.siteService.entityIsNull(siteEntities))
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HouseDistrictEntity> returnValue = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            returnValue.add(this.getHouseDistrictsFromSites(siteEntity));
        }

        return returnValue;
    }


    @Override
    public Boolean entityIsNull(HouseDistrictEntity nmHouseDistrictEntity) {
        return nmHouseDistrictEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<HouseDistrictEntity> nmHouseDistrictEntities) {
        return nmHouseDistrictEntities == null;
    }

    @Override
    public Boolean requestIsNull(HouseDistrictDetailsRequest houseDistrictDetailsRequest) {

        return houseDistrictDetailsRequest == null;
    }


}
