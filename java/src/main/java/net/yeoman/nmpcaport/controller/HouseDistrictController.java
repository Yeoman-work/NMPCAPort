package net.yeoman.nmpcaport.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictPagination;
import net.yeoman.nmpcaport.services.Impl.HouseDistrictServiceImpl;

@RestController
@RequestMapping("/houseDistricts")
public class HouseDistrictController {

    private final HouseDistrictServiceImpl houseDistrictService;

    public HouseDistrictController(HouseDistrictServiceImpl houseDistrictService){

        this.houseDistrictService = houseDistrictService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public HouseDistrictPagination getAllHouseCommitteeEssentials(@RequestParam(value ="pageNo", defaultValue ="0") int pageNo, @RequestParam(value = "limit", defaultValue = "10") int limit){

        return this.houseDistrictService.getHouseDistrictPageInfoEndPoint(pageNo, limit);
    }

    @GetMapping(path="/search/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public HouseDistrictPagination getSearchHouseCommittee(@PathVariable("name") String name, @RequestParam(value ="startIndex", defaultValue="0") int startIndex, 
    													   @RequestParam(value="endIndex" , defaultValue ="10") int endIndex
	
	){
    	
    	System.out.println("start " + startIndex);
    	System.out.println("end " + endIndex);
    	return this.houseDistrictService.getHouseDistrictPageInfoSearchEndPoint(name, startIndex, endIndex);
    }
    
    
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public void createNewHouseDistrict(HouseDistrictDetailsRequest houseDistrictDetailsRequest){

    }

    @PostMapping(path = "/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void createNewHouseDistrictBulk(@RequestBody HouseDistrictDetailsRequestList houseDistrictDetailsRequestList){

        this.houseDistrictService.createHouseDistrictBulk(houseDistrictDetailsRequestList);

    }


}
