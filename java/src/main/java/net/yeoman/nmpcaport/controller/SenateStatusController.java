package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.entities.SenateStatusEntity;
import net.yeoman.nmpcaport.io.request.SenateStatus.SenateStatusRequest;
import net.yeoman.nmpcaport.io.request.SenateStatus.SenateStatusRequestList;
import net.yeoman.nmpcaport.services.Impl.SenateStatusServiceImpl;
import net.yeoman.nmpcaport.shared.dto.SenateStatusDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/senateStatus")
public class SenateStatusController {

    @Autowired
    private SenateStatusServiceImpl senateStatusService;

    @Autowired
    private Utils utils;

    @GetMapping(path = "/{senateStatusId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SenateStatusDto getSenateStatus(@PathVariable("senateStatusId") String senateStatusId){

        return this.senateStatusService.getSenateStatus(senateStatusId);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SenateStatusDto createSenateStatus(@RequestBody SenateStatusRequest senateStatusRequest){

        return this.senateStatusService.createSenateStatus(new ModelMapper().map(senateStatusRequest, SenateStatusDto.class));
    }

    @PostMapping(path = "/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<SenateStatusDto> CreateSenateStatusBulk(SenateStatusRequestList senateStatusRequestList){
        List<SenateStatusDto> serviceInput = new ArrayList<>();
        List<SenateStatusDto> returnValue = new ArrayList<>();

        for(SenateStatusRequest status: senateStatusRequestList.getSenateStatusRequestList()){

            serviceInput.add(new ModelMapper().map(status, SenateStatusDto.class));
        }

        returnValue = this.senateStatusService.createSenateStatus(serviceInput);

        return returnValue;
    }
}
