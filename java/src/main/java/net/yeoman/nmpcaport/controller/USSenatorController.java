package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.UsSenator.USSenatorDetailsRequest;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorEssentials;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorResponse;
import net.yeoman.nmpcaport.services.Impl.USSenatorServiceImpl;
import net.yeoman.nmpcaport.shared.dto.USSenatorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usSenators")
public class USSenatorController {


    private final USSenatorServiceImpl usSenatorService;

    public USSenatorController(USSenatorServiceImpl usSenatorService){

        this.usSenatorService = usSenatorService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<USSenatorResponse> getAllSenators(){


        return this.usSenatorService.getUsSenatorResponse(this.usSenatorService.getUSSenatorEntities());
    }

    @GetMapping(path="/{senatorId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public USSenatorEssentials getSenator(@PathVariable("senatorId") String senatorId){

        return this.usSenatorService.getUSSenatorEssentials(this.usSenatorService.getUSSenatorEntity(senatorId));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public USSenatorResponse createSenator(@RequestBody USSenatorDetailsRequest usSenatorDetailsRequest){


        USSenatorDto usSenatorDto = this.usSenatorService.createSenator(new ModelMapper().map(usSenatorDetailsRequest, USSenatorDto.class));

        return new ModelMapper().map(usSenatorDto, USSenatorResponse.class);
    }


    @PutMapping
    public String updateSenator(){

        return "inside update senator";
    }

    @DeleteMapping
    public String deleteSenator(){

        return "inside delete Senator";
    }


}
