package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.io.response.CongressionalRepResponse.CongressionalRepEssentials;
import net.yeoman.nmpcaport.services.CongressionalRepService;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.CongressionalRepErrorMessages;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.CongressionalDistrictServiceException;
import net.yeoman.nmpcaport.exception.CongressionalRepServiceException;
import net.yeoman.nmpcaport.exception.PoliticalPartyServiceException;
import net.yeoman.nmpcaport.io.repositories.CongressionalRepRepository;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.shared.dto.CongressionalRepDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CongressionalRepServiceImpl implements CongressionalRepService {


    private final CongressionalRepRepository congressionalRepRepository;
    private final CongressionalDistrictServiceImpl congressionalDistrictService;
    private final PoliticalPartyServiceImpl politicalPartyService;
    private final AssignedNumberServiceImpl assignedNumberService;
    private final OfficeAssignmentServiceImpl officeAssignmentService;
    private final StaffServiceImpl staffService;
    private final Utils utils;

    public CongressionalRepServiceImpl(CongressionalDistrictServiceImpl congressionalDistrictService,
                                       CongressionalRepRepository congressionalRepRepository,
                                       PoliticalPartyServiceImpl politicalPartyService,
                                       AssignedNumberServiceImpl assignedNumberService,
                                       OfficeAssignmentServiceImpl officeAssignmentService,
                                       StaffServiceImpl staffService,
                                       Utils utils
    ){

        this.congressionalRepRepository = congressionalRepRepository;
        this.congressionalDistrictService = congressionalDistrictService;
        this.politicalPartyService = politicalPartyService;
        this.assignedNumberService = assignedNumberService;
        this.officeAssignmentService = officeAssignmentService;
        this.staffService = staffService;
        this.utils = utils;
    }



    @Override
    public CongressionalRepDto getCongressionalRep(String congressionalRepId) {

        return null;
    }

    @Override
    public CongressionalRepDto createCongressionalRep(CongressionalRepDto congressionalRepDto) {

        //first name validation
        if(congressionalRepDto.getFirstName().isBlank()) throw  new CongressionalRepServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        if(congressionalRepDto.getFirstName().length() < 3) throw  new CongressionalRepServiceException(CongressionalRepErrorMessages.FIRST_NAME_LENGTH.getErrorMessage());
        if(congressionalRepDto.getFirstName().length() > 25) throw  new CongressionalRepServiceException(CongressionalRepErrorMessages.FIRST_NAME_LENGTH.getErrorMessage());

        //last name validation
        if(congressionalRepDto.getLastName().isBlank()) throw  new CongressionalRepServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        if(congressionalRepDto.getLastName().length() < 3) throw new CongressionalRepServiceException(CongressionalRepErrorMessages.LAST_NAME_LENGTH.getErrorMessage());
        if(congressionalRepDto.getLastName().length() > 25) throw new CongressionalRepServiceException(CongressionalRepErrorMessages.LAST_NAME_LENGTH.getErrorMessage());

        //party field populated test
        if(congressionalRepDto.getPoliticalParty().isBlank()) throw new CongressionalRepServiceException(ErrorMessages.NO_PARTY_AFFILIATION.getErrorMessage());

        //check email length
        if(congressionalRepDto.getEmail().length() > 150) throw new CongressionalRepServiceException(ErrorMessages.EMAIL_CHARACTER_LENGTH.getErrorMessage());

        //check picture length
        if(congressionalRepDto.getPicture().length() > 250) throw new CongressionalRepServiceException(ErrorMessages.PICTURE_LENGTH.getErrorMessage());

        //website length
        if(congressionalRepDto.getWebsite().length() > 300) throw new CongressionalRepServiceException(ErrorMessages.WEBSITE_URL.getErrorMessage());

        //convert to Entity after test passed
        CongressionalRepEntity congressionalRep = new ModelMapper().map(congressionalRepDto, CongressionalRepEntity.class);

        //set public id
        congressionalRep.setCongressionalRepId(utils.generateRandomID());

        //check if public id exist
        while(this.congressionalRepRepository.existsByCongressionalRepId(congressionalRep.getCongressionalRepId())){
            //reset public id
            congressionalRep.setCongressionalRepId(utils.generateRandomID());
        }

        //get political party
        PoliticalPartyEntity politicalPartyEntity = this.politicalPartyService.politicalPartyEntity(congressionalRepDto.getPoliticalParty());


        //if party is null throw error message
        if(politicalPartyEntity == null) throw new PoliticalPartyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //set political party
        congressionalRep.setPoliticalPartyEntity(politicalPartyEntity);


        if(!congressionalRepDto.getCongressionalDistrict().isBlank()){
            //get congressional district
            CongressionalDistrictEntity congressionalDistrictEntity = this.congressionalDistrictService.getCongressionalDistrictEntity(congressionalRepDto.getCongressionalDistrict());

            //check entity exist
            if(congressionalDistrictEntity == null) throw new CongressionalDistrictServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            //set district
            congressionalRep.setDistrictEntity(congressionalDistrictEntity);
        }


        CongressionalRepEntity storedRep = this.congressionalRepRepository.save(congressionalRep);

        CongressionalRepDto returnValue = new ModelMapper().map(storedRep, CongressionalRepDto.class);

        if(returnValue.getDistrictEntity() != null){

            returnValue.setDistrictResponse(new ModelMapper().map(returnValue.getDistrictEntity(), CongressionalDistrictResponse.class));

        }

        return returnValue;
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

        CongressionalRepEntity storedCongressionalRep = this.congressionalRepRepository.save(congressionalRep);

        return new ModelMapper().map(storedCongressionalRep, CongressionalRepDto.class);

    }


    @Override
    public CongressionalRepDto deleteCongressionalRep(String repId) {
        return null;
    }

    @Override
    public CongressionalRepEntity getCongressionalRepEntity(String congressionalRepId) {

        return this.congressionalRepRepository.findByCongressionalRepId(congressionalRepId);

    }

    @Override
    public List<CongressionalRepEntity> getAllCongressionalRepEntities() {
        return this.congressionalRepRepository.findAll();
    }

    @Override
    public CongressionalRepEssentials getAllCongressionalRepEssentials(CongressionalRepEntity congressionalRepEntity) {

        if(this.entityIsNull(congressionalRepEntity))
            throw new CongressionalRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        CongressionalRepEssentials congressionalRepEssentials = new CongressionalRepEssentials();

        congressionalRepEssentials.setFirstName(congressionalRepEntity.getFirstName());
        congressionalRepEssentials.setLastName(congressionalRepEntity.getLastName());
        congressionalRepEssentials.setEmail(congressionalRepEntity.getEmail());
        congressionalRepEssentials.setPicture(congressionalRepEntity.getPicture());
        congressionalRepEssentials.setWebsite(congressionalRepEntity.getWebsite());
        congressionalRepEssentials.setCongressionalRepId(congressionalRepEntity.getCongressionalRepId());
        congressionalRepEssentials.setLocationEssentialsResponses(
                this.officeAssignmentService.getLocationEssentials(
                        congressionalRepEntity.getOfficeAssignmentEntities()
                )
        );

        congressionalRepEssentials.setPhoneNumberEssentials(
                this.assignedNumberService.getPhoneNumberEssentials(
                        congressionalRepEntity.getAssignedNumberEntities()
                )
        );

        congressionalRepEssentials.setStaffEssentials(
                this.staffService.getStaffEssentials(
                        congressionalRepEntity.getStaffEntities()
                )
        );

        congressionalRepEssentials.setCongressionalDistrictEssentialsResponse(
                this.congressionalDistrictService.entityToEssentials(
                        congressionalRepEntity.getDistrictEntity()
                )
        );
        return congressionalRepEssentials;
    }

    @Override
    public List<CongressionalRepEssentials> getAllCongressionalRepEssentials(List<CongressionalRepEntity> congressionalRepEntities) {

        if(this.entityIsNull(congressionalRepEntities))
            throw new CongressionalRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CongressionalRepEssentials> returnValue = new ArrayList<>();

        for(CongressionalRepEntity congressionalRepEntity: congressionalRepEntities){

            returnValue.add(this.getAllCongressionalRepEssentials(congressionalRepEntity));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(CongressionalRepEntity congressionalRepEntity) {
        return congressionalRepEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<CongressionalRepEntity> congressionalRepEntity) {
        return congressionalRepEntity == null;
    }


}
