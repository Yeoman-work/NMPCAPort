package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.CongressionalDistrictEntity;
import net.yeoman.nmpcaport.exception.CongressionalDistrictServiceException;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictNestedResponse;
import net.yeoman.nmpcaport.shared.dto.CongressionalDistrictDto;

import java.util.List;

public interface CongressionalDistrictService {

    public CongressionalDistrictDto getCongressionalDistrict(String congressionalDistrictId);
    public CongressionalDistrictDto createCongressionalDistrict(CongressionalDistrictDto congressionalDistrictDto);
    public List<CongressionalDistrictDto> bulkCreateCongressionalDistrict(List<CongressionalDistrictDto> congressionalDistrictDtoList);
    public CongressionalDistrictDto updateCongressionalDistrict(String congressionalDistrictId, CongressionalDistrictDto congressionalDistrictDto);
    public CongressionalDistrictDto deleteCongressionalDistrict(String congressionalDistrictId);
    public List<CongressionalDistrictDto> findAllCongressionalDistricts();


    //entity to essentials
    public CongressionalDistrictEssentialsResponse entityToEssentials(CongressionalDistrictEntity congressionalDistrictEntity);

    public CongressionalDistrictDto entityToDto(CongressionalDistrictEntity congressionalDistrictEntity);
    public List<CongressionalDistrictDto> entityToDto(List<CongressionalDistrictEntity> congressionalDistrictEntityList);

    public CongressionalDistrictNestedResponse dtoToNestedResponse(CongressionalDistrictDto congressionalDistrictDto);
    public List<CongressionalDistrictNestedResponse> dtoToNestedResponse(List<CongressionalDistrictDto> congressionalDistrictDtoList);

    public Boolean entityIsNull(CongressionalDistrictEntity congressionalDistrictEntity);
    public Boolean entityIsNull(List<CongressionalDistrictEntity> congressionalDistrictEntityList);

    public Boolean dtoIsNull(CongressionalDistrictDto congressionalDistrictDto);
    public Boolean dtoIsNull(List<CongressionalDistrictDto> congressionalDistrictDtoList);

    public CongressionalDistrictServiceException isNullError(Boolean isNull);

}
