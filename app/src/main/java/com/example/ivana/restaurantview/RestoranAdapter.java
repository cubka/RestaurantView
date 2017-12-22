package com.example.ivana.restaurantview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivana on 12/18/2017.
 */

public class RestoranAdapter extends RecyclerView.Adapter<RestoranAdapter.ViewHolder> {
    Context context;
    OnRestranClickListener onRestranClickListener;

    List<Restoran> restoranList = new ArrayList<>();

    public void setItems (List<Restoran> restoranList1){
        restoranList = restoranList1;
    }
    public RestoranAdapter(Context _context, OnRestranClickListener _onRestranClickListener){
        context=_context;
        onRestranClickListener = _onRestranClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.activity_row, parent , false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Restoran restorant = restoranList.get(position);
        holder.restoranIme.setText(restorant.name);
        holder.restoranOcena.setText(restorant.rating);
        holder.laymain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRestranClickListener.onRowClick(restorant, position);
            }
        });

        Picasso.with(context).load(restorant.getLogo()).centerInside().fit().into(holder.restoranSlika);

    }

    @Override
    public int getItemCount() {

            return restoranList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imeNaRestoran)
        TextView restoranIme;
        @BindView(R.id.ocenaNaRestoran)
        TextView restoranOcena;
        @BindView(R.id.slikaNaRestoran)
        ImageView restoranSlika;
        @BindView(R.id.lin)
        LinearLayout laymain;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
