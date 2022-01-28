package net.yeoman.nmpcaport.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.yeoman.nmpcaport.io.request.zipCode.ZipCodeDetailsRequestModel;
import net.yeoman.nmpcaport.io.request.zipCode.ZipCodeRequestList;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeEssentials;
import net.yeoman.nmpcaport.services.Impl.ZipCodeServiceImpl;

@RestController
@RequestMapping("/zipCodes")
public class ZipCodeController {

    
    private final ZipCodeServiceImpl zipCodeService;
    
    ZipCodeController(ZipCodeServiceImpl zipCodeService){
    	
    	this.zipCodeService = zipCodeService;
    }
    

    @PostMapping(path="/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XHTML_XML_VALUE},
    			 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    		)
    public List<ZipCodeEssentials> massZipCodeCreation(@RequestBody ZipCodeRequestList zipCodeRequestList){

        return this.zipCodeService.createZipCodesFromEndPoint(zipCodeRequestList); 
    }
    
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XHTML_XML_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ZipCodeEssentials zipCodeCreation(@RequestBody ZipCodeDetailsRequestModel zipCodeRequestModel) {
    
    	return this.zipCodeService.createZipCodeEntityFromEndPoint(zipCodeRequestModel);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ZipCodeEssentials> allZipCodes(){
    		
    	return this.zipCodeService.getZipcodesForDropDowns();

    }
}
