package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.AdapterFlightForMaskapai;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManagementFlightForMaskapai extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DetailDataPenerbangan> listData = new ArrayList<>();
    AdapterFlightForMaskapai mAdapter;

    private DatabaseReference dataPenerbangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_for_maskapai);

        recyclerView = (RecyclerView)findViewById(R.id.rey);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mAdapter = new AdapterFlightForMaskapai(ManagementFlightForMaskapai.this, listData);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(itemDecoration);

        dataPenerbangan = FirebaseDatabase.getInstance().getReference("Detail Flights");

        dataPenerbangan.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan newPost = dataSnapshot.getValue(DetailDataPenerbangan.class);

                SharedPreferences sharedPref2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                final String maskap = sharedPref2.getString("username","");


                if (newPost.getId().toString().indexOf(maskap) >= 0) {

                    tambahPenerbangan(newPost.getId().toString(), newPost.getFlights_id().toString().trim(), newPost.getFrom().toString().trim(),
                            newPost.getTo().toString().trim(), newPost.getDates().toString().trim(),
                            newPost.getJamBerangkat().toString().trim(), newPost.getJamTiba().toString().trim(),
                            newPost.getKuota_E().toString().trim(), newPost.getKuota_B().toString().trim(),
                            newPost.getKuota_F().toString().trim(), newPost.getHarga_E().toString().trim(),
                            newPost.getHarga_B().toString().trim(), newPost.getHarga_F().toString().trim(), newPost.getMaxReschedule());
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        System.out.println(darimana_ms + kemana_ms);



//        tambahPenerbangan();

    }




    private void tambahPenerbangan(String id, String Flights_id, String From, String To, String Dates, String JamBerangkat, String JamTiba ,
                                   String Kuota_E, String Kuota_B, String Kuota_F,
                                   String Harga_E, String Harga_B, String Harga_F, String maxsssssss){

        //private String Flights_id, From, To, Dates, JamBerangkat, JamTiba , Kuota_E, Kuota_B, Kuota_F, Harga_E, Harga_B, Harga_F;

        DetailDataPenerbangan dat = new  DetailDataPenerbangan(id, Flights_id, From, To, Dates, JamBerangkat, JamTiba, Kuota_E, Kuota_B, Kuota_F,
                Harga_E, Harga_B, Harga_F, maxsssssss);
        listData.add(dat);

        mAdapter.notifyDataSetChanged();

    }
}
