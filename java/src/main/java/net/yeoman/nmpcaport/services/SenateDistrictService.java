package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.io.request.senateDistrict.SenateDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;

import java.util.List;

public interface SenateDistrictService {

    public SenateDistrictDto getDistrict(String districtId);
    public SenateDistrictDto createDistrict(SenateDistrictDto senateDistrictDto);
    public SenateDistrictDto updateDistrict(String senateDistrictId, SenateDistrictDto senateDistrictDto);
    public SenateDistrictDto deleteDistrict(String senateDistrictId);
    public SenateDistrictEntity findSenateDistrictEntity(String senateDistrictId);
    public List<SenateDistrictDto> getAllSenateDistricts();
    public List<SenateDistrictDto> createBulkSenateDistrict(List<SenateDistrictDto> senateDistrictDtoList);


    public SenateDistrictDto entityToDto(SenateDistrictEntity senateDistrictEntity);
    public List<SenateDistrictDto> entityToDto(List<SenateDistrictEntity> senateDistrictEntities);
    public SenateDistrictNestedResponse dtoToNestedResponse(SenateDistrictDto senateDistrictDto);
    public List<SenateDistrictNestedResponse> dtoToNestedResponse(List<SenateDistrictDto> senateDistrictDtoList);

    public Boolean entityIsNull(SenateDistrictEntity senateDistrictEntity);
    public Boolean entityIsNull(List<SenateDistrictEntity> senateDistrictEntities);
    public Boolean dtoIsNull(SenateDistrictDto senateDistrictDto);
    public Boolean dtoIsNull(List<SenateDistrictDto> senateDistrictDtoList);
}
