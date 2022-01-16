package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.service.ServiceDetailsRequestModel;
import net.yeoman.nmpcaport.io.request.service.ServiceRequestListModel;
import net.yeoman.nmpcaport.io.response.service.ServiceResponse;
import net.yeoman.nmpcaport.services.Impl.ServiceServiceImpl;
import net.yeoman.nmpcaport.shared.dto.ServiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceServiceImpl serviceImpl;

    @GetMapping("/{serviceId}")
    public ServiceResponse getService(@PathVariable("serviceId") String serviceId){

        return this.serviceImpl.dtoToResponse(
                this.serviceImpl.entityToDto(
                        this.serviceImpl.getServiceEntity(serviceId)
                )
        );
    }

    @GetMapping
    public List<ServiceResponse> getAllService(){
        List<ServiceResponse> returnValue = new ArrayList<>();
        List<ServiceDto> serviceDtoList = this.serviceImpl.allServices();

        for(ServiceDto serviceDto: serviceDtoList){

            returnValue.add(new ModelMapper().map(serviceDto, ServiceResponse.class));
        }
        return returnValue;
    }

    @PostMapping
    public ServiceResponse createService(@RequestBody ServiceDetailsRequestModel serviceDetails){

        ServiceDto serviceDto = this.serviceImpl.createService(new ModelMapper().map(serviceDetails, ServiceDto.class));


        return new ModelMapper().map(serviceDto, ServiceResponse.class);
    }

    @PostMapping(value = "/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                             produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ServiceResponse> createServicesBulk(@RequestBody ServiceRequestListModel serviceRequestListModel){
            List<ServiceDto> serviceDtoConversionList = new ArrayList<>();
            List<ServiceResponse> returnValue = new ArrayList<>();


             serviceDtoConversionList = this.serviceImpl.createServiceBulk(serviceRequestListModel);

             for(ServiceDto serviceDto: serviceDtoConversionList){

                 returnValue.add(new ModelMapper().map(serviceDto, ServiceResponse.class));
             }

             return returnValue;
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
