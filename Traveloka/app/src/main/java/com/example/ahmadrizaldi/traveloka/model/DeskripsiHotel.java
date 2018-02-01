package com.example.ahmadrizaldi.traveloka.model;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.TambahGambar;

public class DeskripsiHotel extends AppCompatActivity {

    private EditText deskripsi;
    private Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deskripsi_hotel);

        deskripsi = (EditText)findViewById(R.id.deskripsi_H);

        simpan = (Button)findViewById(R.id.simpanDeskripsi__);

        Bundle a = getIntent().getExtras();

        deskripsi.setText(a.getCharSequence("deskripsi"));

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_ = new Intent();

                intent_.putExtra("deskripsi", deskripsi.getText().toString().trim());

                setResult(RESULT_OK, intent_);
                finish();

                DeskripsiHotel.this.setResult(Activity.RESULT_OK, intent_);
            }
        });


    }
}
