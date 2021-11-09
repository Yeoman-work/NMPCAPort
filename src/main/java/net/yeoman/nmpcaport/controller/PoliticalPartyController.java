package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.politicalParty.PoliticalPartyDetailsRequest;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.services.Impl.PoliticalPartyServiceImpl;
import net.yeoman.nmpcaport.shared.dto.PoliticalPartyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/politicalParties")
public class PoliticalPartyController {

    @Autowired
    private PoliticalPartyServiceImpl politicalPartyService;


    @GetMapping("/{politicalPartyId}")
    private PoliticalPartyResponse getPoliticalParty(@PathVariable("politicalPartyId") String politicalPartyId){

        return new ModelMapper().map(this.politicalPartyService.getPoliticalParty(politicalPartyId), PoliticalPartyResponse.class);
    }

    @PostMapping
    private PoliticalPartyResponse createPoliticalParty(@RequestBody PoliticalPartyDetailsRequest partyDetails){

        PoliticalPartyDto partyDto = this.politicalPartyService.createPoliticalParty(new ModelMapper().map(partyDetails, PoliticalPartyDto.class));

        return new ModelMapper().map(partyDto, PoliticalPartyResponse.class);
    }

    @PutMapping
    private String updatePoliticalParty(){

        return "update political party";
    }

    @DeleteMapping
    private String deletePoliticalParty(){

        return "delete political party";
    }
}
