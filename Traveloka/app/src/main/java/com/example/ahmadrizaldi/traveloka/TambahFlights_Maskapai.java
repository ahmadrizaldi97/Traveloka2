package com.example.ahmadrizaldi.traveloka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.list.datapenerbangan;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class TambahFlights_Maskapai extends AppCompatActivity {

    private int year_x, month_x, day_x, hour_x, minute_x;
    public EditText darimana, kemana, tanggal, kuotaE, kuotaB, kuotaF, hargaE, hargaB, hargaF, jamberangkat, jamtiba, jumlahMax;
    public Spinner flightid;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID2 = 0;
    private Button tambah;
    DatabaseReference databaseku, tambahFlight;
    String kodebandara_from, kodebandara_to, namaMaskapai;

    boolean pertamaataukedua;

    NumberPicker numberpicker;
    TextView textviewku;

    String jumlah_penumpang;

    ArrayAdapter<String> adapter;
    List<String> list;


    ////
    TextView maskapaiNama;
    ImageView gambarLogo;


    private Calendar c = new Calendar() {
        @Override
        protected int handleGetLimit(int i, int i1) {

            return 0;
        }

        @Override
        protected int handleComputeMonthStart(int i, int i1, boolean b) {
            return 0;
        }

        @Override
        protected int handleGetExtendedYear() {
            return 0;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_flights__maskapai);
//
        year_x = 2018;
        month_x = 3;
        day_x = 12;

        maskapaiNama = (TextView)findViewById(R.id.maskapai_nama);

        gambarLogo = (ImageView)findViewById(R.id.GambarTambah);


//
        flightid = (Spinner)findViewById(R.id.flightid_mskp);
        darimana = (EditText)findViewById(R.id.darimana_mskp);
        kemana = (EditText)findViewById(R.id.kemana_mskp);
        tanggal = (EditText)findViewById(R.id.tanggal_berangkat_mskp);
        jamberangkat = (EditText)findViewById(R.id.jamberangkat_mskp);
        jamtiba = (EditText)findViewById(R.id.durasi);

        kuotaE = (EditText)findViewById(R.id.kuotaeconomy);
        kuotaB = (EditText)findViewById(R.id.kuotabusniess);
        kuotaF = (EditText)findViewById(R.id.kuotafirstclass);

        hargaE = (EditText)findViewById(R.id.hargaeconomyclass);
        hargaB = (EditText)findViewById(R.id.hargabusinessclass);
        hargaF = (EditText)findViewById(R.id.hargafirstclass);

        jumlahMax = (EditText)findViewById(R.id.jumlahMaximalReschedule);

        tambah = (Button)findViewById(R.id.tambahPenerbangan);



        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        namaMaskapai = sharedPref.getString("username","");
        databaseku = FirebaseDatabase.getInstance().getReference("Flights").child(namaMaskapai);
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

        //recycleview
        darimana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent as = new Intent(TambahFlights_Maskapai.this, ListPenerbangans.class);
                startActivityForResult(as, 3);

            }
        });

        //recycleview
        kemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent as = new Intent(TambahFlights_Maskapai.this, ListPenerbangans.class);
                startActivityForResult(as, 4);
            }
        });

        kuotaE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kuota(kuotaE, "Economy");
            }
        });

        kuotaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kuota(kuotaB, "Business");
            }
        });

        kuotaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kuota(kuotaF, "First Class");
            }
        });

        list = new ArrayList<>();
        list.add("Pilih Penerbangan");

        //nampilkanwaktu
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



        databaseku.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                datapenerbangan newPost = dataSnapshot.getValue(datapenerbangan.class);
                list.add(newPost.getIdPenerbangan());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



//
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flightid.setAdapter(adapter);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,list
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        flightid.setAdapter(spinnerArrayAdapter);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahPenerbangan();
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

//            String jm ="0", mnt = "0";
//            if (String.valueOf(hour_x).length() == 1){
//                jm = "0" + String.valueOf(hour_x);
//            }
//
//            if (String.valueOf(minute_x).length() == 1){
//                mnt = "0" + String.valueOf(minute_x);
//            }

            if (!pertamaataukedua){
                jamberangkat.setText(hour_x + ":" + minute_x);
            }else{
                jamtiba.setText(hour_x + ":" + minute_x);
            }



        }
    };
//
    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int i, int i1, int i2) {

            year_x = i;
            month_x = i1 + 1;
            day_x = i2;

//            view.setMinDate(c.getTimeInMillis());

            String q = String.valueOf(year_x);

//            System.out.println(year_x + "/" + month_x + "/" + day_x);

            tanggal.setText(day_x + "/" + month_x + "/"+year_x);


        }
    };
//
//    private void addFlights(){
//        String flightId = flightid.getText().toString().trim();
//        String harga
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("bandara");
                String result2 = data.getStringExtra("kodebandara");
                String result3 = data.getStringExtra("namaLokasi");
                kodebandara_from = data.getStringExtra("kodebandara") + "*" + data.getStringExtra("bandara") + "^" + data.getStringExtra("namaLokasi");
                darimana.setText(result + " ("+ result2+ ")" );
                Toast.makeText(this, result,Toast.LENGTH_SHORT).show();
            }
        }if (requestCode == 4){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("bandara");
                String result2 = data.getStringExtra("kodebandara");
                String result3 = data.getStringExtra("namaLokasi");
                kodebandara_to = data.getStringExtra("kodebandara") + "*" + data.getStringExtra("bandara") + "^" + data.getStringExtra("namaLokasi");
                kemana.setText(result + " ("+ result2+ ")" );
                Toast.makeText(this, result,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void tambahPenerbangan(){
        if (!TextUtils.isEmpty(kuotaE.getText())){
            String id = tambahFlight.push().getKey();
            id = id + "*" + namaMaskapai;
            DetailDataPenerbangan d = new DetailDataPenerbangan(id, flightid.getSelectedItem().toString(), kodebandara_from,kodebandara_to,
                    tanggal.getText().toString(), jamberangkat.getText().toString(), jamtiba.getText().toString(),
                    kuotaE.getText().toString(), kuotaB.getText().toString(), kuotaF.getText().toString(), hargaE.getText().toString(),
                    hargaB.getText().toString(), hargaF.getText().toString(), jumlahMax.getText().toString());

            tambahFlight.child(id).setValue(d);
            Toast.makeText(TambahFlights_Maskapai.this, "Tambah Sukses", Toast.LENGTH_SHORT).show();

        }
    }

    private void Kuota(final EditText editText, String jenis){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TambahFlights_Maskapai.this);
        View mView = getLayoutInflater().inflate(R.layout.dialogkuota_ekonomi, null);
        final SeekBar Ekonomi = (SeekBar) mView.findViewById(R.id.seekbar_ekonomi);
        final TextView jeniskursi = (TextView)mView.findViewById(R.id.textView128);
        final TextView jumlah = (TextView)mView.findViewById(R.id.jumlahE);
        final Button selesai = (Button)mView.findViewById(R.id.selesai_E);

        jeniskursi.setText("Kuota " + jenis);

        Ekonomi.setMax(400);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        Ekonomi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                jumlah.setText("Jumlah : " + i);
                jumlah_penumpang = String.valueOf(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText(jumlah_penumpang);
                dialog.dismiss();
            }
        });

    }



}
