package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.io.request.fund.FundRequestListModel;
import net.yeoman.nmpcaport.io.request.fund.FundRequestModel;
import net.yeoman.nmpcaport.repositories.FundRepository;
import net.yeoman.nmpcaport.repositories.SiteRepository;
import net.yeoman.nmpcaport.services.FundService;
import net.yeoman.nmpcaport.shared.dto.FundDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private FundRepository fundRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private Utils utils;

    @Override
    public FundDto getFund(String fundId) {

        return new ModelMapper().map(this.fundRepository.findByFundId(fundId), FundDto.class);
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

        return this.fundRepository.findByFundId(fundId);
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



}
