package com.example.supermarket;

public class Contact {

    private int contactID;
    private String contactName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private int liquorRating;
    private int productRating;
    private int meatRating;
    private int cheeseRating;
    private int easeRating;

    public Contact() {
        contactID = -1;
    }

    public int getLiquorRating() {
        return liquorRating;
    }

    public void setLiquorRating(int liquorRating) {
        this.liquorRating = liquorRating;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    public int getMeatRating() {
        return meatRating;
    }

    public void setMeatRating(int meatRating) {
        this.meatRating = meatRating;
    }

    public int getCheeseRating() {
        return cheeseRating;
    }

    public void setCheeseRating(int cheeseRating) {
        this.cheeseRating = cheeseRating;
    }

    public int getEaseRating() {
        return easeRating;
    }

    public void setEaseRating(int easeRating) {
        this.easeRating = easeRating;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
