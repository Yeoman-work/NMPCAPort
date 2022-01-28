package net.yeoman.nmpcaport.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.yeoman.nmpcaport.entities.CityEntity;
import net.yeoman.nmpcaport.errormessages.CityErrorFormatMessages;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.CityServiceException;
import net.yeoman.nmpcaport.io.repositories.CityRepository;
import net.yeoman.nmpcaport.io.request.city.CityDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.city.CityEssentials;
import net.yeoman.nmpcaport.services.CityService;
import net.yeoman.nmpcaport.shared.utils.Utils;

@Service
public class CityServiceImpl implements CityService {

    
    private final CityRepository cityRepository;
    private final Utils utils;
    
    CityServiceImpl(CityRepository cityRepository,
    				Utils utils
	){
    	this.cityRepository = cityRepository;
    	this.utils = utils;
    }

    
  //get city entities
    @Override
    public List<CityEntity> findAllCities() {

        return this.cityRepository.findAll();
    }



    @Override
    public CityEntity findCity(String cityId) {

        return this.cityRepository.findByCityId(cityId);
    }

   
    
  //convert entity to essentials
    

    @Override
    public CityEssentials entityToEssentials(CityEntity cityEntity) {

        if(this.entityIsNull(cityEntity))
            throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(cityEntity, CityEssentials.class);
    }

    @Override
    public List<CityEssentials> entityToEssentials(List<CityEntity> cityEntityList) {

        if(this.entityIsNull(cityEntityList))
            throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CityEssentials> returnValue = new ArrayList<>();

        for(CityEntity city: cityEntityList){

            returnValue.add(this.entityToEssentials(city));
        }

        return returnValue;
    }

    
    //check if an entity is null
    @Override
    public Boolean entityIsNull(CityEntity cityEntity) {
        return cityEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<CityEntity> cityEntityList) {
        return cityEntityList == null;
    }

	


	@Override
	public CityEntity generateUniqueId(CityEntity cityEntity) {
		
		if(this.entityIsNull(cityEntity)) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		cityEntity.setCityId(this.utils.generateRandomID());
		
		while(this.cityRepository.existsByCityId(cityEntity.getCityId())) {
			
			cityEntity.setCityId(this.utils.generateRandomID());
		}
		
		
		return cityEntity;
	}
	//update city

	@Override
	public CityEntity updateCityEntity(String cityId, CityDetailsRequestModel cityDetialsRequestModel) {
		
		if(cityId == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		if(cityDetialsRequestModel == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		CityEntity cityEntity = this.findCity(cityId);
		
		if(!cityEntity.getName().equals(cityDetialsRequestModel.getName())) {
			
			cityEntity.setName(cityDetialsRequestModel.getName());
		}
		
		return cityEntity;
	}

	//create city
	@Override
	public CityEntity createCity(String cityName) {
		
		if(cityName == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		if(this.cityRepository.existsByName(cityName)) throw new CityServiceException(CityErrorFormatMessages.CITY_ALREADY_EXIST.getFormatCityErrorMessage(cityName));
		
		CityEntity cityEntity = this.generateUniqueId(new CityEntity());
		
		cityEntity.setName(cityName);
	
		return this.saveEntity(cityEntity);
	}

	@Override
	public List<CityEntity> createCities(List<String> cityNames) {
		
		if(cityNames == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		List<CityEntity> returnValue = new ArrayList<>();
		
		for(String cityName: cityNames) {
			
			returnValue.add(this.createCity(cityName));
		}
		
		return returnValue;
	}
	
	
	 //saved city entity

	@Override
	public CityEntity saveEntity(CityEntity cityEntity) {
		
		return this.cityRepository.save(cityEntity);
	}

	
	@Override
	public List<CityEntity> saveEntites(List<CityEntity> cityEntities) {
		
		if(this.entityIsNull(cityEntities)) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		List<CityEntity> returnValue = new ArrayList<>();
		
		for(CityEntity city: cityEntities ) {
			
			returnValue.add(this.saveEntity(city));
		}
		
		return returnValue;
	}

	
	//pagination
	@Override
	public Page<CityEntity> findByPagination(int pageNo, int size) {
		
		if (pageNo > 0) pageNo -= 1;
		
		PageRequest pageableRequest = PageRequest.of(pageNo, size);
		
		Page<CityEntity> cityPage = this.cityRepository.findAll(pageableRequest);
		
		return cityPage;
	}
	
	
	//total pages

	@Override
	public int getTotalPages(Page<CityEntity> cityPage) {
	
		if(cityPage == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		return cityPage.getTotalPages();
	}
	
	
	//total items
	
	@Override
	public Long getTotalItems(Page<CityEntity> cityPage) {
		
		if(cityPage == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		return cityPage.getTotalElements();
	}

	
	
	
	//end points
	
	//getMappings
	@Override
	public List<CityEssentials> getAllCityEssentials(int pageNo, int size) {
		
		List<CityEntity> cityEntites = this.findByPagination(pageNo, size).getContent();
		
		return this.entityToEssentials(cityEntites);
	}
	
	
	
	//postMappings

	//create cityNames
	@Override
	public List<CityEssentials> createCitiesProcess(List<String> cityNames) {
	  
		if(cityNames == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		List<CityEntity> newCities = this.createCities(cityNames);
		
		return this.entityToEssentials(newCities);
	}

	
	//create one city
	@Override
	public CityEssentials createCityProcess(String cityName) {
		
		if(cityName == null) throw new CityServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		CityEntity city = this.createCity(cityName);
		
		return this.entityToEssentials(city);
	}

//putMapping
	
	@Override
	public CityEssentials updateCityProcess(String cityId, CityDetailsRequestModel cityDetailsRequestModel) {
		
		CityEntity cityEntity = this.updateCityEntity(cityId, cityDetailsRequestModel);
		
		if(cityEntity == null) throw new CityServiceException(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage());
		
		
		return this.entityToEssentials(cityEntity);
	}

	

}
