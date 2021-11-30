package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.request.zipCode.ZipCodeRequestList;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.services.Impl.ZipCodeServiceImpl;
import net.yeoman.nmpcaport.shared.dto.ZipCodeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/zipCodes")
public class ZipCodeController {

    @Autowired
    private ZipCodeServiceImpl zipCodeService;

    @PostMapping
    public List<ZipCodeResponse> massZipCodeCreation(@RequestBody ZipCodeRequestList zipCodes){

        return this.zipCodeService.createZipCodesFromList(zipCodes.getZipCodes());
    }

    @GetMapping
    public List<ZipCodeResponse> allZipCodes(){
        List<ZipCodeResponse> returnValue = new ArrayList<>();

        List<ZipCodeDto> zipCodes = this.zipCodeService.findALl();

        for(ZipCodeDto zipCode: zipCodes){

            returnValue.add(new ModelMapper().map(zipCode, ZipCodeResponse.class));
        }

        return returnValue;
    }
}
