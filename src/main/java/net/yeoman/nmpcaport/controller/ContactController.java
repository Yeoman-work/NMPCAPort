 package net.yeoman.nmpcaport.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.yeoman.nmpcaport.io.request.contact.ContactDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.contact.ContactResponseModel;
import net.yeoman.nmpcaport.services.Impl.ContactServiceImpl;
import net.yeoman.nmpcaport.shared.dto.ContactDto;

@RestController
@RequestMapping("/contacts")
public class ContactController {


    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping(path = {"/{contactId}"}, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ContactResponseModel getContact(@PathVariable("contactId") String contactId){

    	return new ModelMapper().map(this.contactService.getContact(contactId), ContactResponseModel.class);
    }

    @PostMapping(consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    			 produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ContactResponseModel createContact(@RequestBody ContactDetailsRequestModel contactDetails){

        ContactDto storedContactDto = this.contactService.createContact(new ModelMapper().map(contactDetails, ContactDto.class));

        return new ModelMapper().map(storedContactDto, ContactResponseModel.class);
    }
    

    @PutMapping(path="/{contactId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    								 produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ContactResponseModel updateUser(@PathVariable("contactId") String contactid, @RequestBody ContactDetailsRequestModel contactDetails){
    	
    	ContactDto contactDto = this.contactService.updateContact(contactid, new ModelMapper().map(contactDetails, ContactDto.class));
    	
        return new ModelMapper().map(contactDto, ContactResponseModel.class);
    }

    @DeleteMapping
    public String deleteUser(){

        return "inside delete";
    }
}
