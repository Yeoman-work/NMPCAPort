package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.NMHouseDistrictEntity;
import net.yeoman.nmpcaport.shared.dto.NMHouseDistrictDto;

public interface NMHouseDistrictService {

    public NMHouseDistrictDto getNMHouseDistrict(String houseDistrictId);
    public NMHouseDistrictDto createNMHouseDistrict(NMHouseDistrictDto nmHouseDistrictDto);
    public NMHouseDistrictDto updatedNMHouseDistrict(String nmHouseDistrictId, NMHouseDistrictDto nmHouseDistrictDto);
    public NMHouseDistrictDto deleteNMHouseDistrict(String nmHouseDistrictId);
}
