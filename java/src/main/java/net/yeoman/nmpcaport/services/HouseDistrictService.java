package net.yeoman.nmpcaport.services;

import java.util.List;

import net.yeoman.nmpcaport.entities.HouseDistrictEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictPagination;

public interface HouseDistrictService {


    //delete house district
    void deleteHouseDistrict(String d);

    //update house district
    void updateHouseDistrict(String id, HouseDistrictDetailsRequest houseDistrictDetailsRequest);
    
    //search for district
    List<HouseDistrictEntity> houseDistrictSearch(String name);
    
    
    //pagination
    HouseDistrictPagination getDistrictPageInfo(int pageNo, int limit);
    HouseDistrictPagination getDistrictPageInfoSearch(String name, int startIndex, int endIndex);


    //save house district
    HouseDistrictEntity saveHouseDistrict(HouseDistrictEntity houseDistrictEntity);

    //get house district entities
    HouseDistrictEntity getHouseDistrict(String d);
    List<HouseDistrictEntity> getAllHouseDistrictEntities();

    //generate uniqueId
    HouseDistrictEntity generateUniqueId(HouseDistrictEntity houseDistrictEntity);

    //create house district
    HouseDistrictEntity createHouseDistrict(HouseDistrictDetailsRequest houseDistrictDetailsRequest);
    List<HouseDistrictEntity> createHouseDistrictBulk(HouseDistrictDetailsRequestList houseDistrictDetailsRequestList);

    //get district essentials for dashboards and large pulls
    HouseDistrictEssentialResponse entityToEssentials(HouseDistrictEntity nmHouseDistrictEntity);
    List<HouseDistrictEssentialResponse> entityToEssentials(List<HouseDistrictEntity> houseDistrictEntity);

    //get house districts from site
    HouseDistrictEntity getHouseDistrictsFromSites(SiteEntity siteEntity);
    List<HouseDistrictEntity> getHouseDistrictsFromSites(List<SiteEntity> siteEntities);



     //check for entity is null
     Boolean entityIsNull(HouseDistrictEntity nmHouseDistrictEntity);
     Boolean entityIsNull(List<HouseDistrictEntity> nmHouseDistrictEntities);

     //check if request is null
    Boolean requestIsNull(HouseDistrictDetailsRequest houseDistrictDetailsRequest);
    
    
    //end points
    
    //get request
    HouseDistrictPagination getHouseDistrictPageInfoEndPoint(int pageNo, int limit);
    HouseDistrictPagination getHouseDistrictPageInfoSearchEndPoint(String name, int startIndex, int endIndex);
    
    //post request
    

}
