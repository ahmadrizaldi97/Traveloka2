package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PilihBankTransfer extends AppCompatActivity {

    private TextView bca,mandiri,bri,bni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_bank_transfer);

        bca = (TextView)findViewById(R.id.bcaaa);
        mandiri = (TextView)findViewById(R.id.mandiria);
        bri = (TextView)findViewById(R.id.briaaa);
        bni = (TextView)findViewById(R.id.bnis111);



        bca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                Intent i = new Intent(PilihBankTransfer.this, ReadBeforeYouPay.class);

                Bundle bs = getIntent().getExtras();

                b.putString("id", bs.getString("id").toString());
                b.putString("namaMaskapai",bs.getString("namaMaskapai"));
                b.putString("idreservasi_", bs.getCharSequence("idreservasi_").toString());
                b.putString("FlightId", bs.getCharSequence("FlightId").toString() );
                b.putString("From", bs.getCharSequence("From").toString());
                b.putString("To", bs.getCharSequence("To").toString());
                b.putString("Tanggal", bs.getCharSequence("Tanggal").toString());
                b.putString("JamBerangkat", bs.getCharSequence("JamBerangkat").toString());
                b.putString("JamTiba", bs.getCharSequence("JamTiba").toString());
                b.putString("kelas", bs.getCharSequence("kelas").toString());
                b.putString("poin", bs.getCharSequence("poin").toString());
                b.putString("jumlah_penumpang", bs.getCharSequence("jumlah_penumpang").toString());
                b.putString("darimana", bs.getCharSequence("darimana").toString());
                b.putString("kemana", bs.getCharSequence("kemana").toString());
                b.putString("namaPenumpang", bs.getCharSequence("namaPenumpang").toString());
                b.putInt("harga_tiket", Integer.parseInt(bs.getString("harga_tiket")));
                b.putInt("kode_pembayaran",bs.getInt("kode_pembayaran"));
                b.putInt("total_harga", bs.getInt("total_harga"));
                b.putString("BANK","BCA");

                i.putExtras(b);
                startActivity(i);

            }
        });

        mandiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                Intent i = new Intent(PilihBankTransfer.this, ReadBeforeYouPay.class);

                Bundle bs = getIntent().getExtras();

                b.putString("id", bs.getString("id").toString());
                b.putString("namaMaskapai",bs.getString("namaMaskapai"));
                b.putString("idreservasi_", bs.getCharSequence("idreservasi_").toString());
                b.putString("FlightId", bs.getCharSequence("FlightId").toString() );
                b.putString("From", bs.getCharSequence("From").toString());
                b.putString("To", bs.getCharSequence("To").toString());
                b.putString("Tanggal", bs.getCharSequence("Tanggal").toString());
                b.putString("JamBerangkat", bs.getCharSequence("JamBerangkat").toString());
                b.putString("JamTiba", bs.getCharSequence("JamTiba").toString());
                b.putString("kelas", bs.getCharSequence("kelas").toString());
                b.putString("poin", bs.getCharSequence("poin").toString());
                b.putString("jumlah_penumpang", bs.getCharSequence("jumlah_penumpang").toString());
                b.putString("darimana", bs.getCharSequence("darimana").toString());
                b.putString("kemana", bs.getCharSequence("kemana").toString());
                b.putString("namaPenumpang", bs.getCharSequence("namaPenumpang").toString());
                b.putInt("harga_tiket", Integer.parseInt(bs.getString("harga_tiket")));
                b.putInt("kode_pembayaran",bs.getInt("kode_pembayaran"));
                b.putInt("total_harga", bs.getInt("total_harga"));
                b.putString("BANK","MANDIRI");

                i.putExtras(b);
                startActivity(i);
            }
        });

        bri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                Intent i = new Intent(PilihBankTransfer.this, ReadBeforeYouPay.class);

                Bundle bs = getIntent().getExtras();

                b.putString("id", bs.getString("id").toString());
                b.putString("idreservasi_", bs.getCharSequence("idreservasi_").toString());
                b.putString("namaMaskapai",bs.getString("namaMaskapai"));
                b.putString("FlightId", bs.getCharSequence("FlightId").toString() );
                b.putString("From", bs.getCharSequence("From").toString());
                b.putString("To", bs.getCharSequence("To").toString());
                b.putString("Tanggal", bs.getCharSequence("Tanggal").toString());
                b.putString("JamBerangkat", bs.getCharSequence("JamBerangkat").toString());
                b.putString("JamTiba", bs.getCharSequence("JamTiba").toString());
                b.putString("kelas", bs.getCharSequence("kelas").toString());
                b.putString("poin", bs.getCharSequence("poin").toString());
                b.putString("jumlah_penumpang", bs.getCharSequence("jumlah_penumpang").toString());
                b.putString("darimana", bs.getCharSequence("darimana").toString());
                b.putString("kemana", bs.getCharSequence("kemana").toString());
                b.putString("namaPenumpang", bs.getCharSequence("namaPenumpang").toString());
                b.putInt("harga_tiket", Integer.parseInt(bs.getString("harga_tiket")));
                b.putInt("kode_pembayaran",bs.getInt("kode_pembayaran"));
                b.putInt("total_harga", bs.getInt("total_harga"));
                b.putString("BANK","BRI");

                i.putExtras(b);
                startActivity(i);
            }
        });


        bni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                Intent i = new Intent(PilihBankTransfer.this, ReadBeforeYouPay.class);

                Bundle bs = getIntent().getExtras();

                b.putString("id", bs.getString("id").toString());
                b.putString("namaMaskapai",bs.getString("namaMaskapai"));
                b.putString("idreservasi_", bs.getCharSequence("idreservasi_").toString());
                b.putString("FlightId", bs.getCharSequence("FlightId").toString() );
                b.putString("From", bs.getCharSequence("From").toString());
                b.putString("To", bs.getCharSequence("To").toString());
                b.putString("Tanggal", bs.getCharSequence("Tanggal").toString());
                b.putString("JamBerangkat", bs.getCharSequence("JamBerangkat").toString());
                b.putString("JamTiba", bs.getCharSequence("JamTiba").toString());
                b.putString("kelas", bs.getCharSequence("kelas").toString());
                b.putString("poin", bs.getCharSequence("poin").toString());
                b.putString("jumlah_penumpang", bs.getCharSequence("jumlah_penumpang").toString());
                b.putString("darimana", bs.getCharSequence("darimana").toString());
                b.putString("kemana", bs.getCharSequence("kemana").toString());
                b.putString("namaPenumpang", bs.getCharSequence("namaPenumpang").toString());
                b.putInt("harga_tiket", Integer.parseInt(bs.getString("harga_tiket")));
                b.putInt("kode_pembayaran",bs.getInt("kode_pembayaran"));
                b.putInt("total_harga", bs.getInt("total_harga"));
                b.putString("BANK","BNI");

                i.putExtras(b);
                System.out.println(bs.getInt("harga_tiket") + "-------------------");
                startActivityForResult(i, 404);
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 404){
            if (resultCode == RESULT_OK){
                Intent intent_ = new Intent();
                setResult(Activity.RESULT_OK, intent_);
                finish();
            }

        }
    }
}
