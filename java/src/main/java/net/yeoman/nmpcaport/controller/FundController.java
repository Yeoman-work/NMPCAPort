package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.fund.FundRequestListModel;
import net.yeoman.nmpcaport.io.request.fund.FundRequestModel;
import net.yeoman.nmpcaport.io.response.fund.FundResponseModel;
import net.yeoman.nmpcaport.services.Impl.FundServiceImpl;
import net.yeoman.nmpcaport.shared.dto.FundDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funds")
public class FundController {

    @Autowired
    private FundServiceImpl fundService;


    @GetMapping("/{fundId}")
    public FundResponseModel getOneFund(@PathVariable("fundId") String fundId){

        return this.fundService.getFundResponse(fundId);
    }

    @GetMapping
    public List<FundResponseModel> getAllFunding(){
        List<FundResponseModel> returnValue = new ArrayList<>();
        List<FundDto> fundDtoList = this.fundService.getAllFunding();

        for(FundDto fund: fundDtoList){

            returnValue.add(new ModelMapper().map(fund, FundResponseModel.class));
        }

        return returnValue;
    }

    @PostMapping
    public FundDto createFund(@RequestBody FundRequestModel fundRequestModel){

        return  this.fundService.createFund(new ModelMapper().map(fundRequestModel, FundDto.class));
    }


    @PostMapping("/bulk")
    public List<FundResponseModel> createFundsBulk(@RequestBody FundRequestListModel fundRequestListModel){
        List<FundResponseModel> returnValue = new ArrayList<>();
        List<FundDto> fundDtoList = this.fundService.createFundBulk(fundRequestListModel);

        for(FundDto fund: fundDtoList){

            returnValue.add(new ModelMapper().map(fund, FundResponseModel.class));
        }

        return returnValue;

    }

    @PutMapping("/{fundId}")
    public FundDto updateFund(@PathVariable("fundId") String fundId, FundRequestModel fundRequestModel){

        return this.fundService.updateFund(new ModelMapper().map(fundRequestModel, FundDto.class), fundId);

    }
}
