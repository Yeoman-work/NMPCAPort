package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.services.Impl.HouseDistrictServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houseDistricts")
public class HouseDistrictController {

    private final HouseDistrictServiceImpl houseDistrictService;

    public HouseDistrictController(HouseDistrictServiceImpl houseDistrictService){

        this.houseDistrictService = houseDistrictService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<HouseDistrictEssentialResponse> getAllHouseCommitteeEssentials(){

        return this.houseDistrictService.entityToEssentials(this.houseDistrictService.getAllHouseDistrictEntities());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public void createNewHouseDistrict(HouseDistrictDetailsRequest houseDistrictDetailsRequest){

    }

    @PostMapping(path = "/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void createNewHouseDistrictBulk(@RequestBody HouseDistrictDetailsRequestList houseDistrictDetailsRequestList){

        this.houseDistrictService.createHouseDistrictBulk(houseDistrictDetailsRequestList);

    }


}
