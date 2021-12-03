package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.entities.FundEntity;
import net.yeoman.nmpcaport.io.request.fund.FundRequestListModel;
import net.yeoman.nmpcaport.io.request.fund.FundRequestModel;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.services.FundService;
import net.yeoman.nmpcaport.services.Impl.FundServiceImpl;
import net.yeoman.nmpcaport.shared.dto.FundDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("funds")
public class FundController {

    @Autowired
    private FundServiceImpl fundService;


    @GetMapping("/{fundId}")
    public FundResponseModel getOneFund(@PathVariable("fundId") String fundId){

        FundDto fundDto = this.fundService.getFund(fundId);

        return new ModelMapper().map(fundDto, FundResponseModel.class);
    }

    @PostMapping
    public FundDto createFund(FundRequestModel fundRequestModel){

        return  this.fundService.createFund(new ModelMapper().map(fundRequestModel, FundDto.class));
    }


    @PostMapping("/bulk")
    public List<FundDto> createFundsBulk(FundRequestListModel fundRequestListModel){


        return this.fundService.createFundBulk(fundRequestListModel);

    }

    @PutMapping("/{fundId}")
    public FundDto updateFund(@PathVariable("fundId") String fundId, FundRequestModel fundRequestModel){

        return this.fundService.updateFund(new ModelMapper().map(fundRequestModel, FundDto.class), fundId);

    }
}
