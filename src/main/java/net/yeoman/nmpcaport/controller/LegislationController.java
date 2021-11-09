package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.legislation.LegislationDetailsRequest;
import net.yeoman.nmpcaport.io.response.legislation.LegislationResponse;
import net.yeoman.nmpcaport.services.Impl.LegislationServiceImpl;
import net.yeoman.nmpcaport.shared.dto.LegislationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/legislation")
public class LegislationController  {

    @Autowired
    private LegislationServiceImpl legislationService;


    @GetMapping
    public String getLegislation(){

        return "inside legislation";
    }

    @PostMapping
    public LegislationResponse createLegislation(@RequestBody LegislationDetailsRequest legislationDetails){


        return new ModelMapper().map(this.legislationService.createLegislation(new ModelMapper().map(legislationDetails, LegislationDto.class)), LegislationResponse.class);
    }

    @PutMapping
    public String updateLegislation(){

        return "inside update legislation";
    }

    @DeleteMapping
    public String deleteLegislation(){

        return "inside delete Mapping";
    }


}
