package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.congressionalDistrict.CongressionalDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.congressionalRep.CongressionalRepDetailsRequest;
import net.yeoman.nmpcaport.io.response.CongressionalRepResponse.CongressionalRepResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.services.Impl.CongressionalRepServiceImpl;
import net.yeoman.nmpcaport.shared.dto.CongressionalRepDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/congressionalReps")
public class CongressionalRepController {

    @Autowired
    private CongressionalRepServiceImpl congressionalRepService;

    @GetMapping("/{congressionalRepId}")
    public CongressionalRepResponse getCongressionalRep(@PathVariable("congressionalRepId") String congressionalRepId){

        CongressionalRepDto congressionalRepDto = this.congressionalRepService.getCongressionalRep(congressionalRepId);

        return new ModelMapper().map(congressionalRepDto, CongressionalRepResponse.class);
    }


    @PostMapping
    public CongressionalRepResponse createCongressionalRep(@RequestBody CongressionalRepDetailsRequest congressionalRep){

        CongressionalRepDto congressionalRepDto = congressionalRepService.createCongressionalRep(new ModelMapper().map(congressionalRep, CongressionalRepDto.class));

        return new ModelMapper().map(congressionalRepDto, CongressionalRepResponse.class);
    }

    @PutMapping("/{congressionalRepId}")
    public CongressionalRepResponse updateCongressionalRep(@PathVariable("congressionalRepId") String congressionalRepId, @RequestBody CongressionalRepDetailsRequest congressionalRep){

        CongressionalRepDto congressionalRepDto = this.congressionalRepService.updateCongressionalRep(congressionalRepId, new ModelMapper().map(congressionalRep, CongressionalRepDto.class));

        if(congressionalRepDto.getDistrictEntity() != null){
            congressionalRepDto.setDistrict(new ModelMapper().map(congressionalRepDto.getDistrictEntity(), CongressionalDistrictResponse.class));
        }

        return new ModelMapper().map(congressionalRepDto, CongressionalRepResponse.class);
    }

    @DeleteMapping
    public String deleteCongressionalRep(){

        return "delete congressionalRep";
    }
}
