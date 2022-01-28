package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.senateDistrict.SenateDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.senateDistrict.SenateDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.services.Impl.SenateDistrictServiceImpl;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public List<SenateDistrictEssentialResponse> getAllSenateDistrictResponses(){

        return this.senateDistrictService.getAllDistrictEssentials();
    }

    @PostMapping
    public SenateDistrictResponseModel createSenateDistrict(@RequestBody SenateDistrictDetailsRequest senateDistrict){

        SenateDistrictDto senateDistrictDto = this.senateDistrictService.createDistrict(new ModelMapper().map(senateDistrict, SenateDistrictDto.class));

        return new ModelMapper().map(senateDistrictDto, SenateDistrictResponseModel.class);
    }

    @PostMapping(path = {"/bulk"})
    public List<SenateDistrictResponseModel> createSenateDistrictBulk(@RequestBody SenateDistrictDetailsRequestList senateDistrictDetailsRequestList){
        List<SenateDistrictDto> serviceInput = new ArrayList<>();
        List<SenateDistrictResponseModel> returnValue = new ArrayList<>();

        for(SenateDistrictDetailsRequest district: senateDistrictDetailsRequestList.getSenateDistrictDetailsRequests()){

            serviceInput.add(new ModelMapper().map(district, SenateDistrictDto.class));

        }

        List<SenateDistrictDto> createdDistricts =  this.senateDistrictService.createBulkSenateDistrict(serviceInput);

        for(SenateDistrictDto senateDistrictDto: createdDistricts){

            returnValue.add(new ModelMapper().map(senateDistrictDto, SenateDistrictResponseModel.class));
        }

        return returnValue;
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
