package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PenumpangYangAkanDiubah extends AppCompatActivity {

    private Button p1,p2,p3,p4, rescss;
    private String Id_Reservasi, passangerlist, pass;
    private boolean klik, c1,c2,c3,c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penumpang_yang_akan_diubah);

        p1 = (Button)findViewById(R.id.pen1);
        p2 = (Button)findViewById(R.id.pen2);
        p3 = (Button)findViewById(R.id.pen3);
        p4 = (Button)findViewById(R.id.pen4);
        rescss = (Button)findViewById(R.id.reschedule_button);

        final Bundle b = getIntent().getExtras();

        Id_Reservasi = b.getCharSequence("ID_Reservasi").toString();
        passangerlist = b.getCharSequence("namaPenumpang").toString();

        String s = b.getCharSequence("namaPenumpang").toString();

        final String sPlit[] =s.split("\\.");

        if (sPlit.length == 1){
            p1.setText(sPlit[0]);
            p1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (klik == true){
                        p1.setBackgroundColor(Color.parseColor("#dddddd"));
                        klik = false;
                        c1 = true;
                    }else{
                        p1.setBackgroundColor(Color.parseColor("#0770CC"));
                        pass = sPlit[0];
                        klik = true;
                        c1 = false;
                    }



                }
            });

            p2.setVisibility(View.INVISIBLE);
            p3.setVisibility(View.INVISIBLE);
            p4.setVisibility(View.INVISIBLE);
        }else if (sPlit.length == 2){
            p1.setText(sPlit[0]);
            p2.setText(sPlit[1]);

            p1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (klik == true){
                        p1.setBackgroundColor(Color.parseColor("#dddddd"));
                        klik = false;
                        p1.setTag("#dddddd");
                        c1 = true;
                    }else{
                        p1.setBackgroundColor(Color.parseColor("#0770CC"));
                        p1.setTag("#0770CC");
                        klik = true;
                        c1 = false;
                    }
                }
            });

            p2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (klik == true){
                        p2.setBackgroundColor(Color.parseColor("#dddddd"));
                        p2.setTag("#dddddd");
                        klik = false;
                        c2 = true;
                    }else{
                        p2.setBackgroundColor(Color.parseColor("#0770CC"));
                        p2.setTag("#0770CC");
                        klik = true;
                        c2 = false;
                    }


                }
            });

            p3.setVisibility(View.INVISIBLE);
            p4.setVisibility(View.INVISIBLE);
        } else if (sPlit.length == 3){
            p1.setText(sPlit[0]);
            p2.setText(sPlit[1]);
            p3.setText(sPlit[2]);

            p1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (klik == true){
                        p1.setBackgroundColor(Color.parseColor("#dddddd"));
                        klik = false;
                        p1.setTag("#dddddd");
                        c1 = true;
                    }else{
                        p1.setBackgroundColor(Color.parseColor("#0770CC"));
                        p1.setTag("#0770CC");
                        klik = true;
                        c1 = false;
                    }
                }
            });

            p2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (klik == true){
                        p2.setBackgroundColor(Color.parseColor("#dddddd"));
                        p2.setTag("#dddddd");
                        klik = false;
                        c2 = true;
                    }else{
                        p2.setBackgroundColor(Color.parseColor("#0770CC"));
                        p2.setTag("#0770CC");
                        klik = true;
                        c2 = false;
                    }
                }
            });
            p3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (klik == true){
                        klik = false;
                        c3 = true;
                        p3.setTag("#dddddd");
                        p3.setBackgroundColor(Color.parseColor("#dddddd"));
                    }else{
                        p3.setBackgroundColor(Color.parseColor("#0770CC"));
                        klik = true;
                        p3.setTag("#0770CC");
                        c3 = false;
                    }
                }
            });


            p4.setVisibility(View.INVISIBLE);
        }else if (sPlit.length == 4){
            p1.setText(sPlit[0]);
            p2.setText(sPlit[1]);
            p3.setText(sPlit[2]);
            p4.setText(sPlit[3]);

            p1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (klik == true){
                        p1.setBackgroundColor(Color.parseColor("#dddddd"));
                        klik = false;
                        p1.setTag("#dddddd");
                        c1 = true;
                    }else{
                        p1.setBackgroundColor(Color.parseColor("#0770CC"));
                        p1.setTag("#0770CC");
                        klik = true;
                        c1 = false;
                    }
                }
            });

            p2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (klik == true){
                        p2.setBackgroundColor(Color.parseColor("#dddddd"));
                        p2.setTag("#dddddd");
                        klik = false;
                        c2 = true;
                    }else{
                        p2.setBackgroundColor(Color.parseColor("#0770CC"));
                        p2.setTag("#0770CC");
                        klik = true;
                        c2 = false;
                    }
                }
            });
            p3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (klik == true){
                        klik = false;
                        c3 = true;
                        p3.setTag("#dddddd");
                        p3.setBackgroundColor(Color.parseColor("#dddddd"));
                    }else{
                        p3.setBackgroundColor(Color.parseColor("#0770CC"));
                        klik = true;
                        p3.setTag("#0770CC");
                        c3 = false;
                    }
                }
            });
            p4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (klik == true){
                        p4.setBackgroundColor(Color.parseColor("#dddddd"));
                        klik = false;
                        c4 = true;
                        p4.setTag("#dddddd");
                    }else{
                        p4.setBackgroundColor(Color.parseColor("#0770CC"));
                        klik = true;
                        c4 = false;
                        p3.setTag("#0770CC");
                    }
                }
            });



        }

        rescss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent op = new Intent(PenumpangYangAkanDiubah.this, CariPenerbanganbaru.class);
                Bundle bsa = new Bundle();

                String colorCode1 = (String)p1.getTag();
                String colorCode2 = (String)p2.getTag();
                String colorCode3 = (String)p3.getTag();
                String colorCode4 = (String)p4.getTag();

                String penumpang = "";
                if (sPlit.length == 1){

                    

                }else if (sPlit.length == 2){



                }else if (sPlit.length == 3){


                }else if (sPlit.length == 4){


                }else{

                }

                op.putExtras(bsa);
                startActivity(op);

//                System.out.println(" ========" +colorCode1.toString());
//                System.out.println(" ========" +colorCode2.toString());
            }
        });










    }
}
