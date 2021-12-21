package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.NMHouseDistrictCountyEntity;

public interface StateRepCountyService {

    public NMHouseDistrictCountyEntity getStateRepCountyEntity(String stateRepCountyId);
    public NMHouseDistrictCountyEntity createStateRepCountyEntity(NMHouseDistrictCountyEntity stateRepCountyEntity);
    public NMHouseDistrictCountyEntity deleteStateRepCounty(String stateRepCountyId);

}
