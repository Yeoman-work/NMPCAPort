package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.nmHouseDistrict.NMHouseDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.nmHouseDistrict.NMHouseDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;
import net.yeoman.nmpcaport.services.Impl.NMHouseDistrictServiceImpl;
import net.yeoman.nmpcaport.shared.dto.NMHouseDistrictDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nmHouseDistricts")
public class NMHouseDistrictController {

    @Autowired
    private NMHouseDistrictServiceImpl nmHouseDistrictService;

    @GetMapping("/{houseDistrictId}")
    public NMHouseDistrictResponse getNMHouseDistrict(@PathVariable("houseDistrictId") String houseDistrictId){

        NMHouseDistrictDto nmHouseDistrictDto = this.nmHouseDistrictService.getNMHouseDistrict(houseDistrictId);

        return new ModelMapper().map(nmHouseDistrictDto, NMHouseDistrictResponse.class);
    }

    @GetMapping
    public List<NMHouseDistrictResponse> getAllHouseDistricts(){
        List<NMHouseDistrictResponse> returnValue = new ArrayList<>();

        List<NMHouseDistrictDto> nmHouseDistrictDtoList = this.nmHouseDistrictService.getAllNMHouseDistrictResponses();

        for(NMHouseDistrictDto district: nmHouseDistrictDtoList){

            returnValue.add(new ModelMapper().map(district, NMHouseDistrictResponse.class));
        }

        return returnValue;
    }

    @PostMapping
    public NMHouseDistrictResponse createNMHouseDistrict(@RequestBody NMHouseDistrictDetailsRequest houseDistrictDetails){

        NMHouseDistrictDto houseDistrictDto = this.nmHouseDistrictService.createNMHouseDistrict(new ModelMapper().map(houseDistrictDetails, NMHouseDistrictDto.class));

        return new ModelMapper().map(houseDistrictDto, NMHouseDistrictResponse.class);
    }


    @PostMapping("/bulk")
    public List<NMHouseDistrictResponse> bulkCreateNMHouseDistrict(@RequestBody NMHouseDistrictDetailsRequestList houseDistrictDetailsRequestList){

        List<NMHouseDistrictDto> serviceInput = new ArrayList<>();
        List<NMHouseDistrictResponse> returnValue = new ArrayList<>();

        for (NMHouseDistrictDetailsRequest district: houseDistrictDetailsRequestList.getNmHouseDistrictDetailsRequestList()){

            serviceInput.add(new ModelMapper().map(district, NMHouseDistrictDto.class));

        }

        List<NMHouseDistrictDto> savedDistricts = this.nmHouseDistrictService.bulkCreateHouseDistrict(serviceInput);

        for(NMHouseDistrictDto nmHouseDistrictDto: savedDistricts){

            returnValue.add(new ModelMapper().map(nmHouseDistrictDto, NMHouseDistrictResponse.class));
        }

        return returnValue;
    }

    @PutMapping
    public String updateNMHouseDistrict(){

        return "inside update NM house district";
    }

    @DeleteMapping
    public String deleteNMHouseDistrict(){

        return "inside delete NM house district";
    }
}
