package net.yeoman.nmpcaport.io.request.service;

import java.util.List;

public class ServiceRequestListModel {

    private List<ServiceDetailsRequestModel> serviceRequestList;

    public List<ServiceDetailsRequestModel> getServiceRequestList() {
        return serviceRequestList;
    }

    public void setServiceRequestList(List<ServiceDetailsRequestModel> serviceRequestList) {
        this.serviceRequestList = serviceRequestList;
    }
}
