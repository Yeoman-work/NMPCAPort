package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterDashBoard;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseBaseModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.services.Impl.HealthCenterServiceImpl;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/healthCenters")
public class HealthCenterController {


    private final HealthCenterServiceImpl healthCenterService;

    HealthCenterController(HealthCenterServiceImpl healthCenterService){

        this.healthCenterService = healthCenterService;
    }

    @GetMapping(path = "/{healthCenterId}")
    public HealthCenterResponseModel getHealthCenter(@PathVariable("healthCenterId") String healthCenterId){

        return this.healthCenterService.getHealthCenterResponse(
                this.healthCenterService.getHealthCenterEntity(healthCenterId)
        );
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
    public List<HealthCenterDashBoard> getHealthCenters(@RequestParam(value ="page", defaultValue = "0") int page,
                                                        @RequestParam(value= "limit", defaultValue = "25") int limit){

        final long startTime = System.currentTimeMillis();



        List<HealthCenterDashBoard> healthCenterDashBoards = this.healthCenterService.healthCenterDashBoard(page, limit);

        final long stopTime = System.currentTimeMillis();
        
        System.out.println("Total time: " + (stopTime - startTime));

        return healthCenterDashBoards;
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void createHealthCenter(@RequestBody HealthCenterDetailsRequestModel healthCenterDetails){

        this.healthCenterService.createHealthCenter(healthCenterDetails);

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
