package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.PemilikHotel;
import com.example.ahmadrizaldi.traveloka.model.UserList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterHotel extends AppCompatActivity {

    private EditText nama, email1, password, reputasi;

    private Button regis;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_hotel);


        nama = (EditText)findViewById(R.id.namaHotel_rgs);
        email1 = (EditText)findViewById(R.id.emailrgs_hotel);
        password = (EditText)findViewById(R.id.passwordhotel_rgs);
        reputasi = (EditText)findViewById(R.id.reputasirgs);

        regis = (Button)findViewById(R.id.Daftarrgs_hotel);

        firebaseAuth = FirebaseAuth.getInstance();
        userl = FirebaseDatabase.getInstance().getReference("Pemilik Hotel");

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!email1.getText().toString().isEmpty() || !password.getText().toString().isEmpty()){

                    (firebaseAuth.createUserWithEmailAndPassword(email1.getText().toString(), password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            addUser(nama.getText().toString().trim(), email1.getText().toString(), reputasi.getText().toString().trim());

                            if (task.isSuccessful()){

                                pesan(true);
                            }else{
                                pesan(false);
                            }


                        }
                    });
                }else{
                    pesankosong();
                }


            }
        });

    }

    private void addUser(String namaH, String emails, String reputasi){
        String id = userl.push().getKey();
        PemilikHotel usr = new PemilikHotel(id, namaH, emails, reputasi);


        userl.child(id).setValue(usr);

        finish();
    }

    private void pesan(boolean trueorfalse){
        if (trueorfalse){
            Toast.makeText(this, "Register sukses",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Register Gagal",Toast.LENGTH_SHORT).show();
        }
    }

    private void pesankosong(){
        Toast.makeText(this,"Email / Password harus disi",Toast.LENGTH_SHORT).show();
    }
}
