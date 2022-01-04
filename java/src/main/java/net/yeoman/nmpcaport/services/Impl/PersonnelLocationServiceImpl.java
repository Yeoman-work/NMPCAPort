package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.services.PersonnelLocationService;
import net.yeoman.nmpcaport.entities.PersonnelLocationEntity;
import net.yeoman.nmpcaport.io.repositories.PersonnelLocationRepository;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonnelLocationServiceImpl implements PersonnelLocationService {

    @Autowired
    private PersonnelLocationRepository personnelLocationRepository;

    @Autowired
    private Utils utils;



    @Override
    public PersonnelLocationEntity getPersonnelLocation(String publicId) {

        return this.personnelLocationRepository.findByPublicId(publicId);
    }

    @Override
    public PersonnelLocationEntity createPersonnelLocation(PersonnelLocationEntity personnelLocationEntity) {

        return this.personnelLocationRepository.save(personnelLocationEntity) ;
    }

    @Override
    public PersonnelLocationEntity updatePersonnelLocation(String publicId, PersonnelLocationEntity personnelLocationEntity) {
        return null;
    }

    @Override
    public PersonnelLocationEntity deletePersonnelLocation(String publicId) {
        return null;
    }
}
