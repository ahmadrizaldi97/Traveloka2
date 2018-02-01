package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPasswordACT extends AppCompatActivity {

    private EditText email;
    private Button button;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password_act);

        email = (EditText)findViewById(R.id.emailreset);
        button = (Button) findViewById(R.id.resetpassword);
        firebaseAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!email.getText().toString().isEmpty()){

                (firebaseAuth.sendPasswordResetEmail(email.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            pesan(true);
                            Intent resetpassword = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(resetpassword);
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

    private void pesan(boolean benarsalah){
        if (benarsalah){
            Toast.makeText(this, "Pesan terkirim, silahkan cek email",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Email salah",Toast.LENGTH_SHORT).show();
        }
    }

    public void pesankosong(){
        Toast.makeText(this,"Email / Password harus diisi",Toast.LENGTH_SHORT).show();
    }
}
