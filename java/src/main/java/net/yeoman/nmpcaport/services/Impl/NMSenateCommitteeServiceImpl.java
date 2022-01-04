package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.services.NMSenateCommitteeService;
import net.yeoman.nmpcaport.entities.NMSenateCommitteeEntity;
import net.yeoman.nmpcaport.io.request.NMSenateCommittee.NMSenateCommitteesList;
import net.yeoman.nmpcaport.io.response.nmSenateCommittee.NMSenateCommitteeResponse;
import net.yeoman.nmpcaport.io.repositories.NMSenateCommitteeRepository;
import net.yeoman.nmpcaport.shared.dto.NMSenateCommitteeDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NMSenateCommitteeServiceImpl implements NMSenateCommitteeService {

    @Autowired
    private NMSenateCommitteeRepository nmSenateCommitteeRepository;

    @Autowired
    private Utils utils;

    @Override
    public NMSenateCommitteeDto getCommittee(String committeeId) {
        return null;
    }

    @Override
    public NMSenateCommitteeDto createCommittee(NMSenateCommitteeDto nmSenateCommitteeDto) {
        return null;
    }



    @Override
    public NMSenateCommitteeDto updateCommittee(String committeeId, NMSenateCommitteeDto nmSenateCommitteeDto) {
        return null;
    }


    @Override
    public NMSenateCommitteeDto deleteCommittee(String committeeId) {
        return null;
    }

    @Override
    public NMSenateCommitteeEntity getCommitteeEntity(String committeeId) {
        return null;
    }

    @Override
    public List<NMSenateCommitteeResponse> createMultipleCommittees(NMSenateCommitteesList committeesLists) {

        List<NMSenateCommitteeResponse> returnValue = new ArrayList<>();

        return returnValue;
    }
}
