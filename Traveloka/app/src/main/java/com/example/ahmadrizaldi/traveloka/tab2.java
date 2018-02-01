package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmadrizaldi.traveloka.adapter.adapter_reservasi;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.example.ahmadrizaldi.traveloka.list

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 10/22/17.
 */

public class tab2 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Reservasi> reservasiArrayList = new ArrayList<>();
    private adapter_reservasi mAdapter;
    private DatabaseReference reservasi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.etktterbit_konfirmasi, container, false);
        return view;


    }

//    private void openBooking(String idreservasi_, String flights_id_, String kelas_, String bookingDate_, String total_, String pembayaranKe_, String status_, String Urk, String Bookingid){
//        Reservasi rev = new Reservasi(idreservasi_, flights_id_, kelas_, bookingDate_, total_, pembayaranKe_, status_, Urk, Bookingid);
//        reservasiArrayList.add(rev);
//        mAdapter.notifyDataSetChanged();
//    }

}
