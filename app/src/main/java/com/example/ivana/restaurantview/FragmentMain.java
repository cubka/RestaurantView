package com.example.ivana.restaurantview;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by tasev on 12/5/17.
 */

public class FragmentMain extends Fragment {


    private Unbinder mUnbinder;

    @BindView(R.id.image)
    ImageView imageView;

    public static FragmentMain newInstance(String link) {

        Bundle args = new Bundle();
        args.putString("imageUrl", link);
        FragmentMain fragment = new FragmentMain();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, null);
        mUnbinder = ButterKnife.bind(this, view);
        if (getArguments() != null && getArguments().getString("imageUrl") != null) {
            Picasso.with(getActivity()).load(getArguments().getString("imageUrl")).centerInside().fit().into(imageView);
        }


        return view;

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
