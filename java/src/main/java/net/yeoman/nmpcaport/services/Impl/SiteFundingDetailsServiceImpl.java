package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.io.repositories.SiteFundingDetailsRepository;
import net.yeoman.nmpcaport.services.SiteFundingDetailsService;
import net.yeoman.nmpcaport.shared.dto.SiteFundingDetailsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteFundingDetailsServiceImpl implements SiteFundingDetailsService {

    @Autowired
    private SiteFundingDetailsRepository siteFundingDetailsRepository;

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
}
