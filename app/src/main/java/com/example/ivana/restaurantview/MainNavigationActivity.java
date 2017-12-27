package com.example.ivana.restaurantview;

import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ivana.restaurantview.meni.Menu;
import com.example.ivana.restaurantview.restoran.PreferencesManager;
import com.example.ivana.restaurantview.restoran.Restorani;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainNavigationActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;
    int pos;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_activity);
        ButterKnife.bind(this);
        pos= getIntent().getIntExtra("gp",0);
        setUpViewPager();
    }

    public void setUpViewPager() {
       GaleryPageAdapter adapter = new GaleryPageAdapter(this.getSupportFragmentManager(), getList());
        pager.setAdapter(adapter);
    }

    ArrayList<Menu> getList() {
        Restorani menuImg = PreferencesManager.getRestoran(this);
        return  menuImg.restaurants.get(pos).getMenu();
    }




}
