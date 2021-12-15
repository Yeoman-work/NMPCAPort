package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.shared.dto.NMHouseStatusDto;

import java.util.List;

public interface NMHouseStatusService {

    public NMHouseStatusDto getNMHouseStatusDto(String houseStatusId);
    public NMHouseStatusDto createNMHouseStatus(NMHouseStatusDto nmHouseStatusDto);
    public NMHouseStatusDto updateNMHouseStatus(String statusId, NMHouseStatusDto nmHouseStatusDto);
    public NMHouseStatusDto deleteNMHouseStatus(String statusId);
    public List<NMHouseStatusDto> getAllNMHouseStatus();
    public List<NMHouseStatusDto> createdNMHouseStatusBulk(List<NMHouseStatusDto> nmHouseStatusDtoList);
}
