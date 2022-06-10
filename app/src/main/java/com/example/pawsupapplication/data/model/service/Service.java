package com.example.pawsupapplication.data.model.service;

import java.math.BigDecimal;

/**
 *
 * @author Yunfei Wang
 * Interface representing a service
 *
 */
public interface Service {

    /**
     * Get the id of the service
     * @return the id of the service
     */
    public int getServiceId();

    /**
     * Set the id of the service given an id
     * @param id the id of the service
     */
    public void setServiceId(int id);
    /**
     * Get the user id of the service
     * @return the user id of the service
     */
    public String getUserId();

    /**
     * Set the user id of the service given an id
     * @param id the user id of the service
     */
    public void setUserId(String id);
    /**
     * Get the name of the service
     * @return the name of the service
     */
    public String getServiceName();

    /**
     * Set the name of the service given the name
     * @param name the name of the service
     */
    public void setServiceName(String name);
    /**
     * Get the description of the service
     * @return the description of the service
     */
    public String getServiceDesc();

    /**
     * Set the description of the service
     * @param desc the description of the service
     */
    public void setServiceDesc(String desc);
    /**
     * Get the address of the service
     * @return the address of the service
     */
    public String getServiceAddress();

    /**
     * Set the address of the service
     * @param ads the address of the service
     */
    public void setServiceAddress(String ads);
    /**
     * Get the price of the service
     * @return the price of the service
     */
    public String getServicePrice();

    /**
     * Set the price of the service given a price
     * @param price the price of the service
     */
    public void setServicePrice(String price);

    /**
     * Get the service picture address
     * @return the picture address in a string
     */
    public String getServicePicture();

    /**
     * Set the address of service picture
     * @param servicePicture the picture address
     */
    public void setServicePicture(String servicePicture);

    /**
     * Get the service ID
     * @return the ID in a string
     */
    String getId();

    /**
     * Set the unique ID of the service as a String
     * @param id the picture address
     */
    void setId(String id);
}
