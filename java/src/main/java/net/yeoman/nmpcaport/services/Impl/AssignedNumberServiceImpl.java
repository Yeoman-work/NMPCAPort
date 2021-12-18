package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.AssignedNumberEntity;
import net.yeoman.nmpcaport.io.repositories.AssignedNumberRepository;
import net.yeoman.nmpcaport.services.AssignedNumberService;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

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
}
