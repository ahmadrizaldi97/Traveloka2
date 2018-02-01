package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BookingFragment extends Fragment {


    private String email;

    public BookingFragment() {
        // Required empty public constructor
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_booking, container, false);
//
//        TextView m = (TextView)view.findViewById(R.id.ahjaha);
//
//        m.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                FragmentHotel homeFragments = new FragmentHotel();
//                homeFragments.setEmail(email);
//                FragmentTransaction fragmentTransactions = getFragmentManager().beginTransaction();
//                fragmentTransactions.replace(R.id.content, homeFragments);
//                fragmentTransactions.commit();
//            }
//        });

        SharedPreferences sharedPref =  this.getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final String email = sharedPref.getString("username",null);

        BookingDashboard homeFragment = new BookingDashboard();
        homeFragment.setEmail(email);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, homeFragment);
        fragmentTransaction.commit();

        return view;
    }

}
