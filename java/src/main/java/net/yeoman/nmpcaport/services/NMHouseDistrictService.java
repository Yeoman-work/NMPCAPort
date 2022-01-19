package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.NMHouseDistrictEntity;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictNestedResponse;
import net.yeoman.nmpcaport.shared.dto.NMHouseDistrictDto;

import java.util.List;

public interface NMHouseDistrictService {

    public NMHouseDistrictDto getNMHouseDistrict(String houseDistrictId);
    public NMHouseDistrictDto createNMHouseDistrict(NMHouseDistrictDto nmHouseDistrictDto);
    public NMHouseDistrictDto updatedNMHouseDistrict(String nmHouseDistrictId, NMHouseDistrictDto nmHouseDistrictDto);
    public NMHouseDistrictDto deleteNMHouseDistrict(String nmHouseDistrictId);
    public List<NMHouseDistrictDto> bulkCreateHouseDistrict(List<NMHouseDistrictDto> nmHouseDistrictDtoList);
    public List<NMHouseDistrictDto> getAllNMHouseDistrictResponses();
    public NMHouseDistrictEntity findNMHouseDistrictEntity(String nmHouseDistrictId);


    public NMHouseDistrictEssentialResponse entityToEssentials(NMHouseDistrictEntity nmHouseDistrictEntity);

    public NMHouseDistrictDto entityToDto(NMHouseDistrictEntity nmHouseDistrictEntity);
    public List<NMHouseDistrictDto> entityToDto(List<NMHouseDistrictEntity> nmHouseDistrictEntities);

    public NMHouseDistrictNestedResponse dtoToNestedResponse(NMHouseDistrictDto nmHouseDistrictDto);
    public List<NMHouseDistrictNestedResponse> dtoToNestedResponse(List<NMHouseDistrictDto> nmHouseDistrictDtoList);

     public Boolean entityIsNull(NMHouseDistrictEntity nmHouseDistrictEntity);
     public Boolean entityIsNull(List<NMHouseDistrictEntity> nmHouseDistrictEntities);

     public Boolean dtoIsNull(NMHouseDistrictDto nmHouseDistrictDto);
     public Boolean dtoIsNull(List<NMHouseDistrictDto> houseDistrictDtoList);
}
