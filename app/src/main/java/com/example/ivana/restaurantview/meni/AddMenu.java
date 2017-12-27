package com.example.ivana.restaurantview.meni;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.ivana.restaurantview.MenuDetali;
import com.example.ivana.restaurantview.R;

import com.example.ivana.restaurantview.restoran.PreferencesManager;
import com.example.ivana.restaurantview.restoran.Restorani;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMenu extends AppCompatActivity {



    @BindView(R.id.menuDodadenaSlika)
    ImageView dodadenaMSlika;
    @BindView(R.id.dodadenoImeMeni)
    EditText dodadenoMIme;
    @BindView(R.id.dodadenaCenaMeni)
    EditText dodadenaMCena;
    @BindView(R.id.dodadenaVrednostVegan)
    CheckBox isVeganCheck;
    Restorani restorani;
    int restoran_pos;
    Menu newMenu;
    @BindView(R.id.saveNewMenu)
    Button save_new_menu;
    int menu_pos;
    @BindView(R.id.save_edd_menu)
            Button saveEdMenu;
    Intent i;
    Menu oldMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        ButterKnife.bind(this);
        restorani = PreferencesManager.getRestoran(this);

        restoran_pos = getIntent().getIntExtra("pos",0);

        i = getIntent();

               if (i.hasExtra("edd")){

                   oldMenu = (Menu) i.getSerializableExtra("edd");
            restoran_pos = i.getIntExtra("edd_r_pos",0);
            menu_pos = i.getIntExtra("edd_m_pos",0);
                   dodadenoMIme.setText(oldMenu.getFoodname().toString());
                   dodadenaMCena.setText(oldMenu.getPrice().toString());

//                   if (oldMenu.getLink() != null && !oldMenu.getLink().isEmpty())
//                   Picasso.with(this).load(oldMenu.getLink().toString()).centerInside().fit().into(dodadenaMSlika);


               }

           }
       @OnClick(R.id.saveNewMenu)
       public void onSaveMenuClick (View v) {

           newMenu = new Menu();
           newMenu.setFoodname(dodadenoMIme.getText().toString());
           newMenu.setPrice(dodadenaMCena.getText().toString());
            newMenu.setLink("https://pbs.twimg.com/profile_images/2215476833/homa_logo_400x400.png");


           if (isVeganCheck.isChecked()) {
               newMenu.setIsveg(true);
           } else newMenu.setIsveg(false);




       ArrayList<Menu> menus = restorani.restaurants.get(restoran_pos).getMenu();
       if (menus == null) {
           menus = new ArrayList<>();
       }
       menus.add(newMenu);
       restorani.restaurants.get(restoran_pos).setMenu(menus);
       PreferencesManager.addRestoran(restorani, this);
           setResult(RESULT_OK);


           finish();

   }

    @OnClick(R.id.save_edd_menu)
    public void s (View v) {


        newMenu = new Menu();
        newMenu.setFoodname(dodadenoMIme.getText().toString());
        newMenu.setPrice(dodadenaMCena.getText().toString());
        newMenu.setLink(oldMenu.getLink());
        if (newMenu.getLink() != null && !newMenu.getLink().isEmpty())
            Picasso.with(this).load(newMenu.getLink().toString()).centerInside().fit().into(dodadenaMSlika);

        if (isVeganCheck.isChecked()) {
            newMenu.setIsveg(true);
        } else newMenu.setIsveg(false);


        ArrayList<Menu> menus = restorani.restaurants.get(restoran_pos).getMenu();
        if (menus == null) {
            menus = new ArrayList<>();
        }
        menus.set(menu_pos,newMenu);
        restorani.restaurants.get(restoran_pos).setMenu(menus);
        PreferencesManager.addRestoran(restorani, this);
        Intent intent = new Intent(AddMenu.this,MenuDetali.class);
        intent.putExtra("eddMenu",newMenu);
        setResult(RESULT_OK,intent);



        finish();

    }



}
