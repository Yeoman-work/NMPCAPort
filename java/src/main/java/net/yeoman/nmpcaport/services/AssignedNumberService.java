package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.PhoneNumberEntity;

import java.util.List;

public interface AssignedNumberService {

    public AssignedNumberEntity getAssignedNumber(String assignmentId);
    public AssignedNumberEntity createAssignedNumber(AssignedNumberEntity assignedNumberEntity);
    public AssignedNumberEntity updateAssignedNumber(String assignmentId);
    public AssignedNumberEntity deleteAssignedNumber(String assignmentId);
    public Boolean existByAssignmentId(String assignmentID);
    public AssignedNumberEntity assignmentNumberContactProcess(PhoneNumberEntity phoneNumberEntity, ContactEntity contactEntity);
    public AssignedNumberEntity createAssignedEntity();
    public AssignedNumberEntity savedAssignedNumberEntity(AssignedNumberEntity assignedNumberEntity);
    public Boolean entityIsNull(AssignedNumberEntity assignedNumberEntity);
    public AssignedNumberEntity saveAssignmentEntityContact(ContactEntity contactEntity, PhoneNumberEntity phoneNumberEntity);
    public List<AssignedNumberEntity> savedAssignmentEntityBulk(ContactEntity contactEntity, List<PhoneNumberEntity> phoneNumberEntityList);
    public List<PhoneNumberEntity> getPhoneNumberAssignments(List<AssignedNumberEntity> assignedNumberEntityList);

}
