package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.HouseDistrictEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.HouseDistrictServiceException;
import net.yeoman.nmpcaport.io.repositories.HouseDistrictRepository;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictNestedResponse;
import net.yeoman.nmpcaport.services.HouseDistrictService;
import net.yeoman.nmpcaport.shared.dto.NMHouseDistrictDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseDistrictServiceImpl implements HouseDistrictService {



    private final HouseDistrictRepository houseDistrictRepository;

    private final Utils utils;

    public HouseDistrictServiceImpl(HouseDistrictRepository houseCommitteeRepository,
                                    Utils utils
                                    ){

        this.houseDistrictRepository = houseCommitteeRepository;
        this.utils = utils;
    }


    @Override
    public List<HouseDistrictEntity> getAllHouseDistrictEntities() {

        return this.houseDistrictRepository.findAll();
    }

    @Override
    public HouseDistrictEssentialResponse entityToEssentials(HouseDistrictEntity nmHouseDistrictEntity) {

        if(this.entityIsNull(nmHouseDistrictEntity))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HouseDistrictEssentialResponse nmHouseDistrictEssentialResponse = new HouseDistrictEssentialResponse();

        nmHouseDistrictEssentialResponse.setHouseDistrictId(nmHouseDistrictEntity.getHouseDistrictId());
        nmHouseDistrictEssentialResponse.setName(nmHouseDistrictEntity.getName());

        return nmHouseDistrictEssentialResponse;
    }

    @Override
    public List<HouseDistrictEssentialResponse> entityToEssentials(List<HouseDistrictEntity> houseDistrictEntities) {

        if(this.entityIsNull(houseDistrictEntities))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HouseDistrictEssentialResponse> returnValue = new ArrayList<>();

        for(HouseDistrictEntity houseDistrictEntity: houseDistrictEntities){

            returnValue.add(this.entityToEssentials(houseDistrictEntity));
        }
        return returnValue;
    }



    @Override
    public Boolean entityIsNull(HouseDistrictEntity nmHouseDistrictEntity) {
        return nmHouseDistrictEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<HouseDistrictEntity> nmHouseDistrictEntities) {
        return nmHouseDistrictEntities == null;
    }






}
