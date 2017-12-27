package com.example.ivana.restaurantview.restoran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivana.restaurantview.MainNavigationActivity;
import com.example.ivana.restaurantview.MenuDetali;
import com.example.ivana.restaurantview.R;
import com.example.ivana.restaurantview.meni.AddMenu;
import com.example.ivana.restaurantview.meni.Menu;
import com.example.ivana.restaurantview.meni.MenuAdapter;
import com.example.ivana.restaurantview.meni.OnMenuClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestoranDetali extends AppCompatActivity {

    @BindView(R.id.detaliSlika)
    ImageView restoranDetaliSlika;
    @BindView(R.id.detaliIme)
    TextView restoranDetaliIme;
    @BindView(R.id.detaliGrad)
    TextView restoranDetaliGrad;
    @BindView(R.id.detaliOcena)
    TextView restoranDetaliOcena;
    @BindView(R.id.menuview)
    RecyclerView menuRecycler;
    private MenuAdapter menuAdapter;
    Restoran restorandetali;
    int res_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("extra")) {

            restorandetali = (Restoran) intent.getSerializableExtra("extra");
            res_pos = intent.getIntExtra("_pozicija", 0);


            if (restorandetali.getLogo() != null && !restorandetali.getLogo().isEmpty())
                Picasso.with(this).load(restorandetali.getLogo().toString()).centerInside().fit().into(restoranDetaliSlika);

            restoranDetaliIme.setText(restorandetali.getName());
            restoranDetaliGrad.setText(restorandetali.getCity());
            restoranDetaliOcena.setText(restorandetali.getRating());

        }

        menuAdapter = new MenuAdapter(this, new OnMenuClickListener() {
            @Override
            public void onMenuClick(Menu menu, int _position) {
                Intent menu_intent = new Intent(RestoranDetali.this, MenuDetali.class);
                menu_intent.putExtra("menu", menu);
                menu_intent.putExtra("res_position", res_pos);
                menu_intent.putExtra("menu_pos", _position);
                startActivityForResult(menu_intent,1001);
            }
        });
        menuAdapter.setItems(generateList());

        menuRecycler.setHasFixedSize(true);
        menuRecycler.setLayoutManager(new LinearLayoutManager(this));
        menuRecycler.setAdapter(menuAdapter);
    }

    ArrayList<Menu> generateList() {
        return restorandetali.getMenu();
    }

    @OnClick(R.id.addMenu)
    public void addMeni(View view) {
        Intent intent = new Intent(this, AddMenu.class);
        intent.putExtra("pos", res_pos);
        startActivityForResult(intent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            restorandetali = PreferencesManager.getRestoran(this).restaurants.get(res_pos);

            menuAdapter.setItems(generateList());
            menuAdapter.notifyDataSetChanged();


            }
        else if (requestCode == 1003 && resultCode == RESULT_OK) {
            restorandetali = PreferencesManager.getRestoran(this).restaurants.get(res_pos);
            restoranDetaliIme.setText(restorandetali.getName());
            restoranDetaliGrad.setText(restorandetali.getCity());
            restoranDetaliOcena.setText(restorandetali.getRating());

            menuAdapter.setItems(restorandetali.getMenu());
            menuAdapter.notifyDataSetChanged();


        }


        }


    @OnClick(R.id.galery)
    public void enterGalery (View viewGalery){

        Intent galery = new Intent(RestoranDetali.this,MainNavigationActivity.class);
        galery.putExtra("gp",res_pos);
        startActivity(galery);
    }
    @OnClick(R.id.edit_restoran)
    public void editRestoran (View edit){
        Intent editRest = new Intent(RestoranDetali.this, EditRestoran.class);
        editRest.putExtra("edit_res",restorandetali);
        editRest.putExtra("RE",res_pos);
        startActivityForResult(editRest, 1003);
    }
}