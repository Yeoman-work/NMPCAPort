package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.service.ServiceDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
import net.yeoman.nmpcaport.services.Impl.ServiceServiceImpl;
import net.yeoman.nmpcaport.shared.dto.ServiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceServiceImpl serviceImpl;

    @GetMapping("/{serviceId}")
    public ServiceResponse getService(@PathVariable("serviceId") String serviceId){

        ServiceDto serviceDto = this.serviceImpl.getService(serviceId);

        return new ModelMapper().map(serviceDto, ServiceResponse.class);
    }

    @PostMapping
    public ServiceResponse createService(@RequestBody ServiceDetailsRequestModel serviceDetails){

        ServiceDto serviceDto = this.serviceImpl.createService(new ModelMapper().map(serviceDetails, ServiceDto.class));


        return new ModelMapper().map(serviceDto, ServiceResponse.class);
    }

    @PutMapping
    public String updateService(){

        return "inside updateService";
    }

    @DeleteMapping
    public String deleteService(){

        return "inside delete Service";
    }

}
