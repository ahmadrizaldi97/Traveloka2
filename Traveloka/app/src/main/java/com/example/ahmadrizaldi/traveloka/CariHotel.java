package com.example.ahmadrizaldi.traveloka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

public class CariHotel extends AppCompatActivity {

    private Button cari;
    private int year_x, month_x, day_x;
    private EditText editText4, durasi, jumlahkamar, tanggalCheckIn;
    private String durasiString = "1", jumlahKamara = "1";
    static final int DIALOG_ID = 0;
    private String lok;
    NumberPicker numberpicker;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cari_hotel);

        year_x = 2018;
        month_x = 1;
        day_x = 12;

        cari = (Button)findViewById(R.id.cariHotelllll);

        editText4 = (EditText)findViewById(R.id.editText4);
        durasi = (EditText)findViewById(R.id.editText6);
        jumlahkamar = (EditText)findViewById(R.id.jumlahKamar);
        tanggalCheckIn = (EditText)findViewById(R.id.editText5);

        editText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CariHotel.this, Lokasi.class);
                startActivityForResult(i, 11);
            }
        });

        jumlahkamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CariHotel.this);
                View parentView = getLayoutInflater().inflate(R.layout.dialog, null);
                bottomSheetDialog.setContentView(parentView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()));
                bottomSheetDialog.show();

                numberpicker = (NumberPicker)parentView.findViewById(R.id.numberPicker1);

                numberpicker.setMinValue(1);
                numberpicker.setMaxValue(8);

                numberpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
//                        Dura.setText("Selected Value is : " + i1);
                        jumlahkamar.setText(i1 + " Kamar");
                        jumlahKamara = String.valueOf(i1);
                    }
                });

            }
        });

        tanggalCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });

        durasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CariHotel.this);
                View parentView = getLayoutInflater().inflate(R.layout.dialog2, null);
                bottomSheetDialog.setContentView(parentView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()));
                bottomSheetDialog.show();

                numberpicker = (NumberPicker)parentView.findViewById(R.id.numberPicker2);

                numberpicker.setMinValue(1);
                numberpicker.setMaxValue(15);

                numberpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
//                        textview.setText("Selected Value is : " + i1);
                        durasi.setText(i1 + " Malam");
                        durasiString = String.valueOf(i1);
                    }
                });

            }
        });


        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CariHotel.this, HotelTersedia.class);
                Bundle b = new Bundle();
                b.putString("lokasi", editText4.getText().toString().trim());
                b.putString("tanggalCheckIn", tanggalCheckIn.getText().toString().trim());
                b.putString("durasi", durasiString);
                b.putString("jumlahKamar",jumlahKamara);
                i.putExtras(b);
                System.out.println(durasiString);

                startActivity(i);
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

            tanggalCheckIn.setText(day_x + "/" + month_x + "/"+year_x);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 11){
            if (resultCode == RESULT_OK){
                lok = data.getStringExtra("nama");
                editText4.setText(lok);
                Toast.makeText(this, lok,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
