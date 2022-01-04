package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.NMHouseStatus.NMHouseStatusRequest;
import net.yeoman.nmpcaport.io.request.NMHouseStatus.NMHouseStatusRequestList;
import net.yeoman.nmpcaport.services.Impl.NMHouseStatusServiceImpl;
import net.yeoman.nmpcaport.shared.dto.NMHouseStatusDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nmHouseStatus")
public class NMHouseStatusController {

    @Autowired
    private NMHouseStatusServiceImpl nmHouseStatusService;


    @GetMapping(path = "/{nmHouseStatusId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NMHouseStatusDto getNMHouseStatus(@PathVariable("nmHouseStatusId") String nmHouseStatusId){

        return this.nmHouseStatusService.getNMHouseStatusDto(nmHouseStatusId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NMHouseStatusDto createNMHouseStatus(@RequestBody NMHouseStatusRequest nmHouseStatusRequest){


        return nmHouseStatusService.createNMHouseStatus(new ModelMapper().map(nmHouseStatusRequest, NMHouseStatusDto.class));
    }

    @PostMapping(path ="/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<NMHouseStatusDto> createNMHouseStatusBulk(@RequestBody NMHouseStatusRequestList nmHouseStatusRequestList){

        List<NMHouseStatusDto> serviceInput = new ArrayList<>();
        List<NMHouseStatusDto> returnValue = new ArrayList<>();

        for(NMHouseStatusRequest status: nmHouseStatusRequestList.getNmHouseStatusRequestList()){

            serviceInput.add(new ModelMapper().map(status, NMHouseStatusDto.class));
        }

        returnValue = this.nmHouseStatusService.createdNMHouseStatusBulk(serviceInput);

        return returnValue;

    }
}
