package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.PhoneNumberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhoneNumberRepository extends CrudRepository<PhoneNumberEntity, Long> {

    List<PhoneNumberEntity> findAll();

    PhoneNumberEntity findByPhoneNumberId(String phoneNumberId);

    PhoneNumberEntity findByNumber(String number);

    Boolean existsByPhoneNumberId(String phoneNumberId);

    Boolean existsByNumber(String number);

}
