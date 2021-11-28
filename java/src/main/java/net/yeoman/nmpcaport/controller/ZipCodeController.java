package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.zipCode.ZipCodeRequestList;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.services.Impl.ZipCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipCodes")
public class ZipCodeController {

    @Autowired
    private ZipCodeServiceImpl zipCodeService;

    @PostMapping
    private List<ZipCodeResponse> massZipCodeCreation(@RequestBody ZipCodeRequestList zipCodes){

        return this.zipCodeService.createZipCodesFromList(zipCodes.getZipCodes());
    }
}
