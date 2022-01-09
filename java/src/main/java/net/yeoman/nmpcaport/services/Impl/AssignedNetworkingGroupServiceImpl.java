package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.AssignedNetworkingGroupServiceException;
import net.yeoman.nmpcaport.exception.ContactServiceException;
import net.yeoman.nmpcaport.exception.ErrorMessage;
import net.yeoman.nmpcaport.exception.NetworkingGroupServiceException;
import net.yeoman.nmpcaport.io.repositories.AssignedNetworkingGroupRepository;
import net.yeoman.nmpcaport.services.AssignedNetworkingGroupService;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignedNetworkingGroupServiceImpl implements AssignedNetworkingGroupService {

    @Autowired
    private AssignedNetworkingGroupRepository assignedNetworkingGroupRepository;

    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private NetworkingGroupServiceImpl networkingGroupService;


    @Autowired
    private Utils utils;

    @Override
    public AssignedNetworkingGroupEntity createAssignedNetworkingGroupEntity() {

        //create an assigned networking group entity
        AssignedNetworkingGroupEntity assignedNetworkingGroupEntity = new AssignedNetworkingGroupEntity();

        //set assign networking group entity
        assignedNetworkingGroupEntity.setPublicId(utils.generateRandomID());

        //check if the assign network group entity public id is unique
        while(this.assignedNetworkingGroupRepository.existsByAssignmentId(assignedNetworkingGroupEntity.getPublicId())){

            assignedNetworkingGroupEntity.setPublicId(utils.generateRandomID());
        }

        return assignedNetworkingGroupEntity;
    }

    @Override
    public AssignedNetworkingGroupEntity savedAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity) {

        //check assignment entity is not null
        if(assignedNetworkingGroupEntity == null) throw new AssignedNetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //save entity
        AssignedNetworkingGroupEntity savedAssignmentEntity = this.assignedNetworkingGroupRepository.save(assignedNetworkingGroupEntity);

        return savedAssignmentEntity;
    }

    @Override
    public AssignedNetworkingGroupEntity updateAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity, String assignmentId) {
        return null;
    }

    @Override
    public AssignedNetworkingGroupEntity deleteAssignedNetworkingGroup(String assignedId) {
        return null;
    }

    @Override
    public AssignedNetworkingGroupEntity getAssignedNetworkingGroup(String assignedId) {
        return null;
    }

    @Override
    public Boolean existByNetworkingGroup(String assignedId){

        return this.assignedNetworkingGroupRepository.existsByAssignmentId(assignedId);
    }

    @Override
    public AssignedNetworkingGroupEntity createAssignedNetworkingGroupsContact(NetworkingGroupEntity networkingGroupEntity, ContactEntity contactEntity) {

        //check networking group entity
        if(this.networkingGroupService.) throw new NetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //check contact entity
        if(contactEntity == null) throw new ContactServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //create assigned networking group entity
        AssignedNetworkingGroupEntity assignedNetworkingGroupEntity = this.createAssignedNetworkingGroup();

        //set networking group relationship
        assignedNetworkingGroupEntity.setNetworkingGroupEntity(networkingGroupEntity);

        //set contact entity
        assignedNetworkingGroupEntity.setContactEntity(contactEntity);

        return this.savedAssignedNetworkingGroup(assignedNetworkingGroupEntity);

    }

    @Override
    public Boolean entityIsNull(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity) {
        return null;
    }


}
