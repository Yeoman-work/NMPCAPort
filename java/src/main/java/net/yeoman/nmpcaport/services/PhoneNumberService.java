package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.io.request.PhoneNumberRequest.PhoneNumberRequest;
import net.yeoman.nmpcaport.io.response.phoneNumber.PhoneNumberResponse;
import net.yeoman.nmpcaport.shared.dto.PhoneNumberDto;

import java.util.List;

public interface PhoneNumberService {

    public PhoneNumberEntity getPhoneNumberEntity(String phoneNumberId);
    public List<PhoneNumberEntity> processBulkPhoneNumbers(List<PhoneNumberDto> phoneNumberDtoList);
    public PhoneNumberEntity savedPhoneNumber(PhoneNumberEntity phoneNumberEntity);
    public PhoneNumberEntity createPhoneNumberProcess(PhoneNumberDto phoneNumberDto);
    public PhoneNumberDto getPhoneNumberDto(String phoneNumberId);
    public PhoneNumberEntity createPhoneNumber(PhoneNumberEntity phoneNumberEntity);
    public PhoneNumberDto deletePhoneNumber(String phoneNumberId);
    public PhoneNumberDto updatedPhoneNumber(PhoneNumberDto phoneNumberDto, String phoneNumberId);
    public Boolean phoneNumberIdExist(String phoneNumberId);
    public Boolean phoneNumberExist(String number);
    public PhoneNumberEntity savePhoneNumber(PhoneNumberEntity phoneNumber);
    public PhoneNumberEntity findByNumber(String number);
    public Boolean entityIsNull(PhoneNumberEntity phoneNumberEntity);
    public Boolean dtoIsNull(PhoneNumberDto phoneNumberDto);
    public Boolean requestIsNull(PhoneNumberRequest phoneNumberRequest);
    public Boolean responseIsNull(PhoneNumberResponse phoneNumberResponse);
    public PhoneNumberEntity dtoToEntity(PhoneNumberDto phoneNumberDto);
    public List<PhoneNumberEntity> dtoArrayToEntityArray(List<PhoneNumberDto> phoneNumberDtoList);
    public PhoneNumberResponse dtoToResponse(PhoneNumberDto phoneNumberDto);
    public List<PhoneNumberResponse> dtoArrayToResponseArray(List<PhoneNumberDto> phoneNumberDtoList);
    public PhoneNumberRequest dtoToRequest(PhoneNumberDto phoneNumberDto);
    public List<PhoneNumberRequest> dtoArrayToRequestList(List<PhoneNumberDto> phoneNumberDtoList);
    public PhoneNumberDto entityToDto(PhoneNumberEntity phoneNumberEntity);
    public List<PhoneNumberDto> entityArrayToDtoArray(List<PhoneNumberEntity> phoneNumberEntityList);
    public PhoneNumberDto responseToDto(PhoneNumberRequest phoneNumberRequest);
    public List<PhoneNumberDto> responseArrayToDtoArray(List<PhoneNumberResponse> phoneNumberResponses);
    public PhoneNumberDto requestToDto(PhoneNumberRequest phoneNumberRequest);
    public List<PhoneNumberDto> requestArrayToDto(List<PhoneNumberRequest> phoneNumberRequestList);

}
