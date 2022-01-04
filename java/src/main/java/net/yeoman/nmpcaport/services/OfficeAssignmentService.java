package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.OfficeAssignmentEntity;

public interface OfficeAssignmentService {

    public OfficeAssignmentEntity getOneOfficeAssignment(String publicId);
    public OfficeAssignmentEntity createOfficeAssignment(OfficeAssignmentEntity officeAssignmentEntity);
    public OfficeAssignmentEntity updateOfficeAssignment(OfficeAssignmentEntity officeAssignmentEntity, String publicId);
    public OfficeAssignmentEntity deleteOfficeAssignment(String publicId);
    public Boolean officePublicIdExist(String publicId);
}
