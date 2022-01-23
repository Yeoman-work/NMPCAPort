package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.SenateCommitteeEntity;
import net.yeoman.nmpcaport.io.request.NMSenateCommittee.NMSenateCommitteesList;
import net.yeoman.nmpcaport.io.response.nmSenateCommittee.NMSenateCommitteeResponse;
import net.yeoman.nmpcaport.shared.dto.NMSenateCommitteeDto;

import java.util.List;

public interface NMSenateCommitteeService {

    public NMSenateCommitteeDto getCommittee(String committeeId);
    public NMSenateCommitteeDto createCommittee(NMSenateCommitteeDto nmSenateCommitteeDto);
    public NMSenateCommitteeDto updateCommittee(String committeeId, NMSenateCommitteeDto nmSenateCommitteeDto);
    public NMSenateCommitteeDto deleteCommittee(String committeeId);
    public SenateCommitteeEntity getCommitteeEntity(String committeeId);
    public List<NMSenateCommitteeResponse> createMultipleCommittees(NMSenateCommitteesList committeesLists);
}
