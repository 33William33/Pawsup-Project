package com.example.pawsupapplication.data.model.product;

/**
 * This class is the structure for the product, it is composed of strings that will handle
 * product name, seller, price, location, categories, rating, description, and
 * link to a picture.
 *
 * @author Lingfeng Yang
 */

public class Product {


    private Integer proId;
    private String productName;
    private String productQty;
    private String price;
    private String rating;
    private String picture;
    private String productID;

    public Product(String productName, String productQty, String price,
                   String rating, String picture, String productID){
        super();
        this.productName = productName;
        this.productQty = productQty;
        this.price = price;
        this.rating = rating;
        this.picture = picture;
        this.productID = productID;
    }

    public Integer getProductId() { return proId; }
    public void setProductId(Integer productId) { this.proId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductQty() { return productQty; }
    public void setProductQty(String productQty) { this.productQty = productQty; }
    public String getProductPrice() { return price; }
    public void setProductPrice(String price) { this.price = price; }
    public String getProductRating() { return rating; }
    public void setProductRating(String rating) { this.rating = rating; }
    public String getProductPicture() { return picture; }
    public void setProductPicture(String picture) { this.picture = picture; }
    public String getId() { return productID; }
    public void setId(String id) { this.productID = id; }
}