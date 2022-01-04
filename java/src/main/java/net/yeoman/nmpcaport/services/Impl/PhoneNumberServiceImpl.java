package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import net.yeoman.nmpcaport.io.repositories.PhoneNumberRepository;
import net.yeoman.nmpcaport.services.PhoneNumberService;
import net.yeoman.nmpcaport.shared.dto.PhoneNumberDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {


    @Autowired
    private PhoneNumberRepository phoneNumberRepository;


    @Override
    public PhoneNumberEntity getPhoneNumberEntity(String phoneNumberId) {

        return this.phoneNumberRepository.findByPhoneNumberId(phoneNumberId);
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

        PhoneNumberEntity phoneNumberEntity = this.phoneNumberRepository.save(phoneNumber);

        return phoneNumberEntity;
    }

    @Override
    public PhoneNumberEntity findByNumber(String number) {

        PhoneNumberEntity phoneNumberEntity = this.phoneNumberRepository.findByNumber(number);

        return phoneNumberEntity;
    }
}
