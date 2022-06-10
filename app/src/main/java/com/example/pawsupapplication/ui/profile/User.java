package com.example.pawsupapplication.ui.profile;

public class User {
    private String name;
    private String birthday;
    private String location;
    private String email;
    private String phone;
    private String password;

    public User(){
        name = "John Smith";
        birthday = "03/18/1997";
        location = "Toronto, Ontario";
        email = "johnsmith@gmail.com";
        phone = "416-000-0000";
        password = "Password1";
    }
    public void setName(String s){
        name = changeString(s);
    }

    public void setBirthday(String s){ birthday = changeString(s); }

    public void setLocation(String l){
        location = changeString(l);
    }

    public void setEmail(String e){
        email = changeString(e);
    }

    public void setPhone(String p){
        phone = changeString(p);
    }

    public void setPassword(String p){
        password = changeString(p);
    }

    public String getName(){
        return name;
    }

    public String getBirthday(){
        return birthday;
    }

    public String getLocation(){
        return location;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public String getPassword(){
        return password;
    }

    private static String changeString(String s){
        return s;
    }
}
