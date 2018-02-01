package com.example.ahmadrizaldi.traveloka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.AdapterReservasiForHotel;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReservasiHotel_PemilikHotel extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ReservasiHotel> reservasiHotels = new ArrayList<>();
    AdapterReservasiForHotel mAdapter;

    private DatabaseReference dataBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi_hotel__pemilik_hotel);

        dataBooking = FirebaseDatabase.getInstance().getReference("Reservasi Hotel");

        final Bundle b = getIntent().getExtras();

        recyclerView = (RecyclerView)findViewById(R.id.resevasihotel_pemilik);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterReservasiForHotel(ReservasiHotel_PemilikHotel.this, reservasiHotels,b );
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(itemDecoration);

        dataBooking.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ReservasiHotel res = dataSnapshot.getValue(ReservasiHotel.class);


                if (b.getString("id_hotel").toString().equals(res.getIdHotel().toString()) && res.getStatus().toString().equals("Konfirmasi")) {
                    TambahReservasiHotel(res.getIdReservasi(), res.getEmail(), res.getBookingdate(),
                            res.getIdHotel(), res.getIdRoom(), res.getBank(), res.getTotal(),
                            res.getKodePembayaran(), res.getPoinTerpakai(), res.getPoinPending(),
                            res.getPermintaanKhusus(), res.getDurasi(), res.getJumlahKamar(), res.getNamaHotel(),
                            res.getNamaRoom(), res.getDataPemesan(), res.getStatus(), res.getVoucher(), res.getUrl(),
                            res.getTanggalCheckIn(), res.getTanggalCheckOut());
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

    private void TambahReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String
            total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus,
                                      String durasii, String jumlahKamarr, String namaHotell, String namaRooma,
                                      String dataPemesana, String statys, String voucherr, String urll, String checIn, String checkOut){
        ReservasiHotel res = new ReservasiHotel(idReservasi, email, bookingdate, idHotel, idRoom, bank, total,
                kodePembayaran, poinTerpakai, poinPending, permintaanKhusus, statys, durasii, jumlahKamarr, namaHotell, namaRooma, dataPemesana,
                voucherr, urll, checIn, checkOut);

        reservasiHotels.add(res);
        mAdapter.notifyDataSetChanged();
    }
}
