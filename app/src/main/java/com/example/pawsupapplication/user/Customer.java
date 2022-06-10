package com.example.pawsupapplication.user;


/**
 *
 * @author Yunfei Wang Abstract class representing a customer
 *
 */
public class Customer extends User{
    private String id;
    private String name;
    private String address;
    private int roleId;
    private boolean provider;
    /**
     * Get the address of the user
     *
     * @return address of the user
     */
    public boolean getProvider() {
        return this.provider;
    }
    /**
     * Set the address of the user given an address
     *
     * @param provider address of the user
     */
    public void setProvider(boolean provider) {
        this.provider = provider;
    }
    /**
     * Constructor for creating a customer
     * @param id id of the user
     * @param name name of the user
     * @param address address of the user
     */
    public Customer(String id, String name, int age, String address) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setProvider(false);
    }
}
