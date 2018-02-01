package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.Adapter_Booking_Hotel;
import com.example.ahmadrizaldi.traveloka.adapter.Adapter_KonfirmasiBooking_Hotel;
import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class KonfirmasiPembayaranHotel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ReservasiHotel> reservasiHotels = new ArrayList<>();
    private Adapter_KonfirmasiBooking_Hotel mAdapter;

    private DatabaseReference datareservasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konfirmasi_pembayaran_hotel);

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewkonfirmasipembayaranHotel);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new Adapter_KonfirmasiBooking_Hotel(KonfirmasiPembayaranHotel.this, reservasiHotels);
        recyclerView.setAdapter(mAdapter);

        datareservasi = FirebaseDatabase.getInstance().getReference("Reservasi Hotel");

        datareservasi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ReservasiHotel res = dataSnapshot.getValue(ReservasiHotel.class);


                if (res.getStatus().toString().trim().equals("Bayar") || res.getStatus().toString().trim().equals("Bayar*") ||
                        res.getStatus().toString().trim().equals("Konfirmasi")){

                    TambahReservasiHotel(res.getIdReservasi(), res.getEmail(), res.getBookingdate(),
                            res.getIdHotel(), res.getIdRoom(), res.getBank(), res.getTotal(),
                            res.getKodePembayaran(), res.getPoinTerpakai(), res.getPoinPending(),
                            res.getPermintaanKhusus(), res.getDurasi(), res.getJumlahKamar(),
                            res.getNamaHotel(), res.getNamaRoom(), res.getDataPemesan(), res.getVoucher(),
                            res.getStatus(), res.getUrl(), res.getTanggalCheckIn(), res.getTanggalCheckOut());

                }

                System.out.println(res.getStatus());
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

    private void TambahReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom,
                                      String bank, String total, String kodePembayaran, String poinTerpakai, String poinPending,
                                      String permintaanKhusus, String durasii, String jumlahKamarr, String namaHotell,
                                      String namaRooma, String dataPemesana, String vouch, String statuss, String urll, String tanggalCheckIn, String checkOut){
        ReservasiHotel res = new ReservasiHotel(idReservasi, email, bookingdate, idHotel, idRoom, bank, total, kodePembayaran,
                poinTerpakai, poinPending, permintaanKhusus, statuss, durasii, jumlahKamarr, namaHotell,
                namaRooma, dataPemesana, vouch, urll, tanggalCheckIn, checkOut);

        reservasiHotels.add(res);
        mAdapter.notifyDataSetChanged();
    }
}
