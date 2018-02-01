package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeHotel extends AppCompatActivity {

    private Button tambahHotel, tambahRoom, roomList, reservasiList, keluar;
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_hotel);

        tambahHotel = (Button)findViewById(R.id.MS_tambahID1);
        tambahRoom = (Button)findViewById(R.id.MS_tambahPenerbangan1);
        roomList = (Button)findViewById(R.id.MS_DataRoom1);
        reservasiList = (Button)findViewById(R.id.MS_DataReservasi1);
        keluar = (Button)findViewById(R.id.MS_Exit1);
        welcome = (TextView)findViewById(R.id.MS_welcome1);

        tambahHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeHotel.this, TambahHotel.class);
                startActivity(i);
            }
        });

        tambahRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeHotel.this, PilihHotelUntukTambahRoom.class);
                startActivity(i);
            }
        });

        reservasiList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeHotel.this, PilihHotelUntukLiharReservasi.class);
                startActivity(i);
            }
        });

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String namaMaskapai = sharedPref.getString("namaHotel","");
        welcome.setText(namaMaskapai);

        roomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeHotel.this, DaftarHotel_Pemilik.class);
                startActivity(i);
            }
        });
        
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
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
