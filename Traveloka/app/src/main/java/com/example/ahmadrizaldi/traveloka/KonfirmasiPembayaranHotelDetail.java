package com.example.ahmadrizaldi.traveloka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;
import com.example.ahmadrizaldi.traveloka.model.UserList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class KonfirmasiPembayaranHotelDetail extends AppCompatActivity {

    private TextView idReservasi, tanggalBooking, namaHotel, namaRoom, namaPemesan, emailPemesan, noHpPemesan, totalBayar;
    private ImageView buktiPembayaranl;
    private Button konfirmasi;

    private DatabaseReference dataReservasi, userss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konfirmasi_pembayaran_hotel_detail);

        idReservasi = (TextView)findViewById(R.id.textView33Hotel);
        tanggalBooking = (TextView)findViewById(R.id.textView40a1);
        namaHotel = (TextView)findViewById(R.id.namaHotelKonf);
        namaRoom = (TextView)findViewById(R.id.namaRoomKonf);
        namaPemesan = (TextView)findViewById(R.id.namaKonfHotel);
        emailPemesan = (TextView)findViewById(R.id.emailKonfHotel);
        noHpPemesan = (TextView)findViewById(R.id.noHpKonf);
        totalBayar = (TextView)findViewById(R.id.dha1721872);

        buktiPembayaranl = (ImageView)findViewById(R.id.buktiPembayaranHotelkonf);

        dataReservasi = FirebaseDatabase.getInstance().getReference("Reservasi Hotel");
        userss = FirebaseDatabase.getInstance().getReference("User");

        konfirmasi = (Button) findViewById(R.id.konfirmasi_book_hotel_konf);

        final Bundle bs = getIntent().getExtras();

        idReservasi.setText(bs.getString("idReservasi"));
        tanggalBooking.setText(bs.getString("bookingdate"));
        namaHotel.setText(bs.getString("namaHotel"));
        namaRoom.setText(bs.getString("namaRoom"));

        String dataPemesan = bs.getString("dataPemesan");
        int index = 0;
        int index2 =0;
        index = dataPemesan.indexOf("*");
        namaPemesan.setText(dataPemesan.substring(0, index));

        index2 = dataPemesan.indexOf("^");
        noHpPemesan.setText(dataPemesan.substring(index + 1, index2));

        emailPemesan.setText(dataPemesan.substring(index2 + 1));

        totalBayar.setText("Rp. " + bs.getString("total"));

        if (bs.getString("status").toString().trim().equals("Konfirmasi")){
            konfirmasi.setVisibility(View.INVISIBLE);
        }

        String alamatUrl = bs.getString("url");
        if (!alamatUrl.equals("---")){
            loadImageFromUrl(alamatUrl, buktiPembayaranl);

        }

        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SALTCHARS = "1234567890";
                StringBuilder salt = new StringBuilder();
                Random rnd = new Random();
                while (salt.length() < 10) { // length of the random string.
                    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                    salt.append(SALTCHARS.charAt(index));
                }
                String voucherFinal = salt.toString();

                TambahReservasiHotel(bs.getString("idReservasi"), bs.getString("email"), bs.getString("bookingdate"), bs.getString("idHotel"),
                        bs.getString("idRoom"), bs.getString("bank"), bs.getString("total"), bs.getString("kodePembayaran"),
                        bs.getString("poinTerpakai"), bs.getString("poinPending"),bs.getString("permintaanKhusus"), bs.getString("durasi"),
                        bs.getString("jumlahKamar"), bs.getString("namaHotel"), bs.getString("namaRoom"), bs.getString("dataPemesan"),
                        voucherFinal, bs.getString("url"), bs.getString("tanggalCheckIn"), bs.getString("checkOut"));

                konfirmasi.setVisibility(View.INVISIBLE);

                userss.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String emails =  bs.getString("email");

                        UserList newPost = dataSnapshot.getValue(UserList.class);
                        if (newPost.getEmail().equals(emails)){

                            int poinlama = Integer.parseInt(newPost.getPoin().toString());
                            int poinbaru = poinlama + Integer.parseInt(bs.getString("poinPending"));
                            update_user(dataSnapshot.getKey() ,emails, String.valueOf(poinbaru));
                            
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


        });

    }

    private void update_user(String key, String emai, String poi){
        UserList us = new UserList(emai, poi);
        userss.child(key).setValue(us);
    }

    private void TambahReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String
            total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus, String durasiss, String jumlahKamarss,
                                      String namaHotela, String namaRoomaa, String dataPemesana, String voucher, String urll, String tanggalCheckIn, String checkOut){
        ReservasiHotel res = new ReservasiHotel(idReservasi, email, bookingdate, idHotel, idRoom, bank, total, kodePembayaran,
                poinTerpakai, poinPending, permintaanKhusus, "Konfirmasi", durasiss, jumlahKamarss, namaHotela, namaRoomaa, dataPemesana,
                voucher, urll, tanggalCheckIn, checkOut);

        dataReservasi.child(idReservasi).setValue(res);
    }

    private void loadImageFromUrl(String uri, ImageView gambar){
        Picasso.with(KonfirmasiPembayaranHotelDetail.this).load(uri).placeholder(R.drawable.travelokalogo)
                .error(R.drawable.travelokalogo)
                .into(gambar, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

//        ImageView im =  Picasso.with(context).load(uri).placeholder(R.drawable.travelokalogo).error(R.drawable.travelokalogo);

    }
}
