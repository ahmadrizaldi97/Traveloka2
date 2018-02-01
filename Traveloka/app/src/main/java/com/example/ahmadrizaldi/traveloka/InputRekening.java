package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputRekening extends AppCompatActivity {

    String bank;
    EditText namaRekening;
    DatabaseReference banks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_rekening);

        Button button = (Button)findViewById(R.id.btn_tambah);
        final EditText editText = (EditText)findViewById(R.id.no_rekening);
        namaRekening = (EditText)findViewById(R.id.namaRekening);

        Bundle b = getIntent().getExtras();
        bank = b.getString("bank").toString();

        banks = FirebaseDatabase.getInstance().getReference("Rekening");


        banks.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Rekening tw = dataSnapshot.getValue(Rekening.class);

                if (tw.getNamaRekening().toString().equals(bank)){
                    editText.setText(tw.getNomor().toString());
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



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                tambahdata(editText.getText().toString().trim(), namaRekening.getText().toString().trim());
            }
        });
    }

    private void pesan(String pesan){
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    public void tambahdata(String a, String namaRekening){
        String b = bank;

        Rekening rekening = new Rekening(b,a, namaRekening);
        banks.child(b).setValue(rekening);
        Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show();

    }
}
