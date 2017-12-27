package com.example.ivana.restaurantview.restoran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.ivana.restaurantview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddRestorani extends AppCompatActivity {

      private Restorani restorani;

    @BindView(R.id.dodajName)
    EditText dodadenoIme;
    @BindView(R.id.dodajCity)
    EditText dodadenGrad;
    @BindView(R.id.dodajRating)
    EditText ocena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        restorani = PreferencesManager.getRestoran(this);

    }
    @OnClick(R.id.save)
    public void saveRestoran (View view) {

        Restoran dresorani = new Restoran();
        dresorani.setCity(dodadenGrad.getText().toString());
        dresorani.setName(dodadenoIme.getText().toString());
        dresorani.setRating(ocena.getText().toString());
        restorani.restaurants.add(dresorani);
        PreferencesManager.addRestoran(restorani,this);
        finish();
    }
}
