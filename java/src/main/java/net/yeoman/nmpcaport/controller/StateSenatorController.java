package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.stateSenator.StateSenatorDetailsRequest;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorEssentials;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorResponse;
import net.yeoman.nmpcaport.services.Impl.StateSenatorServiceImpl;
import net.yeoman.nmpcaport.shared.dto.StateSenatorDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stateSenators")
public class StateSenatorController {


    private final StateSenatorServiceImpl stateSenatorService;

    public StateSenatorController(StateSenatorServiceImpl stateSenatorService){

        this.stateSenatorService = stateSenatorService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<StateSenatorNestedResponse> getStateSenators(){

        return this.stateSenatorService.stateSenatorDashboardData(this.stateSenatorService.getStateSenatorEntities());
    }

    @GetMapping(path="/essentials",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<StateSenatorEssentials> getStateSenatorEssentials(){

        return this.stateSenatorService.getStateSenatorEssentials(this.stateSenatorService.getStateSenatorEntities());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public StateSenatorResponse createStateSenator(@RequestBody StateSenatorDetailsRequest StateSenatorDetails){

        StateSenatorDto stateSenatorDto = this.stateSenatorService.createSenator(new ModelMapper().map(StateSenatorDetails, StateSenatorDto.class));

        return new ModelMapper().map(stateSenatorDto, StateSenatorResponse.class);
    }

    @PutMapping
    public String updatedStateSenator(){

        return "inside update state senator";
    }


    @DeleteMapping
    public String deleteStateSenator(){

        return "inside delete state senator";
    }
}
