package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InsertDataHotel extends AppCompatActivity {

    private TextView permintaan, permintaan2, lanjut, kamar_pilihan, namaHotel,
            hargaHotel, namaPemesan, tanggalCheckIn, tanggalCheckOut, ubahPemesan, keteranganPemesan;

    private String checkOut;
    String noHp = "-";
    String email = "-";
    String nm = "-";
    String permintaanKhusus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_hotel);

        permintaanKhusus = "---";

        permintaan = (TextView)findViewById(R.id.textView118);
        permintaan2 = (TextView)findViewById(R.id.textView120);
        kamar_pilihan = (TextView)findViewById(R.id.kamar_pilihan);
        namaHotel = (TextView)findViewById(R.id.textView113);
        hargaHotel = (TextView)findViewById(R.id.hargahotsadel);
        namaPemesan = (TextView)findViewById(R.id.textView117);
        tanggalCheckIn = (TextView)findViewById(R.id.tanggal_checkin);
        tanggalCheckOut = (TextView)findViewById(R.id.tanggal_checkout);
        ubahPemesan = (TextView)findViewById(R.id.ubahpemesan);
        keteranganPemesan = (TextView)findViewById(R.id.keterangan_pemesan);


        Bundle b = getIntent().getExtras();

        kamar_pilihan.setText(b.getCharSequence("namaroom"));
        namaHotel.setText(b.getCharSequence("namaHotel"));

        tanggalCheckIn.setText(b.getString("tanggalCheckIn"));
        int index;
        index = b.getString("tanggalCheckIn").indexOf("/");
        checkOut = b.getString("tanggalCheckIn").substring(0, index);

        checkOut = String.valueOf(Integer.parseInt(checkOut) + Integer.parseInt(b.getString("durasi")));

        checkOut = checkOut + b.getString("tanggalCheckIn").substring(index);
        tanggalCheckOut.setText(checkOut);

        hargaHotel.setText("Rp. " + b.getCharSequence("harga"));

//        harga tanggalCheckIn


        lanjut = (Button)findViewById(R.id.hotel_lanjut_ke_review);

        permintaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertDataHotel.this, PermintaanKhusus.class);
                startActivityForResult(i, 780);
            }
        });

        permintaan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertDataHotel.this, PermintaanKhusus.class);
                startActivityForResult(i, 780);
            }
        });

        ubahPemesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertDataHotel.this, DataPemesan_Hotel.class);
                startActivityForResult(i, 876);
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertDataHotel.this, ReviewHotel.class);

                Bundle b =new Bundle();
                Bundle ba = getIntent().getExtras();

                String poin = ba.getCharSequence("harga").toString();

                String poinPending="";

                int panjang = String.valueOf(poin).length();

                String uang = poin;

                if (panjang == 6){
                    poinPending = uang.substring(0,2);
                }else if (panjang == 7){
                    poinPending = uang.substring(0,3);
                }else if (panjang == 8){
                    poinPending = uang.substring(0,4);
                }else if (panjang == 9){
                    poinPending = uang.substring(0,5);
                }else if (panjang == 10){
                    poinPending = uang.substring(0,6);
                }


                if (noHp.equals("-") || nm.equals("-") || noHp.equals("-")){

                    pesan();
                }else {

                    b.putString("ID", ba.getCharSequence("ID").toString());
                    b.putString("IDRoom", ba.getString("IDRoom").toString());
                    b.putString("durasi", ba.getCharSequence("durasi").toString());
                    b.putString("jumlahKamar", ba.getCharSequence("jumlahKamar").toString());
                    b.putString("checkIn", tanggalCheckIn.getText().toString().trim());
                    b.putString("checkOut", tanggalCheckOut.getText().toString());
                    b.putString("namaroom", ba.getCharSequence("namaroom").toString());
                    b.putString("harga", ba.getCharSequence("harga").toString());
                    b.putString("namaPemesan", nm);
                    b.putString("noHp", noHp);
                    b.putString("permintaanKhusus", permintaanKhusus);
                    b.putString("email", email);
                    b.putString("kebikakan", ba.getCharSequence("kebikakan").toString());
                    b.putString(("namaHotel"), ba.getCharSequence("namaHotel").toString());
                    b.putString(("poin"), poinPending);

                    i.putExtras(b);

                    startActivityForResult(i, 912);
                }
            }
        });
    }

    private void pesan(){
        Toast.makeText(InsertDataHotel.this, "Harap mengisi semua data", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 876){
            if (resultCode == RESULT_OK){
                String nama = data.getStringExtra("nama");
                noHp = data.getStringExtra("noHp");
                nm = nama;
                email = data.getStringExtra("email");
                namaPemesan.setText(nama);
                keteranganPemesan.setText(noHp + "/" + email);

            }
        }


        if (requestCode == 912){
            if (resultCode == RESULT_OK){
                Intent u = new Intent(InsertDataHotel.this, Booking_Hotel.class);
                startActivity(u);

                finish();

            }
        }
    }



}
