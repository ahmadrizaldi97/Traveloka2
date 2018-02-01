package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataPemesan extends AppCompatActivity {


    private Button simpandata;
    private EditText nama, hp, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pemesan);

        simpandata = (Button)findViewById(R.id.simpandatapemesan);
        nama = (EditText)findViewById(R.id.editText2);
        hp = (EditText)findViewById(R.id.editText3);
        email = (EditText)findViewById(R.id.emailPemesan);

        simpandata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_ = new Intent();

                intent_.putExtra("nama", nama.getText().toString());
                intent_.putExtra("hp", hp.getText().toString());
                intent_.putExtra("email", email.getText().toString());

                setResult(RESULT_OK,intent_);
                finish();

            }
        });
    }
}
