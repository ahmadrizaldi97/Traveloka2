package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class InsertData3 extends AppCompatActivity {

    private TextView darimanakemana, tanggal, jamberangkat, darimanakemana2, kelas, nilai, ubahdata, namaPemesan, email, hp, hargass;
    private int sa, penumpang =0;
    private Button lanjut;
    private EditText namaPenumpang,namaPenumpang2, namaPenumpang3;
    private int hargaku;
    private String penumpang_lengkap;

    private ImageView gambarLogo;
    private TextView maskapaiNama;

    private String id_penerbangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data3);

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
        namaPenumpang = (EditText)findViewById(R.id.nama3Penumpang);
        namaPenumpang2 = (EditText)findViewById(R.id.nama3Penumpang2);
        namaPenumpang3 = (EditText)findViewById(R.id.nama3Penumpang3);
        gambarLogo = (ImageView)findViewById(R.id.imageView2);
        maskapaiNama = (TextView)findViewById(R.id.textView5);


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
                Intent ubahdata = new Intent(InsertData3.this, DataPemesan.class);
                startActivityForResult(ubahdata, 1);
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent oo = new Intent(InsertData3.this, Review_.class);
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
                b.putString("namaPenumpang", namaPenumpang.getText().toString() + "." + namaPenumpang2.getText().toString() + "." + namaPenumpang3.getText().toString());
                b.putString("hargaku", String.valueOf(hargaku));
                oo.putExtras(b);

                startActivityForResult(oo, 404);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String nama = data.getStringExtra("nama");
                String emails = data.getStringExtra("email");
                String hps = data.getStringExtra("hp");
//                String email = data.getStringExtra("kodebandara");

                namaPemesan.setText(nama);
                email.setText(emails);
                hp.setText(hps);


//                Toast.makeText(this, result,Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 404){
            if (resultCode == RESULT_OK){
                Intent intent_ = new Intent();
                setResult(Activity.RESULT_OK, intent_);
                finish();
            }

        }

    }
}
