package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.HealthCenter.HealthCenterDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.services.Impl.HealthCenterServiceImpl;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthCenters")
public class HealthCenterController {

    @Autowired
    private HealthCenterServiceImpl healthCenterService;

    @GetMapping(path = "/{healthCenterId}")
    public HealthCenterResponseModel getHealthCenter(@PathVariable("healthCenterId") String healthCenterId){

        return new ModelMapper().map(this.healthCenterService.getHealthCenter(healthCenterId), HealthCenterResponseModel.class);
    }

    @PostMapping
    public HealthCenterResponseModel createHealthCenter(@RequestBody HealthCenterDetailsRequestModel healthCenterDetails){

        return this.healthCenterService.createHealthCenter(new ModelMapper().map(healthCenterDetails, HealthCenterDto.class));
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
