package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.HealthCenterEntity;
import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.io.response.HealthCenter.HealthCenterResponseModel;
import net.yeoman.nmpcaport.io.response.contact.ContactNestedResponseModel;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;
import net.yeoman.nmpcaport.repositories.HealthCenterRepository;
import net.yeoman.nmpcaport.repositories.UserRepository;
import net.yeoman.nmpcaport.services.HealthCenterService;
import net.yeoman.nmpcaport.shared.dto.HealthCenterDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthCenterServiceImpl implements HealthCenterService {

    @Autowired
    private HealthCenterRepository healthCenterRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private Utils utils;

    @Override
    public HealthCenterDto getHealthCenter(String healthCenterId) {

        HealthCenterEntity healthCenterEntity = this.healthCenterRepository.findByHealthCenterId(healthCenterId);

        HealthCenterDto healthCenterDto = new ModelMapper().map(healthCenterEntity, HealthCenterDto.class);

        if(healthCenterDto.getUsers().size() > 0){

            List<UserDetailsResponseModel> userResponseList = new ArrayList<>();

            for(UserEntity user: healthCenterDto.getUsers()){

                userResponseList.add(new ModelMapper().map(user, UserDetailsResponseModel.class));
            }

            healthCenterDto.setUserDetailsResponseList(userResponseList);
        }

        if(healthCenterDto.getContacts().size() > 0){

            List<ContactNestedResponseModel> contactNestedResponseList = new ArrayList<>();

            for(ContactEntity contact: healthCenterDto.getContacts()){

                contactNestedResponseList.add(new ModelMapper().map(contact, ContactNestedResponseModel.class));
            }

            healthCenterDto.setContactNestedResponseList(contactNestedResponseList);
        }

        return healthCenterDto;
    }

    @Override
    public HealthCenterResponseModel createHealthCenter(HealthCenterDto healthCenterDto) {

        HealthCenterEntity healthCenterEntity = new ModelMapper().map(healthCenterDto, HealthCenterEntity.class);

        healthCenterEntity.setHealthCenterId(utils.generateRandomID());

        if(healthCenterDto.getUserIds() != null){
            List<UserEntity> userEntities = new ArrayList<>();

            for(String userId: healthCenterDto.getUserIds()){

                userEntities.add(this.userService.getUserEntity(userId));
            }

            healthCenterEntity.setUsers(userEntities);
        }

        if(healthCenterDto.getContactIds() != null){

            List<ContactEntity> contacts = new ArrayList<>();

            for(String contactId: healthCenterDto.getContactIds()){

                contacts.add(this.contactService.getContactEntity(contactId));
            }

            healthCenterEntity.setContacts(contacts);
        }

        HealthCenterEntity storedHealthCenter = this.healthCenterRepository.save(healthCenterEntity);

        HealthCenterResponseModel savedHealthCenter = new ModelMapper().map(storedHealthCenter, HealthCenterResponseModel.class);

        if(storedHealthCenter.getContacts() != null){
            List<ContactNestedResponseModel> contacts = new ArrayList<>();
            for(ContactEntity contact: storedHealthCenter.getContacts()){

                contacts.add(new ModelMapper().map(contact, ContactNestedResponseModel.class));
            }

            savedHealthCenter.setContactNestedResponseList(contacts);

        }

        if(storedHealthCenter.getUsers() != null){

            List<UserDetailsResponseModel> users = new ArrayList<>();

            for(UserEntity user: storedHealthCenter.getUsers()){

                users.add(new ModelMapper().map(user, UserDetailsResponseModel.class));
            }

            savedHealthCenter.setUserDetailsResponseList(users);
        }

        return new ModelMapper().map(savedHealthCenter, HealthCenterResponseModel.class);
    }

    @Override
    public HealthCenterDto updateHealthCenter(String healthCenterId, HealthCenterDto healthCenterDto) {
        return null;
    }

    @Override
    public HealthCenterDto deleteHealthCenter(String healthCenterId) {
        return null;
    }
}
