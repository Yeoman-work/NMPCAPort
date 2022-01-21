package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.InterimCommitteeEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.InterimCommitteeServiceException;
import net.yeoman.nmpcaport.io.repositories.InterimCommitteeRepository;
import net.yeoman.nmpcaport.io.response.interimCommittee.InterimCommitteeEssentials;
import net.yeoman.nmpcaport.io.response.interimCommittee.InterimCommitteeResponse;
import net.yeoman.nmpcaport.services.InterimCommitteeService;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterimCommitteeServiceImpl implements InterimCommitteeService {

    private final InterimCommitteeRepository interimCommitteeRepository;

    private final InterimCommitteeAssignmentServiceImpl interimCommitteeAssignmentService;

    private final StateRepServiceImpl stateRepService;

    private  final Utils utils;

    public InterimCommitteeServiceImpl(InterimCommitteeRepository interimCommitteeRepository,
                                        InterimCommitteeAssignmentServiceImpl interimCommitteeAssignmentService,
                                        StateRepServiceImpl stateRepService,
                                        Utils utils
    ){

        this.interimCommitteeRepository = interimCommitteeRepository;
        this.interimCommitteeAssignmentService = interimCommitteeAssignmentService;
        this.stateRepService = stateRepService;
        this.utils = utils;
    }


    @Override
    public InterimCommitteeEntity getInterimCommittee(String publicId) {
        return this.interimCommitteeRepository.findByPublicId(publicId);
    }

    @Override
    public List<InterimCommitteeEntity> getAllInterimCommittees() {
        return this.interimCommitteeRepository.findAll();
    }

    @Override
    public InterimCommitteeEssentials getInterimCommitteeEssentials(InterimCommitteeEntity interimCommitteeEntity) {

        if(this.entityIsNull(interimCommitteeEntity))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        InterimCommitteeEssentials interimCommitteeEssentials = new InterimCommitteeEssentials();

        interimCommitteeEssentials.setName(interimCommitteeEntity.getName());
        interimCommitteeEssentials.setPublicId(interimCommitteeEntity.getPublicId());


        return interimCommitteeEssentials;
    }

    @Override
    public List<InterimCommitteeEssentials> getInterimCommitteeEssentials(List<InterimCommitteeEntity> interimCommitteeEntities) {

        if(this.entityIsNull(interimCommitteeEntities))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<InterimCommitteeEssentials> returnValue = new ArrayList<>();

        for(InterimCommitteeEntity interimCommittee: interimCommitteeEntities){

            returnValue.add(this.getInterimCommitteeEssentials(interimCommittee));
        }

        return returnValue;
    }

    @Override
    public InterimCommitteeResponse getInterimCommitteeResponse(InterimCommitteeEntity interimCommittee) {

        if(this.entityIsNull(interimCommittee))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        InterimCommitteeResponse interimCommitteeResponse = new InterimCommitteeResponse();

        interimCommitteeResponse.setName(interimCommittee.getName());
        interimCommitteeResponse.setPublicId(interimCommittee.getPublicId());
        interimCommitteeResponse.setDescription(interimCommittee.getDescription());
        interimCommitteeResponse.setCreatedAt(interimCommittee.getCreatedAt());
        interimCommitteeResponse.setUpdatedAt(interimCommittee.getCreatedAt());
        interimCommitteeResponse.setStateRepEssentials(
                this.interimCommitteeAssignmentService.getStateRepEssentials(
                        interimCommittee.getInterimCommitteeAssignments()
                )
        );

        return interimCommitteeResponse;
    }

    @Override
    public List<InterimCommitteeResponse> getInterimCommitteeResponse(List<InterimCommitteeEntity> interimCommitteeEntities) {

        if(this.entityIsNull(interimCommitteeEntities))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<InterimCommitteeResponse> returnValue = new ArrayList<>();

        for(InterimCommitteeEntity committeeEntity: interimCommitteeEntities){

            returnValue.add(this.getInterimCommitteeResponse(committeeEntity));
        }

        return returnValue;
    }

    @Override
    public InterimCommitteeEntity createInterimCommittee(InterimCommitteeEntity interimCommittee) {

        if(this.entityIsNull(interimCommittee))
            throw new InterimCommitteeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        interimCommittee.setPublicId(this.utils.generateRandomID());

        while(this.interimCommitteeRepository.existsByPublicId(interimCommittee.getPublicId())){

            interimCommittee.setPublicId(this.utils.generateRandomID());
        }

        return interimCommittee;
    }

    @Override
    public InterimCommitteeEntity save(InterimCommitteeEntity interimCommitteeEntity) {
        return this.interimCommitteeRepository.save(interimCommitteeEntity);
    }

    @Override
    public Boolean entityIsNull(InterimCommitteeEntity interimCommittee) {
        return interimCommittee ==  null;
    }

    @Override
    public Boolean entityIsNull(List<InterimCommitteeEntity> interimCommitteeEntities) {
        return interimCommitteeEntities == null;
    }
}
