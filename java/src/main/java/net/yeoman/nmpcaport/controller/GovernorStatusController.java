package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.governorStatus.GovernorStatusRequest;
import net.yeoman.nmpcaport.io.response.GovernorStatus.GovernorStatusResponse;
import net.yeoman.nmpcaport.services.Impl.GovernorStatusServiceImpl;
import net.yeoman.nmpcaport.shared.dto.GovernorStatusDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/governorStatus")
public class GovernorStatusController {

    @Autowired
    private GovernorStatusServiceImpl governorStatusService;


    @GetMapping("/{publicId}")
    public GovernorStatusResponse getOneStatus(@PathVariable("publicId") String publicId){

        return new ModelMapper().map(this.governorStatusService.findStatusById(publicId), GovernorStatusResponse.class);
    }

    @PostMapping()
    public GovernorStatusResponse createOneStatus(GovernorStatusRequest governorStatusRequest){

        return new ModelMapper().map(new ModelMapper().map(governorStatusRequest, GovernorStatusDto.class), GovernorStatusResponse.class);
    }

    @PutMapping("/{publicId}")
    public GovernorStatusResponse updateStatus(@PathVariable("publicId") String publicId, GovernorStatusRequest governorStatusRequest){

        return new ModelMapper().map(this.governorStatusService.updateStatus(new ModelMapper().map(governorStatusRequest, GovernorStatusDto.class), publicId), GovernorStatusResponse.class);
    }

    @DeleteMapping("/{publicId}")
    public GovernorStatusResponse deleteStatus(@PathVariable("publicId") String publicId){

        return new ModelMapper().map(this.governorStatusService.deleteGovernorStatus(publicId), GovernorStatusResponse.class);
    }

    @PostMapping("/bulk")
    public List<GovernorStatusResponse> bulkCreate(List<GovernorStatusRequest> governorStatusRequests){

        return this.governorStatusService.createStatusBulk(governorStatusRequests);
    }
}
