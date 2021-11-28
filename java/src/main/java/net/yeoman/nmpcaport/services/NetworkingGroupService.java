package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;

public interface NetworkingGroupService {

   NetworkingGroupDto getNetworkingGroupById(String netGrpId);
   NetworkingGroupDto createNetworkingGroup(NetworkingGroupDto networkingGroup);
   NetworkingGroupDto updateNetworkingGroup(NetworkingGroupDto networkingGroup, String networkingGroupId);
   NetworkingGroupResponseModel deleteNetworkingGroup(String networkingGroupId);
   NetworkingGroupEntity networkGroupEntityByNetworkingGroupId(String netGrpId);

}
