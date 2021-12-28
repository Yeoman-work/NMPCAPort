package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.errormessages.CongressionalRepErrorMessages;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.CongressionalDistrictServiceException;
import net.yeoman.nmpcaport.exception.CongressionalRepServiceException;
import net.yeoman.nmpcaport.exception.ErrorMessage;
import net.yeoman.nmpcaport.exception.PoliticalPartyServiceException;
import net.yeoman.nmpcaport.io.repositories.CongressionalDistrictRepository;
import net.yeoman.nmpcaport.io.repositories.CongressionalRepRepository;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictResponse;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.services.CongressionalRepService;
import net.yeoman.nmpcaport.shared.dto.CongressionalRepDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CongressionalRepServiceImpl implements CongressionalRepService {

    @Autowired
    private CongressionalRepRepository congressionalRepRepository;

    @Autowired
    private CongressionalDistrictServiceImpl congressionalDistrictService;

    @Autowired
    private PoliticalPartyServiceImpl politicalPartyService;

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
        return null;
    }

    @Override
    public List<CongressionalRepDto> getAllCongressionalReps() {

        List<CongressionalRepDto> returnValue = new ArrayList<>();

        //get all reps
        List<CongressionalRepEntity> allReps = this.congressionalRepRepository.findAll();

        //convert reps to repDTOs
        for(CongressionalRepEntity rep: allReps){

            //convert dto
            CongressionalRepDto repDto = new ModelMapper().map(rep, CongressionalRepDto.class);

            //check for district
            if(rep.getDistrictEntity() != null){

                //convert district to response if found

                repDto.setDistrictResponse(new ModelMapper().map(rep.getDistrictEntity(), CongressionalDistrictResponse.class));
            }

            //check for party
            if(rep.getPoliticalPartyEntity() != null){

                //convert party to response
                repDto.setPoliticalPartyResponse(new ModelMapper().map(rep.getPoliticalPartyEntity(), PoliticalPartyResponse.class));
            }

            if(rep.getAssignedNumberEntities().size() > 0){
                //phone number response list
                List<PhoneNumberResponse> phoneNumberResponses = new ArrayList<>();
                //cycle through responses
                for(AssignedNumberEntity assigned: rep.getAssignedNumberEntities()){

                    PhoneNumberResponse phoneNumberResponse = new ModelMapper().map(assigned.getPhoneNumberEntity(), PhoneNumberResponse.class);

                    phoneNumberResponses.add(phoneNumberResponse);

                }

                repDto.setPhoneNumberResponse(phoneNumberResponses);
            }

            returnValue.add(repDto);
        }

        return returnValue;
    }
}
