package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.Adapter_Hotel_For_PemilikHotel;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DaftarHotel_Pemilik extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Hotel> hotelList = new ArrayList<>();
    Adapter_Hotel_For_PemilikHotel mAdapter;

    DatabaseReference dataHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_hotel_pemilik);

        dataHotel = FirebaseDatabase.getInstance().getReference("Hotel");

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewkedua_keempat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Hotel_For_PemilikHotel(DaftarHotel_Pemilik.this, hotelList);
        recyclerView.setAdapter(mAdapter);

        dataHotel.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Hotel datahotel = dataSnapshot.getValue(Hotel.class);

                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String pemilik = sharedPref.getString("idHotel","");


                if (pemilik.equals(datahotel.getId_Pemilik().toString().trim())) {

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
        hotelList.add(hot);

        mAdapter.notifyDataSetChanged();
    }
}
