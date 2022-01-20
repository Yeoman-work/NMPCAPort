package net.yeoman.nmpcaport.services.Impl;


import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.AssignedNetworkingGroupServiceException;
import net.yeoman.nmpcaport.exception.AssignedNumberServiceException;
import net.yeoman.nmpcaport.exception.ContactServiceException;
import net.yeoman.nmpcaport.exception.PhoneNumberServiceException;
import net.yeoman.nmpcaport.io.repositories.AssignedNumberRepository;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberEssentials;
import net.yeoman.nmpcaport.services.AssignedNumberService;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignedNumberServiceImpl implements AssignedNumberService {

    @Autowired
    private AssignedNumberRepository assignedNumberRepository;

    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private PhoneNumberServiceImpl phoneNumberService;

    @Autowired
    private Utils utils;


    @Override
    public AssignedNumberEntity getAssignedNumber(String assignmentId) {

        return this.assignedNumberRepository.findByAssignmentId(assignmentId);
    }



    @Override
    public AssignedNumberEntity updateAssignedNumber(String assignmentId) {
        return null;
    }

    @Override
    public AssignedNumberEntity deleteAssignedNumber(String assignmentId) {

        AssignedNumberEntity assignedNumberEntity = this.assignedNumberRepository.findByAssignmentId(assignmentId);
        this.assignedNumberRepository.delete(assignedNumberEntity);

        return assignedNumberEntity;
    }

    @Override
    public Boolean existByAssignmentId(String assignmentID) {

        return this.assignedNumberRepository.existsByAssignmentId(assignmentID);
    }

    @Override
    public AssignedNumberEntity assignmentNumberContactProcess(PhoneNumberEntity phoneNumberEntity, ContactEntity contactEntity) {

        //create assignment entity
        AssignedNumberEntity assignedNumberEntity = new AssignedNumberEntity();

        //set the assignment entity public id
        assignedNumberEntity.setAssignmentId(utils.generateRandomID());

        //check id is unique
        while(this.assignedNumberRepository.existsByAssignmentId(assignedNumberEntity.getAssignmentId())){

            assignedNumberEntity.setAssignmentId(utils.generateRandomID());

        }

        //assign contact entity
        assignedNumberEntity.setContactEntity(contactEntity);
        //assign phone number entity
        assignedNumberEntity.setPhoneNumberEntity(phoneNumberEntity);



        return this.assignedNumberRepository.save(assignedNumberEntity);
    }

    @Override
    public void assignmentNumberContactProcess(List<PhoneNumberEntity> phoneNumberEntityList, ContactEntity contactEntity) {

        if(this.phoneNumberService.entityIsNull(phoneNumberEntityList))
            throw new PhoneNumberServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(this.contactService.entityIsNull(contactEntity))
            throw new ContactServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        for(PhoneNumberEntity phoneNumber: phoneNumberEntityList){

            AssignedNumberEntity assignmentEntity = this.createAssignedEntity();

            assignmentEntity.setContactEntity(contactEntity);
            assignmentEntity.setPhoneNumberEntity(phoneNumber);

            this.savedAssignedNumberEntity(assignmentEntity);

        }

    }

    @Override
    public PhoneNumberEssentials getPhoneNumberEssentials(AssignedNumberEntity assignedNumberEntity) {

        if(this.entityIsNull(assignedNumberEntity))
            throw new AssignedNetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        PhoneNumberEssentials phoneNumberEssentials = new PhoneNumberEssentials();

        phoneNumberEssentials.setNumber(
                assignedNumberEntity.getPhoneNumberEntity().getNumber()
        );

        phoneNumberEssentials.setDescription(
                assignedNumberEntity.getPhoneNumberEntity().getDescription()
        );

        phoneNumberEssentials.setPhoneNumberId(
                assignedNumberEntity.getPhoneNumberEntity().getPhoneNumberId()
        );

        return phoneNumberEssentials;
    }

    @Override
    public List<PhoneNumberEssentials> getPhoneNumberEssentials(List<AssignedNumberEntity> assignedNumberEntityList) {

        if(this.entityIsNull(assignedNumberEntityList))
            throw new AssignedNetworkingGroupServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<PhoneNumberEssentials> returnValue = new ArrayList<>();

        for(AssignedNumberEntity assignment: assignedNumberEntityList){

            returnValue.add(this.getPhoneNumberEssentials(assignment));
        }

        return returnValue;
    }


    @Override
    public AssignedNumberEntity createAssignedEntity() {

        AssignedNumberEntity assignedNumber = new AssignedNumberEntity();

        assignedNumber.setAssignmentId(utils.generateRandomID());

        while(this.assignedNumberRepository.existsByAssignmentId(assignedNumber.getAssignmentId())){

            assignedNumber.setAssignmentId(utils.generateRandomID());
        }

        return assignedNumber;
    }

    @Override
    public AssignedNumberEntity savedAssignedNumberEntity(AssignedNumberEntity assignedNumberEntity) {

        return this.assignedNumberRepository.save(assignedNumberEntity) ;
    }

    @Override
    public Boolean entityIsNull(AssignedNumberEntity assignedNumberEntity) {

        return assignedNumberEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<AssignedNumberEntity> assignedNumberEntityList) {
        return assignedNumberEntityList == null;
    }

    @Override
    public AssignedNumberEntity saveAssignmentEntityContact(ContactEntity contactEntity, PhoneNumberEntity phoneNumberEntity) {

        //check for contact entity
        if(this.contactService.entityIsNull(contactEntity)) throw new ContactServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //check for phone number entity
        if(this.phoneNumberService.entityIsNull(phoneNumberEntity)) throw new PhoneNumberServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //create assignment entity
        AssignedNumberEntity assignedNumber = this.createAssignedEntity();

        //set contact entity
        assignedNumber.setContactEntity(contactEntity);

        //set phone number
        assignedNumber.setPhoneNumberEntity(phoneNumberEntity);

        return this.savedAssignedNumberEntity(assignedNumber);
    }

    @Override
    public List<AssignedNumberEntity> savedAssignmentEntityBulk(ContactEntity contactEntity, List<PhoneNumberEntity> phoneNumberEntityList) {

        List<AssignedNumberEntity> returnValue = new ArrayList<>();

        //loop over phone number entities
        for(PhoneNumberEntity phoneNumber: phoneNumberEntityList){

            //create relationship
            AssignedNumberEntity savedAssignedNumberEntity = this.saveAssignmentEntityContact(contactEntity, phoneNumber);

            returnValue.add(savedAssignedNumberEntity);
        }

        return returnValue;
    }

    @Override
    public List<PhoneNumberEntity> getPhoneNumberEntities(List<AssignedNumberEntity> assignedNumberEntityList) {
        return null;
    }


    @Override
    public List<PhoneNumberEntity> getPhoneNumberAssignments(List<AssignedNumberEntity> assignedNumberEntityList) {

        if(assignedNumberEntityList == null) throw new AssignedNumberServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        List<PhoneNumberEntity> returnValue = new ArrayList<>();

        for(AssignedNumberEntity assignedNumber: assignedNumberEntityList){

            returnValue.add(assignedNumber.getPhoneNumberEntity());
        }

        return returnValue;
    }


}
