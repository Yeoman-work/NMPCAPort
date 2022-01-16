package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.ServiceEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteServiceDetailsEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.ServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceServiceException;
import net.yeoman.nmpcaport.io.repositories.SiteServiceDetailsRepository;
import net.yeoman.nmpcaport.services.ServiceService;
import net.yeoman.nmpcaport.services.SiteServiceDetailsService;
import net.yeoman.nmpcaport.shared.dto.SiteServiceDetailsDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteServiceDetailsServiceImpl implements SiteServiceDetailsService {

    @Autowired
    private SiteServiceDetailsRepository siteServiceDetailsRepository;

    @Autowired
    private ServiceServiceImpl serviceService;

    @Autowired
    private SiteServiceImpl siteService;

    @Autowired
    private Utils utils;


    @Override
    public SiteServiceDetailsDto getSiteService(String id) {

        SiteServiceDetailsEntity serviceDetailsEntity = this.siteServiceDetailsRepository.findBySiteServiceDetailsId(id);

        return new ModelMapper().map(serviceDetailsEntity, SiteServiceDetailsDto.class);
    }

    @Override
    public SiteServiceDetailsDto deleteSiteService(String id) {

        SiteServiceDetailsEntity serviceDetailsEntity = this.siteServiceDetailsRepository.findBySiteServiceDetailsId(id);

        return new ModelMapper().map(serviceDetailsEntity, SiteServiceDetailsDto.class);
    }

    @Override
    public SiteServiceDetailsEntity createSiteService() {

        return this.generateUniqueId(new SiteServiceDetailsEntity());
    }

    @Override
    public SiteServiceDetailsDto updateSiteService(SiteServiceDetailsDto serviceDetailsDto, String id) {

        return null;
    }

    @Override
    public void saveSiteServiceEntity(SiteServiceDetailsEntity serviceDetailsEntity) {

        this.siteServiceDetailsRepository.save(serviceDetailsEntity);

        return;
    }

    @Override
    public SiteServiceDetailsEntity generateUniqueId(SiteServiceDetailsEntity siteServiceDetailsEntity) {

        if(this.entityIsNull(siteServiceDetailsEntity)) throw new SiteServiceServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        siteServiceDetailsEntity.setSiteServiceDetailsId(this.utils.generateRandomID());

        while(this.siteServiceDetailsRepository.existsBySiteServiceDetailsId(
                siteServiceDetailsEntity.getSiteServiceDetailsId())){

            siteServiceDetailsEntity.setSiteServiceDetailsId(this.utils.generateRandomID());
        }

        return siteServiceDetailsEntity;
    }

    @Override
    public Boolean existByPublicId(String publicId) {

        return this.siteServiceDetailsRepository.existsBySiteServiceDetailsId(publicId);
    }

    @Override
    public SiteEntity getSiteEntity(SiteServiceDetailsEntity siteServiceDetailsEntity) {

        if(this.entityIsNull(siteServiceDetailsEntity)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return siteServiceDetailsEntity.getSiteEntity();
    }

    @Override
    public List<SiteEntity> getSiteEntities(List<SiteServiceDetailsEntity> siteServiceDetailsEntities) {

        if(this.entityIsNull(siteServiceDetailsEntities)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SiteEntity> returnValue = new ArrayList<>();

        for(SiteServiceDetailsEntity siteServiceDetailsEntity: siteServiceDetailsEntities){

            returnValue.add(siteServiceDetailsEntity.getSiteEntity());
        }

        return returnValue;
    }

    @Override
    public ServiceEntity getServiceEntity(SiteServiceDetailsEntity siteServiceDetailsEntity) {

        if(this.entityIsNull(siteServiceDetailsEntity)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return siteServiceDetailsEntity.getServiceEntity();
    }

    @Override
    public List<ServiceEntity> getServiceEntities(List<SiteServiceDetailsEntity> siteServiceDetailsEntities) {

        if(this.entityIsNull(siteServiceDetailsEntities)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ServiceEntity> returnValue = new ArrayList<>();

        for(SiteServiceDetailsEntity siteServiceDetailsEntity: siteServiceDetailsEntities){

            if(siteServiceDetailsEntity.getSiteEntity() != null){

                returnValue.add(siteServiceDetailsEntity.getServiceEntity());
            }

        }

        return returnValue;
    }

    @Override
    public void linkServicesToSites(List<ServiceEntity> serviceEntities, SiteEntity siteEntity) {

        if(this.serviceService.entityIsNull(serviceEntities)) throw new ServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(this.siteService.entityIsNull(siteEntity)) throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        for(ServiceEntity serviceEntity: serviceEntities){

            SiteServiceDetailsEntity siteServiceDetailsEntity = this.createSiteService();

            siteServiceDetailsEntity.setSiteEntity(siteEntity);
            siteServiceDetailsEntity.setServiceEntity(serviceEntity);

            this.saveSiteServiceEntity(siteServiceDetailsEntity);
        }

        return;
    }


    @Override
    public Boolean entityIsNull(SiteServiceDetailsEntity siteServiceDetailsEntity) {
        return siteServiceDetailsEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<SiteServiceDetailsEntity> siteServiceDetailsEntities) {
        return siteServiceDetailsEntities == null;
    }
}
