package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.NetworkingGroupRequestModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.services.Impl.NetworkingGroupServiceImpl;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/networkingGroups")
public class NetworkingGroupController {

    @Autowired
    private NetworkingGroupServiceImpl networkingGroupService;



    @GetMapping(path = "/{networkingGrpId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NetworkingGroupResponseModel getNetworkingGroup(@PathVariable("networkingGrpId") String networkingGrpId){

        NetworkingGroupDto networkingGroupDto = this.networkingGroupService.getNetworkingGroupById(networkingGrpId);

        return new ModelMapper().map(networkingGroupDto, NetworkingGroupResponseModel.class);
    }



    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NetworkingGroupResponseModel createNetworkingGroup(@RequestBody NetworkingGroupRequestModel networkingGroupDetails){

        NetworkingGroupDto networkingGroupDto = new ModelMapper().map(networkingGroupDetails, NetworkingGroupDto.class);

        NetworkingGroupDto storedNetworkingGroup = this.networkingGroupService.createNetworkingGroup(networkingGroupDto);

        return new ModelMapper().map(storedNetworkingGroup, NetworkingGroupResponseModel.class);
    }



    @PutMapping(path ="/{networkingGroupId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NetworkingGroupResponseModel updateNetworkingGroup(@PathVariable("networkingGroupId") String networkingGroupId,
                                                              @RequestBody NetworkingGroupRequestModel networkingGroupDetails){

        NetworkingGroupDto networkingGroupDto = new ModelMapper().map(networkingGroupDetails, NetworkingGroupDto.class);

        NetworkingGroupDto storedNetworkingGrp = this.networkingGroupService.updateNetworkingGroup(networkingGroupDto, networkingGroupId);

        return new ModelMapper().map(storedNetworkingGrp, NetworkingGroupResponseModel.class);
    }

    @DeleteMapping
    public String deleteNetworkingGroup(){

        return "inside delete networking group";
    }
}
