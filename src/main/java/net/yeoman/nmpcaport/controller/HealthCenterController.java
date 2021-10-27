package net.yeoman.nmpcaport.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthCenters")
public class HealthCenterController {

    @GetMapping
    public String getHealthCenter(){

        return "inside get healthCenter";
    }

    @PostMapping
    public String createHealthCenter(){

        return "inside create healthCenter";
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
