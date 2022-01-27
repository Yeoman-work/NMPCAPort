 package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.response.contact.ContactDashBoard;
import net.yeoman.nmpcaport.io.response.contact.ContactEssentials;
import net.yeoman.nmpcaport.io.response.contact.ContactFormListResponse;
import net.yeoman.nmpcaport.shared.utils.Utils;
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

import java.util.List;

 @RestController
@RequestMapping("/contacts")
public class ContactController {



    private final ContactServiceImpl contactService;


    public ContactController(ContactServiceImpl contactService){

        this.contactService = contactService;

    }

    @GetMapping(path = {"/{contactId}"}, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ContactResponseModel getContact(@PathVariable("contactId") String contactId){

    	return this.contactService.getContactResponseModel(this.contactService.getContactEntity(contactId));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ContactDashBoard> getAllContacts(){


        return this.contactService.contactDashboardData(this.contactService.getAllContactEntities());
    }

    @GetMapping(path = "/essentials", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ContactEssentials> getContactEssentials(){


        return this.contactService.getContactEssentials(this.contactService.getAllContactEntities());
    }

//    @GetMapping(path="/formContacts", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ContactFormListResponse getContactsForm(){
//
//        return this.contactService.contactsForNetworkingGroup();
//    }

    @PostMapping(consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    			 produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void createContact(@RequestBody ContactDetailsRequestModel contactDetails){
        //process single contact
        this.contactService.createContact(contactDetails);

    }
    

//    @PutMapping(path="/{contactId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
//    								 produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ContactResponseModel updateUser(@PathVariable("contactId") String contactid, @RequestBody ContactDetailsRequestModel contactDetails){
//
//
//        return new ModelMapper().map(contactDto, ContactResponseModel.class);
//    }

    @DeleteMapping
    public String deleteUser(){

        return "inside delete";
    }
}
