package net.yeoman.nmpcaport.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    
    //getMappings start here
    
	 @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	    public List<ZipCodeEssentials> allZipCodes(@RequestParam(value="pageNo", defaultValue="0") int pageNo, 
	    										   @RequestParam(value="limit", defaultValue="10") int limit
	   ){	
	    	return this.zipCodeService.getZipcodesForDropDowns(pageNo, limit);
	
	    }
    
    @GetMapping(path="/search/{name}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<ZipCodeEssentials> zipCodeSearch(@PathVariable("name") String name, @RequestParam(value="startIndex", defaultValue="0") int startIndex,
    											 @RequestParam(value="endIndex", defaultValue="9") int endIndex
   ){
    	System.out.println("startIndex " + startIndex);
    	System.out.println("endIndex " + endIndex);
    		return this.zipCodeService.getZipCodeSearch(name, startIndex, endIndex);
    }
    
    //postMapping start here
    @PostMapping(path="/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
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

   
}
