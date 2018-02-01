package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeMaskapai extends AppCompatActivity {

    private Button tambahF, tambahDataP, dataReservasi, Keluar, MS_Reschedule, MS_DataPenerbanganoo;
    private TextView MS_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_maskapai);

        tambahF = (Button)findViewById(R.id.MS_tambahID);
        tambahDataP = (Button)findViewById(R.id.MS_tambahPenerbangan);
        dataReservasi = (Button)findViewById(R.id.MS_DataReservasi);
        Keluar = (Button)findViewById(R.id.MS_Exit);
        MS_welcome = (TextView)findViewById(R.id.MS_welcome);
        MS_Reschedule = (Button)findViewById(R.id.MS_Reschedule);
        MS_DataPenerbanganoo = (Button)findViewById(R.id.MS_DataPenerbanganoo);

        MS_DataPenerbanganoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeMaskapai.this, ManagementFlightForMaskapai.class);
                startActivity(i);
            }
        });

        tambahF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeMaskapai.this, tambah_flights.class);
                startActivity(i);
            }
        });

        MS_Reschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeMaskapai.this, Konfirmasi_Reschedule.class);
                startActivity(i);
            }
        });

        dataReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeMaskapai.this, ReservasiKhususMaskapai.class);
                startActivity(i);
            }
        });

        tambahDataP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sad = new Intent(HomeMaskapai.this, TambahFlights_Maskapai.class);
                startActivity(sad);
            }
        });

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String namaMaskapai = sharedPref.getString("username","");
        MS_welcome.setText(namaMaskapai);

        Keluar.setOnClickListener(new View.OnClickListener() {
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
