package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.request.networkingGroup.NetworkingGroupRequestModel;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;

import java.util.List;

public interface NetworkingGroupService {

   public NetworkingGroupDto getNetworkingGroupById(String netGrpId);
   public NetworkingGroupDto createNetworkingGroup(NetworkingGroupDto networkingGroup);
   public NetworkingGroupDto updateNetworkingGroup(NetworkingGroupDto networkingGroup, String networkingGroupId);
   public NetworkingGroupResponseModel deleteNetworkingGroup(String networkingGroupId);
   public List<NetworkingGroupDto> getAllNetworkingGroups();
   public NetworkingGroupEntity networkGroupEntityByNetworkingGroupId(String netGrpId);
   public List<NetworkingGroupEntity> getMultipleNetworkingGroups(List<String> networkingGroupIds);
   public List<NetworkingGroupDto> entityArrayToDtoArray(List<NetworkingGroupEntity> networkingGroupEntities);
   public List<NetworkingGroupEntity> DtoArrayToEntityArray(List<NetworkingGroupDto> networkingGroupDtoList);
   public List<NetworkingGroupResponseModel> DtoArrayToResponseArray(List<NetworkingGroupDto> networkingGroupDtoList);
   public List<NetworkingGroupDto> requestArrayToDtoArray(List<NetworkingGroupRequestModel> networkingGroupRequestModelList);
   public NetworkingGroupDto entityToDto(NetworkingGroupEntity networkingGroupEntity);
   public NetworkingGroupEntity DtoToEntity(NetworkingGroupDto networkingGroupDtoList);
   public NetworkingGroupResponseModel DtoToResponse(NetworkingGroupDto networkingGroupDto);
   public NetworkingGroupDto requestToDto(NetworkingGroupRequestModel networkingGroupRequestModel);
   public Boolean entityIsNull(NetworkingGroupEntity networkingGroupEntity);
   public Boolean dtoIsNull(NetworkingGroupDto networkingGroupDto);
   public Boolean responseIsNull(NetworkingGroupResponseModel networkingGroupResponseModel);
}
