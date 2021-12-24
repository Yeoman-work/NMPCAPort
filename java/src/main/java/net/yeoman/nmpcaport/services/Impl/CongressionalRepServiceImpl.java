package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.CongressionalDistrictEntity;
import net.yeoman.nmpcaport.entities.CongressionalRepEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.CongressionalDistrictServiceException;
import net.yeoman.nmpcaport.exception.CongressionalRepServiceException;
import net.yeoman.nmpcaport.io.repositories.CongressionalDistrictRepository;
import net.yeoman.nmpcaport.io.repositories.CongressionalRepRepository;
import net.yeoman.nmpcaport.services.CongressionalRepService;
import net.yeoman.nmpcaport.shared.dto.CongressionalRepDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CongressionalRepServiceImpl implements CongressionalRepService {

    @Autowired
    private CongressionalRepRepository congressionalRepRepository;

    @Autowired
    private CongressionalDistrictRepository congressionalDistrictRepository;

    @Autowired
    private Utils utils;


    @Override
    public CongressionalRepDto getCongressionalRep(String congressionalRepId) {

        CongressionalRepEntity congressionalRepEntity = this.congressionalRepRepository.findByCongressionalRepId(congressionalRepId);

        if(congressionalRepEntity == null) throw new CongressionalRepServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return new ModelMapper().map(congressionalRepEntity, CongressionalRepDto.class);
    }

    @Override
    public CongressionalRepDto createCongressionalRep(CongressionalRepDto congressionalRepDto) {

        CongressionalRepEntity congressionalRep = new ModelMapper().map(congressionalRepDto, CongressionalRepEntity.class);

        congressionalRep.setCongressionalRepId(utils.generateRandomID());

        while(this.congressionalRepRepository.existsByCongressionalRepId(congressionalRep.getCongressionalRepId())){

            congressionalRep.setCongressionalRepId(utils.generateRandomID());
        }
        CongressionalRepEntity storedRep = this.congressionalRepRepository.save(congressionalRep);

        return new ModelMapper().map(storedRep, CongressionalRepDto.class);
    }

    @Override
    public CongressionalRepDto updateCongressionalRep(String repId, CongressionalRepDto congressionalRepDto) {

        CongressionalRepEntity congressionalRep = this.congressionalRepRepository.findByCongressionalRepId(repId);

        if(!congressionalRep.getFirstName().equals(congressionalRepDto.getFirstName())){

            congressionalRep.setFirstName(congressionalRepDto.getFirstName());
        }

        if(!congressionalRep.getFirstName().equals(congressionalRepDto.getLastName())){

            congressionalRep.setLastName(congressionalRepDto.getLastName());
        }

        if(congressionalRep.getDistrictEntity() != null){

            if(!congressionalRep.getDistrictEntity().getCongressionalDistrictId().equals(congressionalRepDto.getCongressionalDistrictIdentifier())){

                CongressionalDistrictEntity congressionalDistrict = this.congressionalDistrictRepository.findByCongressionalDistrictId(congressionalRepDto.getCongressionalDistrictIdentifier());

                if(congressionalDistrict == null) throw new CongressionalDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                congressionalRep.setDistrictEntity(congressionalDistrict);
            }
        }

        if(congressionalRep.getDistrictEntity() == null){

            if(congressionalRepDto.getCongressionalDistrictIdentifier() != null){

                congressionalRep.setDistrictEntity(this.congressionalDistrictRepository.findByCongressionalDistrictId(congressionalRepDto.getCongressionalDistrictIdentifier()));
            }
        }

        if(congressionalRep.getPicture() != null){

            if(!congressionalRep.getPicture().equals(congressionalRepDto.getPicture())){
                congressionalRep.setPicture(congressionalRepDto.getPicture());
            }
        }

        if(congressionalRep.getWebsite() != null){

            if(!congressionalRep.getWebsite().equals(congressionalRepDto.getWebsite())){

                congressionalRep.setWebsite(congressionalRepDto.getWebsite());
            }
        }


        CongressionalRepEntity storedCongressionalRep = this.congressionalRepRepository.save(congressionalRep);

        return new ModelMapper().map(storedCongressionalRep, CongressionalRepDto.class);
    }

    @Override
    public CongressionalRepDto deleteCongressionalRep(String repId) {
        return null;
    }

    @Override
    public CongressionalRepEntity getCongressionalRepEntity(String congressionalRepId) {
        return null;
    }
}
