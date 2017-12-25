package com.example.ivana.restaurantview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuDetali extends AppCompatActivity {

    @BindView(R.id.menuDetaliSlika)
    ImageView meni_detali_slika;
    @BindView(R.id.menuDetaliIme)
    TextView menu_detali_ime;
    @BindView(R.id.menuDetaliCena)
    TextView menu_detali_cena;

    Menu menu_detali;
    int restoran_position;
    int menu_position;
    Restoran restoran;
    Menu edd_detali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detali);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        if (intent.hasExtra("menu")) {


            menu_detali = (Menu) intent.getSerializableExtra("menu");
            restoran_position = intent.getIntExtra("res_position", 0);
            menu_position = intent.getIntExtra("menu_pos", 0);
            if (menu_detali.getLink() != null && !menu_detali.getLink().isEmpty())
                Picasso.with(this).load(menu_detali.getLink().toString()).centerInside().fit().into(meni_detali_slika);

            menu_detali_ime.setText(menu_detali.getFoodname());
            menu_detali_cena.setText(menu_detali.getPrice());


        }

    }

    @OnClick(R.id.edit_menu)
    public void edit_menu_click(View view) {
        Intent editMenu = new Intent(MenuDetali.this, AddMenu.class);
        editMenu.putExtra("edd", menu_detali);
        editMenu.putExtra("edd_r_pos", restoran_position);
        editMenu.putExtra("edd_m_pos", menu_position);
        startActivityForResult(editMenu, 1002);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1002 && resultCode == RESULT_OK) {

            if (data.hasExtra("eddMenu")) {
                menu_detali = (Menu) data.getExtras().getSerializable("eddMenu");
                menu_detali_ime.setText(menu_detali.getFoodname().toString());
                menu_detali_cena.setText(menu_detali.getPrice().toString());

                if (menu_detali.getLink() != null && !menu_detali.getLink().isEmpty())
                    Picasso.with(this).load(menu_detali.getLink().toString()).centerInside().fit().into(meni_detali_slika);

                menu_detali_ime.setText(menu_detali.getFoodname());
                menu_detali_cena.setText(menu_detali.getPrice());

            }


        }
    }
}

