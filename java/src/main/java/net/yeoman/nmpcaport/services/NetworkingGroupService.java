package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.exception.NetworkingGroupServiceException;
import net.yeoman.nmpcaport.io.request.networkingGroup.NetworkingGroupRequestModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupDashBoard;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupEssentials;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupFormResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;

import java.util.List;

public interface NetworkingGroupService {

   //get networking
   void createNetworkingGroup(NetworkingGroupRequestModel networkingGroupRequestModel);

   void updateNetworkingGroup(NetworkingGroupRequestModel networkingGroupRequestModel, String NetworkingGroupId);

   //networking Group essentials
   NetworkingGroupDashBoard getNetworkingGroupEssential(NetworkingGroupEntity networkingGroupEntity);
   List<NetworkingGroupDashBoard> getNetworkingGroupEssential(List<NetworkingGroupEntity> networkingGroupEntities);

   //generate entity with unique id
   NetworkingGroupEntity generateNetworkingGroupWithId(NetworkingGroupEntity networkingGroupEntity);

   //networking group form response
   NetworkingGroupFormResponseModel networkingGroupFrom(NetworkingGroupEntity networkingGroupEntity);

   //networking group entity
   List<NetworkingGroupEntity> getMultipleNetworkingGroups(List<String> networkingGroupIds);
   NetworkingGroupEntity saveNetworkingGroupEntity(NetworkingGroupEntity networkingGroupEntity);
   List<NetworkingGroupEntity> getNetworkingGroupEntities();
   NetworkingGroupEntity getNetworkingGroupById(String netGrpId);


   //delete networking group
   void deleteNetworkingGroup(String networkingGroupId);

   //networking group form response
   NetworkingGroupFormResponseModel getFormResponseModel(String id);


   //is null
   Boolean entityIsNull(NetworkingGroupEntity networkingGroupEntity);
   Boolean entityIsNull(List<NetworkingGroupEntity> networkingGroupEntities);
   Boolean requestIsNull(NetworkingGroupRequestModel networkingGroupRequestModel);
   Boolean requestIsNull(List<NetworkingGroupRequestModel> networkingGroupRequestModelList);
   Boolean responseIsNull(NetworkingGroupResponseModel networkingGroupResponseModel);

   //check networking groups fields
   Boolean checkNameLength(NetworkingGroupRequestModel networkingGroupRequestModel);
   Boolean checkDescriptionLength(NetworkingGroupRequestModel networkingGroupRequestModel);
   Boolean checkForMemberIds(String memberIds);

   //throw field exception
   NetworkingGroupServiceException throwNameLengthError();
   NetworkingGroupServiceException throwDescriptionLength();
}
