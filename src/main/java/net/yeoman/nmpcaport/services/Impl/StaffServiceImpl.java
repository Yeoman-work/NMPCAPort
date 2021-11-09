package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.StaffEntity;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;
import net.yeoman.nmpcaport.repositories.StaffRepository;
import net.yeoman.nmpcaport.services.StaffService;
import net.yeoman.nmpcaport.shared.dto.StaffDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private Utils utils;

    @Override
    public StaffDto getStaffMember(String staffId) {
        return null;
    }


    @Override
    public StaffDto createStaffMember(StaffDto staffDto) {

        StaffEntity staffMember = new ModelMapper().map(staffDto, StaffEntity.class);

        staffMember.setStaffId(utils.generateRandomID());

        while(this.staffRepository.existsByStaffId(staffMember.getStaffId())){

            staffMember.setStaffId(utils.generateRandomID());

        }

        StaffEntity storedStaffMember = this.staffRepository.save(staffMember);


        return new ModelMapper().map(storedStaffMember, StaffDto.class) ;
    }

    @Override
    public StaffDto updateStaffMember(String staffId, StaffDto staffDto) {
        return null;
    }

    @Override
    public StaffDto deleteStaffMember(String staffId) {
        return null;
    }
}
