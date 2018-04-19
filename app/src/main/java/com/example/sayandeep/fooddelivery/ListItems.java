package com.example.sayandeep.fooddelivery;

/**
 * Created by Admin on 19-04-2018.
 */

public class ListItems {
    String username, location;

    public ListItems(String username, String location) {
        this.username = username;
        this.location = location;
    }

    public String getUsernameName() {
        return username;
    }

    public String getLocation() {
        return location;
    }
}
