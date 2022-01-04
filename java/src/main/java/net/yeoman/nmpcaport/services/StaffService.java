package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.shared.dto.StaffDto;

public interface StaffService {

    public StaffDto getStaffMember(String staffId);
    public StaffDto createStaffMemberForUSSenator(StaffDto staffDto);
    public StaffDto updateStaffMember(String staffId, StaffDto staffDto);
    public StaffDto deleteStaffMember(String staffId);
    public StaffDto createStaffMemberForCongressionalRep(StaffDto staffDto);
}
