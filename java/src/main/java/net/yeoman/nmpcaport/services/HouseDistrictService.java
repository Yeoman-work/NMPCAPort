package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.HouseDistrictEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
import net.yeoman.nmpcaport.shared.dto.NMHouseDistrictDto;

import java.util.List;

public interface HouseDistrictService {


    //delete house district
    public void deleteHouseDistrict(String publicId);

    //update house district
    public void updateHouseDistrict(String publicId, HouseDistrictDetailsRequest houseDistrictDetailsRequest);

    //get request to entity
    public HouseDistrictEntity convertRequestToEntity(HouseDistrictDetailsRequest houseDistrictDetailsRequest);

    //save house district
    public HouseDistrictEntity saveHouseDistrict(HouseDistrictEntity houseDistrictEntity);

    //get house district entities
    public HouseDistrictEntity getHouseDistrict(String publicId);
    public List<HouseDistrictEntity> getAllHouseDistrictEntities();

    //generate uniqueId
    public HouseDistrictEntity generateUniqueId(HouseDistrictEntity houseDistrictEntity);

    //create house district
    public HouseDistrictEntity createHouseDistrict(HouseDistrictDetailsRequest houseDistrictDetailsRequest);

    //get district essentials for dashboards and large pulls
    public HouseDistrictEssentialResponse entityToEssentials(HouseDistrictEntity nmHouseDistrictEntity);
    public List<HouseDistrictEssentialResponse> entityToEssentials(List<HouseDistrictEntity> houseDistrictEntity);

    //get house districts from site
    public HouseDistrictEntity getHouseDistrictsFromSites(SiteEntity siteEntity);
    public List<HouseDistrictEntity> getHouseDistrictsFromSites(List<SiteEntity> siteEntities);



     //check for entity is null
     public Boolean entityIsNull(HouseDistrictEntity nmHouseDistrictEntity);
     public Boolean entityIsNull(List<HouseDistrictEntity> nmHouseDistrictEntities);

     //check if request is null
    public Boolean requestIsNull(HouseDistrictDetailsRequest houseDistrictDetailsRequest);

}
