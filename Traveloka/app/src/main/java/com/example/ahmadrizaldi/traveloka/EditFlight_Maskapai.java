package com.example.ahmadrizaldi.traveloka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditFlight_Maskapai extends AppCompatActivity {

    public EditText darimana, kemana, tanggal, kuotaE, kuotaB, kuotaF, hargaE, hargaB, hargaF, jamberangkat, jamtiba, jumlahMax;
    public EditText flightid;
    TextView maskapaiNama;
    ImageView gambarLogo;
    Button tambah;

    DatabaseReference tambahFlight;

    boolean pertamaataukedua;
    private int year_x, month_x, day_x, hour_x, minute_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_flight__maskapai);

        year_x = 2018;
        month_x = 3;
        day_x = 12;

        maskapaiNama = (TextView)findViewById(R.id.maskapai_namaEdit);
        gambarLogo = (ImageView)findViewById(R.id.GambarTambahEdit);
        flightid = (EditText)findViewById(R.id.flightid_mskpEdit);
        darimana = (EditText)findViewById(R.id.darimana_mskpEdit);
        kemana = (EditText)findViewById(R.id.kemana_mskpEdit);
        tanggal = (EditText)findViewById(R.id.tanggal_berangkat_mskpEdit);
        jamberangkat = (EditText)findViewById(R.id.jamberangkat_mskpEdit);
        jamtiba = (EditText)findViewById(R.id.durasiEdit);

        kuotaE = (EditText)findViewById(R.id.kuotaeconomyEdit);
        kuotaB = (EditText)findViewById(R.id.kuotabusniessEdit);
        kuotaF = (EditText)findViewById(R.id.kuotafirstclassEdit);

        hargaE = (EditText)findViewById(R.id.hargaeconomyclassEdit);
        hargaB = (EditText)findViewById(R.id.hargabusinessclassEdit);
        hargaF = (EditText)findViewById(R.id.hargafirstclassEdit);

        jumlahMax = (EditText)findViewById(R.id.jumlahMaximalRescheduleEdit);

        tambah = (Button)findViewById(R.id.tambahPenerbanganEdit);

        tambahFlight = FirebaseDatabase.getInstance().getReference("Detail Flights");

        final Bundle bs = getIntent().getExtras();

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String namaMaskapai = sharedPref.getString("username","");
        tambahFlight = FirebaseDatabase.getInstance().getReference("Detail Flights");

        maskapaiNama.setText(namaMaskapai);

//

        if (namaMaskapai.equals("Lion Air")){
            gambarLogo.setImageResource(R.drawable.lionairlogo);
        }else if (namaMaskapai.equals("Garuda Indonesia")){
            gambarLogo.setImageResource(R.drawable.garudalogo);
        }else if (namaMaskapai.equals("Sriwijaya Air")){
            gambarLogo.setImageResource(R.drawable.sriwijawalogo);
        }else if (namaMaskapai.equals("Wings Air")){
            gambarLogo.setImageResource(R.drawable.wingslogo);
        }else if (namaMaskapai.equals("Citilink")){
            gambarLogo.setImageResource(R.drawable.citilink);
        }else if (namaMaskapai.equals("Air Asia")){
            gambarLogo.setImageResource(R.drawable.airasia);
        }else if (namaMaskapai.equals("Trigana Air")){
            gambarLogo.setImageResource(R.drawable.triganalogo);
        }


        flightid.setText(bs.getString("flightid").toString());
        int index1, index2;
        index1 = bs.getString("from").toString().indexOf("*");
        index2 = bs.getString("from").toString().indexOf("^") + 1;

        darimana.setText(bs.getString("from").toString().substring(index2) + " (" + bs.getString("from").toString().substring(0, index1) + ")");

        index1 = bs.getString("to").toString().indexOf("*");
        index2 = bs.getString("to").toString().indexOf("^") + 1;

        kemana.setText(bs.getString("to").toString().substring(index2) + " (" + bs.getString("to").toString().substring(0, index1) + ")");

        tanggal.setText(bs.getString("date").toString());
        jamberangkat.setText(bs.getString("jamB").toString());
        jamtiba.setText(bs.getString("jamT").toString());

        kuotaE.setText(bs.getString("kuotae"));
        kuotaB.setText(bs.getString("kuotab"));
        kuotaF.setText(bs.getString("kuotaf"));

        hargaE.setText(bs.getString("hargae"));
        hargaB.setText(bs.getString("hargab"));
        hargaF.setText(bs.getString("hargaf"));

        jumlahMax.setText(bs.getString("maxResc"));


        jamberangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pertamaataukedua = false;
                showDialog(12);
            }
        });

        ////nampilkanwaktu
        jamtiba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pertamaataukedua = true;
                showDialog(12);
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahPenerbangan(bs.getString("id").toString(), bs.getString("from").toString(), bs.getString("to").toString());
                tambah.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){




        if (id == 0) {
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        }else if(id == 12){
            return new TimePickerDialog(this, kTimePickerListener, hour_x, minute_x, false);
        }
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            hour_x = i;
            minute_x = i1;



            timePicker.setIs24HourView(true);


            System.out.println(hour_x + "/" + minute_x);

            if (!pertamaataukedua){
                jamberangkat.setText(hour_x + ":" + minute_x);
            }else{
                jamtiba.setText(hour_x + ":" + minute_x);
            }



        }
    };

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int i, int i1, int i2) {

            year_x = i;
            month_x = i1 + 1;
            day_x = i2;


        }
    };

    private void tambahPenerbangan(String idd, String daria, String kemnsa){
        if (!TextUtils.isEmpty(kuotaE.getText())){
            DetailDataPenerbangan d = new DetailDataPenerbangan(idd, flightid.getText().toString().toString(), daria,kemnsa,
                    tanggal.getText().toString(), jamberangkat.getText().toString(), jamtiba.getText().toString(),
                    kuotaE.getText().toString(), kuotaB.getText().toString(), kuotaF.getText().toString(), hargaE.getText().toString(),
                    hargaB.getText().toString(), hargaF.getText().toString(), jumlahMax.getText().toString());

            tambahFlight.child(idd).setValue(d);
            Toast.makeText(EditFlight_Maskapai.this, "Edit Sukses", Toast.LENGTH_SHORT).show();

        }
    }

}
