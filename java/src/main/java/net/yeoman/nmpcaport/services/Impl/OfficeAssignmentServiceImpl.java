package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.services.OfficeAssignmentService;
import net.yeoman.nmpcaport.entities.OfficeAssignmentEntity;
import net.yeoman.nmpcaport.io.repositories.OfficeAssignmentRepository;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
