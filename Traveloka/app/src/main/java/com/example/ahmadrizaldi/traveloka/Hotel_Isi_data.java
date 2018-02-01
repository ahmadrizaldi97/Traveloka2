package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hotel_Isi_data extends AppCompatActivity {

    Button permintaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__isi_data);

        permintaan = (Button)findViewById(R.id.tambahpermintaankhusus);


        permintaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent u = new Intent(Hotel_Isi_data.this, PermintaanKhusus.class);
                startActivity(u);
            }
        });


    }
}
