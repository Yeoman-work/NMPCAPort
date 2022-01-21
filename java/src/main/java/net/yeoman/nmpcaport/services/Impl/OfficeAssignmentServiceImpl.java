package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.LocationServiceException;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationEssentialsResponse;
import net.yeoman.nmpcaport.services.OfficeAssignmentService;
import net.yeoman.nmpcaport.entities.OfficeAssignmentEntity;
import net.yeoman.nmpcaport.io.repositories.OfficeAssignmentRepository;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeAssignmentServiceImpl implements OfficeAssignmentService {

    @Autowired
    private OfficeAssignmentRepository officeAssignmentRepository;

    @Autowired
    private Utils utils;


    @Override
    public OfficeAssignmentEntity getOneOfficeAssignment(String publicId) {

        return this.officeAssignmentRepository.findByPublicId(publicId);
    }

    @Override
    public OfficeAssignmentEntity createOfficeAssignment(OfficeAssignmentEntity officeAssignmentEntity) {

        return this.officeAssignmentRepository.save(officeAssignmentEntity);
    }

    @Override
    public OfficeAssignmentEntity updateOfficeAssignment(OfficeAssignmentEntity officeAssignmentEntity, String publicId) {


        return null;
    }

    @Override
    public OfficeAssignmentEntity deleteOfficeAssignment(String publicId) {
        return null;
    }

    @Override
    public Boolean officePublicIdExist(String publicId) {

        return this.officeAssignmentRepository.existsByPublicId(publicId);
    }

    @Override
    public LocationEssentialsResponse getLocationEssentials(OfficeAssignmentEntity officeAssignmentEntity) {

        if(this.entityIsNull(officeAssignmentEntity))
            throw new LocationServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        LocationEssentialsResponse locationEssentialsResponse = new LocationEssentialsResponse();

        locationEssentialsResponse.setName(officeAssignmentEntity.getLocationEntity().getName());
        locationEssentialsResponse.setStreetAddress(officeAssignmentEntity.getLocationEntity().getStreetAddress());
        locationEssentialsResponse.setDescription(officeAssignmentEntity.getLocationEntity().getDescription());
        locationEssentialsResponse.setCity(officeAssignmentEntity.getLocationEntity().getCityEntity().getName());
        locationEssentialsResponse.setCounty(officeAssignmentEntity.getLocationEntity().getCountyEntity().getName());
        locationEssentialsResponse.setZipCode(officeAssignmentEntity.getLocationEntity().getZipCodeEntity().getName());

        return locationEssentialsResponse;
    }

    @Override
    public List<LocationEssentialsResponse> getLocationEssentials(List<OfficeAssignmentEntity> officeAssignmentEntities) {

        if(this.entityIsNull(officeAssignmentEntities))
            throw new LocationServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<LocationEssentialsResponse> returnValue = new ArrayList<>();

        for(OfficeAssignmentEntity officeAssignment: officeAssignmentEntities){

            returnValue.add(this.getLocationEssentials(officeAssignment));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(OfficeAssignmentEntity officeAssignmentEntity) {
        return  officeAssignmentEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<OfficeAssignmentEntity> officeAssignmentEntity) {
        return officeAssignmentEntity == null;
    }
}
