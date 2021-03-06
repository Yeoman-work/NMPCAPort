package net.yeoman.nmpcaport.services;

import java.util.List;

import org.springframework.data.domain.Page;

import net.yeoman.nmpcaport.entities.CityEntity;
import net.yeoman.nmpcaport.io.request.city.CityDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.city.CityEssentials;
import net.yeoman.nmpcaport.io.response.city.CityEssentialsPagination;

public interface CityService {


	
	//get city entities
    CityEntity findCity(String cityId);
    List<CityEntity> findAllCities();

    //query search by name
    List<CityEntity> searchForCityEntity(String name);

    //convert entity to essentials
    CityEssentials entityToEssentials(CityEntity cityEntity);
    List<CityEssentials> entityToEssentials(List<CityEntity> cityEntityList);

    //update
    CityEntity updateCityEntity(String cityId, CityDetailsRequestModel cityDetialsRequestModel);
    
    //create city
    CityEntity generateUniqueId(CityEntity cityEntity);
    CityEntity createCity(String cityName);
    List<CityEntity> createCities(List<String> cityNames);
    
    
    //pagination
    
    //get page
    Page<CityEntity> getCityPageInfo(int pageNo, int size);
    
    
    //Pagination
    CityEssentialsPagination getCityPageInfoEssentials(int pageNo, int limit);
    CityEssentialsPagination getCityPageInfoEssentialsSearch(String name, int startIndex, int endIndex);
    
    //get page Number
    int getTotalPages(Page<CityEntity> cityPage);
    
    //get total number of items
    Long getTotalItems(Page<CityEntity> cityPage);
    
    //previous page
    Boolean hasPrevious(Page<CityEntity> cityPage);
    Boolean hasNext(Page<CityEntity> cityPage);
    
    
    //saved city entity
    CityEntity saveEntity(CityEntity cityEntity);
    List<CityEntity> saveEntites(List<CityEntity> cityEntity);
    
    
    
    //check if an entity is null
    Boolean entityIsNull(CityEntity cityEntity);
    Boolean entityIsNull(List<CityEntity> cityEntityList);
   
    
    //end points
    
    //getMapping
    CityEssentialsPagination getAllCityEssentials(int pageNo, int size);
    CityEssentialsPagination getAllCityEssentialsSearch(String name, int startIndex, int endIndex);
    
    //post mapping
    List<CityEssentials> createCitiesProcess(List<String> cityNames);
    CityEssentials createCityProcess(String cityName);
    
    //putMapping
    CityEssentials updateCityProcess(String cityId, CityDetailsRequestModel cityDetailsRequestModel);

}
