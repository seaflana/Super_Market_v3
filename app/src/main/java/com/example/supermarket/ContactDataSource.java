package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContactDataSource {

    private SQLiteDatabase database;
    private ContactDBHelper dbHelper;

    public ContactDataSource(Context context) {
        dbHelper = new ContactDBHelper(context);
    }

    //Open and close methods are used to access and end access to the database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //Methods for Inserting and Updating Contacts
    public boolean insertContact(SuperMarket c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("marketname", c.getMarketName());
            initialValues.put("streetaddress", c.getStreetAddress());
            initialValues.put("city", c.getCity());
            initialValues.put("state", c.getState());
            initialValues.put("zipcode", c.getZipCode());
            initialValues.put("liquorrating", c.getLiquorRating());
            initialValues.put("productrating", c.getProductRating());
            initialValues.put("meatrating", c.getMeatRating());
            initialValues.put("cheeserating", c.getCheeseRating());
            initialValues.put("easerating", c.getEaseRating());

            didSucceed = database.insert("supermarket.db", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean updateContact(SuperMarket c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getMarketID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("marketname", c.getMarketName());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipCode());
            updateValues.put("liquorrating", c.getLiquorRating());
            updateValues.put("productrating", c.getProductRating());
            updateValues.put("meatrating", c.getMeatRating());
            updateValues.put("cheeserating", c.getCheeseRating());
            updateValues.put("easerating", c.getEaseRating());

            didSucceed = database.update("supermarket", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    //Retrieve the new Contact ID
    public int getLastContactID() {
        int lastId;
        try {
            String query = "Select MAX(_id) from supermarket";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }
}
