package com.example.ivana.restaurantview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestoranDetali extends AppCompatActivity {

    @BindView(R.id.detaliSlika)
    ImageView restoranDetaliSlika;
    @BindView(R.id.detaliIme)
    TextView restoranDetaliIme;
    @BindView(R.id.detaliGrad)
    TextView restoranDetaliGrad;
    @BindView(R.id.detaliOcena)
    TextView restoranDetaliOcena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("extra")) {

            Restoran restorandetali = (Restoran) intent.getSerializableExtra("extra");

            Picasso.with(this).load(restorandetali.getLogo().toString()).centerInside().fit().into(restoranDetaliSlika);

            restoranDetaliIme.setText(restorandetali.getName());
            restoranDetaliGrad.setText(restorandetali.getCity());
            restoranDetaliOcena.setText(restorandetali.getRating());


        }
    }

}
