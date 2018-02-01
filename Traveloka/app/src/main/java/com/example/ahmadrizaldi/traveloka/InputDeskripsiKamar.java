package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputDeskripsiKamar extends AppCompatActivity {

    private EditText deskripsi;
    private Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_deskripsi_kamar);

        deskripsi = (EditText)findViewById(R.id.deskripsiRoomInput);
        simpan = (Button)findViewById(R.id.simpandeskripsiRoomInput);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_ = new Intent();
                intent_.putExtra("deskripsi", deskripsi.getText().toString().trim());
                ((Activity)InputDeskripsiKamar.this).setResult(Activity.RESULT_OK, intent_);
                ((Activity) InputDeskripsiKamar.this).finish();
            }
        });




    }
}
