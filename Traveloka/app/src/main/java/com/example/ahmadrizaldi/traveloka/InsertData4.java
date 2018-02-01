package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class InsertData4 extends AppCompatActivity {

    private TextView darimanakemana, tanggal, jamberangkat, darimanakemana2, kelas, nilai, ubahdata, namaPemesan, email, hp, hargass;
    private int sa, penumpang =0;
    private Button lanjut;
    private EditText namaPenumpang,namaPenumpang2, namaPenumpang3, namaPenumpang4;
    private int hargaku;
    private String penumpang_lengkap;

    private ImageView gambarLogo;
    private TextView maskapaiNama;

    private String id_penerbangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data4);

        darimanakemana = (TextView)findViewById(R.id.darimanakemana_rvw);
        tanggal = (TextView)findViewById(R.id.textView4);
        jamberangkat = (TextView)findViewById(R.id.textView7);
        darimanakemana2 = (TextView)findViewById(R.id.darimanakemana_rvw2);
        kelas = (TextView)findViewById(R.id.kelas_rvw);
        nilai = (TextView)findViewById(R.id.textView3);
        ubahdata = (TextView)findViewById(R.id.textView16);
        namaPemesan = (TextView)findViewById(R.id.textView11);
        email = (TextView)findViewById(R.id.emailPemesannaaaa);
        hp = (TextView)findViewById(R.id.textView18);
        hargass = (TextView)findViewById(R.id.total);
        lanjut = (Button)findViewById(R.id.CariPenerbanganssss);
        namaPenumpang = (EditText)findViewById(R.id.nama4Penumpang);
        namaPenumpang2 = (EditText)findViewById(R.id.nama4Penumpang2);
        namaPenumpang3 = (EditText)findViewById(R.id.nama4Penumpang3);
        namaPenumpang4 = (EditText)findViewById(R.id.nama4Penumpang4);
        gambarLogo = (ImageView)findViewById(R.id.imageView2);
        maskapaiNama = (TextView)findViewById(R.id.textView5);

        penumpang_lengkap = namaPenumpang.getText().toString() + "," + namaPenumpang2.getText().toString() +
                "," + namaPenumpang3.getText().toString() + "," + namaPenumpang4.getText().toString();


        Bundle b = getIntent().getExtras();

        darimanakemana.setText(b.getCharSequence("From").toString() + " - " + b.getCharSequence("To").toString() );
        tanggal.setText("Rab, " + b.getCharSequence("Tanggal").toString());
        jamberangkat.setText(b.getCharSequence("JamBerangkat").toString()  + " - " + b.getCharSequence("JamTiba").toString() );
        darimanakemana2.setText(b.getCharSequence("From").toString() + " - " + b.getCharSequence("To").toString());
        kelas.setText(b.getCharSequence("kelas").toString());
        hargass.setText(b.getCharSequence("harga"));

        hargaku = Integer.parseInt(hargass.getText().toString().substring(2).trim());

        penumpang = Integer.parseInt(b.getCharSequence("jumlah_penumpang").toString());

        id_penerbangan = b.getString("id");

        if (id_penerbangan.indexOf("Lion Air") > 0){
            maskapaiNama.setText("Lion Air");
            gambarLogo.setImageResource(R.drawable.lionairlogo);
        }else if (id_penerbangan.indexOf("Garuda Indonesia") > 0){
            gambarLogo.setImageResource(R.drawable.garudalogo);
            maskapaiNama.setText("Garuda Indonesia");
        }else if (id_penerbangan.indexOf("Sriwijaya Air") > 0){
            gambarLogo.setImageResource(R.drawable.sriwijawalogo);
            maskapaiNama.setText("Sriwijaya Air");
        }else if (id_penerbangan.indexOf("Wings Air")  > 0){
            gambarLogo.setImageResource(R.drawable.wingslogo);
            maskapaiNama.setText("Wings Air");
        }else if (id_penerbangan.indexOf("Citilink")  > 0){
            gambarLogo.setImageResource(R.drawable.citilink);
            maskapaiNama.setText("Citilink");
        }else if (id_penerbangan.indexOf("Air Asia")  > 0){
            gambarLogo.setImageResource(R.drawable.airasia);
            maskapaiNama.setText("Air Asia");
        }else if (id_penerbangan.indexOf("Trigana Air")  > 0){
            gambarLogo.setImageResource(R.drawable.triganalogo);
            maskapaiNama.setText("Trigana Air");
        }

        ubahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ubahdata = new Intent(InsertData4.this, DataPemesan.class);
                startActivityForResult(ubahdata, 1);
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent oo = new Intent(InsertData4.this, Review_.class);
                Bundle b = new Bundle();

                Bundle bs = getIntent().getExtras();

                b.putString("id", id_penerbangan);
                b.putString("namaMaskapai", maskapaiNama.getText().toString());
                b.putString("FlightId", bs.getCharSequence("FlightId").toString() );
                b.putString("From", bs.getCharSequence("From").toString());
                b.putString("To", bs.getCharSequence("To").toString());
                b.putString("Tanggal", bs.getCharSequence("Tanggal").toString());
                b.putString("JamBerangkat", bs.getCharSequence("JamBerangkat").toString());
                b.putString("JamTiba", bs.getCharSequence("JamTiba").toString());
                b.putString("kelas", kelas.getText().toString());
                b.putString("harga", nilai.getText().toString());
                b.putString("poin", bs.getCharSequence("poin").toString());
                b.putString("jumlah_penumpang", bs.getCharSequence("jumlah_penumpang").toString());
                b.putString("darimana", bs.getCharSequence("darimana").toString());
                b.putString("kemana", bs.getCharSequence("kemana").toString());
                b.putString("namaPenumpang", namaPenumpang.getText().toString() + "." + namaPenumpang2.getText().toString() + "." + namaPenumpang3.getText().toString() + "." + namaPenumpang4.getText().toString());
                b.putString("hargaku", String.valueOf(hargaku));
                oo.putExtras(b);

                startActivityForResult(oo, 404);

            }
        });
    }
}
