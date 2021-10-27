package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.NetworkingGroupEntity;
import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.networkingGroup.NetworkingGroupResponseModel;
import net.yeoman.nmpcaport.repositories.ContactRepository;
import net.yeoman.nmpcaport.repositories.NetworkingGroupRepository;
import net.yeoman.nmpcaport.services.ContactService;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private NetworkingGroupServiceImpl networkingGroupService;

    @Autowired
    private Utils utils;



    @Override
    public ContactDto nestedNetworkingGroups(ContactDto contactDto) {
        List<NetworkingGroupResponseModel> networkGroupResponseList = new ArrayList<>();

        for(NetworkingGroupEntity networkingGroup: contactDto.getNetworkingGroups()){

            NetworkingGroupResponseModel networkingGroupResponseModel = new ModelMapper().map(networkingGroup, NetworkingGroupResponseModel.class);

            networkGroupResponseList.add(networkingGroupResponseModel);
        }
        contactDto.setNetworkingGroupResponse(networkGroupResponseList);

        return contactDto;
    }
    
    
    
    @Override
    public ContactDto getContact(String contactId) {
        
    	ContactEntity contactEntity = this.contactRepository.findByContactId(contactId);
    	
    	if(contactEntity == null) throw new RuntimeException(contactId);
    	
    	
    	return new ModelMapper().map(contactEntity, ContactDto.class);
        
    }

    @Override
    public ContactDto createContact(ContactDto contactDto) {

        ContactEntity contactEntity = new ModelMapper().map(contactDto, ContactEntity.class);

        if(contactDto.getNetworkingGroupIds().size() > 0){
            List<NetworkingGroupEntity> networkingGroups = new ArrayList<>();
            for(String networkingGroupId: contactDto.getNetworkingGroupIds()){

                NetworkingGroupEntity networkingGroup = networkingGroupService.networkGroupEntityByNetworkingGroupId(networkingGroupId);
                networkingGroups.add(networkingGroup);
            }

            contactEntity.setNetworkingGroups(networkingGroups);
        }

        contactEntity.setContactId(utils.generateRandomID());

        ContactEntity storedContact = this.contactRepository.save(contactEntity);

        return new ModelMapper().map(storedContact, ContactDto.class);
    }

    @Override
    public ContactDto updateContact(String contactId, ContactDto contactDto) {
        
    	//get the contact entity
    	ContactEntity contactEntity = this.contactRepository.findByContactId(contactId);
    	
    	// check if firstName is updated
    	if(!contactEntity.getFirstName().equals(contactDto.getFirstName())) {
    		
    		contactEntity.setFirstName(contactDto.getFirstName());
    	}
    	
    	//check if last name was updated
    	if(!contactEntity.getLastName().equals(contactDto.getLastName())) {
    		
    		contactEntity.setLastName(contactDto.getLastName());
    	}
    	//check if the title is null
    	if(contactEntity.getTitle() == null) {
    		
    		contactEntity.setTitle(contactDto.getTitle());
    		
    	}
    	//check if the title is different from userInput
    	if(!contactEntity.getTitle().equals(contactDto.getTitle())){
    		
    		contactEntity.setTitle(contactDto.getTitle());
    		
    	}
    	
    	//check if the user updated the networking groups
    	if(contactDto.getNetworkingGroupIds().size() > 0) {
    		
    		List<NetworkingGroupEntity> networkEntityList = new ArrayList<>();
    		
    		for(String networkingGroupId: contactDto.getNetworkingGroupIds()) {
    			
    			 NetworkingGroupEntity networkingEntity = this.networkingGroupService.networkGroupEntityByNetworkingGroupId(networkingGroupId);
    			 
    			 networkEntityList.add(networkingEntity);
    		}
    		
    		contactEntity.setNetworkingGroups(networkEntityList);
    	}

        ContactEntity updatedContact = this.contactRepository.save(contactEntity);


    	
    	return nestedNetworkingGroups(new ModelMapper().map(updatedContact, ContactDto.class));
    }



    @Override
    public ContactDto deleteContact(String contactId) {
        return null;
    }


}
