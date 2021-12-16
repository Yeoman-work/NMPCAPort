package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.legislation.LegislationDetailsRequest;
import net.yeoman.nmpcaport.io.response.legislation.LegislationResponse;
import net.yeoman.nmpcaport.services.Impl.LegislationServiceImpl;
import net.yeoman.nmpcaport.shared.dto.LegislationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/legislation")
public class LegislationController  {

    @Autowired
    private LegislationServiceImpl legislationService;


    @GetMapping
    public List<LegislationResponse> getLegislation(){
        List<LegislationResponse> returnValue = new ArrayList<>();

        List<LegislationDto> legislationDtoList = this.legislationService.getLegislationList();

        for(LegislationDto legislationDto: legislationDtoList){

            returnValue.add(new ModelMapper().map(legislationDto, LegislationResponse.class));
        }

        return returnValue;
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
