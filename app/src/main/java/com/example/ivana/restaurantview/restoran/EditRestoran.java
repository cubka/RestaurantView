package com.example.ivana.restaurantview.restoran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ivana.restaurantview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditRestoran extends AppCompatActivity {

    @BindView(R.id.edit_res_img)
    ImageView resImgView;
    @BindView(R.id.edit_res_name)
    EditText e_rest_name;
    @BindView(R.id.edit_res_grad)
    EditText e_rest_city;
    @BindView(R.id.edit_res_rating)
    EditText e_rest_rat;

    Restoran editRestoran;
    Restoran restoran;
    int position;
    Restorani restorani;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restoran);
        ButterKnife.bind(this);

        restorani = PreferencesManager.getRestoran(this);

        Intent intent = getIntent();

        if (intent.hasExtra("edit_res")){

            editRestoran = (Restoran) intent.getSerializableExtra("edit_res");

            position = intent.getIntExtra("RE",0);
            e_rest_name.setText(editRestoran.getName().toString());
            e_rest_city.setText(editRestoran.getCity().toString());
            e_rest_rat.setText(editRestoran.getRating().toString());


        }
    }

    @OnClick(R.id.save_edit_res)
    public void save_edit_rest (View view){
        editRestoran.setName(e_rest_name.getText().toString());
        editRestoran.setCity(e_rest_city.getText().toString());
        editRestoran.setRating(e_rest_rat.getText().toString());
        restorani.restaurants.set(position,editRestoran);
        PreferencesManager.addRestoran(restorani, this);
        Intent i = new Intent(EditRestoran.this,RestoranDetali.class);
        setResult(RESULT_OK,i);
        finish();
    }
}
