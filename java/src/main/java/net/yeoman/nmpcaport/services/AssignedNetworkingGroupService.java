package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;

import java.util.List;

public interface AssignedNetworkingGroupService {

    //assignment networking group
    public AssignedNetworkingGroupEntity createAssignedNetworkingGroupEntity();
    public AssignedNetworkingGroupEntity savedAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    public AssignedNetworkingGroupEntity updateAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity, String assignmentId);
    public void deleteAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    public AssignedNetworkingGroupEntity getAssignedNetworkingGroup(String assignedId);
    public List<AssignedNetworkingGroupEntity> assignNetworkingGroupToContact(List<ContactEntity> contactEntities, NetworkingGroupEntity networkingGroupEntity);

    //get networking group entities
    public List<NetworkingGroupEntity>  networkingGroupEntities(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities);

    //get contact entity
    public List<ContactEntity> getContactEntities(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities);

    //exist
    public Boolean existByNetworkingGroup(String assignedId);
    public Boolean entityIsNull(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    public Boolean entityIsNull(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities);



    //saved
    public AssignedNetworkingGroupEntity createAndSaveAssignedNetworkingGroupsContact(NetworkingGroupEntity networkingGroupEntity, ContactEntity contactEntity);



}
