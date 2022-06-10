package com.example.pawsupapplication.data.model;

/**
 * This class provides a structure for the review object.
 * It is composed of parameters for handling the rating,
 * review title, review body, and the date of creation.
 * It also contains functions for setting and accessing
 * the parameters.
 *
 * @author Annas Rahuma and Shu Sun
 */

public class Review {
    private float rating;
    private String date;
    private String title;
    private String reviewText;


    public Review(float r, String d, String t, String rt){
        rating = r;
        date = d;
        title = t;
        reviewText = rt;
    }

    public void setRating(float i){
        rating = i;
    }

    public float getRating(){
        return rating;
    }

    public void setTitle(String s){
        title = s;
    }

    public String getTitle(){
        return title;
    }

    public void setReview(String s){
        reviewText = s;
    }

    public String getReview(){
        return reviewText;
    }

    public void setDate(String s){
        date = s;
    }

    public String getDate(){
        return date;
    }
}
