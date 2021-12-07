package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.io.request.fund.FundRequestListModel;
import net.yeoman.nmpcaport.shared.dto.FundDto;

import java.util.List;

public interface FundService {

    public FundDto getFund(String fundId);
    public FundDto createFund(FundDto fundDto);
    public FundDto updateFund(FundDto fundDto, String fundId);
    public FundDto deleteFund(String fundId);
    public FundEntity getFundEntity(String fundId);
    public List<FundDto> getAllFunding();
    public List<FundDto> createFundBulk(FundRequestListModel fundRequestListModel);
}
