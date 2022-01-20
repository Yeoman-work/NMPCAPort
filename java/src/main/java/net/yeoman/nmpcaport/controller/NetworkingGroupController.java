package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.networkingGroup.NetworkingGroupRequestModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupDashBoard;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupFormResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.services.Impl.NetworkingGroupServiceImpl;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/networkingGroups")
public class NetworkingGroupController {

    @Autowired
    private NetworkingGroupServiceImpl networkingGroupService;



    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<NetworkingGroupDashBoard> getAllNetworkingGroups(){

        final long start = System.currentTimeMillis();

        List<NetworkingGroupDashBoard> networkingGroupDashBoards =
                this.networkingGroupService.getNetworkingGroupEssential(
                        this.networkingGroupService.getNetworkingGroupEntities()
                );
        final long stop = System.currentTimeMillis();

        System.out.println("total time: " + (stop - start));

        return networkingGroupDashBoards;
    }


    @GetMapping(path = "/{networkingGrpId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NetworkingGroupFormResponseModel getNetworkingGroup(@PathVariable("networkingGrpId") String networkingGrpId){


        return this.networkingGroupService.getFormResponseModel(networkingGrpId);
    }



    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NetworkingGroupResponseModel createNetworkingGroup(@RequestBody NetworkingGroupRequestModel networkingGroupDetails){


        NetworkingGroupDto storedNetworkingGroup = this.networkingGroupService.createNetworkingGroup(this.networkingGroupService.requestToDto(networkingGroupDetails));

        return this.networkingGroupService.dtoToResponse(storedNetworkingGroup);
    }



    @PutMapping(path ="/{networkingGroupId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NetworkingGroupResponseModel updateNetworkingGroup(@PathVariable("networkingGroupId") String networkingGroupId,
                                                              @RequestBody NetworkingGroupRequestModel networkingGroupDetails){

        NetworkingGroupDto networkingGroupDto = new ModelMapper().map(networkingGroupDetails, NetworkingGroupDto.class);

        NetworkingGroupDto storedNetworkingGrp = this.networkingGroupService.updateNetworkingGroup(networkingGroupDto, networkingGroupId);

        return new ModelMapper().map(storedNetworkingGrp, NetworkingGroupResponseModel.class);
    }

    @DeleteMapping(path = "/{networkingGroupId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public NetworkingGroupResponseModel deleteNetworkingGroup(@PathVariable("networkingGroupId") String networkingGroupId){

        return this.networkingGroupService.deleteNetworkingGroup(networkingGroupId);
    }
}
