package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.PhoneNumberServiceException;
import net.yeoman.nmpcaport.io.repositories.PhoneNumberRepository;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.services.PhoneNumberService;
import net.yeoman.nmpcaport.shared.dto.PhoneNumberDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {


    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private Utils utils;


    @Override
    public PhoneNumberEntity getPhoneNumberEntity(String phoneNumberId) {

        return this.phoneNumberRepository.findByPhoneNumberId(phoneNumberId);
    }

    @Override
    public List<PhoneNumberEntity> processBulkPhoneNumbers(List<PhoneNumberDto> phoneNumberRequestList) {

        List<PhoneNumberEntity> returnValue = new ArrayList<>();

        for(PhoneNumberDto phoneNumber: phoneNumberRequestList){

            returnValue.add(this.utils.objectMapper().map(phoneNumber, PhoneNumberEntity.class));
        }
        return returnValue;
    }

    @Override
    public PhoneNumberEntity savedPhoneNumber(PhoneNumberEntity phoneNumberEntity) {

        return this.phoneNumberRepository.save(phoneNumberEntity);
    }

    @Override
    public PhoneNumberEntity createPhoneNumberProcess(PhoneNumberDto phoneNumberDto){

        return null;
    }

    @Override
    public PhoneNumberDto getPhoneNumberDto(String phoneNumberId) {

        PhoneNumberEntity phoneNumberEntity = this.phoneNumberRepository.findByPhoneNumberId(phoneNumberId);

        return new ModelMapper().map(phoneNumberEntity, PhoneNumberDto.class);
    }

    @Override
    public PhoneNumberEntity createPhoneNumber(PhoneNumberEntity phoneNumberEntity) {

        PhoneNumberEntity phoneNumber = this.phoneNumberRepository.save(phoneNumberEntity);

        return phoneNumber;
    }

    @Override
    public PhoneNumberDto deletePhoneNumber(String phoneNumberId) {

        PhoneNumberEntity phoneNumberEntity = this.phoneNumberRepository.findByPhoneNumberId(phoneNumberId);

        return new ModelMapper().map(phoneNumberEntity, PhoneNumberDto.class);
    }

    @Override
    public PhoneNumberDto updatedPhoneNumber(PhoneNumberDto phoneNumberDto, String phoneNumberId) {
        return null;
    }

    @Override
    public Boolean phoneNumberIdExist(String phoneNumberId) {

        return this.phoneNumberRepository.existsByPhoneNumberId(phoneNumberId);
    }

    @Override
    public Boolean phoneNumberExist(String number) {


        return this.phoneNumberRepository.existsByNumber(number);
    }

    @Override
    public PhoneNumberEntity savePhoneNumber(PhoneNumberEntity phoneNumber) {

        return this.phoneNumberRepository.save(phoneNumber);

    }

    @Override
    public PhoneNumberEntity findByNumber(String number) {

        PhoneNumberEntity phoneNumberEntity = this.phoneNumberRepository.findByNumber(number);

        return phoneNumberEntity;
    }

    @Override
    public Boolean entityIsNull(PhoneNumberEntity phoneNumberEntity) {

        return phoneNumberEntity == null;
    }

    @Override
    public Boolean dtoIsNull(PhoneNumberDto phoneNumberDto) {

        return phoneNumberDto == null;
    }

    @Override
    public Boolean requestIsNull(PhoneNumberRequest phoneNumberRequest) {

        return phoneNumberRequest == null;
    }

    @Override
    public Boolean responseIsNull(PhoneNumberResponse phoneNumberResponse) {

        return phoneNumberResponse == null;
    }

    @Override
    public PhoneNumberEntity dtoToEntity(PhoneNumberDto phoneNumberDto) {

        return this.utils.objectMapper().map(phoneNumberDto, PhoneNumberEntity.class);
    }

    @Override
    public List<PhoneNumberEntity> dtoArrayToEntityArray(List<PhoneNumberDto> phoneNumberDtoList) {

        List<PhoneNumberEntity> returnValue = new ArrayList<>();

        for(PhoneNumberDto phoneNumberDto: phoneNumberDtoList){

            PhoneNumberEntity phoneNumberEntity = this.utils.objectMapper().map(phoneNumberDto, PhoneNumberEntity.class);

            phoneNumberEntity.setPhoneNumberId(utils.generateRandomID());

            while(this.phoneNumberIdExist(phoneNumberEntity.getPhoneNumberId())){

                phoneNumberEntity.setPhoneNumberId(utils.generateRandomID());
            }

            PhoneNumberEntity savedPhoneNumberEntity = this.savePhoneNumber(phoneNumberEntity);

            returnValue.add(savedPhoneNumberEntity);

        }

        return returnValue;
    }

    @Override
    public PhoneNumberResponse dtoToResponse(PhoneNumberDto phoneNumberDto) {

        return this.utils.objectMapper().map(phoneNumberDto, PhoneNumberResponse.class);
    }

    @Override
    public List<PhoneNumberResponse> dtoArrayToResponseArray(List<PhoneNumberDto> phoneNumberDtoList) {

        List<PhoneNumberResponse> returnValue = new ArrayList<>();

        for(PhoneNumberDto phoneNumber: phoneNumberDtoList){

            returnValue.add(this.utils.objectMapper().map(phoneNumber, PhoneNumberResponse.class));
        }

        return returnValue;
    }

    @Override
    public PhoneNumberRequest dtoToRequest(PhoneNumberDto phoneNumberDto) {

        return this.utils.objectMapper().map(phoneNumberDto, PhoneNumberRequest.class) ;
    }

    @Override
    public List<PhoneNumberRequest> dtoArrayToRequestList(List<PhoneNumberDto> phoneNumberDtoList) {

        List<PhoneNumberRequest> returnValue = new ArrayList<>();

        for(PhoneNumberDto phoneNumberDto: phoneNumberDtoList){

            returnValue.add(this.utils.objectMapper().map(phoneNumberDto, PhoneNumberRequest.class));
        }

        return returnValue;
    }

    @Override
    public PhoneNumberDto entityToDto(PhoneNumberEntity phoneNumberEntity) {

        return this.utils.objectMapper().map(phoneNumberEntity, PhoneNumberDto.class);
    }

    @Override
    public List<PhoneNumberDto> entityArrayToDtoArray(List<PhoneNumberEntity> phoneNumberEntityList) {

        List<PhoneNumberDto> returnValue = new ArrayList<>();

        for(PhoneNumberEntity phoneNumber: phoneNumberEntityList){

            returnValue.add(this.utils.objectMapper().map(phoneNumber, PhoneNumberDto.class));
        }
        return null;
    }

    @Override
    public PhoneNumberDto responseToDto(PhoneNumberRequest phoneNumberRequest) {

        return this.utils.objectMapper().map(phoneNumberRequest, PhoneNumberDto.class);
    }

    @Override
    public List<PhoneNumberDto> responseArrayToDtoArray(List<PhoneNumberResponse> phoneNumberResponses) {

        List<PhoneNumberDto> returnValue = new ArrayList<>();

        for(PhoneNumberResponse phoneNumber: phoneNumberResponses){

            returnValue.add(this.utils.objectMapper().map(phoneNumber, PhoneNumberDto.class));
        }

        return returnValue;
    }

    @Override
    public PhoneNumberDto requestToDto(PhoneNumberRequest phoneNumberRequest) {

        return this.utils.objectMapper().map(phoneNumberRequest, PhoneNumberDto.class);
    }

    @Override
    public List<PhoneNumberDto> requestArrayToDto(List<PhoneNumberRequest> phoneNumberRequestList) {

        List<PhoneNumberDto> returnValue = new ArrayList<>();

        for(PhoneNumberRequest phoneNumber: phoneNumberRequestList){

            returnValue.add(this.utils.objectMapper().map(phoneNumber, PhoneNumberDto.class));
        }
        return returnValue;
    }
}
