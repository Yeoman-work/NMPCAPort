package net.yeoman.nmpcaport.services;

import java.util.List;

import org.springframework.data.domain.Page;

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
    
    //pagination
    Page<ZipCodeEntity> getPageInfo(int pageNo, int limit);
    
    //get zipCode entity list
    List<ZipCodeEntity> getPageOfEntities(Page<ZipCodeEntity> pageEntity);
    
    
    //number of page
    int getTotalNumberOfPagesA(Page<ZipCodeEntity> pageEntity);
    
    //getting total number of items
    Long getTotalItems(Page<ZipCodeEntity> pageEntity);
    
    //previous page
    Boolean hasPrevious(Page<ZipCodeEntity> pageEntity);
    
    //next page
    Boolean hasNext(Page<ZipCodeEntity> pageEntity);
    
    
    //end point functions
    
    // getMappings 
    
    List<ZipCodeEssentials> getZipcodesForDropDowns(int pageNo, int limit);
    
    
    //postMapping
    List<ZipCodeEssentials> createZipCodesFromEndPoint(ZipCodeRequestList zipCodeRequestList);
    ZipCodeEssentials createZipCodeEntityFromEndPoint(ZipCodeDetailsRequestModel zipCodeDetailsRequestModel);
    
   
}
