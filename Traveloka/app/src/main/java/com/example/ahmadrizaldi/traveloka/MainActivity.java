package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button keRegister, keLogin, reschedule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if (sharedPref.getString("username","") != ""){

            if (sharedPref.getString("user","").equals("user")) {
                Intent keMainMenu = new Intent(getApplicationContext(), MainM.class);
                startActivity(keMainMenu);
            }else if (sharedPref.getString("user","").equals("maskapai")) {
                Intent keMainMenu = new Intent(getApplicationContext(), HomeMaskapai.class);
                startActivity(keMainMenu);
            }else if (sharedPref.getString("user","").equals("hotel")) {
                Intent keMainMenu = new Intent(getApplicationContext(), HomeHotel.class);
                startActivity(keMainMenu);
            }else if (sharedPref.getString("user","").equals("admin")) {
                Intent keMainMenu = new Intent(getApplicationContext(), HomeAdmin.class);
                startActivity(keMainMenu);
            }
            finish();
        }

        keRegister = (Button)findViewById(R.id.keregister);
        keLogin = (Button)findViewById(R.id.kelogin);

        keRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keregister = new Intent(getApplicationContext(), Register_ACT.class);
                startActivity(keregister);
            }
        });

        keLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keLogin = new Intent(getApplicationContext(), Login_ACT.class);
                startActivity(keLogin);
            }
        });

//        reschedule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
