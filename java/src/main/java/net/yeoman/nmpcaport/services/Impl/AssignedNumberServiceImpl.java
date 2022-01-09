package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.AssignedNumberServiceException;
import net.yeoman.nmpcaport.io.repositories.AssignedNumberRepository;
import net.yeoman.nmpcaport.services.AssignedNumberService;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignedNumberServiceImpl implements AssignedNumberService {

    @Autowired
    private AssignedNumberRepository assignedNumberRepository;

    @Autowired
    private Utils utils;


    @Override
    public AssignedNumberEntity getAssignedNumber(String assignmentId) {

        return this.assignedNumberRepository.findByAssignmentId(assignmentId);
    }

    @Override
    public AssignedNumberEntity createAssignedNumber(AssignedNumberEntity assignedNumberEntity) {

        assignedNumberEntity.setAssignmentId(utils.generateRandomID());

        return this.assignedNumberRepository.save(assignedNumberEntity);
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

        AssignedNumberEntity savedAssignedNumberEntity = this.assignedNumberRepository.save(assignedNumberEntity);

        if(savedAssignedNumberEntity == null) throw new AssignedNumberServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return savedAssignedNumberEntity;
    }


}
