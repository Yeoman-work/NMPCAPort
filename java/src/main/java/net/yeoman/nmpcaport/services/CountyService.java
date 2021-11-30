package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.CountyEntity;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.shared.dto.ContactDto;

import java.util.List;

public interface CountyService {

    public List<CountyEntity> findAll();
    public CountyEntity findCountyEntity(String countyId);
    public ContactDto findCountyById(String countyId);
    public List<CountyResponse> createCounties(List<String> countiesList);
    public List<CountyResponse> countyResponse();

}
