package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.ServiceException;
import net.yeoman.nmpcaport.io.repositories.SiteFundingDetailsRepository;
import net.yeoman.nmpcaport.services.SiteFundingDetailsService;
import net.yeoman.nmpcaport.shared.dto.SiteFundingDetailsDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteFundingDetailsServiceImpl implements SiteFundingDetailsService {

    @Autowired
    private SiteFundingDetailsRepository siteFundingDetailsRepository;

    @Autowired
    private Utils utils;

    @Override
    public SiteFundingDetailsDto getSiteFunding(String id) {

        SiteFundingDetailsEntity siteFundingDetailsEntity = this.siteFundingDetailsRepository.findBySiteFundingDetailsId(id);
        return new ModelMapper().map(siteFundingDetailsEntity, SiteFundingDetailsDto.class);
    }

    @Override
    public SiteFundingDetailsDto deleteSiteFunding(String id) {

        SiteFundingDetailsEntity siteFundingDetails = this.siteFundingDetailsRepository.findBySiteFundingDetailsId(id);
        this.siteFundingDetailsRepository.delete(siteFundingDetails);

        return new ModelMapper().map(siteFundingDetails, SiteFundingDetailsDto.class);
    }

    @Override
    public List<SiteFundingDetailsDto> getAllSiteFunding() {

        return null;
    }

    @Override
    public SiteFundingDetailsDto createSiteFunding(SiteFundingDetailsEntity siteFundingDetailsServiceEntity) {

        SiteFundingDetailsEntity siteFundingDetails = this.siteFundingDetailsRepository.save(siteFundingDetailsServiceEntity);
        return new ModelMapper().map(siteFundingDetails, SiteFundingDetailsDto.class);
    }

    @Override
    public SiteFundingDetailsEntity createSiteFundingEntity(SiteFundingDetailsEntity siteFundingDetailsEntity) {

        return this.siteFundingDetailsRepository.save(siteFundingDetailsEntity);
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
