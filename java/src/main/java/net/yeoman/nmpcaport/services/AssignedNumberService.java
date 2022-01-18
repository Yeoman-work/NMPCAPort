package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.AssignedNetworkingGroupEntity;
import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.entities.ContactEntity;
import net.yeoman.nmpcaport.entities.PhoneNumberEntity;

import java.util.List;

public interface AssignedNumberService {

    //return Assignment entity
    public AssignedNumberEntity getAssignedNumber(String assignmentId);
    public AssignedNumberEntity updateAssignedNumber(String assignmentId);
    public AssignedNumberEntity deleteAssignedNumber(String assignmentId);
    public AssignedNumberEntity assignmentNumberContactProcess(PhoneNumberEntity phoneNumberEntity, ContactEntity contactEntity);
    public void assignmentNumberContactProcess(List<PhoneNumberEntity> phoneNumberEntityList, ContactEntity contactEntity);




    public AssignedNumberEntity createAssignedEntity();
    public AssignedNumberEntity savedAssignedNumberEntity(AssignedNumberEntity assignedNumberEntity);
    public AssignedNumberEntity saveAssignmentEntityContact(ContactEntity contactEntity, PhoneNumberEntity phoneNumberEntity);
    public List<AssignedNumberEntity> savedAssignmentEntityBulk(ContactEntity contactEntity, List<PhoneNumberEntity> phoneNumberEntityList);

    public List<PhoneNumberEntity> getPhoneNumberEntities(List<AssignedNumberEntity> assignedNumberEntityList);

    public Boolean existByAssignmentId(String assignmentID);

    public Boolean entityIsNull(AssignedNumberEntity assignedNumberEntity);

    public List<PhoneNumberEntity> getPhoneNumberAssignments(List<AssignedNumberEntity> assignedNumberEntityList);

}
