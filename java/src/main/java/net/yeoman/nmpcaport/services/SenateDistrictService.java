package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;

import java.util.List;

public interface SenateDistrictService {

    public SenateDistrictDto getDistrict(String districtId);
    public SenateDistrictDto createDistrict(SenateDistrictDto senateDistrictDto);
    public SenateDistrictDto updateDistrict(String senateDistrictId, SenateDistrictDto senateDistrictDto);
    public SenateDistrictDto deleteDistrict(String senateDistrictId);
    public SenateDistrictEntity findSenateDistrictEntity(String senateDistrictId);
    public List<SenateDistrictDto> getAllSenateDistricts();
}
