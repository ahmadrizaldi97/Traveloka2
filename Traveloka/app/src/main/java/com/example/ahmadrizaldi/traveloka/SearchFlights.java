package com.example.ahmadrizaldi.traveloka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchFlights extends AppCompatActivity {

    private EditText darimana, kemana, tanggalBerangkat, penumpang;
    private int year_x, month_x, day_x;
    private String lok_darimana, lok_kemana, kode_bandara_dari, kode_bandara_ke, tanggalBerangkat_= "";
    static final int DIALOG_ID = 0;
    private Button cari;
    private Spinner spin;

    private NumberPicker numberpicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        year_x = 2018;
        month_x = 1;
        day_x = 12;


        darimana = (EditText) findViewById(R.id.darimana);
        kemana = (EditText)findViewById(R.id.kemana);
        tanggalBerangkat = (EditText)findViewById(R.id.tanggal_berangkat);
        penumpang = (EditText)findViewById(R.id.jumlah_penumpang);
        spin = (Spinner)findViewById(R.id.kelass);

        cari = (Button)findViewById(R.id.CariPenerbanganssss);

//        "Makassar","Sultan Hasanuddin","UPG"
//        "Surabaya","Juanda","SUB"

        kode_bandara_dari = "UPG*Sultan Hasanuddin^Makassar";
        kode_bandara_ke = "SUB*Juanda^Surabaya";
        tanggalBerangkat_ = "19/3/2018";

        lok_darimana = "Makassar";
        lok_kemana = "Surabaya";


        final String darimana_ = darimana.getText().toString();
        final String kemana_ = kemana.getText().toString();

        penumpang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SearchFlights.this);
                View parentView = getLayoutInflater().inflate(R.layout.dialog3, null);
                bottomSheetDialog.setContentView(parentView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()));
                bottomSheetDialog.show();

                numberpicker = (NumberPicker)parentView.findViewById(R.id.numberPicker1000);

                numberpicker.setMinValue(1);
                numberpicker.setMaxValue(4);

                numberpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
//                        Dura.setText("Selected Value is : " + i1);
                        String jumlah = String.valueOf(i1);
                        penumpang.setText(jumlah);
                    }
                });
            }
        });

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
                Intent is = new Intent(getApplicationContext(), PenerbanganTersedia.class);
                Bundle b = new Bundle();


                    b.putString("dari", kode_bandara_dari);
                    b.putString("kemana", kode_bandara_ke);
                    b.putString("tanggal", tanggalBerangkat_);


                b.putString("lok_dari", lok_darimana);
                b.putString("lok_ke", lok_kemana);
                b.putString("ID_Reservasi","kosong");
                b.putString("jumlahpenumpang", penumpang.getText().toString());
                b.putString("kelas", spin.getSelectedItem().toString());

                is.putExtras(b);

                startActivity(is);
//                System.out.println(kode_bandara_dari);

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
