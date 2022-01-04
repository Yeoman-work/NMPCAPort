package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.NMSenateCommittee.NMSenateCommitteesList;
import net.yeoman.nmpcaport.io.response.nmSenateCommittee.NMSenateCommitteeResponse;
import net.yeoman.nmpcaport.services.Impl.NMSenateCommitteeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nmSenateCommittees")
public class NMSenateCommitteeController {


    @Autowired
    private NMSenateCommitteeServiceImpl nmSenateCommitteeService;

    @GetMapping
    public String getCommittee(){

        return "inside get committee";
    }


    @PostMapping("/batch")
    public List<NMSenateCommitteeResponse> createMultipleCommittees(@RequestBody NMSenateCommitteesList committeesList){

        return this.nmSenateCommitteeService.createMultipleCommittees(committeesList);
    }

    @PostMapping
    public String createCommittee(){

        return "inside create committee";
    }


    @PutMapping
    public String updateCommittee(){

        return "inside update committee";
    }

    @DeleteMapping
    public String deleteCommittee(){

        return "inside delete committee";
    }
}
