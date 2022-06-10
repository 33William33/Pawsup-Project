package com.example.pawsupapplication.data.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the structure for the petcard, it is composed of strings that will handle
 * profile pics, names, genders, neutered or spayed, types, weights, and additional information
 * about pet.
 *
 * @author Annas Rahuma
 */

public class PetCard {


    private String profilePic;
    private String name;
    private String gender;
    private String ns;
    private String type;
    private String weight;
    private String information;
    public PetCard(String profilePic, String name, String gender, String ns, String type, String weight,
                   String information) {
        super();
        this.profilePic = profilePic;
        this.name = name;
        this.gender = gender;
        this.ns = ns;
        this.type = type;
        this.weight = weight;
        this.information = information;
    }

    public String getProfilePic() { return profilePic; }
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNs() {
        return ns;
    }
    public void setNs(String ns) {
        this.ns = ns;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
}