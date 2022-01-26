package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.response.contact.ContactEssentials;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupEssentials;

import java.util.List;

public interface AssignedNetworkingGroupService {

    //assignment networking group
    AssignedNetworkingGroupEntity createAssignedNetworkingGroupEntity();
    void savedAssignedNetworkingGroupNoReturn(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    AssignedNetworkingGroupEntity savedAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    AssignedNetworkingGroupEntity updateAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity, String assignmentId);
    AssignedNetworkingGroupEntity getAssignedNetworkingGroup(String assignedId);



    //get networking group essentials
    NetworkingGroupEssentials getNetworkingGroupEssentials(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    List<NetworkingGroupEssentials> getNetworkingEssentials(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntityList);

    //get contact essentials
    ContactEssentials getContactEssentials(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    List<ContactEssentials> getContactEssentials(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities);


    //create Relationship with contacts
    void assignNetworkingGroupsToContact(ContactEntity contactEntity, List<NetworkingGroupEntity> networkingGroupEntities);
    List<AssignedNetworkingGroupEntity> assignNetworkingGroupToContact(List<ContactEntity> contactEntities, NetworkingGroupEntity networkingGroupEntity);

    //remove relationship with contacts
    void removedNetworkingGroupAssignment(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);


    //get networking group entities
    List<NetworkingGroupEntity>  networkingGroupEntities(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities);

    //get contact entity
    List<ContactEntity> getContactEntities(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities);


    //exist
    Boolean existByNetworkingGroup(String assignedId);
    Boolean entityIsNull(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);
    Boolean entityIsNull(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities);


    //delete assignment entity
    void deleteAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity);

    //saved
    void createAndSaveAssignedNetworkingGroupsContact(NetworkingGroupEntity networkingGroupEntity, ContactEntity contactEntity);



}
