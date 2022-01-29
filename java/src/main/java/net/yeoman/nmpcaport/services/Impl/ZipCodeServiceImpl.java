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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	
	
	@Override
	public List<ZipCodeEntity> zipCodeSearchContaining(String name) {
		
		if(name == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		return this.zipCodeRepository.findByNameContaining(name);
	}

	
//pagination
	@Override
	public Page<ZipCodeEntity> getPageInfo(int pageNo, int limit) {
		
		if(pageNo > 0) pageNo -= 1;
		
		PageRequest pageableRequest = PageRequest.of(pageNo, limit);
		
		Page<ZipCodeEntity> zipCodePage = this.zipCodeRepository.findAll(pageableRequest);
		
		return zipCodePage;
	}


	@Override
	public List<ZipCodeEntity> getPageOfEntities(Page<ZipCodeEntity> pageEntity) {
		
		if(pageEntity == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		
		return pageEntity.getContent();
	}


	@Override
	public int getTotalNumberOfPagesA(Page<ZipCodeEntity> pageEntity) {
		
		if(pageEntity == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		return pageEntity.getTotalPages();
	}


	@Override
	public Long getTotalItems(Page<ZipCodeEntity> pageEntity) {
		
		if(pageEntity == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		return pageEntity.getTotalElements();
	}


	@Override
	public Boolean hasPrevious(Page<ZipCodeEntity> pageEntity) {
		
		if(pageEntity == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage()); 
		
		return pageEntity.hasPrevious();
	}


	@Override
	public Boolean hasNext(Page<ZipCodeEntity> pageEntity) {
		
		if(pageEntity == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		return pageEntity.hasNext();
	}


	
	

	
    //end point functions
    
	
    // getMappings 
    
	
    //path = zipCodes

    @Override
	public List<ZipCodeEssentials> getZipcodesForDropDowns(int pageNo, int limit) {
		
		Page<ZipCodeEntity> zipCodePage = this.getPageInfo(pageNo, limit);
		
		List<ZipCodeEntity> zipCodeEntities = this.getPageOfEntities(zipCodePage);
		
		return this.entityToEssentials(zipCodeEntities);
  	}
  
    @Override
	public List<ZipCodeEssentials> getZipCodeSearch(String zipCodeName, int StartIndex, int endIndex) {
		
    	if(zipCodeName == null) throw new ZipCodeServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
    	
    	List<ZipCodeEntity> zipCodes = this.zipCodeSearchContaining(zipCodeName);
    	
    	int limit = zipCodes.size() - endIndex;
    	
    	if(limit <= 0) {
    		
    		limit = zipCodes.size() - 1;
    	}else {
    		
    		limit = endIndex;
    	}
    	
    	List<ZipCodeEssentials> returnValue = new ArrayList<>();
    	
    	for(int i = 0; i <= limit; i++) {
    	
    		
    		if(zipCodes.get(i) == null) break;
    		returnValue.add(this.entityToEssentials(zipCodes.get(i)));
    	}
    	
		return returnValue;
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
