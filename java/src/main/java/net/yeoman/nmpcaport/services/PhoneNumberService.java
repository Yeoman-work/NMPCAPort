package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.shared.dto.PhoneNumberDto;

public interface PhoneNumberService {

    public PhoneNumberEntity getPhoneNumberEntity(String phoneNumberId);
    public PhoneNumberEntity createPhoneNumberProcess(PhoneNumberDto phoneNumberDto);
    public PhoneNumberDto getPhoneNumberDto(String phoneNumberId);
    public PhoneNumberEntity createPhoneNumber(PhoneNumberEntity phoneNumberEntity);
    public PhoneNumberDto deletePhoneNumber(String phoneNumberId);
    public PhoneNumberDto updatedPhoneNumber(PhoneNumberDto phoneNumberDto, String phoneNumberId);
    public Boolean phoneNumberIdExist(String phoneNumberId);
    public Boolean phoneNumberExist(String number);
    public PhoneNumberEntity savePhoneNumber(PhoneNumberEntity phoneNumber);
    public PhoneNumberEntity findByNumber(String number);

}
