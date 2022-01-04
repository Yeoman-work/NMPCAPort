package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;

import java.util.List;

public interface NetworkingGroupService {

   public NetworkingGroupDto getNetworkingGroupById(String netGrpId);
   public NetworkingGroupDto createNetworkingGroup(NetworkingGroupDto networkingGroup);
   public NetworkingGroupDto updateNetworkingGroup(NetworkingGroupDto networkingGroup, String networkingGroupId);
   public NetworkingGroupResponseModel deleteNetworkingGroup(String networkingGroupId);
   public List<NetworkingGroupDto> getAllNetworkingGroups();
   public NetworkingGroupEntity networkGroupEntityByNetworkingGroupId(String netGrpId);

}
