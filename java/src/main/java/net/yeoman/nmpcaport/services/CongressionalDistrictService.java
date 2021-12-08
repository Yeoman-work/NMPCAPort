package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.shared.dto.CongressionalDistrictDto;

import java.util.List;

public interface CongressionalDistrictService {

    public CongressionalDistrictDto getCongressionalDistrict(String congressionalDistrictId);
    public CongressionalDistrictDto createCongressionalDistrict(CongressionalDistrictDto congressionalDistrictDto);
    public List<CongressionalDistrictDto> bulkCreateCongressionalDistrict(List<CongressionalDistrictDto> congressionalDistrictDtoList);
    public CongressionalDistrictDto updateCongressionalDistrict(String congressionalDistrictId, CongressionalDistrictDto congressionalDistrictDto);
    public CongressionalDistrictDto deleteCongressionalDistrict(String congressionalDistrictId);
    public List<CongressionalDistrictDto> findAllCongressionalDistricts();

}
