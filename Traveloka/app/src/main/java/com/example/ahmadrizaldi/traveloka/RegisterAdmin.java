package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterAdmin extends AppCompatActivity {

    private EditText nama, email, password;
    private Button registerl;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference userl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_admin);

        nama = (EditText)findViewById(R.id.rgsadmin);
        email = (EditText)findViewById(R.id.rgsemail);
        password = (EditText)findViewById(R.id.rgspass);

        registerl = (Button)findViewById(R.id.rgsadminbutton);

        firebaseAuth = FirebaseAuth.getInstance();
        userl = FirebaseDatabase.getInstance().getReference("Admin");

        registerl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!email.getText().toString().isEmpty() || !password.getText().toString().isEmpty()){

                    (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()){
                                addUser(nama.getText().toString().trim(), email.getText().toString().toString().trim());

                                Intent keLogin = new Intent(getApplicationContext(), HomeAdmin.class);
                                startActivity(keLogin);
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

    private void addUser(String nama, String email){
        Admin adm = new Admin(nama, email);
        String id = userl.push().getKey();

        userl.child(id).setValue(adm);

    }

    private void pesan(boolean trueorfalse){
        if (trueorfalse){
            Toast.makeText(this, "Register sukses",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Harap mengisi data dengan benar",Toast.LENGTH_SHORT).show();
        }
    }

    private void pesankosong(){
        Toast.makeText(this,"Harap mengisi data dengan benar",Toast.LENGTH_SHORT).show();
    }


}
