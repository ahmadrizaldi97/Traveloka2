package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataPemesan_Hotel extends AppCompatActivity {

    private EditText nama, noHp, email;
    private Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_pemesan__hotel);

        nama = (EditText)findViewById(R.id.namalengkap_hotel);
        noHp = (EditText)findViewById(R.id.noHP_Hotel);
        email = (EditText)findViewById(R.id.emailPemesan_hotel);

        simpan = (Button)findViewById(R.id.simpandatapemesan_hotel);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().trim().indexOf("@") < 0 || email.getText().toString().trim().indexOf(".") < 0){
                    pesan();
                }else {

                    Intent intent_ = new Intent();
                    intent_.putExtra("nama", nama.getText().toString().trim());
                    intent_.putExtra("noHp", noHp.getText().toString().trim());
                    intent_.putExtra("email", email.getText().toString().trim());
                    ((Activity) DataPemesan_Hotel.this).setResult(Activity.RESULT_OK, intent_);
                    ((Activity) DataPemesan_Hotel.this).finish();
                }
            }
        });

    }

    private void pesan(){
        Toast.makeText(DataPemesan_Hotel.this, "Data Tidak Valid", Toast.LENGTH_SHORT).show();
    }
}
