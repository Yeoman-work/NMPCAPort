package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.nmHouseDistrict.NMHouseDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.response.nmHouseDistrict.NMHouseDistrictResponse;
import net.yeoman.nmpcaport.services.Impl.NMHouseDistrictServiceImpl;
import net.yeoman.nmpcaport.shared.dto.NMHouseDistrictDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public NMHouseDistrictResponse createNMHouseDistrict(@RequestBody NMHouseDistrictDetailsRequest houseDistrictDetails){

        NMHouseDistrictDto houseDistrictDto = this.nmHouseDistrictService.createNMHouseDistrict(new ModelMapper().map(houseDistrictDetails, NMHouseDistrictDto.class));

        return new ModelMapper().map(houseDistrictDto, NMHouseDistrictResponse.class);
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
