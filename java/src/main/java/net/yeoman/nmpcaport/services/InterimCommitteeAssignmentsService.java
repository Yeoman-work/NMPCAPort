package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.InterimCommitteeAssignmentEntity;
import net.yeoman.nmpcaport.entities.InterimCommitteeEntity;
import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.io.response.stateRep.StateRepEssentials;
import net.yeoman.nmpcaport.services.Impl.InterimCommitteeAssignmentServiceImpl;

import java.util.List;

public interface InterimCommitteeAssignmentsService {

    //save assignment
    public InterimCommitteeAssignmentEntity savedAssignment(InterimCommitteeAssignmentEntity interimCommittee);

    //create assignment
    public InterimCommitteeAssignmentEntity createAssignment(InterimCommitteeAssignmentEntity assignmentEntity);

    //save
    public InterimCommitteeAssignmentEntity saveAssignmentEntity(InterimCommitteeAssignmentEntity interimCommitteeAssignmentEntity);

    //get state reps
    public StateRepEntity getStateRepEntities(InterimCommitteeAssignmentEntity interimCommitteeAssignmentEntity);
    public List<StateRepEntity> getStateRepEntities(List<InterimCommitteeAssignmentEntity> interimCommitteeAssignmentEntity);


    //get state rep essentials
    public StateRepEssentials getStateRepEssentials(InterimCommitteeAssignmentEntity interimCommitteeAssignmentEntity);
    public List<StateRepEssentials> getStateRepEssentials(List<InterimCommitteeAssignmentEntity> interimCommitteeAssignmentEntities);


    //check entity is null
    public Boolean entityIsNull(InterimCommitteeAssignmentEntity interimCommitteeAssignmentEntity);
    public Boolean entityIsNull(List<InterimCommitteeAssignmentEntity> interimCommitteeAssignmentEntities);


}
