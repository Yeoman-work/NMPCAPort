package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.AssignedNumberEntity;

public interface AssignedNumberService {

    public AssignedNumberEntity getAssignedNumber(String assignmentId);
    public AssignedNumberEntity createAssignedNumber(AssignedNumberEntity assignedNumberEntity);
    public AssignedNumberEntity updateAssignedNumber(String assignmentId);
    public AssignedNumberEntity deleteAssignedNumber(String assignmentId);
    public Boolean existByAssignmentId(String assignmentID);

}
