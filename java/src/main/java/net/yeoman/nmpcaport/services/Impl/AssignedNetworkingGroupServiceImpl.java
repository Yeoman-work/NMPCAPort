package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.AssignedNetworkingGroupServiceException;
import net.yeoman.nmpcaport.exception.ContactServiceException;
import net.yeoman.nmpcaport.exception.NetworkingGroupServiceException;
import net.yeoman.nmpcaport.io.repositories.AssignedNetworkingGroupRepository;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupEssentials;
import net.yeoman.nmpcaport.services.AssignedNetworkingGroupService;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        assignedNetworkingGroupEntity.setPublicId(this.utils.generateRandomID());

        //check if the assign network group entity public id is unique
        while(this.assignedNetworkingGroupRepository.existsByPublicId(assignedNetworkingGroupEntity.getPublicId())){

            assignedNetworkingGroupEntity.setPublicId(this.utils.generateRandomID());
        }

        return assignedNetworkingGroupEntity;
    }



    @Override
    public List<AssignedNetworkingGroupEntity> assignNetworkingGroupToContact(List<ContactEntity> contactEntities, NetworkingGroupEntity networkingGroupEntity) {

        if(contactEntities == null) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(networkingGroupEntity == null) throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
        List<AssignedNetworkingGroupEntity> returnValue = new ArrayList<>();

        for(ContactEntity contactEntity: contactEntities){

            AssignedNetworkingGroupEntity assignedNetworkingGroupEntity = this.createAssignedNetworkingGroupEntity();

            assignedNetworkingGroupEntity.setNetworkingGroupEntity(networkingGroupEntity);
            assignedNetworkingGroupEntity.setContactEntity(contactEntity);

             returnValue.add(this.savedAssignedNetworkingGroup(assignedNetworkingGroupEntity));
        }

        return returnValue;

    }

    @Override
    public AssignedNetworkingGroupEntity savedAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity) {

        //check assignment entity is not null
        if(assignedNetworkingGroupEntity == null) throw new AssignedNetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //save entity
         return this.assignedNetworkingGroupRepository.save(assignedNetworkingGroupEntity);


    }

    @Override
    public void savedAssignedNetworkingGroupNoReturn(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity) {

        if(this.entityIsNull(assignedNetworkingGroupEntity))
            throw new AssignedNetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        this.assignedNetworkingGroupRepository.save(assignedNetworkingGroupEntity);

        return;

    }

    @Override
    public AssignedNetworkingGroupEntity updateAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity, String assignmentId) {
        return null;
    }

    @Override
    public void deleteAssignedNetworkingGroup(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity){

        this.assignedNetworkingGroupRepository.delete(assignedNetworkingGroupEntity);

        return;
    }

    @Override
    public AssignedNetworkingGroupEntity getAssignedNetworkingGroup(String assignedId) {

        return this.assignedNetworkingGroupRepository.findByPublicId(assignedId);
    }

    @Override
    public NetworkingGroupEssentials getNetworkingGroupEssentials(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity) {

        if(this.entityIsNull(assignedNetworkingGroupEntity))
            throw new AssignedNetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        NetworkingGroupEssentials networkingGroupEssentials = new NetworkingGroupEssentials();

        networkingGroupEssentials.setNetworkingGroupId(
                assignedNetworkingGroupEntity.getNetworkingGroupEntity().getNetworkingGroupId()
        );

        networkingGroupEssentials.setName(
                assignedNetworkingGroupEntity.getNetworkingGroupEntity().getName()
        );


        return networkingGroupEssentials;
    }

    @Override
    public List<NetworkingGroupEssentials> getNetworkingEssentials(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntityList) {

        if(this.entityIsNull(assignedNetworkingGroupEntityList))
            throw new AssignedNetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<NetworkingGroupEssentials> returnValue = new ArrayList<>();

        for(AssignedNetworkingGroupEntity assignment: assignedNetworkingGroupEntityList){

            returnValue.add(this.getNetworkingGroupEssentials(assignment));
        }

        return returnValue;
    }

    @Override
    public void assignNetworkingGroupsToContact(ContactEntity contactEntity, List<NetworkingGroupEntity> networkingGroupEntities) {

        if(this.contactService.entityIsNull(contactEntity))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(this.networkingGroupService.entityIsNull(networkingGroupEntities))
            throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        for(NetworkingGroupEntity group: networkingGroupEntities){

            AssignedNetworkingGroupEntity assignedNetworkingGroupEntity = this.createAssignedNetworkingGroupEntity();

            assignedNetworkingGroupEntity.setNetworkingGroupEntity(group);
            assignedNetworkingGroupEntity.setContactEntity(contactEntity);

            this.savedAssignedNetworkingGroup(assignedNetworkingGroupEntity);
        }

        return;
    }


    @Override
    public List<NetworkingGroupEntity> networkingGroupEntities(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities) {

        if(entityIsNull(assignedNetworkingGroupEntities)) throw new AssignedNetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<NetworkingGroupEntity> returnValue = new ArrayList<>();

        for(AssignedNetworkingGroupEntity assignment: assignedNetworkingGroupEntities){

            returnValue.add(assignment.getNetworkingGroupEntity());

        }

        return returnValue;
    }

    @Override
    public List<ContactEntity> getContactEntities(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities) {

        final long start = System.currentTimeMillis();

        if(entityIsNull(assignedNetworkingGroupEntities))
            throw new AssignedNetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ContactEntity> returnValue = new ArrayList<>();

        for(AssignedNetworkingGroupEntity assignment: assignedNetworkingGroupEntities){

            returnValue.add(assignment.getContactEntity());
        }

        final long stop = System.currentTimeMillis();

        System.out.println("get contact entities total time: " + (stop - start));

        return returnValue;
    }

    @Override
    public List<ContactNestedResponseModel> getNestedContactResponse(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntityList) {

         final long start = System.currentTimeMillis();

         if(this.entityIsNull(assignedNetworkingGroupEntityList))
            throw new AssignedNetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ContactEntity> contactEntities = this.getContactEntities(assignedNetworkingGroupEntityList);

        List<ContactDto> contactDtoList = this.contactService.partialEntityToDto(contactEntities);

        List<ContactNestedResponseModel> nestedResponse = this.contactService.dtoToNestedResponse(contactDtoList);

        final  long stop = System.currentTimeMillis();

        System.out.println("nested contact time: " + ( stop - start));

        return nestedResponse;
    }

    @Override
    public Boolean existByNetworkingGroup(String assignedId){

        return this.assignedNetworkingGroupRepository.existsByPublicId(assignedId);
    }

    @Override
    public void createAndSaveAssignedNetworkingGroupsContact(NetworkingGroupEntity networkingGroupEntity, ContactEntity contactEntity) {

        //check networking group entity
        if(this.networkingGroupService.entityIsNull(networkingGroupEntity))
            throw new NetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //check contact entity
        if(this.contactService.entityIsNull(contactEntity))
            throw new ContactServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //create assigned networking group entity
        AssignedNetworkingGroupEntity assignedNetworkingGroupEntity = this.createAssignedNetworkingGroupEntity();

        //set networking group relationship
        assignedNetworkingGroupEntity.setNetworkingGroupEntity(networkingGroupEntity);

        //set contact entity
        assignedNetworkingGroupEntity.setContactEntity(contactEntity);

        //save assignment
        this.savedAssignedNetworkingGroup(assignedNetworkingGroupEntity);

        return;

    }

    @Override
    public Boolean entityIsNull(AssignedNetworkingGroupEntity assignedNetworkingGroupEntity) {
        return assignedNetworkingGroupEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<AssignedNetworkingGroupEntity> assignedNetworkingGroupEntities) {
        return assignedNetworkingGroupEntities == null;
    }


}
