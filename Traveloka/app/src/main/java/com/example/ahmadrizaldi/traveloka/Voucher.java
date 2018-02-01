package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel_Fix;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Voucher extends AppCompatActivity {

    private TextView namaPemesan, voucherKode, tanggalCheckIn, tanggalCheckOut, durasi,
            jumlkamar, namaHotel, alamatHotel, namaRoom, keteranganReservasi, catatanHote, selengkapnyaCatatan,tulisanSaja, detailHotel;
    private DatabaseReference dataHotel, dataRoom;

    FragmentMap homeFragment = new FragmentMap();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voucher);

        namaPemesan = (TextView)findViewById(R.id.namaPemesanV);
        voucherKode = (TextView)findViewById(R.id.status_booking_HotelV);
        tulisanSaja = (TextView)findViewById(R.id.Booka);
        detailHotel = (TextView)findViewById(R.id.lihatHotelDetailV);
        tanggalCheckIn = (TextView)findViewById(R.id.tanggalCheckInV);
        tanggalCheckOut = (TextView)findViewById(R.id.tanggalCheckOutV);
        durasi = (TextView)findViewById(R.id.jumlahMalamV);
        jumlkamar = (TextView)findViewById(R.id.jumlahKamarV);
        namaHotel = (TextView)findViewById(R.id.namaHotelV);
        alamatHotel = (TextView)findViewById(R.id.alamatV);
        namaRoom = (TextView)findViewById(R.id.namaKamarvV);
        keteranganReservasi = (TextView)findViewById(R.id.keteranganV);
        catatanHote = (TextView)findViewById(R.id.catatanPentingV);
        selengkapnyaCatatan = (TextView)findViewById(R.id.selengkapnyacatatanPentingV);

        dataHotel = FirebaseDatabase.getInstance().getReference("Hotel");
        dataRoom = FirebaseDatabase.getInstance().getReference("Room Hotel");

        final Bundle bs = getIntent().getExtras();

//
//        idRoom


        selengkapnyaCatatan.setVisibility(View.INVISIBLE);

        dataRoom.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                RoomHotel_Fix r = dataSnapshot.getValue(RoomHotel_Fix.class);

                if (r.getID_Room().equals(bs.getString("idRoom"))){
                    catatanHote.setText(r.getDeskripsiKamar());
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



        tanggalCheckOut.setText(bs.getString("checkOut"));
        tulisanSaja.setText("Booked & payable by traveloka");



        int index3;
        index3 = bs.getString("namaPemesan").toString().trim().indexOf("*");
        namaPemesan.setText(bs.getString("namaPemesan").toString().trim().substring(0, index3));
        voucherKode.setText("Voucher #" + bs.getString("kodeVoucher"));
        tanggalCheckIn.setText(bs.getString("checkINN"));
        durasi.setText(bs.getString("durasi") + " malam");
        jumlkamar.setText(bs.getString("jumlahKamar") + " kamar");
        namaHotel.setText(bs.getString("namaHotel"));
        alamatHotel.setText(bs.getString("alamatH"));
        namaRoom.setText(bs.getString("namaRoom"));
        keteranganReservasi.setText(bs.getString("jumlahKamar") + " kamar" + bs.getString("durasi") + " malam");
        catatanHote.setText(bs.getString("kebikakan"));

        selengkapnyaCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Voucher.this);
//                Bundle b = new Bundle();
//                b.putString("kebijakan", bs.getString("kebikakan"));
//                i.putExtras(b);
//                startActivity(i);
            }
        });

        detailHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Voucher.this, HotelDetail.class);
                Bundle b = new Bundle();

                b.putString("id_hotel", bs.getString("id_hotel").toString());
                b.putString("durasi", bs.getString("durasi").toString());
                b.putString("tanggalCheckIn", bs.getString("tanggalCheckIn").toString());
                b.putString("jumlahKamar", bs.getString("jumlahKamar").toString());
                b.putString("namaHotel", bs.getString("namaHotel").toString());
                b.putDouble("lat", bs.getDouble("lat") );
                b.putDouble("lang", bs.getDouble("lang"));
                b.putString("kebikakan", bs.getString("kebikakan").toString());
                b.putString("deskripsi_h", bs.getString("deskripsi_h").toString());
                b.putString("alamatH", bs.getString("alamatH").toString());
                b.putString("gm1", bs.getString("gm1").toString());
                b.putString("gm2", bs.getString("gm2").toString());
                b.putString("gm3", bs.getString("gm3").toString());
                b.putString("gm4", bs.getString("gm4").toString());
                b.putString("gm5", bs.getString("gm5").toString());
                i.putExtras(b);

                startActivity(i);
            }
        });


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content5, homeFragment);
        fragmentTransaction.commit();
        homeFragment.setLat(bs.getDouble("lat"));
        homeFragment.setLang(bs.getDouble("lang"));

//        b.putString("id_hotel", ba.getString("id_hotel").toString());
//        b.putString("durasi", ba.getString("durasi").toString());
//        b.putString("tanggalCheckIn", ba.getString("tanggalCheckIn"));
//        b.putString("jumlahKamar", ba.getString("jumlahKamar").toString());
//        b.putString("kebikakan",ba.getString("kebikakan").toString());
//        b.putString("namaHotel", ba.getString("namaHotel").toString());
//        b.putString("deskripsi_h", ba.getString("deskripsi_h").toString());
//        b.putString("alamatH", ba.getString("alamatH").toString());

    }
}
