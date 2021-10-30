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

        if(healthCenterDto.getUsers() != null){

            List<UserDetailsResponseModel> userResponseList = new ArrayList<>();

            for(UserEntity user: healthCenterDto.getUsers()){

                userResponseList.add(new ModelMapper().map(user, UserDetailsResponseModel.class));
            }

            healthCenterDto.setUserDetailsResponseList(userResponseList);
        }

        if(healthCenterDto.getContacts() != null){

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

        HealthCenterEntity newHealthCenter = this.healthCenterRepository.save(healthCenterEntity);



        if(healthCenterDto.getUserIds() != null){

            List<UserEntity> users = new ArrayList<>();
            for(String userId: healthCenterDto.getUserIds()){

                UserEntity user = this.userService.getUserEntity(userId);

                user.setHealthCenter(newHealthCenter);

                this.userService.saveUser(user);

                users.add(user);
            }

            healthCenterDto.setUsers(users);

        }

        if(healthCenterDto.getContactIds() != null){

            List<ContactEntity> contacts = new ArrayList<>();
            for(String contactId: healthCenterDto.getContactIds()){

                ContactEntity contact = this.contactService.getContactEntity(contactId);

                contact.setHealthCenter(newHealthCenter);

                this.contactService.saveContact(contact);

                contacts.add(contact);

            }
            healthCenterDto.setContacts(contacts);
        }


        HealthCenterResponseModel healthCenterResponse = new ModelMapper().map(healthCenterEntity, HealthCenterResponseModel.class);

        if(healthCenterDto.getContacts() != null){

            List<ContactNestedResponseModel> contacts = new ArrayList<>();

            for(ContactEntity contact: healthCenterDto.getContacts()){

                contacts.add(new ModelMapper().map(contact, ContactNestedResponseModel.class));
            }

            healthCenterResponse.setContactNestedResponseList(contacts);

        }

        if(healthCenterDto.getUsers() != null){

            List<UserDetailsResponseModel> users = new ArrayList<>();

            for(UserEntity user: healthCenterDto.getUsers()){

                users.add(new ModelMapper().map(user, UserDetailsResponseModel.class));
            }

            healthCenterResponse.setUserDetailsResponseList(users);
        }

        return healthCenterResponse;
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
