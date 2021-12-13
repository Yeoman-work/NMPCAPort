package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.SiteServiceDetailsEntity;
import net.yeoman.nmpcaport.io.repositories.SiteServiceDetailsRepository;
import net.yeoman.nmpcaport.services.SiteServiceDetailsService;
import net.yeoman.nmpcaport.shared.dto.SiteServiceDetailsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
