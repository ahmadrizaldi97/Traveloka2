package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.adapter.Adapter_datapenerbanganMaskapai;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataPenerbangan_MSKP extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DetailDataPenerbangan> dataPenerbangen = new ArrayList<>();
    private Adapter_datapenerbanganMaskapai mAdapter;
    private String namaMaskapai;

    DatabaseReference D_dataPenerbangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_penerbangan__mskp);

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewdataPenerbangana);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_datapenerbanganMaskapai(DataPenerbangan_MSKP.this, dataPenerbangen);
        recyclerView.setAdapter(mAdapter);

        D_dataPenerbangan = FirebaseDatabase.getInstance().getReference("Detail Flights");

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        namaMaskapai = sharedPref.getString("username","");

        D_dataPenerbangan.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan detailDataPenerbangan = dataSnapshot.getValue(DetailDataPenerbangan.class);

                if (detailDataPenerbangan.getId().toString().indexOf(namaMaskapai) > 0){
                    tambahPenerbangan(detailDataPenerbangan.getId(), detailDataPenerbangan.getFlights_id(), detailDataPenerbangan.getFrom(), detailDataPenerbangan.getTo(), detailDataPenerbangan.getDates(), detailDataPenerbangan.getJamBerangkat(), detailDataPenerbangan.getJamTiba()
                    , detailDataPenerbangan.getKuota_E(),detailDataPenerbangan.getKuota_B(), detailDataPenerbangan.getKuota_F(), detailDataPenerbangan.getHarga_E(), detailDataPenerbangan.getHarga_B(), detailDataPenerbangan.getHarga_F(), detailDataPenerbangan.getMaxReschedule());
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


    }

    private void tambahPenerbangan(String id, String Flights_id, String From, String To, String Dates, String JamBerangkat, String JamTiba ,
                                   String Kuota_E, String Kuota_B, String Kuota_F,
                                   String Harga_E, String Harga_B, String Harga_F, String maxsssssss){
   DetailDataPenerbangan dat = new  DetailDataPenerbangan(id, Flights_id, From, To, Dates, JamBerangkat, JamTiba, Kuota_E, Kuota_B, Kuota_F,
                Harga_E, Harga_B, Harga_F, maxsssssss);
        dataPenerbangen.add(dat);

        mAdapter.notifyDataSetChanged();

    }

}
