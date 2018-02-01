package com.example.ahmadrizaldi.traveloka;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.ahmadrizaldi.traveloka.model.UserList;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_ACT extends AppCompatActivity {

    private EditText email, password;
    private Button daftarkan;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__act);

        email = (EditText)findViewById(R.id.emailrgs);
        password = (EditText)findViewById(R.id.passwordrgs);
        daftarkan = (Button)findViewById(R.id.Daftarrgs);
        firebaseAuth = FirebaseAuth.getInstance();

        userl = FirebaseDatabase.getInstance().getReference("User");

        daftarkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!email.getText().toString().isEmpty() || !password.getText().toString().isEmpty()){

                    (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            addUser(email.getText().toString());

                            if (task.isSuccessful()){
                                Intent keLogin = new Intent(getApplicationContext(), Login_ACT.class);
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

    private void addUser(String email){
        UserList usr = new UserList(email, "0");
        String id = userl.push().getKey();

        userl.child(id).setValue(usr);
    }

    private void pesan(boolean trueorfalse){
        if (trueorfalse){
            Toast.makeText(this, "Register sukses",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Register Gagal",Toast.LENGTH_SHORT).show();
        }
    }

    private void pesankosong(){
        Toast.makeText(this,"Email / Password harus diisi",Toast.LENGTH_SHORT).show();
    }
}
