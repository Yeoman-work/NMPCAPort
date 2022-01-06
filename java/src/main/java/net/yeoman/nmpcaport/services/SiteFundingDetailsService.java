package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.shared.dto.SiteFundingDetailsDto;

import java.util.List;

public interface SiteFundingDetailsService {

    public SiteFundingDetailsDto getSiteFunding(String id);
    public SiteFundingDetailsDto deleteSiteFunding(String id);
    public List<SiteFundingDetailsDto> getAllSiteFunding();
    public SiteFundingDetailsDto createSiteFunding(SiteFundingDetailsEntity siteFundingDetailsServiceEntity);
    public SiteFundingDetailsEntity createSiteFundingEntity(SiteFundingDetailsEntity siteFundingDetailsEntity);
    public Boolean existByPublicId(String publicId);
}
