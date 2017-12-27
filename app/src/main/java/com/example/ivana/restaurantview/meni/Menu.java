package com.example.ivana.restaurantview.meni;

import java.io.Serializable;

/**
 * Created by Ivana on 12/18/2017.
 */

public class Menu implements Serializable {

    public String link;
    public String price;
    public String foodname;
    public boolean isveg;

    public Menu() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public boolean isIsveg() {
        return isveg;
    }

    public void setIsveg(boolean isveg) {
        this.isveg = isveg;
    }
}
