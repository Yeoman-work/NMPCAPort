package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.CountyEntity;
import net.yeoman.nmpcaport.io.response.County.CountyResponse;
import net.yeoman.nmpcaport.repositories.CountyRepository;
import net.yeoman.nmpcaport.services.CountyService;
import net.yeoman.nmpcaport.shared.dto.ContactDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountyServiceImpl implements CountyService {

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private Utils utils;

    @Override
    public List<CountyEntity> findAll() {
        return this.countyRepository.findAll();
    }

    @Override
    public CountyEntity findCountyEntity(String countyId) {

        return this.countyRepository.findByCountyId(countyId);
    }

    @Override
    public ContactDto findCountyById(String countyId) {
        return null;
    }

    @Override
    public List<CountyResponse> createCounties(List<String> countiesList) {
        List<CountyResponse> counties = new ArrayList<>();

        for(String county: countiesList){

            if(!this.countyRepository.existsByName(county)){

                CountyEntity countyEntity = new CountyEntity();

                countyEntity.setCountyId(utils.generateRandomID());
                countyEntity.setName(county);

                CountyEntity storedCounty = this.countyRepository.save(countyEntity);

                counties.add(new ModelMapper().map(storedCounty, CountyResponse.class));
            }
        }
        return counties;
    }

    @Override
    public List<CountyResponse> countyResponse() {
        List<CountyResponse> returnValue = new ArrayList<>();

        List<CountyEntity> counties =this.countyRepository.findAll();

        for(CountyEntity county: counties){

            returnValue.add(new ModelMapper().map(county, CountyResponse.class));
        }

        return returnValue;
    }


}
