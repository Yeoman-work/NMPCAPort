package net.yeoman.nmpcaport.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.yeoman.nmpcaport.io.request.senateDistrict.SenateDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.senateDistrict.SenateDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialsPagination;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictResponseModel;
import net.yeoman.nmpcaport.services.Impl.SenateDistrictServiceImpl;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;

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

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SenateDistrictEssentialsPagination getAllSenateDistrictResponses(@RequestParam(value = "pageNo", defaultValue="0") int pageNo,
    																		@RequestParam(value = "limit", defaultValue="10") int limit
	){

        return this.senateDistrictService.getSenateDistrictPageInfoEndPoint(pageNo, limit);
    }
    
    @GetMapping(path="/search/{name}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SenateDistrictEssentialsPagination getAllSenateDistrictSearch(@PathVariable("name") String name, 
    																	 @RequestParam(value ="startIndex", defaultValue="0") int startIndex,
																		 @RequestParam(value="endIndex", defaultValue="10") int endIndex 									
	){
    	System.out.println("start index " + startIndex);
    	System.out.println("end Index " + endIndex);
    	return this.senateDistrictService.getSenateDistrictPageInfoSearchEndPoint(name, startIndex, endIndex);
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
