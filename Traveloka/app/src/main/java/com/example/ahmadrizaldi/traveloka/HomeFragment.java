package com.example.ahmadrizaldi.traveloka;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ahmadrizaldi.traveloka.adapter.CustomSwipeAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        adapter = new CustomSwipeAdapter(this.getActivity());
        context = this.getActivity();
        viewPager.setAdapter(adapter);

        Button b = (Button)view.findViewById(R.id.cariPesawat);
        Button hotel = (Button)view.findViewById(R.id.CariHotel);
        Button kereta = (Button)view.findViewById(R.id.Kereta);
        Button pesawathotel = (Button)view.findViewById(R.id.FlightandHotel);
        Button pulsa = (Button)view.findViewById(R.id.pulsa);
        Button Aktivasi = (Button)view.findViewById(R.id.aktivasi);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context , SearchFlights.class);
                startActivity(i);
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CariHotel.class);
                startActivity(i);
            }
        });

        return view;

    }

}
