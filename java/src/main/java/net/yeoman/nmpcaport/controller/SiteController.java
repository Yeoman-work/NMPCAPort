package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestListModel;
import net.yeoman.nmpcaport.io.request.site.SiteDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.site.SiteDetailsNestedResponse;
import net.yeoman.nmpcaport.services.Impl.HealthCenterServiceImpl;
import net.yeoman.nmpcaport.services.Impl.SiteServiceImpl;
import net.yeoman.nmpcaport.shared.dto.SiteDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sites")
public class SiteController {


    private final SiteServiceImpl siteService;

    private final HealthCenterServiceImpl healthCenterService;

    public SiteController(SiteServiceImpl siteService,
                          HealthCenterServiceImpl healthCenterService
    ){
        this.siteService = siteService;
        this.healthCenterService = healthCenterService;
    }


    @GetMapping
    public String getSite(){

        return "inside get site";
    }


    @PostMapping("/bulk/{healthCenterId}")
    public void createSitesWithOutId(@PathVariable("healthCenterId") String healthCenterId, @RequestBody SiteDetailsRequestListModel siteDetailsRequestListModel){



        return;
    }

    @PutMapping
    public String updateSite(){

        return "inside update";
    }

    @DeleteMapping
    public String deleteMapping(){

        return "inside delete";
    }
}
