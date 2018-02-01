package com.example.ahmadrizaldi.traveloka;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.ahmadrizaldi.traveloka.adapter.adapter_reservasi;
import com.example.ahmadrizaldi.traveloka.adapter.adapter_tujuanataudari;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;

import java.util.ArrayList;

public class KonfirmasiPembayaran extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Reservasi> reservasiArrayList = new ArrayList<>();
    private adapter_reservasi mAdapter;
    private DatabaseReference reservasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewkonfirmasipembayaran);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        reservasi = FirebaseDatabase.getInstance().getReference("Reservasi");

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        mAdapter = new adapter_reservasi(KonfirmasiPembayaran.this, reservasiArrayList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(itemDecoration);

        reservasi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                final ProgressDialog dialog = new ProgressDialog(this);
//                dialog.setTitle("Upload Gambar");
//                dialog.show();

                Reservasi newPost = dataSnapshot.getValue(Reservasi.class);
                openBooking(newPost.getIdreservasi().toString(), newPost.getFlights_id().toString(), newPost.getKelas().toString(),
                        newPost.getBookingDate().toString(), newPost.getTotal().toString(),
                        newPost.getPembayaranKe().toString(), newPost.getStatus().toString(), newPost.getUrl().toString(), newPost.getPNR(),
                        newPost.getEmail(), newPost.getPoinPending(), newPost.getPotongPoin(), newPost.getJumlahPenumpang(), newPost.getKodePembayaran(), newPost.getReschedule(), newPost.getIDBaru());

//                dialog.dismiss();

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

    //String idreservasi, String flights_id, String kelas, String bookingDate, String total, String pembayaranKe, String status
    private void openBooking(String idreservasi_, String flights_id_, String kelas_, String bookingDate_, String total_, String pembayaranKe_, String status_, String Urk, String Bookingid, String emaillaa, String poinnaa, String potong, String jumlahPenumpang, String kodePembayaran, String rescc, String baru){
        Reservasi rev = new Reservasi(idreservasi_, flights_id_, kelas_, bookingDate_, total_, pembayaranKe_, status_, Urk, Bookingid, emaillaa, poinnaa, potong, jumlahPenumpang, kodePembayaran, rescc, baru);
        reservasiArrayList.add(rev);
        mAdapter.notifyDataSetChanged();
    }

}
