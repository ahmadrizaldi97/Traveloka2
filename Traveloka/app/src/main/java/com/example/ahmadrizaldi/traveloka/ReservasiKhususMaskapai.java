package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.AdapterReservasiForMaskapai;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReservasiKhususMaskapai extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Reservasi> ress = new ArrayList<>();
    AdapterReservasiForMaskapai mAdapter;

    private DatabaseReference dataReservasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservasi_khusus_maskapai);

        dataReservasi = FirebaseDatabase.getInstance().getReference("Reservasi");

        recyclerView = (RecyclerView)findViewById(R.id.forMaskapai);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);


        SharedPreferences sharedPref2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final String maskap = sharedPref2.getString("username","");

        mAdapter = new AdapterReservasiForMaskapai(ReservasiKhususMaskapai.this, ress, maskap);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(itemDecoration);


        dataReservasi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reservasi newPost = dataSnapshot.getValue(Reservasi.class);

                if (newPost.getStatus().toString().equals("Konfirmasi") && newPost.getFlights_id().toString().indexOf(maskap) >= 0){
                    openBooking(newPost.getIdreservasi().toString(), newPost.getFlights_id().toString(), newPost.getKelas().toString(),
                            newPost.getBookingDate().toString(), newPost.getTotal().toString(),
                            newPost.getPembayaranKe().toString(), newPost.getStatus().toString(),
                            newPost.getUrl().toString(), newPost.getPNR() ,newPost.getEmail().toString(),
                            newPost.getPoinPending().toString(), newPost.getPotongPoin(),
                            newPost.getJumlahPenumpang(), newPost.getKodePembayaran(),
                            newPost.getReschedule(), newPost.getIDBaru());
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

    private void openBooking(String idreservasi_, String flights_id_, String kelas_, String bookingDate_, String total_, String pembayaranKe_, String status_, String Urk, String Bookingid, String emailss, String poinp, String potong, String jumlahPenumpang, String kodePembayaran, String resss, String gd){
        Reservasi rev = new Reservasi(idreservasi_, flights_id_, kelas_, bookingDate_, total_, pembayaranKe_, status_, Urk, Bookingid, emailss, poinp, potong, jumlahPenumpang, kodePembayaran, resss, gd);
        ress.add(rev);
        mAdapter.notifyDataSetChanged();
    }

}
