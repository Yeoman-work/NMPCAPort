package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.county.CountyRequestList;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.repositories.CountyRepository;
import net.yeoman.nmpcaport.services.Impl.CountyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/counties")
public class CountyController {

    @Autowired
    private CountyServiceImpl countyService;

    @PostMapping
    public List<CountyResponse> createCountiesOnMass(@RequestBody CountyRequestList countyRequestList){

        return this.countyService.createCounties(countyRequestList.getNames());
    }
}
