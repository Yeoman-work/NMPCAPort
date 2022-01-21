package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.OfficeAssignmentEntity;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationEssentialsResponse;

import java.util.List;

public interface OfficeAssignmentService {

    public OfficeAssignmentEntity getOneOfficeAssignment(String publicId);
    public OfficeAssignmentEntity createOfficeAssignment(OfficeAssignmentEntity officeAssignmentEntity);
    public OfficeAssignmentEntity updateOfficeAssignment(OfficeAssignmentEntity officeAssignmentEntity, String publicId);
    public OfficeAssignmentEntity deleteOfficeAssignment(String publicId);
    public Boolean officePublicIdExist(String publicId);

    public LocationEssentialsResponse getLocationEssentials(OfficeAssignmentEntity officeAssignmentEntity);
    public List<LocationEssentialsResponse> getLocationEssentials(List<OfficeAssignmentEntity> officeAssignmentEntities);

    //assignment entity
    public Boolean entityIsNull(OfficeAssignmentEntity officeAssignmentEntity);
    public Boolean entityIsNull(List<OfficeAssignmentEntity> officeAssignmentEntity);


}
