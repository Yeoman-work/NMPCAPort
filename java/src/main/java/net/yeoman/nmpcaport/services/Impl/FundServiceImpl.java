package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.SiteFundingDetailsEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.FundServiceException;
import net.yeoman.nmpcaport.exception.SiteFundingServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.io.request.fund.FundRequestListModel;
import net.yeoman.nmpcaport.io.request.fund.FundRequestModel;
import net.yeoman.nmpcaport.io.repositories.FundRepository;
import net.yeoman.nmpcaport.io.response.fund.FundEssentialsResponse;
import net.yeoman.nmpcaport.io.response.fund.FundNestedResponse;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.services.FundService;
import net.yeoman.nmpcaport.shared.dto.FundDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundServiceImpl implements FundService {


    private final FundRepository fundRepository;
    private final SiteFundingDetailsServiceImpl siteFundingDetailsService;
    private final Utils utils;

    public FundServiceImpl(FundRepository fundRepository,
                           SiteFundingDetailsServiceImpl siteFundingDetailsService,
                           Utils utils
    ){

        this.fundRepository = fundRepository;
        this.siteFundingDetailsService = siteFundingDetailsService;
        this.utils = utils;

    }



    @Override
    public FundResponseModel getFundResponse(String fundId) {

        return this.dtoToResponse(
                this.entityToDto(
                        this.fundRepository.findByFundId(fundId)
                )
        );
    }

    @Override
    public FundEssentialsResponse entityToEssential(FundEntity fundEntity) {

        if(this.entityIsNull(fundEntity))
            throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(fundEntity, FundEssentialsResponse.class);
    }

    @Override
    public List<FundEssentialsResponse> entityToEssential(List<FundEntity> fundList) {

        if(this.entityIsNull(fundList)) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<FundEssentialsResponse> returnValue = new ArrayList<>();

        for(FundEntity fund: fundList){

            returnValue.add(this.entityToEssential(fund));
        }

        return returnValue.stream()
                .sorted(Comparator.comparing(FundEssentialsResponse::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<FundEntity> gatherFundEntity(List<SiteFundingDetailsEntity> fundingDetailsEntities, List<FundEntity> fundEntities) {

        if(this.entityIsNull(fundEntities))
            throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        if(this.siteFundingDetailsService.entityIsNull(fundingDetailsEntities))
            throw new SiteFundingServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        for(SiteFundingDetailsEntity siteFunding: fundingDetailsEntities){

            if(!fundEntities.contains(siteFunding.getFundEntity())){

                fundEntities.add(siteFunding.getFundEntity());
            }

        }

        return fundEntities;
    }


    @Override
    public FundEssentialsResponse getEssentialsFromSite(SiteEntity siteEntity) {

        if(siteEntity == null)
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());


        return null;
    }

    @Override
    public List<FundEssentialsResponse> getEssentialsFromSite(List<SiteEntity> siteEntities) {

        if(siteEntities == null)
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<FundEntity> fundingFromSitesList = new ArrayList<>();
        List<FundEssentialsResponse> fundEssentialsFromSitesList = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            fundingFromSitesList = this.gatherFundEntity(
                    siteEntity.getSiteFundingDetailsEntities(),
                    fundingFromSitesList
            );
        }

        for(FundEntity fund: fundingFromSitesList){

            fundEssentialsFromSitesList.add(this.entityToEssential(fund));
        }

        return fundEssentialsFromSitesList;
    }


    @Override
    public FundDto createFund(FundDto fundDto) {

        FundEntity fundEntity = new ModelMapper().map(fundDto, FundEntity.class);
        fundEntity.setFundId(utils.generateRandomID());
        FundEntity savedFund = this.fundRepository.save(fundEntity);

        return new ModelMapper().map(savedFund, FundDto.class);
    }

    @Override
    public FundDto updateFund(FundDto fundDto, String fundId) {

        FundEntity preUpdateFund = this.fundRepository.findByFundId(fundId);


        if(!preUpdateFund.getName().equals(fundDto.getName())){
            preUpdateFund.setName(fundDto.getName());
        }

        FundEntity updatedEntity = this.fundRepository.save(preUpdateFund);


        return new ModelMapper().map(updatedEntity, FundDto.class);
    }

    @Override
    public FundDto deleteFund(String fundId) {

        FundEntity fundEntity = this.fundRepository.findByFundId(fundId);

        this.fundRepository.delete(fundEntity);

        return new ModelMapper().map(fundEntity, FundDto.class);
    }

    @Override
    public FundEntity getFundEntity(String fundId) {

        if(fundId == null) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.fundRepository.findByFundId(fundId);
    }

    @Override
    public List<FundEntity> getFundEntities(List<String> fundIds) {

        if(fundIds == null) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<FundEntity> returnValue = new ArrayList<>();

        for(String fundId: fundIds){

            returnValue.add(this.getFundEntity(fundId));

        }

        return returnValue;
    }

    @Override
    public List<FundDto> getAllFunding() {

        List<FundDto> returnValue = new ArrayList<>();
        List<FundEntity> fundEntityList = this.fundRepository.findAll();

        for(FundEntity fundEntity: fundEntityList){

            returnValue.add(new ModelMapper().map(fundEntity, FundDto.class));
        }

        return returnValue;
    }

    @Override
    public List<FundDto> createFundBulk(FundRequestListModel fundRequestListModel) {
        List<FundDto> fundDtoList = new ArrayList<>();

        for(FundRequestModel fund: fundRequestListModel.getFundRequestModelList()){

            FundEntity fundEntity = new ModelMapper().map(fund, FundEntity.class);

            fundEntity.setFundId(utils.generateRandomID());

            FundEntity savedFund = this.fundRepository.save(fundEntity);

            fundDtoList.add(new ModelMapper().map(savedFund, FundDto.class));

        }

        return fundDtoList;
    }



    @Override
    public FundDto entityToDto(FundEntity fundEntity) {

        if(entityIsNull(fundEntity)) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(fundEntity, FundDto.class);
    }

    @Override
    public List<FundDto> entityToDto(List<FundEntity> fundEntityList) {

        if(entityIsNull(fundEntityList)) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<FundDto> returnValue = new ArrayList<>();

        for(FundEntity fundEntity: fundEntityList){

            returnValue.add(this.entityToDto(fundEntity));
        }

        return returnValue;
    }

    @Override
    public FundNestedResponse dtoToNestedResponse(FundDto fundDto) {

        if(this.dtoIsNull(fundDto)) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(fundDto, FundNestedResponse.class);
    }

    @Override
    public List<FundNestedResponse> dtoToNestedResponse(List<FundDto> fundDtoList) {

        if(this.dtoIsNull(fundDtoList)) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<FundNestedResponse> returnValue = new ArrayList<>();

        for(FundDto fundDto: fundDtoList){

            returnValue.add(dtoToNestedResponse(fundDto));
        }

        return returnValue;
    }

    @Override
    public FundResponseModel dtoToResponse(FundDto fundDto) {

        if(this.dtoIsNull(fundDto)) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(fundDto, FundResponseModel.class);
    }

    @Override
    public List<FundResponseModel> dtoToResponse(List<FundDto> fundDtoList) {

        if(this.dtoIsNull(fundDtoList)) throw new FundServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<FundResponseModel> returnValue = new ArrayList<>();

        for(FundDto fundDto: fundDtoList){

            returnValue.add(this.dtoToResponse(fundDto));
        }
        return returnValue;
    }

    @Override
    public Boolean entityIsNull(FundEntity fundEntity) {
        return fundEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<FundEntity> fundEntityList) {
        return fundEntityList == null;
    }

    @Override
    public Boolean dtoIsNull(List<FundDto> fundDtoList) {
        return fundDtoList == null;
    }

    @Override
    public Boolean dtoIsNull(FundDto fundDto) {
        return fundDto == null;
    }


}
