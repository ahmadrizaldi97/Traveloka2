package com.example.ahmadrizaldi.traveloka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class PermintaanKhusus extends AppCompatActivity {

    private String permintaanKhusus = ".";
    private CheckBox bebasAsapRokok, pintuPenghubung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permintaan_khusus_hotel);

        bebasAsapRokok = (CheckBox)findViewById(R.id.bebasAsapRokok);
        pintuPenghubung = (CheckBox)findViewById(R.id.kamardenganpintupenghubung);
        Button selesai = (Button)findViewById(R.id.selesai);


        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
