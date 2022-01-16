package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseBaseModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseFull;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.services.Impl.HealthCenterServiceImpl;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/healthCenters")
public class HealthCenterController {

    @Autowired
    private HealthCenterServiceImpl healthCenterService;

    @GetMapping(path = "/{healthCenterId}")
    public HealthCenterResponseModel getHealthCenter(@PathVariable("healthCenterId") String healthCenterId){

        return new ModelMapper().map(this.healthCenterService.getHealthCenter(healthCenterId), HealthCenterResponseModel.class);
        //return this.healthCenterService.getHealthCenterEntity(healthCenterId);
    }

    @GetMapping(path="/base")
    public List<HealthCenterResponseBaseModel> getAllHealthCenter(){
        ModelMapper modelMapper = new ModelMapper();
        List<HealthCenterResponseBaseModel> returnValue = new ArrayList<>();

        List<HealthCenterDto> healthCenterDtoList = this.healthCenterService.getAllHealthCenters();

        for(HealthCenterDto healthCenter: healthCenterDtoList){

            returnValue.add(modelMapper.map(healthCenter, HealthCenterResponseBaseModel.class));
        }

        return returnValue;

    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<HealthCenterResponseFull> getHealthCenters(@RequestParam(value ="page", defaultValue = "0") int page,
                                                           @RequestParam(value= "limit", defaultValue = "25") int limit){


        return this.healthCenterService.dtoToResponseFull(this.healthCenterService.getHealthCenters(page, limit));
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void createHealthCenter(@RequestBody HealthCenterDetailsRequestModel healthCenterDetails){

        this.healthCenterService.createHealthCenter(healthCenterDetails);

        return;
    }

    @PutMapping
    public String updateHealthCenter(){

        return "inside update healthCenter";
    }

    @DeleteMapping
    public String deleteHealthCenter(){

        return " inside delete HealthCenter";
    }
}
