package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class PenumpangYangAkanDiUbah_ extends AppCompatActivity {

    private CheckBox c1, c2, c3, c4;
    private Button caribaru;
    private String Id_Reservasi, passangerlist, awal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penumpang_yang_akan_di_ubah_);

        c1 = (CheckBox)findViewById(R.id.penump1);
        c2 = (CheckBox)findViewById(R.id.penump2);
        c3 = (CheckBox)findViewById(R.id.penump3);
        c4 = (CheckBox)findViewById(R.id.penump4);
        caribaru = (Button)findViewById(R.id.reschedule_button_kedua);


        Bundle b = getIntent().getExtras();

        Id_Reservasi = b.getString("ID_Reservasi").toString();
        passangerlist = b.getString("namaPenumpang").toString();
        awal = b.getCharSequence("namaPenumpang").toString();

        String s = b.getCharSequence("namaPenumpang").toString();

        final String sPlit[] =s.split("\\.");

        if (sPlit.length == 1){
            c1.setText(sPlit[0]);
            c2.setVisibility(View.INVISIBLE);
            c3.setVisibility(View.INVISIBLE);
            c4.setVisibility(View.INVISIBLE);
        }else if (sPlit.length == 2){
            c1.setText(sPlit[0]);
            c2.setText(sPlit[1]);
            c3.setVisibility(View.INVISIBLE);
            c4.setVisibility(View.INVISIBLE);
        }else if (sPlit.length == 3){
            c1.setText(sPlit[0]);
            c2.setText(sPlit[1]);
            c3.setText(sPlit[2]);
            c4.setVisibility(View.INVISIBLE);
        }else{
            c1.setText(sPlit[0]);
            c2.setText(sPlit[1]);
            c3.setText(sPlit[2]);
            c4.setText(sPlit[3]);
        }

        caribaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent op = new Intent(PenumpangYangAkanDiUbah_.this, CariPenerbanganbaru.class);

//                String p1 = c1.getText().toString();
//                String p2 = c2.getText().toString();
//                String p3 = c3.getText().toString();
//                String p4 = c4.getText().toString();

                if (sPlit.length == 1){
                    passangerlist = "";
                    if (c1.isChecked()){
                        passangerlist += c1.getText().toString() + ".";
                    }


                }else if (sPlit.length == 2){
                    passangerlist = "";
                    if (c1.isChecked()){
                        passangerlist += c1.getText().toString() + ".";
                    }
                    if (c2.isChecked()){
                        passangerlist += c2.getText().toString() + ".";
                    }
                }else if (sPlit.length == 3){
                    passangerlist = "";
                    if (c1.isChecked()){
                        passangerlist += c1.getText().toString() + ".";
                    }
                    if (c2.isChecked()){
                        passangerlist += c2.getText().toString() + ".";
                    }
                    if (c3.isChecked()){
                        passangerlist += c3.getText().toString() + ".";
                    }
                }else{
                    passangerlist = "";
                    if (c1.isChecked()){
                        passangerlist += c1.getText().toString() + ".";
                    }
                    if (c2.isChecked()){
                        passangerlist += c2.getText().toString() + ".";
                    }
                    if (c3.isChecked()){
                        passangerlist += c3.getText().toString() + ".";
                    }
                    if (c4.isChecked()){
                        passangerlist += c4.getText().toString() + ".";
                    }
                }
                Bundle bsa = new Bundle();
                Bundle bs = getIntent().getExtras();

                bsa.putString("ID_Reservasi", bs.getString("ID_Reservasi"));
                bsa.putString("kelas", bs.getString("kelas"));
                bsa.putString("namaMaskapai", bs.getString("namaMaskapai"));
                bsa.putString("idPenerbangan",bs.getString("idPenerbangan"));
                bsa.putString("ID_Reservasi", Id_Reservasi);
                bsa.putString("Passanger", passangerlist);
                bsa.putString("Passanger_Awal", awal);

                op.putExtras(bsa);
                startActivity(op);
                System.out.println(passangerlist);

            }
        });
    }
}
