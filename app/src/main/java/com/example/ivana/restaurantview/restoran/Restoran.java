package com.example.ivana.restaurantview.restoran;

import com.example.ivana.restaurantview.meni.Menu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ivana on 12/18/2017.
 */

public class Restoran implements Serializable {

    public String logo;
    public String city;
    public String name;
    public String rating;
    public ArrayList <Menu> menu;


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ArrayList<Menu> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Menu> menu) {
        this.menu = menu;
    }
}


