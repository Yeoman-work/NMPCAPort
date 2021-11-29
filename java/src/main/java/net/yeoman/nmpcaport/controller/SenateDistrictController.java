package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.senateDistrict.SenateDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.services.Impl.SenateDistrictServiceImpl;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/senateDistricts")
public class SenateDistrictController {

    @Autowired
    private SenateDistrictServiceImpl senateDistrictService;


    @GetMapping("/{districtId}")
    public SenateDistrictResponseModel getSenateDistrict(@PathVariable("districtId") String districtId){

        SenateDistrictDto senateDistrictDto = this.senateDistrictService.getDistrict(districtId);

        return new ModelMapper().map(senateDistrictDto, SenateDistrictResponseModel.class);
    }

    @PostMapping
    public SenateDistrictResponseModel createSenateDistrict(@RequestBody SenateDistrictDetailsRequest senateDistrict){

        SenateDistrictDto senateDistrictDto = this.senateDistrictService.createDistrict(new ModelMapper().map(senateDistrict, SenateDistrictDto.class));

        return new ModelMapper().map(senateDistrictDto, SenateDistrictResponseModel.class);
    }

    @PutMapping
    public String updateSenateDistrict(){

        return "inside update senate district";
    }

    @DeleteMapping
    public String deleteSenateDistrict(){

        return "inside delete senate District";
    }
}