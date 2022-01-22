package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.stateRep.StateRepDetailsRequest;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepEssentials;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepNestedResponse;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepResponse;
import net.yeoman.nmpcaport.services.Impl.StateRepServiceImpl;
import net.yeoman.nmpcaport.shared.dto.StateRepDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stateReps")
public class StateRepController {

    @Autowired
    private StateRepServiceImpl stateRepService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<StateRepNestedResponse> getAllStateReps(){


        return this.stateRepService.getStateRepNestedReps(this.stateRepService.getAllEntities());
    }

    @GetMapping(path="/essentials",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<StateRepEssentials> getStateRepEssentials(){

        return this.stateRepService.getStateRepEssentials(this.stateRepService.getAllEntities());
    }

    @GetMapping("/{stateRepId}")
    public StateRepResponse getStateRep(@PathVariable("stateRepId") String stateRepId){

        return new ModelMapper().map(this.stateRepService.getStateRep(stateRepId), StateRepResponse.class);
    }

    @PostMapping
    public StateRepResponse createStateRep(@RequestBody StateRepDetailsRequest stateRepDetails){
        System.out.println( "Read this " + stateRepDetails.getParty());
        StateRepDto stateRepDto = this.stateRepService.createStateRep(new ModelMapper().map(stateRepDetails, StateRepDto.class));

        return new ModelMapper().map(stateRepDto, StateRepResponse.class);
    }


    @PutMapping
    public String updateStateRep(){

        return "inside update state rep";
    }


    @DeleteMapping
    public String deleteStateRep(){

        return "inside delete state rep";
    }
}
