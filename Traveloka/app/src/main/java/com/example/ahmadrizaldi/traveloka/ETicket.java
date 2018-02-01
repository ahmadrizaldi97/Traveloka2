package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.example.ahmadrizaldi.traveloka.model.passangerlist;
import com.google.firebase.database.FirebaseDatabase;

public class ETicket extends AppCompatActivity {

    private TextView PNR, kelass, drmn, kmn, namaPenumpang, maskapai,
            namaBandaraDarimana, namaBandaraKemana, jamBerangkat1, jamTiba1;
    private ImageView gambarLogo;
    private String IdTransaksi;
    private DatabaseReference passanger;
    private Button rescedule;
    private String PassangerList, flight, namaMaskapai;
    String idPenerbangan = "";
    private String Kelassa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eticket);

        PNR = (TextView)findViewById(R.id.textView46);
        kelass = (TextView)findViewById(R.id.kelas_etkt);
        drmn = (TextView)findViewById(R.id.darimana_etkt);
        kmn = (TextView)findViewById(R.id.kemana_etkt);
        namaPenumpang = (TextView)findViewById(R.id.namaPenumpang_etkt);
        maskapai = (TextView)findViewById(R.id.textView49);
        namaBandaraDarimana = (TextView)findViewById(R.id.namaBandara);
        namaBandaraKemana = (TextView)findViewById(R.id.namaBandara2);
        jamBerangkat1 = (TextView)findViewById(R.id.jamBerangkat111);
        jamTiba1 = (TextView)findViewById(R.id.jamTiba111);
        gambarLogo = (ImageView)findViewById(R.id.logoMaskapaiG);


        rescedule = (Button)findViewById(R.id.reschedule);


        passanger = FirebaseDatabase.getInstance().getReference("Detail Reservasi");

        Bundle op = getIntent().getExtras();

//        op.putString("jamBerangkat", bs.getString("jamBerangkat"));
//        op.putString("jamTiba", bs.getString("JamTiba"));
//        op.putString("tanggalBerangkat", bs.getString("Tanggal"));
//        FLIGHT_ID

        idPenerbangan = op.getString("FLIGHT_ID");
        jamBerangkat1.setText(op.getString("jamBerangkat"));
        jamTiba1.setText(op.getString("jamTiba"));

        namaMaskapai = op.getString("namaMaskapai");
        maskapai.setText(op.getString("namaMaskapai") + " " + op.getString("FLIGHT_ID_02"));

        if (namaMaskapai.equals("Lion Air")){
            gambarLogo.setImageResource(R.drawable.lionairlogo);
        }else if (namaMaskapai.equals("Garuda Indonesia")){
            gambarLogo.setImageResource(R.drawable.garudalogo);
        }else if (namaMaskapai.equals("Sriwijaya Air")){
            gambarLogo.setImageResource(R.drawable.sriwijawalogo);
        }else if (namaMaskapai.equals("Wings Air")){
            gambarLogo.setImageResource(R.drawable.wingslogo);
        }else if (namaMaskapai.equals("Citilink")){
            gambarLogo.setImageResource(R.drawable.citilink);
        }else if (namaMaskapai.equals("Air Asia")){
            gambarLogo.setImageResource(R.drawable.airasia);
        }else if (namaMaskapai.equals("Trigana Air")){
            gambarLogo.setImageResource(R.drawable.triganalogo);
        }

        String darimana = op.getString("darimana");
        String kemana = op.getString("kemana");
        String kodebandara, namaBandara;

        int index, index2;
        index = darimana.indexOf("^") + 1;
        index2 = darimana.indexOf("*");

        kodebandara = darimana.substring(index) + " (" + darimana.substring(0, index2) + ")";
        namaBandara = darimana.substring(index2 +1, index -1);
        namaBandaraDarimana.setText(namaBandara);

        drmn.setText(kodebandara);

        index = kemana.indexOf("^") + 1;
        index2 = kemana.indexOf("*");

        kodebandara = kemana.substring(index) + " (" + kemana.substring(0, index2) + ")";
        namaBandara = kemana.substring(index2 +1, index -1);
        namaBandaraKemana.setText(namaBandara);

        kmn.setText(kodebandara);


        PNR.setText(op.getCharSequence("PNR").toString());
        kelass.setText(op.getCharSequence("KELAS").toString());
        Kelassa = op.getCharSequence("KELAS").toString();

        if (op.getCharSequence("idBaru").toString().equals("")){
            IdTransaksi = op.getCharSequence("ID_RESERVASI").toString();
        }else{
            IdTransaksi = op.getCharSequence("idBaru").toString();
        }



        passanger.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                passangerlist newPost = dataSnapshot.getValue(passangerlist.class);

                if (newPost.getId_Reservasi().equals(IdTransaksi)){

                    String sa = newPost.getNama().toString();
                    String sPlit[] =sa.split("\\.");

                    PassangerList = newPost.getNama().toString();

                    int banyak = sPlit.length;
                    if (String.valueOf(banyak).toString().equals("1")){
                        namaPenumpang.setText("1. " +  sPlit[0]);

                    }else if (String.valueOf(banyak).toString().equals("2")){
                        namaPenumpang.setText("1. " +  sPlit[0] + "\n" + "2. " + sPlit[1]);

                    }else if (String.valueOf(banyak).toString().equals("3")){
                        namaPenumpang.setText("1. " +  sPlit[0] + "\n" + "2. " +  sPlit[1] +  "\n" + "3. "  + sPlit[2]);

                    }else if (String.valueOf(banyak).toString().equals("4")){

                        namaPenumpang.setText("1. " +sPlit[0] + "\n" +"2. " + sPlit[1] + "\n" +"3. " +  sPlit[2] +  "\n" + "4. " + sPlit[3]);
                    }else{
                        namaPenumpang.setText(sPlit[0]);
                    }

//                    namaPenumpang.setText("1. " + newPost.getNama().toString());

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        if (sharedPref.getString("user","").equals("maskapai")) {
            rescedule.setVisibility(View.GONE);
        }



//        if (op.getString("jenis").equals("maskapai")){
//            rescedule.setVisibility(View.GONE);
//        }

        rescedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ETicket.this, PenumpangYangAkanDiUbah_.class);
                Bundle b = new Bundle();

                b.putString("ID_Reservasi", IdTransaksi);
                b.putString("kelas", Kelassa);
                b.putString("namaMaskapai", namaMaskapai);
                b.putString("idPenerbangan",idPenerbangan);
                b.putString("namaPenumpang", PassangerList);

                i.putExtras(b);

                startActivity(i);
            }
        });

    }

}
