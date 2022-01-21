package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.InterimCommitteeAssignmentEntity;
import net.yeoman.nmpcaport.entities.InterimCommitteeEntity;
import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.InterimCommitteeAssignmentServiceException;
import net.yeoman.nmpcaport.exception.InterimCommitteeServiceException;
import net.yeoman.nmpcaport.io.repositories.InterimCommitteeAssignmentRepository;
import net.yeoman.nmpcaport.io.repositories.InterimCommitteeRepository;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepEssentials;
import net.yeoman.nmpcaport.services.InterimCommitteeAssignmentsService;
import net.yeoman.nmpcaport.services.NMHouseDistrictService;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterimCommitteeAssignmentServiceImpl implements InterimCommitteeAssignmentsService {

    private final InterimCommitteeAssignmentRepository interimCommitteeAssignmentRepository;
    private final NMHouseDistrictServiceImpl nmHouseDistrictService;
    private final PoliticalPartyServiceImpl politicalPartyService;
    private final Utils utils;

    public InterimCommitteeAssignmentServiceImpl(InterimCommitteeAssignmentRepository interimCommitteeAssignmentRepository,
                                                 NMHouseDistrictServiceImpl nmHouseDistrictService,
                                                 PoliticalPartyServiceImpl politicalPartyService,
                                                 Utils utils
     ){

        this.interimCommitteeAssignmentRepository = interimCommitteeAssignmentRepository;
        this.nmHouseDistrictService = nmHouseDistrictService;
        this.politicalPartyService = politicalPartyService;
        this.utils = utils;
    }


    @Override
    public InterimCommitteeAssignmentEntity savedAssignment(InterimCommitteeAssignmentEntity interimCommittee) {
        return this.interimCommitteeAssignmentRepository.save(interimCommittee);
    }

    @Override
    public InterimCommitteeAssignmentEntity createAssignment(InterimCommitteeAssignmentEntity assignmentEntity) {

        if(entityIsNull(assignmentEntity))
            throw new InterimCommitteeAssignmentServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        assignmentEntity.setPublicId(this.utils.generateRandomID());

        while(this.interimCommitteeAssignmentRepository.existsByPublicId(assignmentEntity.getPublicId())){

            assignmentEntity.setPublicId(this.utils.generateRandomID());
        }

        return assignmentEntity;
    }

    @Override
    public StateRepEntity getStateRepEntities(InterimCommitteeAssignmentEntity interimCommitteeAssignment) {

        if(this.entityIsNull(interimCommitteeAssignment))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return interimCommitteeAssignment.getStateRepEntity();
    }

    @Override
    public List<StateRepEntity> getStateRepEntities(List<InterimCommitteeAssignmentEntity> interimCommitteeAssignmentEntities) {

        if(this.entityIsNull(interimCommitteeAssignmentEntities))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<StateRepEntity> returnValue = new ArrayList<>();

        for(InterimCommitteeAssignmentEntity assignment: interimCommitteeAssignmentEntities){

            returnValue.add(this.getStateRepEntities(assignment));
        }

        return returnValue;
    }

    @Override
    public StateRepEssentials getStateRepEssentials(InterimCommitteeAssignmentEntity interimCommitteeAssignmentEntity) {

        if(this.entityIsNull(interimCommitteeAssignmentEntity))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        StateRepEssentials stateRepEssentials = new StateRepEssentials();

        stateRepEssentials.setStateRepId(interimCommitteeAssignmentEntity.getStateRepEntity().getStateRepId());
        stateRepEssentials.setFirstName(interimCommitteeAssignmentEntity.getStateRepEntity().getFirstName());
        stateRepEssentials.setLastName(interimCommitteeAssignmentEntity.getStateRepEntity().getLastName());
        stateRepEssentials.setEmail(interimCommitteeAssignmentEntity.getStateRepEntity().getEmail());
        stateRepEssentials.setPicture(interimCommitteeAssignmentEntity.getStateRepEntity().getPicture());
        stateRepEssentials.setPoliticalPartyEssentials(
                this.politicalPartyService.getPoliticalPartyEssentials(
                        interimCommitteeAssignmentEntity.getStateRepEntity().getPoliticalParty()
                )
        );

        stateRepEssentials.setNmHouseDistrictEssentialResponse(
                this.nmHouseDistrictService.entityToEssentials(
                        interimCommitteeAssignmentEntity.getStateRepEntity().getNmHouseDistrict()
                )
        );

        return stateRepEssentials;
    }

    @Override
    public List<StateRepEssentials> getStateRepEssentials(List<InterimCommitteeAssignmentEntity> interimCommitteeAssignmentEntities) {

        if(this.entityIsNull(interimCommitteeAssignmentEntities))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<StateRepEssentials> returnValue = new ArrayList<>();

        for(InterimCommitteeAssignmentEntity assignment: interimCommitteeAssignmentEntities){

            returnValue.add(this.getStateRepEssentials(assignment));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(InterimCommitteeAssignmentEntity interimCommitteeAssignmentEntity) {
        return  interimCommitteeAssignmentEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<InterimCommitteeAssignmentEntity> interimCommitteeAssignmentEntities) {
        return interimCommitteeAssignmentEntities == null;
    }
}
