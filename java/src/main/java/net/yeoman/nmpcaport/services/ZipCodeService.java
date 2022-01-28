package net.yeoman.nmpcaport.services;

import java.util.List;

import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.request.zipCode.ZipCodeDetailsRequestModel;
import net.yeoman.nmpcaport.io.request.zipCode.ZipCodeRequestList;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeEssentials;

public interface ZipCodeService {

	
	
	//get zipCode code entity
    List<ZipCodeEntity> findAllZipCodeEntities();
    ZipCodeEntity getZipCodeEntity(String zipCodeId);
       
    // zipCode essentials
    ZipCodeEssentials entityToEssentials(ZipCodeEntity zipCodeEntity);
    List<ZipCodeEssentials> entityToEssentials(List<ZipCodeEntity> zipCodeEntities);
    
    //check if the zipCode entity is null
    Boolean entityIsNull(ZipCodeEntity zipCodeEntity);
    Boolean entityIsNull(List<ZipCodeEntity> zipCodeEntities);
 
    
   
    //create zipCode Entities
    ZipCodeEntity generateUniqueId(ZipCodeEntity zipCodeEntity);
    ZipCodeEntity createZipCode(ZipCodeDetailsRequestModel zipCodeDetialsRequestModel);
    List<ZipCodeEntity> createZipCodes(ZipCodeRequestList zipCodeRequestList);
    
    //save the zipEntity
    ZipCodeEntity saveZipCodeEntity(ZipCodeEntity zipCodeEntity);
    List<ZipCodeEntity> saveZipCodeEnitties(List<ZipCodeEntity> zipCodeEntities);
    
    
    //end point functions
    
    // getMappings 
    
    //path = zipCodes
    List<ZipCodeEssentials> getZipcodesForDropDowns();
    
    
    //postMapping
    List<ZipCodeEssentials> createZipCodesFromEndPoint(ZipCodeRequestList zipCodeRequestList);
    ZipCodeEssentials createZipCodeEntityFromEndPoint(ZipCodeDetailsRequestModel zipCodeDetailsRequestModel);
    
   
}
