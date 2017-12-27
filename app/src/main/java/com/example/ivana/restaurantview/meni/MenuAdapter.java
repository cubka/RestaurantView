package com.example.ivana.restaurantview.meni;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ivana.restaurantview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivana on 12/20/2017.
 */

   public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{

       Context context;
    OnMenuClickListener onMenuClickListener;
       List<Menu> menuList = new ArrayList<>();
    public void setItems (List<Menu> menuList1){
        menuList = menuList1;
    }
    public MenuAdapter(Context _context, OnMenuClickListener _onMenuClickListener) {
        context = _context;
        onMenuClickListener = _onMenuClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.activity_menu_row, parent , false);

        MenuAdapter.ViewHolder viewHolder = new MenuAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
         final Menu menu = menuList.get(position);
         holder.meniIme.setText(menu.foodname);
         holder.meniCena.setText(menu.price);

         if (menu.isIsveg() == true) {
             holder.meniVegan.setText("Vegan");
         }
         else {
             holder.meniVegan.setText("Ne e Vegan");
         }
         holder.menu_linear.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onMenuClickListener.onMenuClick(menu, position);

             }
         });

        Picasso.with(context).load(menu.getLink()).centerInside().fit().into(holder.meniSlika);

    }

    @Override
    public int getItemCount() {
            if(menuList == null)
                return 0;
            return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.slikaNaMeni)
        ImageView meniSlika;
        @BindView(R.id.menuIme)
        TextView meniIme;
        @BindView(R.id.menuCena)
        TextView meniCena;
        @BindView(R.id.menuVegan)
        TextView meniVegan;
        @BindView(R.id.lin2)
        LinearLayout menu_linear;
        public ViewHolder(View itemView) {


            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
