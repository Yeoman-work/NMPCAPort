package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.entities.InterimCommitteeEntity;
import net.yeoman.nmpcaport.io.request.interimCommittee.InterimCommitteeRequest;
import net.yeoman.nmpcaport.io.response.interimCommittee.InterimCommitteeResponse;
import net.yeoman.nmpcaport.services.Impl.InterimCommitteeServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/interimCommittees")
public class InterimCommitteeController {

    private final InterimCommitteeServiceImpl interimCommitteeService;

    public InterimCommitteeController(InterimCommitteeServiceImpl interimCommitteeService){

        this.interimCommitteeService = interimCommitteeService;
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<InterimCommitteeResponse> interimCommitteeDashboardView(){

        return this.interimCommitteeService.getInterimCommitteeResponse(this.interimCommitteeService.getAllInterimCommittees());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void createNewInterimGroup(@RequestBody InterimCommitteeRequest interimCommitteeRequest){

        this.interimCommitteeService.storeNewInterimCommittee(interimCommitteeRequest);
    }

}
