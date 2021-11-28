package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.GovernorStatusEntity;
import net.yeoman.nmpcaport.io.request.governorStatus.GovernorStatusRequest;
import net.yeoman.nmpcaport.io.response.GovernorStatus.GovernorStatusResponse;
import net.yeoman.nmpcaport.shared.dto.GovernorStatusDto;

import java.util.List;

public interface GovernorStatusService {

    public GovernorStatusDto findStatusById(String publicId);

    public GovernorStatusDto createStatus(GovernorStatusDto newStatus);

    public GovernorStatusDto updateStatus(GovernorStatusDto updatedStatus, String publicId);

    public GovernorStatusEntity findGovernorStatusEntity(String id);

    public List<GovernorStatusResponse> createStatusBulk(List<GovernorStatusRequest> list);

    public GovernorStatusDto deleteGovernorStatus(String publicId);
}
