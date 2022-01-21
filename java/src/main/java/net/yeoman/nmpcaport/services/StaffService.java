package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.StaffEntity;
import net.yeoman.nmpcaport.io.response.staff.StaffEssentials;
import net.yeoman.nmpcaport.shared.dto.StaffDto;

import java.util.List;

public interface StaffService {

    public StaffDto getStaffMember(String staffId);
    public StaffDto createStaffMemberForUSSenator(StaffDto staffDto);
    public StaffDto updateStaffMember(String staffId, StaffDto staffDto);
    public StaffDto deleteStaffMember(String staffId);
    public StaffDto createStaffMemberForCongressionalRep(StaffDto staffDto);

    //get staff essentials
    public StaffEssentials getStaffEssentials(StaffEntity staffEntity);
    public List<StaffEssentials> getStaffEssentials(List<StaffEntity> staffEntities);

    //check if object is null
    public Boolean entityIsNull(StaffEntity staffEntity);
    public Boolean entityIsNull(List<StaffEntity> staffEntities);
}
