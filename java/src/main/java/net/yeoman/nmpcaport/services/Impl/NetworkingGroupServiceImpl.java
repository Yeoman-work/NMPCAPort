package net.yeoman.nmpcaport.services.Impl;

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
        ModelMapper modelMapper = new ModelMapper();
        List<NetworkingGroupDto> returnValue = new ArrayList<>();

        List<NetworkingGroupEntity> networkingGroupEntities = this.networkingGroupRepository.findAll();

        for(NetworkingGroupEntity networkingGroup: networkingGroupEntities){
            returnValue.add(modelMapper.map(networkingGroup, NetworkingGroupDto.class));
        }

        return returnValue;
    }


    @Override
    public NetworkingGroupEntity networkGroupEntityByNetworkingGroupId(String netGrpId) {

        return this.networkingGroupRepository.findByNetworkingGroupId(netGrpId);
    }


}
