package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.FundServiceException;
import net.yeoman.nmpcaport.exception.ServiceException;
import net.yeoman.nmpcaport.exception.SiteFundingServiceException;
import net.yeoman.nmpcaport.io.repositories.SiteFundingDetailsRepository;
import net.yeoman.nmpcaport.services.SiteFundingDetailsService;
import net.yeoman.nmpcaport.shared.dto.SiteFundingDetailsDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteFundingDetailsServiceImpl implements SiteFundingDetailsService {


    private final SiteFundingDetailsRepository siteFundingDetailsRepository;
    private final Utils utils;

    public SiteFundingDetailsServiceImpl(SiteFundingDetailsRepository siteFundingDetailsRepository,

                                         Utils utils
    ){

        this.siteFundingDetailsRepository = siteFundingDetailsRepository;
        this.utils = utils;

    }

    @Override
    public SiteFundingDetailsDto getSiteFunding(String id) {

        SiteFundingDetailsEntity siteFundingDetailsEntity = this.siteFundingDetailsRepository.findBySiteFundingDetailsId(id);
        return new ModelMapper().map(siteFundingDetailsEntity, SiteFundingDetailsDto.class);
    }

    @Override
    public void deleteSiteFunding(String id) {

        this.siteFundingDetailsRepository.delete(this.siteFundingDetailsRepository.findBySiteFundingDetailsId(id));

    }

    @Override
    public List<SiteFundingDetailsDto> getAllSiteFunding() {

        return null;
    }

    @Override
    public void savedSiteFundDetails(SiteFundingDetailsEntity siteFundingDetailsEntity) {

        this.siteFundingDetailsRepository.save(siteFundingDetailsEntity);

    }

    @Override
    public SiteFundingDetailsEntity saveSiteFundDetailsWithReturnEntity(
            SiteFundingDetailsEntity siteFundingDetailsServiceEntity
    ) {

        return this.siteFundingDetailsRepository.save(siteFundingDetailsServiceEntity);
    }



    @Override
    public SiteFundingDetailsEntity createSiteFundingEntity() {

        return new SiteFundingDetailsEntity();
    }

    @Override
    public SiteFundingDetailsEntity generateUniqueId(SiteFundingDetailsEntity siteFundingDetailsEntity) {

        if(this.entityIsNull(siteFundingDetailsEntity))
            throw new SiteFundingServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        siteFundingDetailsEntity.setSiteFundingDetailsId(this.utils.generateRandomID());

        while(this.existByPublicId(siteFundingDetailsEntity.getSiteFundingDetailsId())){

            siteFundingDetailsEntity.setSiteFundingDetailsId(this.utils.generateRandomID());
        }

        return siteFundingDetailsEntity;
    }

    @Override
    public Boolean existByPublicId(String publicId) {

        return this.siteFundingDetailsRepository.existsBySiteFundingDetailsId(publicId);
    }

    @Override
    public FundEntity getFundEntity(SiteFundingDetailsEntity siteFundingDetailsEntity) {

        if(entityIsNull(siteFundingDetailsEntity)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return siteFundingDetailsEntity.getFundEntity();
    }

    @Override
    public List<FundEntity> getFundEntities(List<SiteFundingDetailsEntity> siteFundingDetailsEntities) {

        if(this.entityIsNull(siteFundingDetailsEntities)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<FundEntity> returnValue = new ArrayList<>();

        for(SiteFundingDetailsEntity siteFundingDetailsEntity: siteFundingDetailsEntities){

            returnValue.add(this.getFundEntity(siteFundingDetailsEntity));
        }

        return returnValue;
    }

    @Override
    public void linkFundingToSites(List<FundEntity> fundEntityList, SiteEntity siteEntity) {

        if(fundEntityList == null)
            throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        for(FundEntity fundEntity: fundEntityList){

            SiteFundingDetailsEntity siteFundingDetailsEntity = this.generateUniqueId(this.createSiteFundingEntity());

            siteFundingDetailsEntity.setSiteEntity(siteEntity);
            siteFundingDetailsEntity.setFundEntity(fundEntity);

            savedSiteFundDetails(siteFundingDetailsEntity);

        }
    }

    @Override
    public SiteEntity getSiteEntity(SiteFundingDetailsEntity siteFundingDetailsEntity) {

        if(this.entityIsNull(siteFundingDetailsEntity)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return siteFundingDetailsEntity.getSiteEntity();
    }

    @Override
    public List<SiteEntity> getSiteEntities(List<SiteFundingDetailsEntity> siteFundingDetailsEntityList) {

        if(this.entityIsNull(siteFundingDetailsEntityList)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SiteEntity> returnValue = new ArrayList<>();

        for(SiteFundingDetailsEntity siteFundingDetailsEntity: siteFundingDetailsEntityList ){

            returnValue.add(this.getSiteEntity(siteFundingDetailsEntity));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(SiteFundingDetailsEntity siteFundingDetailsEntity) {
        return siteFundingDetailsEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<SiteFundingDetailsEntity> siteFundingDetailsEntities) {
        return siteFundingDetailsEntities == null;
    }

    @Override
    public Boolean dtoIsNull(SiteFundingDetailsEntity siteFundingDetailsEntity) {
        return siteFundingDetailsEntity ==  null;
    }

    @Override
    public Boolean dtoIsNull(List<SiteFundingDetailsEntity> siteFundingDetailsEntities) {
        return siteFundingDetailsEntities ==  null;
    }
}
