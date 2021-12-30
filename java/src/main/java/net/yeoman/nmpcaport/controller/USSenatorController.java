package net.yeoman.nmpcaport.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import net.yeoman.nmpcaport.io.request.UsSenator.USSenatorDetailsRequest;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorResponse;
import net.yeoman.nmpcaport.services.Impl.USSenatorServiceImpl;
import net.yeoman.nmpcaport.shared.dto.USSenatorDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usSenators")
public class USSenatorController {

    @Autowired
    private USSenatorServiceImpl usSenatorService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<USSenatorResponse> getAllSenators(){
        List<USSenatorResponse> returnValue = new ArrayList<>();

        List<USSenatorDto> USSenatorDtoList = this.usSenatorService.getAllSenators();

        for(USSenatorDto senator: USSenatorDtoList){

            returnValue.add(new ModelMapper().map(senator, USSenatorResponse.class));
        }

        return returnValue;
    }

    @GetMapping(path="/{senatorId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public USSenatorResponse getSenator(@PathVariable("senatorId") String senatorId){

        return new ModelMapper().map(this.usSenatorService.getSenator(senatorId), USSenatorResponse.class);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public USSenatorResponse createSenator(@RequestBody USSenatorDetailsRequest usSenatorDetailsRequest){


        USSenatorDto usSenatorDto = this.usSenatorService.createSenator(new ModelMapper().map(usSenatorDetailsRequest, USSenatorDto.class));

        return new ModelMapper().map(usSenatorDto, USSenatorResponse.class);
    }


    @PutMapping
    public String updateSenator(){

        return "inside update senator";
    }

    @DeleteMapping
    public String deleteSenator(){

        return "inside delete Senator";
    }


}
