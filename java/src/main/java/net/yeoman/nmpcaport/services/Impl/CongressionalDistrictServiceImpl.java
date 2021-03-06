package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.CongressionalDistrictEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.CongressionalDistrictServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.io.repositories.CongressionalDistrictRepository;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictEssentialsResponse;
import net.yeoman.nmpcaport.io.response.congressionalDistrict.CongressionalDistrictNestedResponse;
import net.yeoman.nmpcaport.services.CongressionalDistrictService;
import net.yeoman.nmpcaport.shared.dto.CongressionalDistrictDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CongressionalDistrictServiceImpl implements CongressionalDistrictService {


    private final CongressionalDistrictRepository congressionalDistrictRepository;
    private final Utils utils;


    public CongressionalDistrictServiceImpl(CongressionalDistrictRepository congressionalDistrictRepository,
                                            Utils utils
    ){
        this.congressionalDistrictRepository = congressionalDistrictRepository;
        this.utils = utils;
    }

    @Override
    public CongressionalDistrictDto getCongressionalDistrict(String congressionalDistrictId) {
        return null;
    }

    @Override
    public CongressionalDistrictDto createCongressionalDistrict(CongressionalDistrictDto congressionalDistrictDto) {

        if(!utils.isCongressionalDistrict(congressionalDistrictDto.getName())) throw new CongressionalDistrictServiceException(ErrorMessages.DISTRICT_NOT_VALID.getErrorMessage());

        if(this.congressionalDistrictRepository.existsByName(congressionalDistrictDto.getName())) throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        CongressionalDistrictEntity congressionalDistrict = new ModelMapper().map(congressionalDistrictDto, CongressionalDistrictEntity.class);

        congressionalDistrict.setCongressionalDistrictId(utils.generateRandomID());

        congressionalDistrict.setNextElection(utils.initial2yearCycle());

        CongressionalDistrictEntity storedCongressionalDistrict = this.congressionalDistrictRepository.save(congressionalDistrict);

        return new ModelMapper().map(storedCongressionalDistrict, CongressionalDistrictDto.class);
    }

    @Override
    public List<CongressionalDistrictDto> bulkCreateCongressionalDistrict(List<CongressionalDistrictDto> congressionalDistrictDtoList) {
        List<CongressionalDistrictDto> returnValue = new ArrayList<>();

        for(CongressionalDistrictDto congressionalDistrictDto: congressionalDistrictDtoList){

            CongressionalDistrictEntity congressionalDistrictEntity = new ModelMapper().map(congressionalDistrictDto, CongressionalDistrictEntity.class);
            congressionalDistrictEntity.setCongressionalDistrictId(utils.generateRandomID());

            CongressionalDistrictEntity savedDistrict = this.congressionalDistrictRepository.save(congressionalDistrictEntity);

            returnValue.add(new ModelMapper().map(savedDistrict, CongressionalDistrictDto.class));
        }

        return returnValue;
    }

    @Override
    public CongressionalDistrictDto updateCongressionalDistrict(String congressionalDistrictId, CongressionalDistrictDto congressionalDistrictDto) {
        return null;
    }

    @Override
    public CongressionalDistrictDto deleteCongressionalDistrict(String congressionalDistrictId) {
        return null;
    }

    @Override
    public List<CongressionalDistrictDto> findAllCongressionalDistricts(){
        List<CongressionalDistrictDto> returnValue = new ArrayList<>();

        List<CongressionalDistrictEntity> congressionalDistrictEntities = this.congressionalDistrictRepository.findAll();

        for(CongressionalDistrictEntity district: congressionalDistrictEntities){

            returnValue.add(new ModelMapper().map(district, CongressionalDistrictDto.class));
        }
        return returnValue;
    }

    @Override
    public CongressionalDistrictEssentialsResponse entityToEssentials(CongressionalDistrictEntity congressionalDistrictEntity) {

        if(this.entityIsNull(congressionalDistrictEntity))
            throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        CongressionalDistrictEssentialsResponse congressionalDistrict = new CongressionalDistrictEssentialsResponse();

        congressionalDistrict.setName(congressionalDistrictEntity.getName());
        congressionalDistrict.setCongressionalDistrictId(congressionalDistrictEntity.getCongressionalDistrictId());

        return congressionalDistrict;
    }

    @Override
    public List<CongressionalDistrictEssentialsResponse> entityToEssentials(List<CongressionalDistrictEntity> congressionalDistrictEntityList) {

        if(this.entityIsNull(congressionalDistrictEntityList))
            throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CongressionalDistrictEssentialsResponse> returnValue = new ArrayList<>();

        for(CongressionalDistrictEntity district: congressionalDistrictEntityList){

            returnValue.add(this.entityToEssentials(district));
        }

        return returnValue.stream()
                .sorted(Comparator.comparing(CongressionalDistrictEssentialsResponse :: getName))
                .collect(Collectors.toList());
    }

    @Override
    public CongressionalDistrictEntity getCongressionalDistrictFromSite(SiteEntity siteEntity) {

        if(siteEntity == null)
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return siteEntity.getCongressionalDistrictEntity();
    }

    @Override
    public List<CongressionalDistrictEntity> getCongressionalDistrictFromSites(List<SiteEntity> siteEntities) {

        if(siteEntities == null)
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CongressionalDistrictEntity> returnValue = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            if(siteEntity.getCongressionalDistrictEntity()!= null){

                if(!returnValue.contains(siteEntity.getCongressionalDistrictEntity())){

                    returnValue.add(siteEntity.getCongressionalDistrictEntity());
                }
            }
        }

        return returnValue.stream()
                .sorted(Comparator.comparing(CongressionalDistrictEntity ::getName))
                .collect(Collectors.toList());
    }

    @Override
    public CongressionalDistrictDto entityToDto(CongressionalDistrictEntity congressionalDistrictEntity) {

        if(this.entityIsNull(congressionalDistrictEntity))
            throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(congressionalDistrictEntity, CongressionalDistrictDto.class);
    }

    @Override
    public List<CongressionalDistrictDto> entityToDto(List<CongressionalDistrictEntity> congressionalDistrictEntityList) {

        if(this.entityIsNull(congressionalDistrictEntityList))
            throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CongressionalDistrictDto> returnValue = new ArrayList<>();

        for(CongressionalDistrictEntity congressionalDistrictEntity: congressionalDistrictEntityList){

            returnValue.add(this.entityToDto(congressionalDistrictEntity));
        }

        return returnValue;
    }

    @Override
    public CongressionalDistrictNestedResponse dtoToNestedResponse(CongressionalDistrictDto congressionalDistrictDto) {

        if(this.dtoIsNull(congressionalDistrictDto)) throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(congressionalDistrictDto, CongressionalDistrictNestedResponse.class);
    }

    @Override
    public List<CongressionalDistrictNestedResponse> dtoToNestedResponse(List<CongressionalDistrictDto> congressionalDistrictDtoList) {

        if(this.dtoIsNull(congressionalDistrictDtoList))
            throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<CongressionalDistrictNestedResponse> returnValue = new ArrayList<>();

        for(CongressionalDistrictDto districtDto: congressionalDistrictDtoList){

            returnValue.add(this.dtoToNestedResponse(districtDto));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(CongressionalDistrictEntity congressionalDistrictEntity) {
        return congressionalDistrictEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<CongressionalDistrictEntity> congressionalDistrictEntityList) {
        return congressionalDistrictEntityList == null;
    }

    @Override
    public Boolean dtoIsNull(CongressionalDistrictDto congressionalDistrictDto) {
        return congressionalDistrictDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<CongressionalDistrictDto> congressionalDistrictDtoList) {
        return congressionalDistrictDtoList == null;
    }

    @Override
    public CongressionalDistrictServiceException isNullError(Boolean isNull) {

        if(isNull) throw new CongressionalDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return null;
    }

    public CongressionalDistrictEntity getCongressionalDistrictEntity(String congressionalId){

        return this.congressionalDistrictRepository.findByCongressionalDistrictId(congressionalId);
    }
}
