package com.example.pawsupapplication.data;


/*
This class is used for all queries used by classes in the application
Learnt how to use SQL and used code as a base from https://www.youtube.com/watch?v=hDSVInZ2JCs&t=3352s
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.pawsupapplication.data.model.History;

import com.example.pawsupapplication.data.model.LoggedInUser;
import com.example.pawsupapplication.data.model.PetCard;
import com.example.pawsupapplication.data.model.product.Product;
import com.example.pawsupapplication.user.Customer;
import com.example.pawsupapplication.data.model.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DAO extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pawsup.db";

    public DAO(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement1 = "CREATE TABLE " +
                "USER_TABLE (UserID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT, Id TEXT, flag TEXT)";
        db.execSQL(createTableStatement1);

        String createTableStatement2 = "CREATE TABLE " +
                "PETCARD_TABLE (PetID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Gender TEXT," +
                " Ns Text, Type TEXT, Weight TEXT, Information TEXT, Picture TEXT, Email TEXT)";
        db.execSQL(createTableStatement2);
      
        String createTableStatement3 = "CREATE TABLE " +
                "HISTORY_TABLE (HisID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Amount INTEGER," +
                " Price Text, Date TEXT, Image TEXT, Email TEXT, Pet TEXT)";
        db.execSQL(createTableStatement3);
      
        String createTableStatement4 = "CREATE TABLE " +
                "SERVICE_TABLE (SerID INTEGER PRIMARY KEY AUTOINCREMENT, UserID TEXT, Name TEXT, Describe TEXT," +
                " Address TEXT, Price TEXT, Picture TEXT, ServiceID TEXT)";
        db.execSQL(createTableStatement4);

        String createTableStatement5 = "CREATE TABLE " +
                "PRODUCT_TABLE (ProID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Quantity TEXT," +
                " Price TEXT, Rating TEXT, Picture TEXT, ProductID TEXT)";
        db.execSQL(createTableStatement5);
      
        String createTableStatement6 = "CREATE TABLE " +
                "PURCHASE_TABLE (purID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, serviceID TEXT," +
                " Amount INTEGER, petName TEXT)";
        db.execSQL(createTableStatement6);
      
        String createTableStatement7 = "CREATE TABLE " +
                "REVIEW_TABLE (reviewID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, serviceID TEXT," +
                " stars INTEGER, title TEXT, body TEXT, date TEXT)";
        db.execSQL(createTableStatement7);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "USER_TABLE");
        db.execSQL("DROP TABLE IF EXISTS " + "PETCARD_TABLE");
        db.execSQL("DROP TABLE IF EXISTS " + "HISTORY_TABLE");
        db.execSQL("DROP TABLE IF EXISTS " + "SERVICE_TABLE");
        db.execSQL("DROP TABLE IF EXISTS " + "PURCHASE_TABLE");
        db.execSQL("DROP TABLE IF EXISTS " + "PRODUCT_TABLE");

        onCreate(db);
    }

    public boolean addUser(LoggedInUser user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("Id", user.getUserId());
        cv.put("flag", 1);

        long report = db.insert("USER_TABLE", null, cv);

        return (report != -1);
    }

    public boolean addPurchase(String email, String serviceID, Integer amount, String pet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("email", email);
        cv.put("serviceID", serviceID);
        cv.put("Amount", amount);
        cv.put("petName", pet);

        long report = db.insert("PURCHASE_TABLE", null, cv);

        return (report != -1);
    }

    public void deletePurchase(String email/*, String serviceID, Integer amount, String pet*/){

        String q = "Select * From PURCHASE_TABLE Where Email = \"" + email + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);



        if(cursor.moveToFirst()){
            do{


                String serviceID = cursor.getString(2);
                int amount = cursor.getInt(3);
                String petName = cursor.getString(4);
                String price;
                String image;
                LocalDate date = LocalDate.now();
                ArrayList<String> A = getPurchasedItems(serviceID);
                if (A.isEmpty()){
                    A = getPurchasedProduct(serviceID);
                    price = A.get(2);
                    image = A.get(4);
                }
                else {
                    price = A.get(4);
                    image = A.get(6);
                }
                History his = new History(amount, price, serviceID, date.toString(), image, petName);
                addHistory(his, email);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        SQLiteDatabase db1 = this.getWritableDatabase();

        q = "Delete From PETCARD_TABLE Where Email = \"" + email +
                "\"";

        System.out.println(q);

        Cursor c1 = db1.rawQuery(q, null);

    }

    public boolean addService(Service ser){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("UserID", ser.getUserId());
        cv.put("Name", ser.getServiceName());
        cv.put("Describe", ser.getServiceDesc());
        cv.put("Address", ser.getServiceAddress());
        cv.put("Price", ser.getServicePrice());
        cv.put("Picture", ser.getServicePicture());
        cv.put("ServiceID", ser.getId());

        long report = db.insert("SERVICE_TABLE", null, cv);

        return (report != -1);
    }

    public boolean addProduct(Product pro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Name", pro.getProductName());
        cv.put("Quantity", pro.getProductQty());
        cv.put("Price", pro.getProductPrice());
        cv.put("Rating", pro.getProductRating());
        cv.put("Picture", pro.getProductPicture());
        cv.put("ProductID", pro.getId());

        long report = db.insert("PRODUCT_TABLE", null, cv);

        return (report != -1);
    }

    public boolean removeProduct(String id){
        String q = "Delete From PRODUCT_TABLE Where ProductID =\"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(q);

        Cursor c = db.rawQuery(q, null);
        System.out.println(c.moveToFirst());
        if(c.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

    public void editProduct(String id, String name, String quantity, String price, String picture){
        SQLiteDatabase db = this.getWritableDatabase();
        String q1 = "UPDATE PRODUCT_TABLE SET Name =\"" + name + "\" Where ProductID =\"" + id + "\"";
        String q2 = "UPDATE PRODUCT_TABLE SET Quantity =\"" + quantity + "\" Where ProductID =\"" + id + "\"";
        String q3 = "UPDATE PRODUCT_TABLE SET Price =\"" + price + "\" Where ProductID =\"" + id + "\"";
        String q4 = "UPDATE PRODUCT_TABLE SET Picture =\"" + picture + "\" Where ProductID =\"" + id + "\"";
        db.execSQL(q1);
        db.execSQL(q2);
        db.execSQL(q3);
        db.execSQL(q4);
        db.close();
    }

    public Map<String,Integer> getPurchases(String email){
        Map<String,Integer> purchased = new HashMap<>();
        String q = "Select * From PURCHASE_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                if(email.equals(cursor.getString(1))){
                    String serviceID = cursor.getString(2);
                    Integer amount = cursor.getInt(3);
                    purchased.put(serviceID, amount);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return purchased;
    }

    public ArrayList<String> getPurchasedItems(String itemID){
        ArrayList<String> item = new ArrayList<>();

        String q = "Select * From SERVICE_TABLE Where ServiceID = \"" + itemID + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                String provider = cursor.getString(1); // index 0
                String name = cursor.getString(2); // 1
                String description = cursor.getString(3); // 2
                String address = cursor.getString(4); // 3
                String price = cursor.getString(5); // 4
                String serviceID = cursor.getString(6); // 5
                String image = cursor.getString(7); // 6

                item.add(provider);
                item.add(name);
                item.add(description);
                item.add(address);
                item.add(price);
                item.add(serviceID);
                item.add(image);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return item;
    }

    public ArrayList<String> getPurchasedProduct(String itemID){
        ArrayList<String> item = new ArrayList<>();

        String q = "Select * From PRODUCT_TABLE Where ProductID = \"" + itemID + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(1); // index 0
                String quantity = cursor.getString(2); // 1
                String price = cursor.getString(3); // 2
                String rating = cursor.getString(4); // 3
                String image = cursor.getString(5); // 4
                String productID = cursor.getString(6); // 5

                item.add(name);
                item.add(quantity);
                item.add(price);
                item.add(rating);
                item.add(image);
                item.add(productID);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return item;
    }

    public Map<String,ArrayList<String>> getUsers(){
        Map<String,ArrayList<String>> users = new HashMap<>();
        String q = "Select * From USER_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                //int ID = cursor.getInt(0);
                String email = cursor.getString(1);
                String password = cursor.getString(2);
                String UserId = cursor.getString(3);

                ArrayList<String> userInfo = new ArrayList<>();
                userInfo.add(password);
                userInfo.add(UserId);

                users.put(email, userInfo);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    public ArrayList<Service> getServices(String UserID){
        ArrayList<Service> services = new ArrayList<>();
        String q = "Select * From SERVICE_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                String userid = cursor.getString(1);
                String name = cursor.getString(2);
                String desc = cursor.getString(3);
                String address = cursor.getString(4);
                String price = cursor.getString(5);
                String picture = cursor.getString(6);
                String serviceId = cursor.getString(7);

                if (userid.compareTo(UserID) == 0){
                    Service ser = new ServiceImpl("",""
                            ,"","","","", "");
                    ser.setServiceId(ID);
                    ser.setUserId(userid);
                    ser.setServiceName(name);
                    ser.setServiceDesc(desc);
                    ser.setServiceAddress(address);
                    ser.setServicePrice(price);
                    ser.setServicePicture(picture);
                    ser.setId(serviceId);
                    services.add(ser);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return services;
    }

    public ArrayList<Service> getAllService(){
        ArrayList<Service> allSer = new ArrayList<>();
        String q = "Select * From SERVICE_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                int serv_id = cursor.getInt(0);
                String serv_userId = cursor.getString(1);
                String serv_name = cursor.getString(2);
                String serv_description = cursor.getString(3);
                String serv_address = cursor.getString(4);
                String serv_price = cursor.getString(5);
                String serv_picture = cursor.getString(6);
                String serviceId = cursor.getString(7);

                Service ser = new ServiceImpl("",""
                        ,"","","","", "");
                ser.setServiceId(serv_id);
                ser.setUserId(serv_userId);
                ser.setServiceName(serv_name);
                ser.setServiceDesc(serv_description);
                ser.setServiceAddress(serv_address);
                ser.setServicePrice(serv_price);
                ser.setServicePicture(serv_picture);
                ser.setId(serviceId);
                allSer.add(ser);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allSer;
    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> allPro = new ArrayList<>();
        String q = "Select * From PRODUCT_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                int prod_id = cursor.getInt(0);
                String prod_name = cursor.getString(1);
                String prod_quantity = cursor.getString(2);
                String prod_price = cursor.getString(3);
                String prod_rating = cursor.getString(4);
                String prod_picture = cursor.getString(5);
                String productId = cursor.getString(6);

                Product pro = new Product(""
                        ,"","","","", "");
                pro.setProductId(prod_id);
                pro.setProductName(prod_name);
                pro.setProductQty(prod_quantity);
                pro.setProductPrice(prod_price);
                pro.setProductRating(prod_rating);
                pro.setProductPicture(prod_picture);
                pro.setId(productId);

                allPro.add(pro);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allPro;
    }

    public Customer getByEmail(String s){
        String q = "Select * From USER_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        Customer c = new Customer("","",0,"");

        if(cursor.moveToFirst()){
            do{
                String email = cursor.getString(1);
                String UserId = cursor.getString(3);
                String flag = cursor.getString(4);
                if (email.compareTo(s) == 0){
                    if(Integer.parseInt(flag) == 0){
                        c.setProvider(true);
                    }else{
                        c.setProvider(false);
                    }
                    c.setId(UserId);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return c;
    }

    public void setFlagByEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String setTableStatement = "UPDATE USER_TABLE SET flag='0' WHERE email = \"" + email + "\"";
        db.execSQL(setTableStatement);
        db.close();
    }

    public boolean addPetCard(PetCard pet, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Name", pet.getName());
        cv.put("Gender", pet.getGender());
        cv.put("Ns", pet.getNs());
        cv.put("Type", pet.getType());
        cv.put("Weight", pet.getWeight());
        cv.put("Information", pet.getInformation());
        cv.put("Picture", pet.getProfilePic());
        cv.put("Email", email);

        long report = db.insert("PETCARD_TABLE", null, cv);

        return (report != -1);
    }
/*
    public boolean addReview(Review rev, String email, String service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("email", email);
        cv.put("serviceID", service);
        cv.put("stars", rev.getRating());
        cv.put("title", rev.getTitle());
        cv.put("body", rev.getReview());
        cv.put("date", rev.getDate().toString());


        long report = db.insert("PETCARD_TABLE", null, cv);

        return (report != -1);
    }*/

    public ArrayList<String> getPetsInfo(String email){
        ArrayList<String> cards = new ArrayList<>();

        String q = "Select * From PETCARD_TABLE Where Email = \"" + email + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String ns = cursor.getString(3);
                String type = cursor.getString(4);
                String weight = cursor.getString(5);
                String information = cursor.getString(6);
                String pet = cursor.getString(6);

                String card = "Name: " + name + "\nGender: " + gender +
                        "\nNeutered/Spayed: " + ns + "\nType: " + type +
                        "\nWeight: " + weight + "\nInformation: " + information;

                cards.add(card);
            }while (cursor.moveToNext());
        }

    cursor.close();
        db.close();
        return cards;
    }

    public ArrayList<String> getPetsName(String email){
        ArrayList<String> names = new ArrayList<>();

        String q = "Select * From PETCARD_TABLE Where Email = \"" + email + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(1);

                names.add(name);
            }while (cursor.moveToNext());
        }



        cursor.close();
        db.close();
        return names;
    }



    public boolean deletePet(String name, String email){
        String q = "Delete From PETCARD_TABLE Where Email = \"" + email +
                "\" and Name =\"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(q);

        Cursor c = db.rawQuery(q, null);
        System.out.println(c.moveToFirst());
        if(c.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updatePet(String name, String email, String ns,
                             String weight, String information, String picture){
        String q = "Update PETCARD_TABLE"
                + " Set Ns = \"" + ns + "\","
                + " Weight = \"" + weight + "\","
                + " Information = \"" + information + "\","
                + " Picture = \"" + picture + "\""
                + " Where Email = \"" + email +
                "\" and Name =\"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(q);

        Cursor c = db.rawQuery(q, null);
        System.out.println(c.moveToFirst());
        if(c.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }


    public ArrayList<String> getPetsPic(String email){
        ArrayList<String> cards = new ArrayList<>();

        String q = "Select * From PETCARD_TABLE Where Email = \"" + email + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                String pic = cursor.getString(7);
                // String picture = cursor.getString(7);



                cards.add(pic);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cards;
    }

    public ArrayList<Float> getReviewStars(String serviceID){
        ArrayList<Float> cards = new ArrayList<>();

        String q = "Select * From REVIEW_TABLE Where serviceID = \"" + serviceID + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                Float star = cursor.getFloat(3);

                cards.add(star);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cards;
    }

    public ArrayList<String> getReviewInfo(String serviceID){
        ArrayList<String> cards = new ArrayList<>();

        String q = "Select * From REVIEW_TABLE Where serviceID = \"" + serviceID + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(4);
                String body = cursor.getString(5);
                String date = cursor.getString(6);
                String review = "Title:" + title
                        + "\nDetails:" + body +
                        "\nDate: " + date ;

                cards.add(review);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cards;
    }


    
    public boolean addHistory(History his, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Name", his.getName());
        cv.put("Amount", his.getAmount());
        cv.put("Price", his.getPrice());
        cv.put("Date", his.getDate());
        cv.put("Image", his.getImage());
        cv.put("Email", email);
        cv.put("Pet", his.getPet());

        long report = db.insert("HISTORY_TABLE", null, cv);

        return (report != -1);
    }

    public ArrayList<String> getHistoryInfo(String email){
        ArrayList<String> cards = new ArrayList<>();
        String q = "Select * From HISTORY_TABLE Where Email = \"" + email + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String price = cursor.getString(3);
                String date = cursor.getString(4);
                String pet = cursor.getString(6);
                // String picture = cursor.getString(7);

                String card = "Transaction: " + name + "\nAmount: " + amount +
                        "\nPrice $: " + price + "\nDate: " + date + "\nPet: " + pet;

                cards.add(card);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cards;
    }


    public ArrayList<String> getHistoryPic(String email){
        ArrayList<String> cards = new ArrayList<>();
        String q = "Select * From HISTORY_TABLE Where Email = \"" + email + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                String pic = cursor.getString(5);
                // String picture = cursor.getString(7);



                cards.add(pic);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cards;
    }

    public Boolean changePass(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("password", password);

        long report = db.update("USER_TABLE", cv, "email=?", new String[]{email});

        return (report != -1);
    }

    public String getUserByID(String userID){
        ArrayList<String> cards = new ArrayList<>();
        String q = "Select * From USER_TABLE Where Id = \"" + userID + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        String email="empty";
        if(cursor.moveToFirst()){
            email = cursor.getString(1);
        }

        cursor.close();
        db.close();
        return email;
    }

}
