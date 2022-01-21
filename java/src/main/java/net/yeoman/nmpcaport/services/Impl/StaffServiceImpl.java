package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.*;
import net.yeoman.nmpcaport.exception.StaffServiceException;
import net.yeoman.nmpcaport.io.response.staff.StaffEssentials;
import net.yeoman.nmpcaport.services.AssignedNumberService;
import net.yeoman.nmpcaport.services.StaffService;
import net.yeoman.nmpcaport.exception.CongressionalRepServiceException;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.PhoneNumberServiceException;
import net.yeoman.nmpcaport.exception.UsSenatorServiceException;
import net.yeoman.nmpcaport.io.repositories.StaffRepository;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;
import net.yeoman.nmpcaport.shared.dto.PhoneNumberDto;
import net.yeoman.nmpcaport.shared.dto.StaffDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;


    public StaffServiceImpl(StaffRepository staffRepository

                            ){

        this.staffRepository = staffRepository;

    }



    @Autowired
    private USSenatorServiceImpl usSenatorService;

    @Autowired
    PhoneNumberServiceImpl phoneNumberService;

    @Autowired
    private AssignedNumberService assignedNumberService;

    @Autowired
    private Utils utils;

    @Override
    public StaffDto getStaffMember(String staffId) {
        return null;
    }


    @Override
    public StaffDto createStaffMemberForUSSenator(StaffDto staffDto) {
        //model mapper
        ModelMapper modelMapper = new ModelMapper();
        //convert staff memberTO to entity
        StaffEntity staffMember = modelMapper.map(staffDto, StaffEntity.class);

        //generate public id for staff member
        staffMember.setStaffId(utils.generateRandomID());

        //check if public is  unique
        while(this.staffRepository.existsByStaffId(staffMember.getStaffId())){

            staffMember.setStaffId(utils.generateRandomID());

        }

        //get US senator entity
        USSenatorEntity usSenatorEntity = this.usSenatorService.getUSSenatorEntity(staffDto.getSenator());

        if(usSenatorEntity == null) throw new UsSenatorServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        //set senator entity for staff member
        staffMember.setUsSenatorEntity(usSenatorEntity);

        //save staff member
        StaffEntity storedStaffMember = this.staffRepository.save(staffMember);

        if(staffDto.getPhoneNumberList().size() > 0){


            //get convert phone request to phone number entity and save
            for(PhoneNumberRequest phoneNumber: staffDto.getPhoneNumberList()){

                if(!this.phoneNumberService.phoneNumberExist(phoneNumber.getNumber())){

                    PhoneNumberEntity phoneNumberEntity = modelMapper.map(phoneNumber, PhoneNumberEntity.class);

                    phoneNumberEntity.setPhoneNumberId(utils.generateRandomID());

                    while(this.phoneNumberService.phoneNumberIdExist(phoneNumberEntity.getPhoneNumberId())){

                        phoneNumberEntity.setPhoneNumberId(utils.generateRandomID());
                    }

                    PhoneNumberEntity savedPhoneNumber = this.phoneNumberService.savePhoneNumber(phoneNumberEntity);

                    AssignedNumberEntity assignedNumberEntity = new AssignedNumberEntity();

                    assignedNumberEntity.setAssignmentId(utils.generateRandomID());

                    while(this.assignedNumberService.existByAssignmentId(assignedNumberEntity.getAssignmentId())){

                        assignedNumberEntity.setAssignmentId(utils.generateRandomID());
                    }

                    assignedNumberEntity.setPhoneNumberEntity(savedPhoneNumber);

                    assignedNumberEntity.setStaffEntity(storedStaffMember);

                    //AssignedNumberEntity saveAssignedNumberEntity = this.assignedNumberService.createAssignedNumber(assignedNumberEntity);

                }else{

                    //get existing phone number entity
                    PhoneNumberEntity phoneNumberEntity = this.phoneNumberService.findByNumber(phoneNumber.getNumber());

                    //check if phone number was found
                    if(phoneNumberEntity == null) throw new PhoneNumberServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

                    //create a new assignment entity
                    AssignedNumberEntity assignedNumberEntity = new AssignedNumberEntity();

                    //set phone number
                    assignedNumberEntity.setPhoneNumberEntity(phoneNumberEntity);

                    //set staff member
                    assignedNumberEntity.setStaffEntity(storedStaffMember);

                    //this.assignedNumberService.createAssignedNumber(assignedNumberEntity);

                }
            }
        }


        return new ModelMapper().map(storedStaffMember, StaffDto.class) ;
    }

    @Override
    public StaffDto updateStaffMember(String staffId, StaffDto staffDto) {
        return null;
    }

    @Override
    public StaffDto deleteStaffMember(String staffId) {
        return null;
    }

    @Override
    public StaffDto createStaffMemberForCongressionalRep(StaffDto staffDto) {

        return  null;
    }

    @Override
    public StaffEssentials getStaffEssentials(StaffEntity staffEntity) {

        if(this.entityIsNull(staffEntity))
            throw new StaffServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        StaffEssentials staffEssentials = new StaffEssentials();

        staffEssentials.setTitle(staffEntity.getTitle());
        staffEssentials.setFirstName(staffEntity.getFirstName());
        staffEssentials.setLastName(staffEntity.getLastName());
        staffEssentials.setStaffId(staffEntity.getStaffId());
        staffEssentials.setEmail(staffEntity.getEmail());
        staffEssentials.setPhoneNumberEssentials(
                this.assignedNumberService.getPhoneNumberEssentials(
                        staffEntity.getAssignedNumberEntities()
                )
        );

        return staffEssentials;
    }

    @Override
    public List<StaffEssentials> getStaffEssentials(List<StaffEntity> staffEntities) {

        if(this.entityIsNull(staffEntities))
            throw new StaffServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<StaffEssentials> returnValue = new ArrayList<>();

        for(StaffEntity staffEntity: staffEntities){

            returnValue.add(this.getStaffEssentials(staffEntity));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(StaffEntity staffEntity) {
        return staffEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<StaffEntity> staffEntities) {
        return staffEntities == null;
    }
}
