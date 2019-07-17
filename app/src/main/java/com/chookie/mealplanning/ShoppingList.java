package com.chookie.mealplanning;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes;

public class ShoppingList {
    public String id;
    public ArrayList<String> ingredients;
    public String date;

    public ShoppingList(String id, String date, ArrayList<String> ingredients) {
        this.id = id;
        this.date = date;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }
}

