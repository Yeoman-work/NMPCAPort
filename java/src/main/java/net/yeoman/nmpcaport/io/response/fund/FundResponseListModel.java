package net.yeoman.nmpcaport.io.response.fund;

import java.util.List;

public class FundResponseListModel {

    List<FundResponseModel> FundResponseModel;

    public List<net.yeoman.nmpcaport.io.response.fund.FundResponseModel> getFundResponseModel() {
        return FundResponseModel;
    }

    public void setFundResponseModel(List<net.yeoman.nmpcaport.io.response.fund.FundResponseModel> fundResponseModel) {
        FundResponseModel = fundResponseModel;
    }

}
