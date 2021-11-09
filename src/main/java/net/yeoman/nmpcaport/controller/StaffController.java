package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.staff.StaffDetailsRequest;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;
import net.yeoman.nmpcaport.services.Impl.StaffServiceImpl;
import net.yeoman.nmpcaport.shared.dto.StaffDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffServiceImpl staffService;


    @GetMapping
    public String getStaffMember(){

        return "get staff member";
    }

    @PostMapping
    public StaffResponse createStaffMember(@RequestBody StaffDetailsRequest staffDetails){

        StaffDto staffDto = this.staffService.createStaffMember(new ModelMapper().map(staffDetails, StaffDto.class));

        return new ModelMapper().map(staffDto, StaffResponse.class);
    }


    @PutMapping
    public String updateStaffMember(){

        return "update staff member";
    }

    @DeleteMapping
    public String deleteStaffMember(){

        return "delete staff member";
    }

}
