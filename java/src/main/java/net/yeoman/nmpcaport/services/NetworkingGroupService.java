package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.request.networkingGroup.NetworkingGroupRequestModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupDashBoard;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupEssentials;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupFormResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;

import java.util.List;

public interface NetworkingGroupService {

   //get networking dto
   public NetworkingGroupDto getNetworkingGroupById(String netGrpId);
   public NetworkingGroupDto createNetworkingGroup(NetworkingGroupDto networkingGroup);
   public NetworkingGroupDto updateNetworkingGroup(NetworkingGroupDto networkingGroup, String networkingGroupId);
   public List<NetworkingGroupDto> getAllNetworkingGroups();
   public NetworkingGroupDto entityToDto(NetworkingGroupEntity networkingGroupEntity);
   public List<NetworkingGroupDto> entityToDto(List<NetworkingGroupEntity> networkingGroupEntities);
   public NetworkingGroupDto entityToDtoBase(NetworkingGroupEntity networkingGroupEntity);
   public List<NetworkingGroupDto> entityToDtoBase(List<NetworkingGroupEntity> networkingGroupEntities);
   public NetworkingGroupDto requestToDto(NetworkingGroupRequestModel networkingGroupRequestModel);
   public List<NetworkingGroupDto> requestToDto(List<NetworkingGroupRequestModel> networkingGroupRequestModelList);

   public List<NetworkingGroupEntity> getNetworkingGroupEntities();

   //networking Group essentials
   public NetworkingGroupDashBoard getNetworkingGroupEssential(NetworkingGroupEntity networkingGroupEntity);
   public List<NetworkingGroupDashBoard> getNetworkingGroupEssential(List<NetworkingGroupEntity> networkingGroupEntities);




   //networking group form response
   public NetworkingGroupFormResponseModel networkingGroupFrom(String netGrpId);
   public NetworkingGroupFormResponseModel dtoToFormResponse(NetworkingGroupDto networkingGroupDto);


   //networking group entity
   public NetworkingGroupEntity networkGroupEntityByNetworkingGroupId(String netGrpId);
   public List<NetworkingGroupEntity> getMultipleNetworkingGroups(List<String> networkingGroupIds);
   public NetworkingGroupEntity dtoToEntity(NetworkingGroupDto networkingGroupDtoList);
   public List<NetworkingGroupEntity> dtoToEntity(List<NetworkingGroupDto> networkingGroupDtoList);
   public NetworkingGroupEntity saveNetworkingGroupEntity(NetworkingGroupEntity networkingGroupEntity);

   //networking group response
   public List<NetworkingGroupResponseModel> dtoToResponse(List<NetworkingGroupDto> networkingGroupDtoList);
   public NetworkingGroupResponseModel dtoToResponse(NetworkingGroupDto networkingGroupDto);
   public NetworkingGroupResponseModel deleteNetworkingGroup(String networkingGroupId);

   //networking group form response
   public NetworkingGroupFormResponseModel getFormResponseModel(String id);


   //is null
   public Boolean entityIsNull(NetworkingGroupEntity networkingGroupEntity);
   public Boolean entityIsNull(List<NetworkingGroupEntity> networkingGroupEntities);
   public Boolean dtoIsNull(NetworkingGroupDto networkingGroupDto);
   public Boolean responseIsNull(NetworkingGroupResponseModel networkingGroupResponseModel);
}
