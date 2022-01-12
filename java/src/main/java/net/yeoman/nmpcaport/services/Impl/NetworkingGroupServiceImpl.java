package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.ContactServiceException;
import net.yeoman.nmpcaport.exception.NetworkingGroupServiceException;
import net.yeoman.nmpcaport.io.request.networkingGroup.NetworkingGroupRequestModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupFormResponseModel;
import net.yeoman.nmpcaport.services.NetworkingGroupService;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;
import net.yeoman.nmpcaport.io.repositories.NetworkingGroupRepository;
import net.yeoman.nmpcaport.io.repositories.UserRepository;
import net.yeoman.nmpcaport.shared.dto.NetworkingGroupDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkingGroupServiceImpl implements NetworkingGroupService {

    @Autowired
    private NetworkingGroupRepository networkingGroupRepository;

    @Autowired
    private AssignedNetworkingGroupServiceImpl assignedNetworkingGroupService;

    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;




    public List<UserDetailsResponseModel> addUsersToNetworkingGroup(NetworkingGroupDto netGrpDto){
        List<UserDetailsResponseModel> returnValue = new ArrayList<>();

        for(UserEntity user: netGrpDto.getUsers()){

            UserDetailsResponseModel userDetails = new ModelMapper().map(user, UserDetailsResponseModel.class);

            returnValue.add(userDetails);

        }
        return returnValue;
    }



    public List<UserEntity> getUserEntityFromUserIds(List<String> userIds){
        List<UserEntity> returnValue = new ArrayList<>();
        for(String userId: userIds){

            UserEntity user = this.userRepository.findByUserId(userId);
            returnValue.add(user);
        }

        return returnValue;
    }





    @Override
    public NetworkingGroupDto getNetworkingGroupById(String netGrpId) {

        NetworkingGroupEntity networkingGroupEntity = this.networkingGroupRepository.findByNetworkingGroupId(netGrpId);

        NetworkingGroupDto networkingGroupDto = new ModelMapper().map(networkingGroupEntity, NetworkingGroupDto.class);

        networkingGroupDto.setUserResponse(addUsersToNetworkingGroup(networkingGroupDto));

        return networkingGroupDto;
    }

    @Override
    public NetworkingGroupDto createNetworkingGroup(NetworkingGroupDto networkingGroup) {

        if(this.networkingGroupRepository.existsByName(networkingGroup.getName())) throw new RuntimeException(networkingGroup.getName());

        NetworkingGroupEntity networkingGroupEntity  = new ModelMapper().map(networkingGroup, NetworkingGroupEntity.class);

        networkingGroupEntity.setNetworkingGroupId(utils.generateRandomID());

        while(this.networkingGroupRepository.existsByNetworkingGroupId(networkingGroupEntity.getNetworkingGroupId())){

            networkingGroupEntity.setNetworkingGroupId(utils.generateRandomID());
        }

        NetworkingGroupEntity storedGroup = this.networkingGroupRepository.save(networkingGroupEntity);

        return new ModelMapper().map(storedGroup, NetworkingGroupDto.class);
    }

    
    @Override
    public NetworkingGroupDto updateNetworkingGroup(NetworkingGroupDto networkingGroup, String networkingGroupId) {


        NetworkingGroupEntity networkingGroupEntity = this.networkingGroupRepository.findByNetworkingGroupId(networkingGroupId);

        if(!networkingGroupEntity.getName().equals(networkingGroup.getName())){

            if(this.networkingGroupRepository.existsByName(networkingGroup.getName())) throw new RuntimeException(networkingGroup.getName());
            networkingGroupEntity.setName(networkingGroup.getName());
        }

        if(!networkingGroupEntity.getDescription().equals(networkingGroup.getDescription())){

            networkingGroupEntity.setDescription(networkingGroup.getDescription());
        }

        List<UserEntity> updatedUsers = getUserEntityFromUserIds(networkingGroup.getUserIds());



        NetworkingGroupEntity storedGroup = this.networkingGroupRepository.save(networkingGroupEntity);

        NetworkingGroupDto networkingGroupDto = new ModelMapper().map(storedGroup, NetworkingGroupDto.class);

        networkingGroupDto.setUserResponse(addUsersToNetworkingGroup(networkingGroupDto));


        return networkingGroupDto;
    }



    @Override
    public NetworkingGroupResponseModel deleteNetworkingGroup(String networkingGroupId) {


        NetworkingGroupEntity networkingGroupEntity = this.networkingGroupRepository.findByNetworkingGroupId(networkingGroupId);

        NetworkingGroupDto networkingGroupDto = new ModelMapper().map(networkingGroupEntity, NetworkingGroupDto.class);

        this.networkingGroupRepository.delete(networkingGroupEntity);

        return new ModelMapper().map(networkingGroupDto, NetworkingGroupResponseModel.class);
    }

    @Override
    public List<NetworkingGroupDto> getAllNetworkingGroups() {

        List<NetworkingGroupDto> networkingGroupDtoList = this.entityToDto(this.networkingGroupRepository.findAll());


        return networkingGroupDtoList;
    }


    @Override
    public NetworkingGroupEntity networkGroupEntityByNetworkingGroupId(String netGrpId) {

        return this.networkingGroupRepository.findByNetworkingGroupId(netGrpId);
    }

    @Override
    public List<NetworkingGroupEntity> getMultipleNetworkingGroups(List<String> networkingGroupIds) {

        List<NetworkingGroupEntity> returnValue = new ArrayList<>();

        for(String id: networkingGroupIds){

            NetworkingGroupEntity networkingGroupEntity = this.networkingGroupRepository.findByNetworkingGroupId(id);

            if(networkingGroupEntity == null) throw new NetworkingGroupServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            returnValue.add(networkingGroupEntity);
        }

        return returnValue;
    }

    @Override
    public List<NetworkingGroupDto> entityToDto(List<NetworkingGroupEntity> networkingGroupEntities) {
        List<NetworkingGroupDto> returnValue = new ArrayList<>();

        for(NetworkingGroupEntity group: networkingGroupEntities){

            NetworkingGroupDto networkingGroupDto = utils.objectMapper().map(group, NetworkingGroupDto.class);

            if(group.getAssignedNetworkingGroupEntities() != null){

                List<ContactEntity> contactEntities = this.assignedNetworkingGroupService.getContactEntities(group.getAssignedNetworkingGroupEntities());

                if(this.contactService.entityIsNull(contactEntities)) throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

                networkingGroupDto.setContactNestedResponses(this.contactService.dtoToNestedResponse(this.contactService.entityToDto(contactEntities)));

            }

            System.out.println(networkingGroupDto);

            returnValue.add(networkingGroupDto);

        }

        return returnValue;
    }

    @Override
    public NetworkingGroupDto entityToDtoBase(NetworkingGroupEntity networkingGroupEntity) {
        return utils.objectMapper().map(networkingGroupEntity, NetworkingGroupDto.class);
    }

    @Override
    public List<NetworkingGroupDto> entityToDtoBase(List<NetworkingGroupEntity> networkingGroupEntities) {

        List<NetworkingGroupDto> returnValue = new ArrayList<>();

        for(NetworkingGroupEntity networkingGroupEntity: networkingGroupEntities){

            returnValue.add(this.entityToDtoBase(networkingGroupEntity));
        }

        return returnValue;
    }

    @Override
    public List<NetworkingGroupEntity> dtoToEntity(List<NetworkingGroupDto> networkingGroupDtoList) {

        List<NetworkingGroupEntity> returnValue = new ArrayList<>();

        for(NetworkingGroupDto group: networkingGroupDtoList){

            returnValue.add(utils.objectMapper().map(group, NetworkingGroupEntity.class));
        }

        return returnValue;
    }

    @Override
    public List<NetworkingGroupResponseModel> dtoToResponse(List<NetworkingGroupDto> networkingGroupDtoList) {

        List<NetworkingGroupResponseModel> returnValue = new ArrayList<>();

        for(NetworkingGroupDto group: networkingGroupDtoList){

            returnValue.add(utils.objectMapper().map(group, NetworkingGroupResponseModel.class));
        }

        return returnValue;
    }

    @Override
    public List<NetworkingGroupDto> requestToDto(List<NetworkingGroupRequestModel> networkingGroupRequestModelList) {

        List<NetworkingGroupDto> returnValue = new ArrayList<>();

        for(NetworkingGroupRequestModel group: networkingGroupRequestModelList){

            returnValue.add(utils.objectMapper().map(group, NetworkingGroupDto.class));
        }

        return returnValue;
    }

    @Override
    public NetworkingGroupFormResponseModel networkingGroupFrom(String netGrpId) {

        NetworkingGroupEntity networkingGroupEntity = this.networkingGroupRepository.findByNetworkingGroupId(netGrpId);

        NetworkingGroupDto networkingGroupDto = this.entityToDto(networkingGroupEntity);

        if(networkingGroupDto.getAssignedNetworkingGroupEntities() != null){

            List<ContactEntity> groupMembers = this.assignedNetworkingGroupService.getContactEntities(networkingGroupEntity.getAssignedNetworkingGroupEntities());

            networkingGroupDto.setMemberContactEntities(groupMembers);
        }

        List<ContactEntity> contactEntities = this.contactService.getAllContactEntities();

        for(ContactEntity contactEntity: contactEntities){

            List<ContactEntity> nonMembers = new ArrayList<>();

            if(!networkingGroupDto.getMemberContactEntities().contains(contactEntity)){

                nonMembers.add(contactEntity);

            }

            networkingGroupDto.setNonMemberContactEntities(nonMembers);
        }

        networkingGroupDto.setMembers(this.contactService.dtoToNestedResponse(this.contactService.entityToDto(networkingGroupDto.getMemberContactEntities())));
        networkingGroupDto.setNonMembers(this.contactService.dtoToNestedResponse(this.contactService.entityToDto(networkingGroupDto.getNonMemberContactEntities())));

        return this.dtoToFormResponse(networkingGroupDto);
    }

    @Override
    public NetworkingGroupFormResponseModel dtoToFormResponse(NetworkingGroupDto networkingGroupDto) {

        return this.utils.objectMapper().map(networkingGroupDto, NetworkingGroupFormResponseModel.class);
    }

    @Override
    public NetworkingGroupDto entityToDto(NetworkingGroupEntity networkingGroupEntity) {

        if(this.entityIsNull(networkingGroupEntity)) throw new NetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        NetworkingGroupDto networkingGroupDto = this.utils.objectMapper().map(networkingGroupEntity, NetworkingGroupDto.class);

        if(networkingGroupEntity.getAssignedNetworkingGroupEntities() != null){

            List<ContactEntity> contactEntities = this.assignedNetworkingGroupService.getContactEntities(networkingGroupEntity.getAssignedNetworkingGroupEntities());

            networkingGroupDto.setContactNestedResponses(this.contactService.dtoToNestedResponse(this.contactService.entityToDto(contactEntities)));
        }

        return networkingGroupDto;

    }

    @Override
    public NetworkingGroupEntity dtoToEntity(NetworkingGroupDto networkingGroupDto) {

        return utils.objectMapper().map(networkingGroupDto, NetworkingGroupEntity.class);
    }

    @Override
    public NetworkingGroupResponseModel dtoToResponse(NetworkingGroupDto networkingGroupDto) {

        return utils.objectMapper().map(networkingGroupDto, NetworkingGroupResponseModel.class);
    }

    @Override
    public NetworkingGroupDto requestToDto(NetworkingGroupRequestModel networkingGroupRequestModel) {

        return utils.objectMapper().map(networkingGroupRequestModel, NetworkingGroupDto.class);
    }

    @Override
    public Boolean entityIsNull(NetworkingGroupEntity networkingGroupEntity) {

        return networkingGroupEntity == null;
    }

    @Override
    public Boolean dtoIsNull(NetworkingGroupDto networkingGroupDto) {

        return networkingGroupDto == null;
    }

    @Override
    public Boolean responseIsNull(NetworkingGroupResponseModel networkingGroupResponseModel) {

        return networkingGroupResponseModel == null;
    }

}
