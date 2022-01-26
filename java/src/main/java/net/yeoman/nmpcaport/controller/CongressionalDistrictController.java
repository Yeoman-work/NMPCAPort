package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.congressionalDistrict.CongressionalDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.congressionalDistrict.CongressionalDistrictDetailsResponseList;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.services.Impl.CongressionalDistrictServiceImpl;
import net.yeoman.nmpcaport.shared.dto.CongressionalDistrictDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/congressionalDistricts")
public class CongressionalDistrictController {


    private final CongressionalDistrictServiceImpl congressionalDistrictService;

    CongressionalDistrictController(CongressionalDistrictServiceImpl congressionalDistrictService){

        this.congressionalDistrictService = congressionalDistrictService;
    }

    @GetMapping("/{congressionalDistrictId}")
    public String getCongressionalDistrict(@PathVariable("congressionalDistrictId") String congressionalDistrictId){

        return "inside get congressional district";
    }

    @GetMapping
    public List<CongressionalDistrictResponse> getAllCongressionalDistrictResponses(){
        List<CongressionalDistrictResponse> returnValue = new ArrayList<>();

        List<CongressionalDistrictDto> congressionalDistrictDtoList = this.congressionalDistrictService.findAllCongressionalDistricts();

        for(CongressionalDistrictDto district: congressionalDistrictDtoList){

            returnValue.add(new ModelMapper().map(district, CongressionalDistrictResponse.class));
        }

        return returnValue;
    }


    @PostMapping
    public CongressionalDistrictResponse createCongressionalDistrict(@RequestBody CongressionalDistrictDetailsRequest districtDetails){

        CongressionalDistrictDto congressionalDistrictDto = this.congressionalDistrictService.createCongressionalDistrict(new ModelMapper().map(districtDetails, CongressionalDistrictDto.class));


        return new ModelMapper().map(congressionalDistrictDto, CongressionalDistrictResponse.class);
    }

    @PostMapping(path="/bulk")
    public List<CongressionalDistrictResponse> bulkCreateCongressionalDistrict(@RequestBody CongressionalDistrictDetailsResponseList congressionalDistrictDetailsResponseList){
        List<CongressionalDistrictDto> serviceInput = new ArrayList<>();
        List<CongressionalDistrictResponse> returnValue = new ArrayList<>();

        for(CongressionalDistrictResponse district: congressionalDistrictDetailsResponseList.getCongressionalDistrictResponseList()){

            serviceInput.add(new ModelMapper().map(district, CongressionalDistrictDto.class));
        }

        List<CongressionalDistrictDto> savedDistricts = this.congressionalDistrictService.bulkCreateCongressionalDistrict(serviceInput);

        for(CongressionalDistrictDto congressionalDistrictDto: savedDistricts){
            returnValue.add(new ModelMapper().map(savedDistricts, CongressionalDistrictResponse.class));
        }

        return returnValue;
    }

    @PutMapping
    public String updateCongressionalDistrict(){

        return "inside update congressional district";
    }

    @DeleteMapping
    public String deleteCongressionalDistrict(){

        return "inside delete congressional map";
    }
}
