package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TambahRekening extends AppCompatActivity {

    Button button;
    TextView bca,mandiri,bri,bni;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_rekening);

//        button = (Button)findViewById(R.id.btn_tambah______);
        bca = (TextView) findViewById(R.id.textView65_rekening);
        mandiri = (TextView)findViewById(R.id.textView65s_rekening);
        bri = (TextView)findViewById(R.id.textView652s_rekening);
        bni = (TextView)findViewById(R.id.textView65s1_rekening);
//        editText = (EditText)findViewById(R.id.no_rekening);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent a = new Intent(TambahRekening.this,Transfer.class);
//                startActivity(a);
//            }
//        });

        bca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(TambahRekening.this,InputRekening.class);
                Bundle b = new Bundle();
                b.putString("bank","bca");
                a.putExtras(b);

                startActivityForResult(a, 616);
            }
        });

        mandiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(TambahRekening.this,InputRekening.class);
                Bundle b = new Bundle();
                b.putString("bank","mandiri");
                a.putExtras(b);

                startActivityForResult(a, 616);
            }
        });

        bri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(TambahRekening.this,InputRekening.class);
                Bundle b = new Bundle();
                b.putString("bank","bri");
                a.putExtras(b);

                startActivityForResult(a, 616);
            }
        });

        bni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(TambahRekening.this,InputRekening.class);
                Bundle b = new Bundle();
                b.putString("bank","bni");
                a.putExtras(b);

                startActivityForResult(a, 616);
            }
        });
    }
}
