package com.example.ivana.restaurantview.restoran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ivana.restaurantview.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListaRestorani extends AppCompatActivity {

    @BindView(R.id.recView)
    RecyclerView myRecyclerView;
    private RestoranAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new RestoranAdapter(this, new OnRestranClickListener() {
            @Override
            public void onRowClick(Restoran restoran, int position) {
                Intent intent = new Intent(ListaRestorani.this, RestoranDetali.class);
                intent.putExtra("extra", restoran);
                intent.putExtra("_pozicija", position);
                startActivity(intent);

            }
        });
        adapter.setItems(generateList());

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(adapter);


    }
            ArrayList<Restoran> generateList() {

                Restorani restorani = PreferencesManager.getRestoran(this);
                return restorani.restaurants;

            }
    @OnClick(R.id.addRest)
    public void addRestoran(View view){
        Intent intent = new Intent(this, AddRestorani.class);
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 ){
            adapter.setItems(generateList());
            adapter.notifyDataSetChanged();
        }
    }
}