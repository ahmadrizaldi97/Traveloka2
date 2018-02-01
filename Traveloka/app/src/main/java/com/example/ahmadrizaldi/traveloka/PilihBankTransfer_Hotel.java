package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PilihBankTransfer_Hotel extends AppCompatActivity {

    private TextView bni, bri, mandiri, bca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_bank_transfer__hotel);

        bni = (TextView)findViewById(R.id.bnis_hotel);
        bri = (TextView)findViewById(R.id.bri_hotel);
        mandiri = (TextView)findViewById(R.id.mandiri_hotel);
        bca = (TextView)findViewById(R.id.bca_hotel);


        bni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PilihBankTransfer_Hotel.this, ReadBeforeYouPay_Hotel.class);
                Bundle oo = new Bundle();
                Bundle ba = getIntent().getExtras();

                oo.putString("bank","bni");
                oo.putString("ID_Transaksi", ba.getCharSequence("ID_Transaksi").toString());
                oo.putString("ID", ba.getCharSequence("ID").toString());
                oo.putString("tanggalCheckIn", ba.getString("tanggalCheckIn"));
                oo.putString("IDRoom", ba.getString("IDRoom").toString());
                oo.putString("checkIn", ba.getString("checkIn"));
                oo.putString("durasi", ba.getString("durasi").toString());
                oo.putString("jumlahKamar", ba.getString("jumlahKamar").toString());
                oo.putString("checkOut", ba.getString("checkOut"));
                oo.putString("noHp", ba.getString("noHp"));
                oo.putString("email", ba.getString("email"));
                oo.putString("permintaanKhusus", ba.getString("permintaanKhusus"));
                oo.putString("hargaKeseluruhan", ba.getString("hargaKeseluruhan"));
                oo.putString("namaroom", ba.getCharSequence("namaroom").toString());
                oo.putString("harga", ba.getCharSequence("harga").toString());
                oo.putString("namaPemesan", ba.getCharSequence("namaPemesan").toString());
                oo.putString("kebikakan",ba.getCharSequence("kebikakan").toString());
                oo.putString(("namaHotel"), ba.getCharSequence("namaHotel").toString());
                oo.putString("kodepembayaran", ba.getCharSequence("kodepembayaran").toString());
                oo.putString("total",ba.getCharSequence("total").toString());
                oo.putString("poin",ba.getCharSequence("poin").toString());

                i.putExtras(oo);
                startActivityForResult(i, 912);



            }
        });

        bri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PilihBankTransfer_Hotel.this, ReadBeforeYouPay_Hotel.class);
                Bundle oo = new Bundle();
                Bundle ba = getIntent().getExtras();

                oo.putString("bank","bri");
                oo.putString("ID_Transaksi", ba.getCharSequence("ID_Transaksi").toString());
                oo.putString("ID", ba.getCharSequence("ID").toString());
                oo.putString("IDRoom", ba.getString("IDRoom").toString());
                oo.putString("tanggalCheckIn", ba.getString("tanggalCheckIn"));
                oo.putString("checkIn", ba.getString("checkIn"));
                oo.putString("noHp", ba.getString("noHp"));
                oo.putString("email", ba.getString("email"));
                oo.putString("checkOut", ba.getString("checkOut"));
                oo.putString("permintaanKhusus", ba.getString("permintaanKhusus"));
                oo.putString("durasi", ba.getString("durasi").toString());
                oo.putString("jumlahKamar", ba.getString("jumlahKamar").toString());
                oo.putString("hargaKeseluruhan", ba.getString("hargaKeseluruhan"));
                oo.putString("namaroom", ba.getCharSequence("namaroom").toString());
                oo.putString("harga", ba.getCharSequence("harga").toString());
                oo.putString("namaPemesan", ba.getCharSequence("namaPemesan").toString());
                oo.putString("kebikakan",ba.getCharSequence("kebikakan").toString());
                oo.putString(("namaHotel"), ba.getCharSequence("namaHotel").toString());
                oo.putString("kodepembayaran", ba.getCharSequence("kodepembayaran").toString());
                oo.putString("total",ba.getCharSequence("total").toString());
                oo.putString("poin",ba.getCharSequence("poin").toString());

                i.putExtras(oo);
                startActivityForResult(i, 912);
            }
        });

        mandiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PilihBankTransfer_Hotel.this, ReadBeforeYouPay_Hotel.class);
                Bundle oo = new Bundle();
                Bundle ba = getIntent().getExtras();

                oo.putString("bank","mandiri");
                oo.putString("ID_Transaksi", ba.getCharSequence("ID_Transaksi").toString());
                oo.putString("ID", ba.getCharSequence("ID").toString());
                oo.putString("IDRoom", ba.getString("IDRoom").toString());
                oo.putString("checkIn", ba.getString("checkIn"));
                oo.putString("checkOut", ba.getString("checkOut"));
                oo.putString("tanggalCheckIn", ba.getString("tanggalCheckIn"));
                oo.putString("noHp", ba.getString("noHp"));
                oo.putString("email", ba.getString("email"));
                oo.putString("permintaanKhusus", ba.getString("permintaanKhusus"));
                oo.putString("durasi", ba.getString("durasi").toString());
                oo.putString("jumlahKamar", ba.getString("jumlahKamar").toString());
                oo.putString("hargaKeseluruhan", ba.getString("hargaKeseluruhan"));
                oo.putString("namaroom", ba.getCharSequence("namaroom").toString());
                oo.putString("harga", ba.getCharSequence("harga").toString());
                oo.putString("namaPemesan", ba.getCharSequence("namaPemesan").toString());
                oo.putString("kebikakan",ba.getCharSequence("kebikakan").toString());
                oo.putString(("namaHotel"), ba.getCharSequence("namaHotel").toString());
                oo.putString("kodepembayaran", ba.getCharSequence("kodepembayaran").toString());
                oo.putString("total",ba.getCharSequence("total").toString());
                oo.putString("poin",ba.getCharSequence("poin").toString());

                i.putExtras(oo);
                startActivityForResult(i, 912);
            }
        });

        bca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PilihBankTransfer_Hotel.this, ReadBeforeYouPay_Hotel.class);
                Bundle oo = new Bundle();
                Bundle ba = getIntent().getExtras();

                oo.putString("bank","bca");
                oo.putString("ID_Transaksi", ba.getCharSequence("ID_Transaksi").toString());
                oo.putString("ID", ba.getCharSequence("ID").toString());
                oo.putString("IDRoom", ba.getString("IDRoom").toString());
                oo.putString("checkIn", ba.getString("checkIn"));
                oo.putString("tanggalCheckIn", ba.getString("tanggalCheckIn"));
                oo.putString("checkOut", ba.getString("checkOut"));
                oo.putString("durasi", ba.getString("durasi").toString());
                oo.putString("noHp", ba.getString("noHp"));
                oo.putString("email", ba.getString("email"));
                oo.putString("permintaanKhusus", ba.getString("permintaanKhusus"));
                oo.putString("jumlahKamar", ba.getString("jumlahKamar").toString());
                oo.putString("hargaKeseluruhan", ba.getString("hargaKeseluruhan"));
                oo.putString("namaroom", ba.getCharSequence("namaroom").toString());
                oo.putString("harga", ba.getCharSequence("harga").toString());
                oo.putString("namaPemesan", ba.getCharSequence("namaPemesan").toString());
                oo.putString("kebikakan",ba.getCharSequence("kebikakan").toString());
                oo.putString(("namaHotel"), ba.getCharSequence("namaHotel").toString());
                oo.putString("kodepembayaran", ba.getCharSequence("kodepembayaran").toString());
                oo.putString("total",ba.getCharSequence("total").toString());
                oo.putString("poin",ba.getCharSequence("poin").toString());

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
