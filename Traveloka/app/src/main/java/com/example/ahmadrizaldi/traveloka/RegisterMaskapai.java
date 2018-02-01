package com.example.ahmadrizaldi.traveloka;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.Maskapai;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class RegisterMaskapai extends AppCompatActivity {

    EditText namaa, emaila, passworda;
    Button register;
    DatabaseReference maskapai;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_maskapai);

        namaa = (EditText) findViewById(R.id.namaMaskapai_rgs);
        emaila = (EditText)findViewById(R.id.emailrgs_maskapai);
        passworda = (EditText)findViewById(R.id.passwordMaskapai_rgs);

        register = (Button)findViewById(R.id.Daftarrgs_maskapai);

        maskapai = FirebaseDatabase.getInstance().getReference("Maskapai");
        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!namaa.getText().toString().isEmpty() || !emaila.getText().toString().isEmpty() || !passworda.getText().toString().isEmpty()){
                    (firebaseAuth.createUserWithEmailAndPassword(emaila.getText().toString().trim(), passworda.getText().toString().trim())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                pesan(true);
                                finish();
                            }else{
                                pesan(false);
                            }
                        }
                    });
                }


                tambahData(namaa.getText().toString().trim(), emaila.getText().toString().trim());
            }
        });
    }

    private void tambahData(String nama, String email){
        String id = maskapai.push().getKey();
        Maskapai dataMaskapai = new Maskapai(id, nama, email);
        maskapai.child(id).setValue(dataMaskapai);
        Toast.makeText(RegisterMaskapai.this, "Registrasi Maskapai Sukses", Toast.LENGTH_SHORT).show();
        finish();

    }

    private void pesan(boolean trueorfalse){
        if (trueorfalse){
            Toast.makeText(this, "Register sukses",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Register Gagal",Toast.LENGTH_SHORT).show();
        }
    }


}
