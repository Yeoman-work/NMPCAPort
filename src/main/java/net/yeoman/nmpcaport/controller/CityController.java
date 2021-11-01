package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.city.CityDetailsRequestModel;
import net.yeoman.nmpcaport.io.request.city.CityRequestList;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.services.Impl.CityServiceImpl;
import net.yeoman.nmpcaport.shared.dto.CityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @PostMapping
    public List<CityResponse> createCity(@RequestBody CityRequestList cityRequestList){

        return this.cityService.createCitiesFromList(cityRequestList.getCityNames());

    }

    @PutMapping("/{cityId}")
    public CityResponse updateCity(@PathVariable("cityId") String cityId, @RequestBody CityDetailsRequestModel cityDetails){

        CityDto cityDto = this.cityService.updateCity(cityId, new ModelMapper().map(cityDetails, CityDto.class));

        return new ModelMapper().map(cityDto, CityResponse.class);
    }
}
