package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.senateDistrict.SenateDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;

import java.util.List;

public interface SenateDistrictService {

    SenateDistrictDto getDistrict(String districtId);
    SenateDistrictDto createDistrict(SenateDistrictDto senateDistrictDto);
    SenateDistrictDto updateDistrict(String senateDistrictId, SenateDistrictDto senateDistrictDto);
    SenateDistrictDto deleteDistrict(String senateDistrictId);


    SenateDistrictEntity findSenateDistrictEntity(String senateDistrictId);
    List<SenateDistrictDto> createBulkSenateDistrict(List<SenateDistrictDto> senateDistrictDtoList);

    //get all senateDistrict entities
    List<SenateDistrictEntity> getAllDistrictEntities();


    //get all districts and convert them to entity
    List<SenateDistrictEssentialResponse> getAllDistrictEssentials();

    //entity to essentials
    SenateDistrictEssentialResponse entityToEssentials(SenateDistrictEntity senateDistrictEntity);
    List<SenateDistrictEssentialResponse> entitiesToEssentials(List<SenateDistrictEntity> senateDistrictEntities);


    //get entities from site(s)
    SenateDistrictEntity getSenateDistrictEntitiesFromSites(SiteEntity siteEntity);
    List<SenateDistrictEntity> getSenateDistrictEntitiesFromSites(List<SiteEntity> siteEntities);

    SenateDistrictDto entityToDto(SenateDistrictEntity senateDistrictEntity);
    List<SenateDistrictDto> entityToDto(List<SenateDistrictEntity> senateDistrictEntities);
    SenateDistrictNestedResponse dtoToNestedResponse(SenateDistrictDto senateDistrictDto);
    List<SenateDistrictNestedResponse> dtoToNestedResponse(List<SenateDistrictDto> senateDistrictDtoList);

    Boolean entityIsNull(SenateDistrictEntity senateDistrictEntity);
    Boolean entityIsNull(List<SenateDistrictEntity> senateDistrictEntities);
    Boolean dtoIsNull(SenateDistrictDto senateDistrictDto);
    Boolean dtoIsNull(List<SenateDistrictDto> senateDistrictDtoList);
}
