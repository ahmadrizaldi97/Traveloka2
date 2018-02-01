package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.ahmadrizaldi.traveloka.list.datapenerbangan;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class tambah_flights extends AppCompatActivity {

     Button simpanFlights, tambahDestinasi;
     EditText flightids;
     Spinner maskapaii;
    String namaMaskapai;
     int drawables;

    private DatabaseReference databaseku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_flights);

        simpanFlights = (Button)findViewById(R.id.button2);
//        tambahDestinasi = (Button)findViewById(R.id.tambahdestinasi);
        flightids = (EditText)findViewById(R.id.flightidmaster);


        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        namaMaskapai = sharedPref.getString("username","");

        databaseku = FirebaseDatabase.getInstance().getReference("Flights");

        simpanFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahFlight(namaMaskapai);
                simpanFlights.setVisibility(View.GONE);
            }
        });

        

    }

    private void tambahFlight(String namaMaskapai){

//
//        if (maskapaii.getSelectedItemPosition() == 0){
//            drawables = R.drawable.garudalogo;
//        }else if (maskapaii.getSelectedItemPosition() == 1){
//            drawables = R.drawable.citilink;
//        }else if (maskapaii.getSelectedItemPosition() == 2){
//            drawables = R.drawable.lionairlogo;
//        }else if (maskapaii.getSelectedItemPosition() == 3){
//            drawables = R.drawable.sriwijawalogo;
//        }else if (maskapaii.getSelectedItemPosition() == 4){
//            drawables = R.drawable.airasia;
//        }else if (maskapaii.getSelectedItemPosition() == 5){
//            drawables = R.drawable.triganalogo;
//        }else{
//            drawables = R.drawable.wingslogo;
//        }

        if (!TextUtils.isEmpty(flightids.getText().toString().trim())){
            datapenerbangan s = new datapenerbangan(flightids.getText().toString().trim());

            String id = databaseku.push().getKey();

            databaseku.child(namaMaskapai).child(id).setValue(s);
            Toast.makeText(this,"Tambah Sukses",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data harus diisi", Toast.LENGTH_SHORT).show();
        }
    }
}
