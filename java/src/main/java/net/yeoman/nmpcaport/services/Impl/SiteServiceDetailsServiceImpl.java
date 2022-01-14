package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.ServiceEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteServiceDetailsEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.ServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.io.repositories.SiteServiceDetailsRepository;
import net.yeoman.nmpcaport.services.ServiceService;
import net.yeoman.nmpcaport.services.SiteServiceDetailsService;
import net.yeoman.nmpcaport.shared.dto.SiteServiceDetailsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteServiceDetailsServiceImpl implements SiteServiceDetailsService {

    @Autowired
    private SiteServiceDetailsRepository siteServiceDetailsRepository;


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
    public SiteServiceDetailsDto createSiteService(SiteServiceDetailsDto siteServiceDetailsDto) {

        SiteServiceDetailsEntity serviceDetailsEntity = new ModelMapper().map(siteServiceDetailsDto, SiteServiceDetailsEntity.class);
        SiteServiceDetailsEntity savedServiceDetailsEntity = this.siteServiceDetailsRepository.save(serviceDetailsEntity);

        return new ModelMapper().map(savedServiceDetailsEntity, SiteServiceDetailsDto.class);
    }

    @Override
    public SiteServiceDetailsDto updateSiteService(SiteServiceDetailsDto serviceDetailsDto, String id) {

        return null;
    }

    @Override
    public SiteServiceDetailsEntity createSiteServiceEntity(SiteServiceDetailsEntity serviceDetailsEntity) {

        return this.siteServiceDetailsRepository.save(serviceDetailsEntity);

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
    public Boolean entityIsNull(SiteServiceDetailsEntity siteServiceDetailsEntity) {
        return siteServiceDetailsEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<SiteServiceDetailsEntity> siteServiceDetailsEntities) {
        return siteServiceDetailsEntities == null;
    }
}
