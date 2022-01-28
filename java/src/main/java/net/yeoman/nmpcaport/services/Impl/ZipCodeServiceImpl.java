package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.ZipCodeServiceException;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeEssentials;
import net.yeoman.nmpcaport.services.ZipCodeService;
import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.io.repositories.ZipCodeRepository;
import net.yeoman.nmpcaport.io.request.zipCode.ZipCodeDetailsRequestModel;
import net.yeoman.nmpcaport.io.request.zipCode.ZipCodeRequestList;
import net.yeoman.nmpcaport.shared.dto.ZipCodeDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {

    
    private final ZipCodeRepository zipCodeRepository;
    private final Utils utils;
    
    ZipCodeServiceImpl(ZipCodeRepository zipCodeRepository,
    		           Utils utils
	){
    	this.zipCodeRepository = zipCodeRepository;
    	this.utils = utils;
    }

    
    // zipCode essentials
   
    @Override
    public ZipCodeEssentials entityToEssentials(ZipCodeEntity zipCodeEntity) {

        if(entityIsNull(zipCodeEntity))
            throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        ZipCodeEssentials zipCodeEssentials = new ZipCodeEssentials();

        zipCodeEssentials.setName(zipCodeEntity.getName());
        zipCodeEssentials.setZipCodeId(zipCodeEntity.getZipCodeId());

        return zipCodeEssentials;
    }

    @Override
    public List<ZipCodeEssentials> entityToEssentials(List<ZipCodeEntity> zipCodeEntities) {

        if(this.entityIsNull(zipCodeEntities))
            throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<ZipCodeEssentials> returnValue = new ArrayList<>();

        for(ZipCodeEntity zipCode: zipCodeEntities){

            returnValue.add(this.entityToEssentials(zipCode));
        }

        return returnValue;
    }

    
  //check if the zipCode entity is null
    @Override
    public Boolean entityIsNull(ZipCodeEntity zipCodeEntity) {
        return  zipCodeEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<ZipCodeEntity> zipCodeEntities) {
        return zipCodeEntities == null;
    }

    
    //get zipCode code entity
	@Override
	public List<ZipCodeEntity> findAllZipCodeEntities() {
		
		return this.zipCodeRepository.findAll();
	}

	
	@Override
	public ZipCodeEntity getZipCodeEntity(String zipCodeId) {
		
		return this.zipCodeRepository.findByZipCodeId(zipCodeId);
	}

	
	 //create zipCode Entities
	@Override
	public ZipCodeEntity createZipCode(ZipCodeDetailsRequestModel zipCodeDetialsRequestModel) {
		
		if(zipCodeDetialsRequestModel == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		ZipCodeEntity zipCodeEntity = this.generateUniqueId(new ZipCodeEntity());
		
		zipCodeEntity.setName(zipCodeDetialsRequestModel.getName());
		
		return zipCodeEntity;
	}


	@Override
	public List<ZipCodeEntity> createZipCodes(ZipCodeRequestList zipCodeRequestList) {
		
		if(zipCodeRequestList == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		List<ZipCodeEntity> returnValue = new ArrayList<>();
		
		for(String request: zipCodeRequestList.getZipCodes()) {
			
			ZipCodeEntity zipCodeEntity = this.generateUniqueId(new ZipCodeEntity());
			
			zipCodeEntity.setName(request);
			
			returnValue.add(this.saveZipCodeEntity(zipCodeEntity));
		}
		
		return returnValue;
	}


	@Override
	public ZipCodeEntity generateUniqueId(ZipCodeEntity zipCodeEntity) {
		
		if(this.entityIsNull(zipCodeEntity)) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		zipCodeEntity.setZipCodeId(utils.generateRandomID());
		
		while(this.zipCodeRepository.existsByZipCodeId(zipCodeEntity.getZipCodeId())) {
			
			zipCodeEntity.setZipCodeId(utils.generateRandomID());
		}
		
		return zipCodeEntity;
	}


	 //save the zipEntity
	@Override
	public ZipCodeEntity saveZipCodeEntity(ZipCodeEntity zipCodeEntity) {
		
		if(this.entityIsNull(zipCodeEntity)) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		
		return this.zipCodeRepository.save(zipCodeEntity);
	}


	@Override
	public List<ZipCodeEntity> saveZipCodeEnitties(List<ZipCodeEntity> zipCodeEntities) {
		
		if(this.entityIsNull(zipCodeEntities)) throw  new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		List<ZipCodeEntity> returnValue = new ArrayList<>();
		
		for(ZipCodeEntity zipCode: zipCodeEntities) {
			
			returnValue.add(this.saveZipCodeEntity(zipCode));
		}
		
		return returnValue;
	}
	
	
    //end point functions
    
	
    // getMappings 
    
	
    //path = zipCodes

  @Override
	public List<ZipCodeEssentials> getZipcodesForDropDowns() {
		
		List<ZipCodeEntity> zipCodeEntities = this.findAllZipCodeEntities();
		
		return this.entityToEssentials(zipCodeEntities);
	}

  
  	//postMapping

	@Override
	public List<ZipCodeEssentials> createZipCodesFromEndPoint(ZipCodeRequestList zipCodeRequestList) {
		
		if(zipCodeRequestList == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		List<ZipCodeEntity> zipCodeentities = this.createZipCodes(zipCodeRequestList);
		
		
		return this.entityToEssentials(zipCodeentities);
	}
	
	

	@Override
	public ZipCodeEssentials createZipCodeEntityFromEndPoint(ZipCodeDetailsRequestModel zipCodeDetailsRequestModel) {
		
		if(zipCodeDetailsRequestModel == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		ZipCodeEntity zipCode = this.createZipCode(zipCodeDetailsRequestModel);
			
		return this.entityToEssentials(zipCode);
	}
   

}
