package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class tambahFasilitasKamar extends AppCompatActivity {

    private String hasil  = "";
    private CheckBox f1,f2,f3,f4,f5,f6,f7,f8,f9;
    private Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_fasilitas_kamar);

        f1 = (CheckBox)findViewById(R.id.fk1);
        f2 = (CheckBox)findViewById(R.id.fk2);
        f3 = (CheckBox)findViewById(R.id.fk3);
        f4 = (CheckBox)findViewById(R.id.fk4);
        f5 = (CheckBox)findViewById(R.id.fk5);
        f6 = (CheckBox)findViewById(R.id.fk6);
        f7 = (CheckBox)findViewById(R.id.fk7);
        f8 = (CheckBox)findViewById(R.id.fk8);
        f9 = (CheckBox)findViewById(R.id.fk9);

        simpan = (Button)findViewById(R.id.fkButton);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_ = new Intent();

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
                if (f6.isChecked()){
                    hasil += f6.getText().toString().trim() +".";
                }
                if (f7.isChecked()){
                    hasil += f7.getText().toString().trim() +".";
                }
                if (f8.isChecked()){
                    hasil += f8.getText().toString().trim() +".";
                }
                if (f9.isChecked()){
                    hasil += f9.getText().toString().trim() +".";
                }


                intent_.putExtra("fasilitasKamar", hasil);
                ((Activity)tambahFasilitasKamar.this).setResult(Activity.RESULT_OK, intent_);
                ((Activity) tambahFasilitasKamar.this).finish();
            }
        });

    }
}
