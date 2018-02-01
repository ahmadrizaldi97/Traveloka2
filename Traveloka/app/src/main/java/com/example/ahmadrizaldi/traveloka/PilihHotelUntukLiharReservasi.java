package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.AdapterPilihHotel;
import com.example.ahmadrizaldi.traveloka.adapter.AdapterPilihHotel002;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PilihHotelUntukLiharReservasi extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Hotel> hotelArrayList = new ArrayList<>();
    private AdapterPilihHotel002 mAdapter;

    private DatabaseReference dataHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_hotel_untuk_lihar_reservasi);


        recyclerView = (RecyclerView)findViewById(R.id.recoooo);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new AdapterPilihHotel002(PilihHotelUntukLiharReservasi.this, hotelArrayList);
        recyclerView.setAdapter(mAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        dataHotel = FirebaseDatabase.getInstance().getReference("Hotel");

        dataHotel.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Hotel datahotel = dataSnapshot.getValue(Hotel.class);

                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                Bundle b = getIntent().getExtras();

                String idPemilik = sharedPref.getString("idHotel","").toString();

                if (idPemilik.equals(datahotel.getId_Pemilik().toString())){
                    tambahHotel(datahotel.getID_Hotel().toString(), datahotel.getNamaHotel().toString(), datahotel.getReputasi().toString(), datahotel.getLokasi().toString(),
                            datahotel.getFasilitas(), datahotel.getDeskripsi(), datahotel.getKebijakan(), datahotel.getLat(),
                            datahotel.getLang(), datahotel.getGM1().toString(), datahotel.getGM2().toString(), datahotel.getGM3().toString(),
                            datahotel.getGM4().toString(), datahotel.getGM5().toString(), datahotel.getAlamat(), datahotel.getId_Pemilik());

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

    private void tambahHotel(String ID_Hotel, String NamaHotel, String Reputasi, String Lokasi, String Fasilitas, String Deskripsi, String Kebijakan,
                             double lat, double lang, String g1, String g2 , String g3, String g4, String g5, String alamat, String pemilik) {
        Hotel hot = new Hotel(ID_Hotel , NamaHotel, Reputasi, Lokasi, Fasilitas, Deskripsi, Kebijakan, lat, lang, g1, g2, g3, g4, g5, alamat, pemilik);
        hotelArrayList.add(hot);

        mAdapter.notifyDataSetChanged();
    }
}
