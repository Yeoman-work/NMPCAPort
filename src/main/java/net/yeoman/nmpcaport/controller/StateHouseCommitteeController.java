package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.stateHouseCommittee.StateHouseCommitteeDetailsList;
import net.yeoman.nmpcaport.io.response.stateHouseCommittee.StateHouseCommitteeResponse;
import net.yeoman.nmpcaport.services.Impl.StateHouseCommitteeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stateHouseCommittees")
public class StateHouseCommitteeController {

    @Autowired
    private StateHouseCommitteeServiceImpl stateHouseCommitteeService;

    @GetMapping
    public String getStateHouseCommittee(){

        return "inside get house committee";
    }

    @PostMapping("/batch")
    public List<StateHouseCommitteeResponse> createMultipleHouseCommittees(@RequestBody StateHouseCommitteeDetailsList stateCommitteeList){

        return this.stateHouseCommitteeService.createStateHouseCommittees(stateCommitteeList) ;
    }

    @PostMapping
    public String createStatHouseCommittee(){

        return "inside create house committee";
    }

    @PutMapping
    public String updateStateHouseCommittee(){

        return "inside update house committee";
    }

    @DeleteMapping
    public String deleteStateHouseCommittee(){

        return "inside delete house committee";
    }
}
