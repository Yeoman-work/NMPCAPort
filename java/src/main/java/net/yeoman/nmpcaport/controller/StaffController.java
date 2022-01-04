package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.staff.StaffDetailsRequestCongressionalRep;
import net.yeoman.nmpcaport.io.request.staff.StaffDetailsRequestSenator;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;
import net.yeoman.nmpcaport.services.Impl.StaffServiceImpl;
import net.yeoman.nmpcaport.shared.dto.StaffDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public StaffResponse createStaffMember(@RequestBody StaffDetailsRequestSenator staffDetails){
        ModelMapper modelMapper = new ModelMapper();
        System.out.println("good up till here");
        StaffDto staffDto = modelMapper.map(staffDetails, StaffDto.class);

        StaffDto savedStaffMember = this.staffService.createStaffMemberForUSSenator(staffDto);


        return modelMapper.map(savedStaffMember, StaffResponse.class);
    }

    @PostMapping(path="/congressionalRep",
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public StaffResponse createStaffMemberCongressionalRep(@RequestBody StaffDetailsRequestCongressionalRep staffDetails){
        ModelMapper modelMapper = new ModelMapper();

        StaffDto staffDto = modelMapper.map(staffDetails, StaffDto.class);

        StaffDto savedStaffMember = this.staffService.createStaffMemberForCongressionalRep(staffDto);


        return modelMapper.map(savedStaffMember, StaffResponse.class);
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
