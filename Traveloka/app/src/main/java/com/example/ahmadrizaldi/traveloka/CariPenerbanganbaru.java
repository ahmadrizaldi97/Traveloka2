package com.example.ahmadrizaldi.traveloka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CariPenerbanganbaru extends AppCompatActivity {

    private EditText darimana, kemana, tanggalBerangkat;
    private int year_x, month_x, day_x;
    private String lok_darimana, lok_kemana, kode_bandara_dari, kode_bandara_ke, tanggalBerangkat_= "";
    static final int DIALOG_ID = 0;
    private Button cari;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cari_penerbanganbaru);

        year_x = 2018;
        month_x = 3;
        day_x = 19;


        darimana = (EditText) findViewById(R.id.darimanaR);
        kemana = (EditText)findViewById(R.id.kemanaR);
        tanggalBerangkat = (EditText)findViewById(R.id.tanggal_berangkatR);

        cari = (Button)findViewById(R.id.CariPenerbanganssssR);

        kode_bandara_dari = "UPG*Sultan Hasanuddin^Makassar";
        kode_bandara_ke = "SUB*Juanda^Surabaya";
        tanggalBerangkat_ = "19/3/2018";

        lok_darimana = "Makassar";
        lok_kemana = "Surabaya";

        final String darimana_ = darimana.getText().toString();
        final String kemana_ = kemana.getText().toString();

        darimana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is = new Intent(getApplicationContext(), ListPenerbangans.class);

                Bundle b = new Bundle();
                b.putString("kode", darimana_.substring(darimana_.indexOf("(") + 1, darimana_.lastIndexOf(")")));
                is.putExtras(b);
                startActivityForResult(is, 1);
            }
        });

        tanggalBerangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });


        SharedPreferences preff = getSharedPreferences("datapenerbangansebelumnya", Context.MODE_PRIVATE);
        if (preff.getString("darimana","") != ""){
            darimana.setText(preff.getString("darimana_",""));
            kemana.setText(preff.getString("kemana_",""));

        }else{

        }

        kemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is = new Intent(getApplicationContext(), ListPenerbangans.class);
                Bundle b = new Bundle();
                b.putString("kode", kemana_.substring(kemana_.indexOf("(") + 1, kemana_.lastIndexOf(")")));
                is.putExtras(b);
                startActivityForResult(is, 2);
            }
        });

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is = new Intent(getApplicationContext(), PenerbanganTersediaR.class);
                Bundle b = new Bundle();
                Bundle bs = getIntent().getExtras();

                b.putString("lok_dari", lok_darimana);
                b.putString("lok_ke", lok_kemana);
                b.putString("dari", kode_bandara_dari);
                b.putString("kemana", kode_bandara_ke);
                b.putString("tanggal", tanggalBerangkat_);
                b.putString("namaMaskapai", bs.getString("namaMaskapai"));
                b.putString("idPenerbangan",bs.getString("idPenerbangan"));
                b.putString("ID_Reservasi", bs.getCharSequence("ID_Reservasi").toString());
                b.putString("Passanger", bs.getCharSequence("Passanger").toString());
                b.putString("Passanger_Awal", bs.getCharSequence("Passanger_Awal").toString());
                b.putBoolean("Langsung", true);
                b.putString("kelas", bs.getString("kelas"));

                String s = bs.getCharSequence("Passanger").toString();

                final String sPlit[] =s.split("\\.");
                b.putString("jumlah_penumpang", String.valueOf(sPlit.length));
                is.putExtras(b);

                startActivity(is);

            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int i, int i1, int i2) {

            year_x = i;
            month_x = i1 +1;
            day_x = i2;

            tanggalBerangkat.setText(day_x + "/" + month_x + "/"+year_x);
            tanggalBerangkat_ = tanggalBerangkat.getText().toString();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("bandara");
                String result2 = data.getStringExtra("kodebandara");
                lok_darimana = result;
                kode_bandara_dari = data.getStringExtra("kodebandara") + "*" + data.getStringExtra("bandara") + "^" + data.getStringExtra("namaLokasi");
                darimana.setText(result + " ("+ result2+ ")" );
                Toast.makeText(this, result,Toast.LENGTH_SHORT).show();
            }
        }if (requestCode == 2){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("bandara");
                String result2 = data.getStringExtra("kodebandara");
                lok_kemana = result;
                kode_bandara_ke = data.getStringExtra("kodebandara") + "*" + data.getStringExtra("bandara") + "^" + data.getStringExtra("namaLokasi");
                kemana.setText(result + " ("+ result2+ ")" );
                Toast.makeText(this, result,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
