package net.yeoman.nmpcaport.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.yeoman.nmpcaport.io.request.city.CityDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.city.CityEssentials;
import net.yeoman.nmpcaport.io.response.city.CityEssentialsPagination;
import net.yeoman.nmpcaport.services.Impl.CityServiceImpl;

@RestController
@RequestMapping("/cities")
public class CityController {

    
    private final CityServiceImpl cityService;

    CityController(CityServiceImpl cityService){
    	
    	this.cityService = cityService;
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CityEssentialsPagination getAllCities(@RequestParam(value="pageNo", defaultValue = "0") int pageNo, 
    										     @RequestParam(value="limit", defaultValue="10") int limit
	){

        return this.cityService.getAllCityEssentials(pageNo, limit);
    }
    
    @GetMapping(path="/search/{name}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CityEssentialsPagination citySearch(@PathVariable("name") String name, @RequestParam(value="startIndex", defaultValue="0") int startIndex,
    										   @RequestParam(value="endIndex", defaultValue="9") int endIndex){
    	System.out.println("start index " + startIndex);
    	System.out.println("endIndex " + endIndex);
    	return this.cityService.getAllCityEssentialsSearch(name, startIndex, endIndex);
    }

    @PostMapping(path="bulk", 
    			 consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    			 produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE}
    		)
    public List<CityEssentials> createCity(@RequestBody List<String> cityList){

        return this.cityService.createCitiesProcess(cityList);

    }

    @PutMapping("/{cityId}")
    public CityEssentials updateCity(@PathVariable("cityId") String cityId, @RequestBody CityDetailsRequestModel cityDetails){

        

        return cityService.updateCityProcess(cityId, cityDetails) ;
    }
}
