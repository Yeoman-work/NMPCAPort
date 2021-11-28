package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.response.USSenator.USSenatorResponse;
import net.yeoman.nmpcaport.services.Impl.USSenatorServiceImpl;
import net.yeoman.nmpcaport.shared.dto.USSenatorDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usSenators")
public class USSenatorController {

    @Autowired
    private USSenatorServiceImpl usSenatorService;

    @Autowired
    private Utils utils;

    @GetMapping("/senatorId")
    public USSenatorResponse getSenator(@PathVariable("senatorId") String senatorId){

        return new ModelMapper().map(this.usSenatorService.getSenator(senatorId), USSenatorResponse.class);
    }

    @PostMapping
    public USSenatorResponse createSenator(@RequestBody USSenatorResponse usSenatorResponse){

        USSenatorDto usSenatorDto = this.usSenatorService.createSenator(new ModelMapper().map(usSenatorResponse, USSenatorDto.class));

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
