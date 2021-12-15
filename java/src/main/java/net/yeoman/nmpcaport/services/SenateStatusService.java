package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.shared.dto.SenateStatusDto;

import java.util.List;

public interface SenateStatusService {

    public SenateStatusDto getSenateStatus(String statusId);
    public SenateStatusDto deleteSenateStatus(String statusId);
    public SenateStatusDto updateSenateStatus(String statusId, SenateStatusDto senateStatusDto);
    public SenateStatusDto createSenateStatus(SenateStatusDto senateStatusDto);
    public List<SenateStatusDto> createSenateStatus(List<SenateStatusDto> senateStatusDtoList);
}
