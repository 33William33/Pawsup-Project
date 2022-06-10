package com.example.pawsupapplication.data.model.service;

import java.math.BigDecimal;

/**
 *
 * @author Yunfei Wang Implementaion of the Service interface
 *
 */
public class ServiceImpl implements Service {

    private int serviceId;
    private String userid;
    private String serviceName;
    private String serviceDesc;
    private String serviceAds;
    private String servicePrice;
    private String servicePicture;
    private String id;

    public ServiceImpl(String userid, String serviceName, String serviceDesc, String serviceAds,
                       String servicePrice, String servicePicture, String id){
        super();

        this.userid = userid;
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.serviceAds = serviceAds;
        this.servicePrice = servicePrice;
        this.servicePicture = servicePicture;
        this.id = id;
    }

    @Override
    public int getServiceId() {
        return this.serviceId;
    }
    @Override
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;

    }
    @Override
    public String getUserId() {
        return this.userid;
    }

    @Override
    public void setUserId(String userid) {
        this.userid = userid;

    }
    @Override
    public String getServiceName() {
        return this.serviceName;
    }
    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;

    }
    @Override
    public String getServiceDesc() {
        return this.serviceDesc;
    }
    @Override
    public void setServiceDesc(String name) {
        this.serviceDesc = serviceDesc;

    }
    @Override
    public String getServiceAddress() {
        return this.serviceAds;
    }
    @Override
    public void setServiceAddress(String serviceAds) {
        this.serviceAds = serviceAds;

    }
    @Override
    public String getServicePrice() {
        return this.servicePrice;
    }

    @Override
    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public String getServicePicture() { return this.servicePicture; }

    @Override
    public void setServicePicture(String servicePicture) { this.servicePicture = servicePicture; }

    @Override
    public String getId() { return this.id; }

    @Override
    public void setId(String id) { this.id = id; }


}
