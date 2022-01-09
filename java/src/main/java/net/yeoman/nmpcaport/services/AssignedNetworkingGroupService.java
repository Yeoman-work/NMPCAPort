package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
public interface AssignedNetworkingGroupService {

    public AssignedNetworkingGroupEntity createAssignedNetworkingGroupEntity();
    public AssignedNetworkingGroupEntity savedAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    public AssignedNetworkingGroupEntity updateAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity, String assignmentId);
    public AssignedNetworkingGroupEntity deleteAssignedNetworkingGroup(String assignedId);
    public AssignedNetworkingGroupEntity getAssignedNetworkingGroup(String assignedId);
    public Boolean existByNetworkingGroup(String assignedId);
    public AssignedNetworkingGroupEntity createAssignedNetworkingGroupsContact(NetworkingGroupEntity networkingGroupEntity, ContactEntity contactEntity);
    public Boolean entityIsNull(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);


}
