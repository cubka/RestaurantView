package com.example.ivana.restaurantview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        ButterKnife.bind(this);
        restorani = PreferencesManager.getRestoran(this);

        restoran_pos = getIntent().getIntExtra("pos",0);

    }
   @OnClick(R.id.saveNewMenu)
    public void onSaveMenuClick (View v) {
        Menu newMenu = new Menu();
        newMenu.setFoodname(dodadenoMIme.getText().toString());
        newMenu.setPrice(dodadenaMCena.getText().toString());
        if (isVeganCheck.isChecked()){
            newMenu.setIsveg(true);
        }
        else newMenu.setIsveg(false);

        restorani.restaurants.get(restoran_pos).getMenu().add(newMenu);
        PreferencesManager.addRestoran(restorani,this);


        finish();
   }
}
