package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.InterimCommitteeEntity;
import net.yeoman.nmpcaport.io.request.interimCommittee.InterimCommitteeRequest;
import net.yeoman.nmpcaport.io.response.interimCommittee.InterimCommitteeEssentials;
import net.yeoman.nmpcaport.io.response.interimCommittee.InterimCommitteeResponse;

import java.util.List;

public interface InterimCommitteeService {

    //get interim committee
    public InterimCommitteeEntity getInterimCommittee(String publicId);

    //get all Interim committees
    public List<InterimCommitteeEntity> getAllInterimCommittees();

    //create new Interim committee
    public void storeNewInterimCommittee(InterimCommitteeRequest interimCommitteeRequest);

    //get interim committee
    public InterimCommitteeEssentials getInterimCommitteeEssentials(InterimCommitteeEntity interimCommitteeEntity);
    public List<InterimCommitteeEssentials> getInterimCommitteeEssentials(List<InterimCommitteeEntity> interimCommitteeEntities);

    //get interim response
    public InterimCommitteeResponse getInterimCommitteeResponse(InterimCommitteeEntity interimCommittee);
    public List<InterimCommitteeResponse> getInterimCommitteeResponse(List<InterimCommitteeEntity> interimCommitteeEntities);

    //create committee
    public InterimCommitteeEntity createInterimCommittee(InterimCommitteeEntity interimCommittee);

    //save
    public InterimCommitteeEntity save(InterimCommitteeEntity interimCommitteeEntity);

    //check if entity is null
    public Boolean entityIsNull(InterimCommitteeEntity interimCommittee);
    public Boolean entityIsNull(List<InterimCommitteeEntity> interimCommitteeEntities);

    //check if request is null
    public Boolean requestIsNull(InterimCommitteeRequest interimCommitteeRequest);


}
