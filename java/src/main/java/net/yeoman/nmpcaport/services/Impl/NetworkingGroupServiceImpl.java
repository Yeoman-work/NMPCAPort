package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.errormessages.NetworkingGroupErrorMessages;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupDashBoard;
import net.yeoman.nmpcaport.services.NetworkingGroupService;
import net.yeoman.nmpcaport.exception.NetworkingGroupServiceException;
import net.yeoman.nmpcaport.io.request.networkingGroup.NetworkingGroupRequestModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupFormResponseModel;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.io.repositories.NetworkingGroupRepository;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkingGroupServiceImpl implements NetworkingGroupService {


    private final NetworkingGroupRepository networkingGroupRepository;
    private final AssignedNetworkingGroupServiceImpl assignedNetworkingGroupService;
    private final ContactServiceImpl contactService;
    private final Utils utils;

    public NetworkingGroupServiceImpl(NetworkingGroupRepository networkingGroupRepository,
                                      AssignedNetworkingGroupServiceImpl assignedNetworkingGroupService,
                                      @Lazy ContactServiceImpl contactService,
                                      Utils utils
    ){
        this.networkingGroupRepository = networkingGroupRepository;
        this.assignedNetworkingGroupService = assignedNetworkingGroupService;
        this.contactService = contactService;
        this.utils = utils;
    }







    @Override
    public NetworkingGroupEntity getNetworkingGroupById(String netGrpId) {

        return this.networkingGroupRepository.findByNetworkingGroupId(netGrpId);
    }

    @Override
    public void createNetworkingGroup(NetworkingGroupRequestModel networkingGroupRequestModel) {

        //check the request is null
        if(this.requestIsNull(networkingGroupRequestModel))
            throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());




        //get networking group
        NetworkingGroupEntity networkingGroupEntity = this.generateNetworkingGroupWithId(new NetworkingGroupEntity());

        //check entity is null
        if(this.entityIsNull(networkingGroupEntity))
            throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        //set name and description
        networkingGroupEntity.setName(networkingGroupRequestModel.getName());
        networkingGroupEntity.setDescription(networkingGroupRequestModel.getDescription());

        NetworkingGroupEntity savedNetworkingGroup = this.saveNetworkingGroupEntity(networkingGroupEntity);

        if(networkingGroupRequestModel.getMemberIds() != null){

            List<ContactEntity> contactEntities = this.contactService.getMultipleContacts(
                    networkingGroupRequestModel.getMemberIds()
            );

            this.assignedNetworkingGroupService.assignNetworkingGroupToContact(contactEntities, savedNetworkingGroup);
        }

    }

    @Override
    public void updateNetworkingGroup(NetworkingGroupRequestModel networkingGroupRequestModel, String networkingGroupId)
    {
        if(this.requestIsNull(networkingGroupRequestModel))
            throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(networkingGroupId == null)
            throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        NetworkingGroupEntity networkingGroupEntity = this.getNetworkingGroupById(networkingGroupId);

        if(networkingGroupEntity == null)
            throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(this.checkNameLength(networkingGroupRequestModel)) throw this.throwNameLengthError();

        if(this.checkDescriptionLength(networkingGroupRequestModel)) throw this.throwDescriptionLength();

        if(!networkingGroupEntity.getName().equals(networkingGroupRequestModel.getName())){

            networkingGroupEntity.setName(networkingGroupRequestModel.getName());
        }

        if(!networkingGroupEntity.getDescription().equals(networkingGroupRequestModel)){

            networkingGroupEntity.setDescription(networkingGroupRequestModel.getDescription());
        }



        List<ContactEntity> checkContactEntities = new ArrayList<>();

        if(networkingGroupRequestModel.getMemberIds() != null){

            for(String id: networkingGroupRequestModel.getMemberIds()){

                checkContactEntities.add(this.contactService.getContactEntity(id));
            }

            for(AssignedNetworkingGroupEntity assignment: networkingGroupEntity.getAssignedNetworkingGroupEntities()){

                if(!checkContactEntities.contains(assignment.getContactEntity())){

                    this.assignedNetworkingGroupService.deleteAssignedNetworkingGroup(assignment);
                }

            }
        }

        this.saveNetworkingGroupEntity(networkingGroupEntity);

    }

    @Override
    public void deleteNetworkingGroup(String networkingGroupId) {


        this.networkingGroupRepository.delete(this.getNetworkingGroupById(networkingGroupId));

    }

    @Override
    public NetworkingGroupFormResponseModel getFormResponseModel(String id) {

        // get networking group entity
        NetworkingGroupEntity networkingGroupEntity = this.getNetworkingGroupById(id);

        //check if entity is null
        if(networkingGroupEntity == null) throw new NetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //get networking group dto
        NetworkingGroupFormResponseModel networkingGroupFormResponseModel = new NetworkingGroupFormResponseModel();

        networkingGroupFormResponseModel.setName(networkingGroupEntity.getName());
        networkingGroupFormResponseModel.setDescription(networkingGroupEntity.getDescription());


        if(networkingGroupEntity.getAssignedNetworkingGroupEntities() != null){

            //get entities from relationships
            networkingGroupFormResponseModel.setMemberIds(
                    this.contactService.peelOffContactIdsToHashMap(
                            this.assignedNetworkingGroupService.getContactEntities(
                                    networkingGroupEntity.getAssignedNetworkingGroupEntities()
                            )
                    )
            );

        }

        return networkingGroupFormResponseModel;
    }




    @Override
    public List<NetworkingGroupEntity> getMultipleNetworkingGroups(List<String> networkingGroupIds) {

        List<NetworkingGroupEntity> returnValue = new ArrayList<>();

        for(String id: networkingGroupIds){

            NetworkingGroupEntity networkingGroupEntity = this.networkingGroupRepository.findByNetworkingGroupId(id);

            if(networkingGroupEntity == null)
                throw new NetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            returnValue.add(networkingGroupEntity);
        }

        return returnValue;
    }



    @Override
    public NetworkingGroupEntity saveNetworkingGroupEntity(NetworkingGroupEntity networkingGroupEntity) {

        return this.networkingGroupRepository.save(networkingGroupEntity);
    }



    @Override
    public List<NetworkingGroupEntity> getNetworkingGroupEntities() {

        return this.networkingGroupRepository.findAll();
    }

    @Override
    public NetworkingGroupDashBoard getNetworkingGroupEssential(NetworkingGroupEntity networkingGroupEntity) {

        if(this.entityIsNull(networkingGroupEntity))
            throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        NetworkingGroupDashBoard networkingGroupDashBoard = new NetworkingGroupDashBoard();

        networkingGroupDashBoard.setCreatedAt(networkingGroupEntity.getCreatedAt());
        networkingGroupDashBoard.setName(networkingGroupEntity.getName());
        networkingGroupDashBoard.setDescription(networkingGroupEntity.getDescription());
        networkingGroupDashBoard.setUpdatedAt(networkingGroupEntity.getUpdatedAt());
        networkingGroupDashBoard.setUpdatedAt(networkingGroupEntity.getUpdatedAt());
        networkingGroupDashBoard.setCreatedAt(networkingGroupEntity.getCreatedAt());
        networkingGroupDashBoard.setNetworkingGroupId(networkingGroupEntity.getNetworkingGroupId());
        networkingGroupDashBoard.setContactEssentials(
                this.assignedNetworkingGroupService.getContactEssentials(
                        networkingGroupEntity.getAssignedNetworkingGroupEntities()
                )
        );

        return networkingGroupDashBoard;
    }

    @Override
    public List<NetworkingGroupDashBoard> getNetworkingGroupEssential(List<NetworkingGroupEntity> networkingGroupEntities) {

        if(this.entityIsNull(networkingGroupEntities))
            throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<NetworkingGroupDashBoard> returnValue = new ArrayList<>();

        for(NetworkingGroupEntity networkingGroup: networkingGroupEntities){

            returnValue.add(this.getNetworkingGroupEssential(networkingGroup));
        }

        return returnValue;
    }

    @Override
    public NetworkingGroupEntity generateNetworkingGroupWithId(NetworkingGroupEntity networkingGroupEntity) {

        networkingGroupEntity.setNetworkingGroupId(this.utils.generateRandomID());

        while(this.networkingGroupRepository.existsByNetworkingGroupId(networkingGroupEntity.getNetworkingGroupId())){

            networkingGroupEntity.setNetworkingGroupId(this.utils.generateRandomID());
        }

        return networkingGroupEntity;
    }



    @Override
    public NetworkingGroupFormResponseModel networkingGroupFrom(NetworkingGroupEntity networkingGroupEntity) {

        NetworkingGroupFormResponseModel networkingGroupFormResponseModel = new NetworkingGroupFormResponseModel();

        networkingGroupFormResponseModel.setMemberIds(
                this.contactService.peelOffContactIdsToHashMap(
                        this.assignedNetworkingGroupService.getContactEntities(
                                networkingGroupEntity.getAssignedNetworkingGroupEntities())
                )
        );

        networkingGroupFormResponseModel.setName(networkingGroupEntity.getName());
        networkingGroupFormResponseModel.setDescription(networkingGroupEntity.getDescription());
        networkingGroupFormResponseModel.setCreatedAt(networkingGroupEntity.getCreatedAt());
        networkingGroupFormResponseModel.setUpdatedAt(networkingGroupEntity.getUpdatedAt());

        return networkingGroupFormResponseModel;
    }


    @Override
    public Boolean entityIsNull(NetworkingGroupEntity networkingGroupEntity) {

        return networkingGroupEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<NetworkingGroupEntity> networkingGroupEntities) {
        return networkingGroupEntities == null;
    }

    @Override
    public Boolean requestIsNull(NetworkingGroupRequestModel networkingGroupRequestModel) {
        return networkingGroupRequestModel == null;
    }

    @Override
    public Boolean requestIsNull(List<NetworkingGroupRequestModel> networkingGroupRequestModelList) {
        return networkingGroupRequestModelList ==  null;
    }


    @Override
    public Boolean responseIsNull(NetworkingGroupResponseModel networkingGroupResponseModel) {

        return networkingGroupResponseModel == null;
    }

    @Override
    public Boolean checkNameLength(NetworkingGroupRequestModel networkingGroupRequestModel) {

        Boolean returnValue = false;

        //check name is 3 characters or more
        if(networkingGroupRequestModel.getName().length() < 2){

            returnValue = true;
        }

        //check is name length is 50 characters or fewer.
        if(networkingGroupRequestModel.getName().length() <= 50){
            returnValue = true;
        }

        return returnValue;
    }

    @Override
    public Boolean checkDescriptionLength(NetworkingGroupRequestModel networkingGroupRequestModel) {

        Boolean returnValue = false;

        //check if description is present
        if(networkingGroupRequestModel.getDescription().length() > 0){

            //check if present description is less than 5
            if(networkingGroupRequestModel.getDescription().length() < 5){

                returnValue = true;
            }
        }

        //check the description length is 250 characters or fewer
        if(networkingGroupRequestModel.getDescription().length() >= 250 ){

            returnValue = true;
        }


        return returnValue;
    }

    @Override
    public Boolean checkForMemberIds(String memberIds) {

        return memberIds == null;
    }

    @Override
    public NetworkingGroupServiceException throwNameLengthError() {

        return new NetworkingGroupServiceException(NetworkingGroupErrorMessages.NAME_LENGTH.getErrorMessage());
    }

    @Override
    public NetworkingGroupServiceException throwDescriptionLength() {
        return new NetworkingGroupServiceException(NetworkingGroupErrorMessages.DESCRIPTION.getErrorMessage());
    }



}
