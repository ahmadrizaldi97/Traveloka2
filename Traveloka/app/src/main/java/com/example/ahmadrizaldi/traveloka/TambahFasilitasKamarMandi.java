package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class TambahFasilitasKamarMandi extends AppCompatActivity {

    private String hasil  = "";
    private CheckBox f1,f2,f3,f4,f5;
    private Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_fasilitas_kamar_mandi);

        f1 = (CheckBox)findViewById(R.id.mfk1);
        f2 = (CheckBox)findViewById(R.id.mfk2);
        f3 = (CheckBox)findViewById(R.id.mfk3);
        f4 = (CheckBox)findViewById(R.id.mfk4);
        f5 = (CheckBox)findViewById(R.id.mfk5);

        simpan = (Button) findViewById(R.id.mkfButton);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();

                if (f1.isChecked()){
                    hasil += f1.getText().toString().trim() +".";
                }
                if (f2.isChecked()){
                    hasil += f2.getText().toString().trim() +".";
                }
                if (f3.isChecked()){
                    hasil += f3.getText().toString().trim() +".";
                }
                if (f4.isChecked()){
                    hasil += f4.getText().toString().trim() +".";
                }
                if (f5.isChecked()){
                    hasil += f5.getText().toString().trim() +".";
                }

                Intent intent_ = new Intent();
                intent_.putExtra("fasilitasKamarMandi", hasil);
                ((Activity)TambahFasilitasKamarMandi.this).setResult(Activity.RESULT_OK, intent_);
                ((Activity) TambahFasilitasKamarMandi.this).finish();

            }
        });


    }
}
