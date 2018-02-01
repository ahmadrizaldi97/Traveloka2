package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeAdmin extends AppCompatActivity {

    private TextView waktu, nama;
    private Button tambahMaskapai, konfirmasiMaskp, tambahPemilik, konfirmasiHotel, rekening, keluar, tambahAdmin;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        nama = (TextView)findViewById(R.id.p10);
        waktu = (TextView) findViewById(R.id.i1);
        tambahMaskapai = (Button)findViewById(R.id.i2);
        konfirmasiMaskp = (Button)findViewById(R.id.i3);
        tambahPemilik = (Button)findViewById(R.id.i4);
        konfirmasiHotel = (Button)findViewById(R.id.i5);
        rekening = (Button)findViewById(R.id.i6);
        keluar = (Button)findViewById(R.id.i7);
        tambahAdmin = (Button)findViewById(R.id.tambahAdmin);

        tambahAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdmin.this, RegisterAdmin.class);
                startActivity(i);
            }
        });

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String namaAdmin = sharedPref.getString("username","");

        String editNama = namaAdmin.substring(0, 1);
        editNama = editNama.toUpperCase();
        namaAdmin = editNama + namaAdmin.substring(1);

        nama.setText(namaAdmin);


        String _bookingDate = "";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 30);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyy");
        _bookingDate = dateFormat.format(c.getTime());

        waktu.setText(_bookingDate);

        tambahMaskapai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdmin.this, RegisterMaskapai.class);
                startActivity(i);
            }
        });

        konfirmasiMaskp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keKonfirmasiPembayaran = new Intent(HomeAdmin.this, KonfirmasiPembayaran.class);
                startActivity(keKonfirmasiPembayaran);
            }
        });

        tambahPemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdmin.this, RegisterHotel.class);
                startActivity(i);
            }
        });

        konfirmasiHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdmin.this, KonfirmasiPembayaranHotel.class);
                startActivity(i);
            }
        });
        
        rekening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdmin.this, TambahRekening.class);
                startActivity(i);
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences2.edit();
                edit.clear().commit();

                Intent keMenuAwal = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(keMenuAwal);
            }
        });

    }

    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
