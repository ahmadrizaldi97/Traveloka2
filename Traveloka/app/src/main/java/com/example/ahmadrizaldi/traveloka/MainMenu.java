package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    private Button keluar, caripenerbangan, ListBooking, Konfirmasi, konfirmasiH,
            reschedule, tambah, tambahHotel, tambah_pemilikHotel, carihotela, tambahrekening,
            registrasiMaskapai, flightid, bookingHotel;
    Button tambahroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if (sharedPref.getString("username","").isEmpty()){
            Intent keMainMenu = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(keMainMenu);
            finish();
        }



        reschedule = (Button)findViewById(R.id.reschedule_button_empat__);

        tambahrekening = (Button)findViewById(R.id.tambahrekening);

        keluar = (Button)findViewById(R.id.logout_);
        caripenerbangan = (Button)findViewById(R.id.CariPenerbangan);
        ListBooking = (Button)findViewById(R.id.listBooking__);
        Konfirmasi = (Button)findViewById(R.id.konfirmasi_pembayaran);
        tambah = (Button)findViewById(R.id.TambahPenerbangannnnns);
        tambahHotel = (Button)findViewById(R.id.tambahHotel_button_empat__);
        carihotela = (Button)findViewById(R.id.carihotela);
        registrasiMaskapai = (Button)findViewById(R.id.TambahMaskapai_);
        flightid = (Button)findViewById(R.id.TambahflightidMaskapai_);
        bookingHotel = (Button)findViewById(R.id.bookingHotel);
        konfirmasiH = (Button)findViewById(R.id.konfirmasi_pembayaranHotel);

        tambahroom = (Button)findViewById(R.id.tambahroom);

        flightid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, tambah_flights.class);
                startActivity(i);
            }
        });

        registrasiMaskapai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, RegisterMaskapai.class);
                startActivity(i);
            }
        });

        tambahroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, PilihHotelUntukTambahRoom.class);
                startActivity(i);
            }
        });

        tambah_pemilikHotel = (Button)findViewById(R.id.tambah_pemilikHotel);

        tambahrekening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, TambahRekening.class);
                startActivity(i);
            }
        });

        tambahHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, TambahHotel.class);
                startActivity(i);
            }
        });

        carihotela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, CariHotel.class);
                startActivity(i);
            }
        });

        tambah_pemilikHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, RegisterHotel.class);
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

        caripenerbangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePenerbangan = new Intent(getApplicationContext(), SearchFlights.class);
                startActivity(kePenerbangan);
            }
        });

        ListBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keKonfirmasiPembayaran = new Intent(MainMenu.this, DaftarBookingForUser.class);
                startActivity(keKonfirmasiPembayaran);
            }
        });

        Konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keKonfirmasiPembayaran = new Intent(MainMenu.this, KonfirmasiPembayaran.class);
                startActivity(keKonfirmasiPembayaran);
            }
        });

        reschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is = new Intent(MainMenu.this, Konfirmasi_Reschedule.class);
                startActivity(is);
            }
        });

        Button lihatriuangan = (Button)findViewById(R.id.lihatroom);
        lihatriuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, DaftarHotel_Pemilik.class);
                startActivity(i);
            }
        });

        bookingHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Booking_Hotel.class);
                startActivity(i);
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sad = new Intent(MainMenu.this, TambahFlights_Maskapai.class);
                startActivity(sad);
            }
        });

        konfirmasiH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, KonfirmasiPembayaranHotel.class);
                startActivity(i);
            }
        });
    }



    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
