package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ReviewHotel extends AppCompatActivity {

    private TextView namaHotel_rvw, namaHotel_rvw_2s, kebijakan, namaPemesan,
            hargaroom, kodepembayaran, total, pesanSingkat, tanggalCheckIn, tanggalCheckOut, hotelRoom, kontakTamu, durasii;
    private Button lanjutkanl;
    private String angka, kodePembayrn;
    private int hargaKeseluruhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_hotel);

        namaHotel_rvw = (TextView)findViewById(R.id.namaHotel_rvw);
        namaHotel_rvw_2s = (TextView)findViewById(R.id.namaHotel_rvw_2s);
        kebijakan = (TextView)findViewById(R.id.Kebijakan);
        namaPemesan = (TextView)findViewById(R.id.nama_tamu_rvw);
        hargaroom = (TextView)findViewById(R.id.harga_troom);
        kodepembayaran = (TextView)findViewById(R.id.kodeUnikHotel_rvw);
        total = (TextView)findViewById(R.id.total_hotel_rvw);
        pesanSingkat = (TextView)findViewById(R.id.textView64);
        tanggalCheckIn = (TextView)findViewById(R.id.tanggal_checkin_rvw);
        tanggalCheckOut = (TextView)findViewById(R.id.tanggal_checkout_rvw);
        hotelRoom = (TextView)findViewById(R.id.namaroom_aarvw);
        kontakTamu = (TextView)findViewById(R.id.kontak_tamu_rvw);
        durasii = (TextView)findViewById(R.id.durasi_checkout_rvw);

        lanjutkanl = (Button)findViewById(R.id.hotel_lanjut_ke_pembayaran_rvw);

        Bundle b = getIntent().getExtras();

        namaHotel_rvw.setText(b.getCharSequence("namaHotel").toString());
        namaHotel_rvw_2s.setText(b.getCharSequence("namaroom").toString());
        kebijakan.setText(b.getCharSequence("kebikakan"));
        namaPemesan.setText(b.getCharSequence("namaPemesan").toString());
        kontakTamu.setText(b.getString("noHp") + "/" + b.getString("email"));

        tanggalCheckIn.setText(b.getString("checkIn"));
        tanggalCheckOut.setText(b.getString("checkOut"));
        durasii.setText(b.getString("durasi") + " malam");

        hotelRoom.setText(b.getString("namaHotel") + " " + b.getString("namaroom")  +" (" + b.getString("jumlahKamar") +") Kmr " + " ("+ b.getString("durasi") +") Mlm" );

        hargaKeseluruhan = (Integer.parseInt(b.getString("harga")) * Integer.parseInt(b.getString("jumlahKamar"))) * Integer.parseInt(b.getString("durasi"));
        hargaroom.setText("Rp. " + String.valueOf(hargaKeseluruhan));

        String emailUser;

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        emailUser = sharedPref.getString("username","").toString();

        pesanSingkat.setText("Selesaikan transaksi dan akun (" + emailUser + ") akan mendapatkan " + b.getCharSequence("poin")+ " Poin");

        Random rand = new Random();
        int  kodePembayaran = rand.nextInt(9999) + 1;
        kodePembayrn = String.valueOf(kodePembayaran);

        kodepembayaran.setText("Rp. " +  String.valueOf(kodePembayaran));

        int  n1 = rand.nextInt(999) + 1;
        int  n2 = rand.nextInt(999) + 1;
        int  n3 = rand.nextInt(999) + 1;
        int  n4 = rand.nextInt(999) + 1;

        angka = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4);


        final int total_semua = hargaKeseluruhan +kodePembayaran;
        total.setText("Rp. " + String.valueOf(total_semua));

        lanjutkanl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ReviewHotel.this, PilihBankTransfer_Hotel.class);

                Bundle oo = new Bundle();
                Bundle ba = getIntent().getExtras();

                oo.putString("ID", ba.getString("ID").toString());
                oo.putString("ID_Transaksi", angka);
                oo.putString("IDRoom", ba.getString("IDRoom").toString());
                oo.putString("namaroom", ba.getString("namaroom").toString());
                oo.putString("harga", ba.getString("harga").toString());
                oo.putString("hargaKeseluruhan", String.valueOf(hargaKeseluruhan));
                oo.putString("checkIn", tanggalCheckIn.getText().toString().trim());
                oo.putString("checkOut", tanggalCheckOut.getText().toString());
                oo.putString("tanggalCheckIn",ba.getString("checkIn").toString() );
                oo.putString("noHp", ba.getString("noHp"));
                oo.putString("email", ba.getString("email"));
                oo.putString("permintaanKhusus", ba.getString("permintaanKhusus"));
                oo.putString("durasi", ba.getString("durasi").toString());
                oo.putString("jumlahKamar", ba.getString("jumlahKamar").toString());
                oo.putString("namaPemesan", namaPemesan.getText().toString());
                oo.putString("kebikakan",ba.getString("kebikakan").toString());
                oo.putString(("namaHotel"), ba.getString("namaHotel").toString());
                oo.putString("kodepembayaran",kodePembayrn);
                oo.putString("total",String.valueOf(total_semua));
                oo.putString(("poin"), ba.getString("poin").toString());

                i.putExtras(oo);
                startActivityForResult(i, 912);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 912){
            if (resultCode == RESULT_OK){
                finish();

            }
        }

    }
}
