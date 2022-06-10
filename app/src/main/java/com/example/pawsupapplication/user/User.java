package com.example.pawsupapplication.user;


/**
 *
 * @author Yunfei Wang Abstract class representing a user
 *
 */
public abstract class User {
    private String id;
    private String name;
    private String address;
    private int roleId;
    /**
     * Get the id of the user
     *
     * @return id of the user
     */
    public String getId() {
        return this.id;
    }
    /**
     * set the id of the user given the id
     *
     * @param id id of the user to be set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Get the name of the user
     *
     * @return the name of the user
     */
    public String getName() {
        return this.name;
    }
    /**
     * set the name of the user given the name
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the address of the user
     *
     * @return address of the user
     */
    public String getAddress() {
        return this.address;
    }
    /**
     * Set the address of the user given an address
     *
     * @param address address of the user
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Get the role id of the user
     *
     * @return Role id of the user
     */
    public int getRoleId() {
        return this.roleId;
    }
}
