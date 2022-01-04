package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.io.response.staff.StaffResponse;
import net.yeoman.nmpcaport.services.USSenatorService;
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

    @Autowired
    private USSenatorRepository usSenatorRepository;

    @Autowired
    private PoliticalPartyServiceImpl politicalPartyService;

    @Autowired
    private Utils utils;

    @Override
    public USSenatorDto getSenator(String senatorId){

        ModelMapper modelMapper = new ModelMapper();
        //get senator entity
        USSenatorEntity usSenatorEntity = this.usSenatorRepository.findBySenatorId(senatorId);

        //convert to Dto
        USSenatorDto usSenatorDto = modelMapper.map(usSenatorEntity, USSenatorDto.class);

        //convert political party entity to response object
        usSenatorDto.setPoliticalPartyResponse(new ModelMapper().map(usSenatorDto.getPoliticalPartyEntity(), PoliticalPartyResponse.class));

        List<LocationResponse> locationResponseList = new ArrayList<>();
        for(OfficeAssignmentEntity assignment: usSenatorDto.getOfficeAssignmentEntities()){

            LocationResponse locationResponse = modelMapper.map(assignment.getLocationEntity(), LocationResponse.class);

            locationResponse.setCityResponse(modelMapper.map(assignment.getLocationEntity().getCityEntity(), CityResponse.class));
            locationResponse.setCountyResponse(modelMapper.map(assignment.getLocationEntity().getCountyEntity(), CountyResponse.class));
            locationResponse.setZipCodeResponse(modelMapper.map(assignment.getLocationEntity().getZipCodeEntity(), ZipCodeResponse.class));

            locationResponseList.add(locationResponse);
        }

        if(usSenatorDto.getStaffEntities() != null){

            List<StaffResponse> staffResponses = new ArrayList<>();

            for(StaffEntity staff: usSenatorDto.getStaffEntities()){

                StaffDto staffDto = modelMapper.map(staff, StaffDto.class);

                if(staffDto.getAssignedNumberEntities() != null){

                    List<PhoneNumberResponse> phoneNumberResponses = new ArrayList<>();

                    for(AssignedNumberEntity assignedNumber: staffDto.getAssignedNumberEntities()){

                        phoneNumberResponses.add(modelMapper.map(assignedNumber.getPhoneNumberEntity(), PhoneNumberResponse.class));

                    }

                    staffDto.setPhoneNumberResponses(phoneNumberResponses);
                }

                staffResponses.add(modelMapper.map(staffDto, StaffResponse.class));
            }

            usSenatorDto.setStaffResponses(staffResponses);

        }

        usSenatorDto.setLocationResponses(locationResponseList);

        return usSenatorDto;
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
    public List<USSenatorDto> getAllSenators() {
        ModelMapper modelMapper = new ModelMapper();
        List<USSenatorDto> returnValue = new ArrayList<>();

        List<USSenatorEntity> fromRepo = this.usSenatorRepository.findAll();

        for(USSenatorEntity senator: fromRepo){

            USSenatorDto usSenatorDto = modelMapper.map(senator, USSenatorDto.class);

            usSenatorDto.setPoliticalPartyResponse(new ModelMapper().map(usSenatorDto.getPoliticalPartyEntity(), PoliticalPartyResponse.class));

            if(usSenatorDto.getOfficeAssignmentEntities() != null || usSenatorDto.getOfficeAssignmentEntities().size() > 0){

                List<LocationResponse> officeLocations = new ArrayList<>();

                for(OfficeAssignmentEntity officeAssignment: usSenatorDto.getOfficeAssignmentEntities()){

                    LocationResponse locationResponse = modelMapper.map(officeAssignment.getLocationEntity(), LocationResponse.class);
                    locationResponse.setCityResponse(modelMapper.map(officeAssignment.getLocationEntity().getCityEntity(), CityResponse.class));
                    locationResponse.setCountyResponse(modelMapper.map(officeAssignment.getLocationEntity().getCountyEntity(), CountyResponse.class));
                    locationResponse.setZipCodeResponse(modelMapper.map(officeAssignment.getLocationEntity().getZipCodeEntity(), ZipCodeResponse.class));

                    officeLocations.add(locationResponse);
                }

                usSenatorDto.setLocationResponses(officeLocations);
            }

            if(usSenatorDto.getStaffEntities() != null){

                List<StaffResponse> staffResponses = new ArrayList<>();

                for(StaffEntity staffEntity: usSenatorDto.getStaffEntities()){

                    StaffResponse staffResponse = modelMapper.map(staffEntity, StaffResponse.class);

                    if(staffEntity.getAssignedNumberEntities() != null){

                        List<PhoneNumberResponse> phoneNumberResponses = new ArrayList<>();

                        for(AssignedNumberEntity assignedNumber: staffEntity.getAssignedNumberEntities()){

                            phoneNumberResponses.add(modelMapper.map(assignedNumber.getPhoneNumberEntity(), PhoneNumberResponse.class));
                        }

                        staffResponse.setPhoneNumberResponses(phoneNumberResponses);
                    }

                    staffResponses.add(staffResponse);
                }

                usSenatorDto.setStaffResponses(staffResponses);
            }


            returnValue.add(usSenatorDto);

        }

        return returnValue;
    }
}
