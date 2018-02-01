package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class FasilitasUtamaHotel extends AppCompatActivity {

    private CheckBox q1,q2,q3,q4,q5,q6,q7,q8;
    private Button simpan;
    private String hasil = ".";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fasilitas_utama);

        q1 = (CheckBox)findViewById(R.id.q1);
        q2 = (CheckBox)findViewById(R.id.q2);
        q3 = (CheckBox)findViewById(R.id.q3);
        q4 = (CheckBox)findViewById(R.id.q4);
        q5 = (CheckBox)findViewById(R.id.q5);
        q6 = (CheckBox)findViewById(R.id.q6);
        q7 = (CheckBox)findViewById(R.id.q7);
        q8 = (CheckBox)findViewById(R.id.q8);

        simpan = (Button)findViewById(R.id.qSimpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (q1.isChecked()){
                    hasil += "wifi.";
                }

                if (q2.isChecked()){
                    hasil += "kolam.";
                }

                if (q3.isChecked()){
                    hasil += "parkir.";
                }

                if (q4.isChecked()){
                    hasil += "restoran.";
                }

                if (q5.isChecked()){
                    hasil += "lift.";
                }


                if (q6.isChecked()){
                    hasil += "kebugaran.";
                }

                if (q7.isChecked()){
                    hasil += "rapat.";
                }


                if (q8.isChecked()){
                    hasil += "antar.";
                }

                Intent intent_ = new Intent();
                intent_.putExtra("fasilitasUtamaHotel", hasil);
                ((Activity)FasilitasUtamaHotel.this).setResult(Activity.RESULT_OK, intent_);
                ((Activity) FasilitasUtamaHotel.this).finish();

            }
        });

    }
}
