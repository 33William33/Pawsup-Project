package com.example.pawsupapplication.data.model;

/**
 * This class is a constructor class for the Order objects. It contains details such as
 * the user, the pets, the date of ordering, the start time, and the end time.
 *
 * @author Shu Sun
 */

public class Order {
    private User user;
    private int order;
    private String pet;
    private String dateOrdered;
    private String start;
    private String end;

    public Order(User u, int o, String p, String d, String s, String e){
        user = u;
        order = o;
        pet = p;
        dateOrdered = d;
        start = s;
        end = e;
    }
    public void setUser(User u){
        user = u;
    }

    public void setPet(String p){ pet = p; }

    public void setOrder(int o){ order = o; }

    public void setDate(String d){
        dateOrdered = d;
    }

    public void setStart(String s){
        start = s;
    }

    public void setEnd(String e){
        end = e;
    }

    public User getUser(){
        return user;
    }

    public int getOrder(){ return order; }

    public String getPet(){
        return pet;
    }

    public String getDate(){
        return dateOrdered;
    }

    public String getStart(){
        return start;
    }

    public String getEnd(){
        return end;
    }
}
