package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.UserList;
import com.example.ahmadrizaldi.traveloka.model.passangerlist;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class Transfer extends AppCompatActivity {

    String Email_, NoRek;
    DatabaseReference DataReservasi, Rekening_;
    Button saveReservasi;
    TextView total_harga, TransaksiId, nomorrekening, namaRekening;
    ImageView gambarBank;

    String idTransaksi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);


        saveReservasi = (Button)findViewById(R.id.sayasudahbayar);
        total_harga = (TextView)findViewById(R.id.jumlahTotals);
        TransaksiId = (TextView)findViewById(R.id.bookingidTransf);
        nomorrekening = (TextView)findViewById(R.id.nomorrekening);
        namaRekening = (TextView)findViewById(R.id.textView30);
        gambarBank = (ImageView)findViewById(R.id.imageView4);


        DataReservasi = FirebaseDatabase.getInstance().getReference("Reservasi");
        Rekening_ = FirebaseDatabase.getInstance().getReference("Rekening");

        final Bundle bs = getIntent().getExtras();

        idTransaksi = bs.getString("idreservasi_").toString();

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        Email_ = sharedPref.getString("username",null);

        DataReservasi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reservasi res = dataSnapshot.getValue(Reservasi.class);

                if (bs.getString("idreservasi_").equals(res.getIdreservasi()) && res.getEmail().toString().equals(Email_)){
                    nomorrekening.setText(res.getPembayaranKe());
                    TransaksiId.setText("Tranasaksi ID : " + res.getIdreservasi());
                    total_harga.setText("Rp. " +res.getTotal());
                    NoRek = res.getPembayaranKe();
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

        Rekening_.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Rekening rek = dataSnapshot.getValue(Rekening.class);

                if (rek.getNomor().equals(NoRek)){
                    namaRekening.setText(rek.getAtasNama());
                    String jenis = rek.getNamaRekening();

                    if (rek.getNamaRekening().trim().equals("mandiri") || rek.getNamaRekening().trim().equals("MANDIRI")){
                        gambarBank.setImageResource(R.drawable.mandiri);
                    }else if (rek.getNamaRekening().trim().equals("bri") || rek.getNamaRekening().trim().equals("BRI")){
                        gambarBank.setImageResource(R.drawable.bri);
                    }else if (rek.getNamaRekening().trim().equals("bca") || rek.getNamaRekening().trim().equals("BCA")){
                        gambarBank.setImageResource(R.drawable.bca);
                    }else{
                        gambarBank.setImageResource(R.drawable.bni);
                    }
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


        saveReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keKonfirmasiPembayaran = new Intent(Transfer.this, MainM.class);

                HashMap<String, Object> result = new HashMap<>();
                result.put("status", "Bayar");

                FirebaseDatabase.getInstance().getReference().child("Reservasi").child(idTransaksi).updateChildren(result);


                startActivity(keKonfirmasiPembayaran);

            }
        });

    }



    public void onBackPressed() {

        Intent intent_ = new Intent();
        setResult(Activity.RESULT_OK, intent_);
        finish();
        moveTaskToBack(false);
    }



}


