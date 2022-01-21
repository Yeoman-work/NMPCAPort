package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorEssentials;
import net.yeoman.nmpcaport.io.response.USSenator.USSenatorResponse;
import net.yeoman.nmpcaport.services.USSenatorService;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.PoliticalPartyServiceException;
import net.yeoman.nmpcaport.exception.UsSenatorServiceException;
import net.yeoman.nmpcaport.io.repositories.USSenatorRepository;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.io.response.LocationResponse.LocationResponse;
import net.yeoman.nmpcaport.io.response.city.CityResponse;
import net.yeoman.nmpcaport.io.response.politcalParty.PoliticalPartyResponse;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.shared.dto.StaffDto;
import net.yeoman.nmpcaport.shared.dto.USSenatorDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class USSenatorServiceImpl implements USSenatorService {


    private final USSenatorRepository usSenatorRepository;

    private final PoliticalPartyServiceImpl politicalPartyService;

    private final StaffServiceImpl staffService;

    private final OfficeAssignmentServiceImpl officeAssignmentService;

    private final Utils utils;

    public USSenatorServiceImpl(USSenatorRepository usSenatorRepository,
                                PoliticalPartyServiceImpl politicalPartyService,
                                StaffServiceImpl staffService,
                                OfficeAssignmentServiceImpl officeAssignmentService,
                                Utils utils
                                ){

        this.usSenatorRepository = usSenatorRepository;
        this.politicalPartyService = politicalPartyService;
        this.staffService = staffService;
        this.officeAssignmentService = officeAssignmentService;
        this.utils = utils;
    }




    @Override
    public USSenatorDto createSenator(USSenatorDto senatorDto) {

        //first name test
        if(senatorDto.getFirstName().isBlank()) throw new UsSenatorServiceException(ErrorMessages.FIRST_NAME_LENGTH.getErrorMessage());
        if(senatorDto.getFirstName().length() < 3) throw new UsSenatorServiceException(ErrorMessages.FIRST_NAME_LENGTH.getErrorMessage());
        if(senatorDto.getFirstName().length() > 25) throw new UsSenatorServiceException(ErrorMessages.FIRST_NAME_LENGTH.getErrorMessage());

        //last name test
        if(senatorDto.getLastName().isBlank()) throw new UsSenatorServiceException(ErrorMessages.LAST_NAME_LENGTH.getErrorMessage());
        if(senatorDto.getLastName().length() < 3) throw new UsSenatorServiceException(ErrorMessages.LAST_NAME_LENGTH.getErrorMessage());
        if(senatorDto.getLastName().length() > 25) throw new UsSenatorServiceException(ErrorMessages.LAST_NAME_LENGTH.getErrorMessage());


        //check website url length
        if(senatorDto.getWebsite().length() > 300) throw  new UsSenatorServiceException(ErrorMessages.EMAIL_CHARACTER_LENGTH.getErrorMessage());

        //check picture URL length
        if(senatorDto.getPicture().length()> 250) throw new UsSenatorServiceException(ErrorMessages.PICTURE_LENGTH.getErrorMessage());

        //email length
        if(senatorDto.getEmail().length() > 120) throw new UsSenatorServiceException(ErrorMessages.EMAIL_CHARACTER_LENGTH.getErrorMessage());

        //check for party
        if(senatorDto.getPoliticalParty().isBlank()) throw new PoliticalPartyServiceException(ErrorMessages.NO_PARTY_AFFILIATION.getErrorMessage());

        //convert Dto to entity
        USSenatorEntity senatorEntity = new ModelMapper().map(senatorDto, USSenatorEntity.class);

        //generate public id
        senatorEntity.setSenatorId(utils.generateRandomID());

        //check that id is available
        while(this.usSenatorRepository.existsBySenatorId(senatorEntity.getSenatorId())){

            senatorEntity.setSenatorId(utils.generateRandomID());
        }


        // get political party
        PoliticalPartyEntity politicalPartyEntity = this.politicalPartyService.politicalPartyEntity(senatorDto.getPoliticalParty());

        // check if party exist
        if(politicalPartyEntity == null) throw new UsSenatorServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //assign political party
        senatorEntity.setPoliticalPartyEntity(politicalPartyEntity);

        //get elected data
        LocalDate elected = senatorEntity.getElected();

        //calculate next election date
        LocalDate nextElectionDate = elected.plusYears(6).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).plusDays(1);

        senatorEntity.setNextElection(nextElectionDate);

        //save senator
        USSenatorEntity savedSenator  = this.usSenatorRepository.save(senatorEntity);

        //concert party entity to party response

        USSenatorDto usSenatorDto = new ModelMapper().map(savedSenator, USSenatorDto.class);

        PoliticalPartyResponse politicalPartyResponse = new ModelMapper().map(usSenatorDto.getPoliticalPartyEntity(), PoliticalPartyResponse.class);

        usSenatorDto.setPoliticalPartyResponse(politicalPartyResponse);

        return usSenatorDto;
    }

    @Override
    public USSenatorDto updateSenator(String senatorId, USSenatorDto usSenatorDto) {
        return null;
    }

    @Override
    public USSenatorDto deleteSenator(String senatorId) {
        return null;
    }

    @Override
    public USSenatorEntity getUSSenatorEntity(String senatorId) {

        return this.usSenatorRepository.findBySenatorId(senatorId);
    }

    @Override
    public USSenatorResponse getUsSenatorResponse(USSenatorEntity usSenatorEntity) {

        if(this.entityIsNull(usSenatorEntity))
            throw new UsSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        USSenatorResponse usSenatorResponse = new USSenatorResponse();

        usSenatorResponse.setSenatorId(usSenatorEntity.getSenatorId());
        usSenatorResponse.setFirstName(usSenatorEntity.getFirstName());
        usSenatorResponse.setLastName(usSenatorEntity.getLastName());
        usSenatorResponse.setEmail(usSenatorEntity.getEmail());
        usSenatorResponse.setPicture(usSenatorEntity.getPicture());
        usSenatorResponse.setElected(usSenatorEntity.getElected());
        usSenatorResponse.setWebsite(usSenatorEntity.getWebsite());
        usSenatorResponse.setNextElection(usSenatorEntity.getNextElection());

        usSenatorResponse.setLocationEssentialsResponses(
                this.officeAssignmentService.getLocationEssentials(
                        usSenatorEntity.getOfficeAssignmentEntities()
                )
        );

        usSenatorResponse.setStaffEssentials(
                this.staffService.getStaffEssentials(
                        usSenatorEntity.getStaffEntities()
                )
        );

        usSenatorResponse.setPoliticalPartyEssentials(
                this.politicalPartyService.getPoliticalPartyEssentials(
                        usSenatorEntity.getPoliticalPartyEntity()
                )
        );

        return usSenatorResponse;
    }

    @Override
    public List<USSenatorResponse> getUsSenatorResponse(List<USSenatorEntity> usSenatorEntities) {

        if(this.entityIsNull(usSenatorEntities))
            throw new UsSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<USSenatorResponse> returnValue = new ArrayList<>();

        for(USSenatorEntity usSenatorEntity: usSenatorEntities){

            returnValue.add(this.getUsSenatorResponse(usSenatorEntity));
        }

        return returnValue;
    }


    @Override
    public List<USSenatorEntity> getUSSenatorEntities() {
        return this.usSenatorRepository.findAll();
    }

    @Override
    public USSenatorEssentials getUSSenatorEssentials(USSenatorEntity usSenatorEntity) {

        if(this.entityIsNull(usSenatorEntity))
            throw new UsSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        USSenatorEssentials usSenatorEssentials = new USSenatorEssentials();

        usSenatorEssentials.setSenatorId(usSenatorEntity.getSenatorId());
        usSenatorEssentials.setFirstName(usSenatorEntity.getFirstName());
        usSenatorEssentials.setLastName(usSenatorEntity.getLastName());
        usSenatorEssentials.setEmail(usSenatorEntity.getEmail());
        usSenatorEssentials.setWebsite(usSenatorEntity.getWebsite());
        usSenatorEssentials.setPicture(usSenatorEntity.getPicture());
        usSenatorEssentials.setElected(usSenatorEntity.getElected());
        usSenatorEssentials.setNextElection(usSenatorEntity.getNextElection());

        usSenatorEssentials.setPoliticalPartyEssentials(
                this.politicalPartyService.getPoliticalPartyEssentials(usSenatorEntity.getPoliticalPartyEntity()
                )
        );

        usSenatorEssentials.setStaffEssentials(
                this.staffService.getStaffEssentials(
                        usSenatorEntity.getStaffEntities()
                )
        );

        usSenatorEssentials.setLocationEssentialsResponses(
                this.officeAssignmentService.getLocationEssentials(
                        usSenatorEntity.getOfficeAssignmentEntities()
                )
        );

        return usSenatorEssentials;
    }

    @Override
    public List<USSenatorEssentials> getUSSenatorEssentials(List<USSenatorEntity> usSenatorEntities) {

        if(entityIsNull(usSenatorEntities))
            throw new UsSenatorServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<USSenatorEssentials> returnValue = new ArrayList<>();

        for(USSenatorEntity usSenator: usSenatorEntities){

            returnValue.add(this.getUSSenatorEssentials(usSenator));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(USSenatorEntity usSenatorEntity) {
        return usSenatorEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<USSenatorEntity> usSenatorEntities) {
        return usSenatorEntities == null;
    }
}
