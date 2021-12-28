package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.PersonnelLocationEntity;

public interface PersonnelLocationService {

    public PersonnelLocationEntity getPersonnelLocation(String publicId);
    public PersonnelLocationEntity createPersonnelLocation(PersonnelLocationEntity personnelLocationEntity);
    public PersonnelLocationEntity updatePersonnelLocation(String publicId, PersonnelLocationEntity personnelLocationEntity);
    public PersonnelLocationEntity deletePersonnelLocation(String publicId);
}
