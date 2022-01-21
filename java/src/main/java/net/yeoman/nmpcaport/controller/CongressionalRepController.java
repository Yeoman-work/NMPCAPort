package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.congressionalRep.CongressionalRepDetailsRequest;
import net.yeoman.nmpcaport.io.response.CongressionalRepResponse.CongressionalRepEssentials;
import net.yeoman.nmpcaport.io.response.CongressionalRepResponse.CongressionalRepResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.services.Impl.CongressionalRepServiceImpl;
import net.yeoman.nmpcaport.shared.dto.CongressionalRepDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/congressionalReps")
public class CongressionalRepController {


    private final CongressionalRepServiceImpl congressionalRepService;

    public CongressionalRepController(CongressionalRepServiceImpl congressionalRepService){

        this.congressionalRepService = congressionalRepService;
    }

    @GetMapping(path = "/{congressionalRepId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CongressionalRepEssentials getCongressionalRep(@PathVariable("congressionalRepId") String congressionalRepId){

       return this.congressionalRepService.getAllCongressionalRepEssentials(
               this.congressionalRepService.getCongressionalRepEntity(congressionalRepId)
       );
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<CongressionalRepEssentials> getAllCongressionalReps(){


        return this.congressionalRepService.getAllCongressionalRepEssentials(
                this.congressionalRepService.getAllCongressionalRepEntities()
        );
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CongressionalRepResponse createCongressionalRep(@RequestBody CongressionalRepDetailsRequest congressionalRep){

        CongressionalRepDto congressionalRepDto = congressionalRepService.createCongressionalRep(new ModelMapper().map(congressionalRep, CongressionalRepDto.class));

        return new ModelMapper().map(congressionalRepDto, CongressionalRepResponse.class);
    }


    @PutMapping("/{congressionalRepId}")
    public CongressionalRepResponse updateCongressionalRep(@PathVariable("congressionalRepId") String congressionalRepId, @RequestBody CongressionalRepDetailsRequest congressionalRep){

        CongressionalRepDto congressionalRepDto = this.congressionalRepService.updateCongressionalRep(congressionalRepId, new ModelMapper().map(congressionalRep, CongressionalRepDto.class));

        if(congressionalRepDto.getDistrictEntity() != null){
            congressionalRepDto.setDistrictResponse(new ModelMapper().map(congressionalRepDto.getDistrictEntity(), CongressionalDistrictResponse.class));
        }

        return new ModelMapper().map(congressionalRepDto, CongressionalRepResponse.class);
    }

    @DeleteMapping
    public String deleteCongressionalRep(){

        return "delete congressionalRep";
    }
}
