package com.example.booksofhumayun.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booksofhumayun.CatagoryHimu;
import com.example.booksofhumayun.CatagoryMisir;
import com.example.booksofhumayun.CatagoryShuvro;
import com.example.booksofhumayun.R;

public class HomeFragment extends Fragment {

    CardView catagory_himu, catagory_misir, catagory_shuvro;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        catagory_himu = v.findViewById(R.id.catagory_himu);
        catagory_misir = v.findViewById(R.id.catagory_misir);
        catagory_shuvro = v.findViewById(R.id.catagory_shuvro);

        catagory_himu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHimu = new Intent(getContext(), CatagoryHimu.class);
                intentHimu.putExtra("catagory","himu");
                startActivity(intentHimu);
            }
        });

        catagory_misir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMisir = new Intent(getContext(), CatagoryMisir.class);
                startActivity(intentMisir);
            }
        });

        catagory_shuvro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShuvro = new Intent(getContext(), CatagoryShuvro.class);
                startActivity(intentShuvro);
            }
        });

        return v;
    }
}
