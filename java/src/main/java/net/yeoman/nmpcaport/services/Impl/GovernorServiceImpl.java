package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.GovernorEntity;
import net.yeoman.nmpcaport.io.repositories.GovernorRepository;
import net.yeoman.nmpcaport.services.GovernorService;
import net.yeoman.nmpcaport.shared.dto.GovernorDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GovernorServiceImpl implements GovernorService {


    @Autowired
    private GovernorRepository governorRepository;

    @Autowired
    private Utils utils;

    @Override
    public GovernorDto getGovernor(String governorId) {
        return null;
    }

    @Override
    public GovernorDto createGovernor(GovernorDto governorDto) {

        GovernorEntity governor = new ModelMapper().map(governorDto, GovernorEntity.class);
        governor.setGovernorId(utils.generateRandomID());

        while(this.governorRepository.existsByGovernorId(governor.getGovernorId())){

            governor.setGovernorId(utils.generateRandomID());

        }


        return null;
    }

    @Override
    public GovernorDto updateGovernor(String governorId, GovernorDto governorDto) {
        return null;
    }

    @Override
    public GovernorDto deleteGovernor(String governorId) {
        return null;
    }

    @Override
    public GovernorEntity getGovernorEntity(String governorId) {
        return null;
    }
}
