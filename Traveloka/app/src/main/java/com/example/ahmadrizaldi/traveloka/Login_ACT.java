package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.Admin;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.example.ahmadrizaldi.traveloka.model.Maskapai;
import com.example.ahmadrizaldi.traveloka.model.PemilikHotel;
import com.example.ahmadrizaldi.traveloka.model.UserList;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_ACT extends AppCompatActivity {

    private EditText email, password;
    private Button loginkan, lupapassword, register;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference datauser, pemilik_hotel, maskapai, userl;
    private String poinku;


    boolean cekAdmin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__act);



        email = (EditText)findViewById(R.id.emaillgn);
        password = (EditText)findViewById(R.id.passwordlgn);
        loginkan = (Button)findViewById(R.id.logins);
        lupapassword = (Button)findViewById(R.id.lupapassword);
        register = (Button)findViewById(R.id.keregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_ACT.this, Register_ACT.class);
                startActivity(i);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();

        datauser = FirebaseDatabase.getInstance().getReference("User");
        pemilik_hotel = FirebaseDatabase.getInstance().getReference("Pemilik Hotel");
        maskapai = FirebaseDatabase.getInstance().getReference("Maskapai");
        userl = FirebaseDatabase.getInstance().getReference("Admin");


        loginkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){



                                pesan(true);


                                datauser.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        UserList newPost = dataSnapshot.getValue(UserList.class);

                                        if (newPost.getEmail().equals(email.getText().toString().trim())){

                                            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPref.edit();

                                            Intent keMainMenu = new Intent(getApplicationContext(), MainM.class);
                                            poinku = newPost.getPoin().toString();

                                            editor.putString("username",email.getText().toString());
                                            editor.putString("password", password.getText().toString());
                                            editor.putString("poin", poinku);
                                            editor.putString("user", "user");
                                            editor.apply();

                                            cekAdmin = false;

                                            System.out.println("Userbiasa");
                                            startActivity(keMainMenu);
                                            finish();

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

                                pemilik_hotel.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();

                                        PemilikHotel pm = dataSnapshot.getValue(PemilikHotel.class);

                                        if (pm.getEmail().equals(email.getText().toString().trim())) {
                                            Intent i = new Intent(Login_ACT.this, HomeHotel.class);

                                            editor.putString("username", pm.getEmail().toString());
                                            editor.putString("namaHotel", pm.getNamaHotel().toString());
                                            editor.putString("idHotel", pm.getID_Hotel().toString());
                                            editor.putString("reputasi", pm.getReputasi().toString());
                                            editor.putString("user", "hotel");
                                            editor.apply();

                                                    startActivity(i);
                                            System.out.println(pm.getID_Hotel());
                                            finish();

                                            cekAdmin = false;
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


                                maskapai.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();

                                        Maskapai maskapai = dataSnapshot.getValue(Maskapai.class);
                                        if (maskapai.getEmail().toString().trim().equals(email.getText().toString().trim())){
                                            Intent i = new Intent(Login_ACT.this, HomeMaskapai.class);

                                            editor.putString("username", maskapai.getNama().toString());
                                            editor.putString("user", "maskapai");
                                            editor.apply();

                                            startActivity(i);
                                            finish();

                                            cekAdmin = false;

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


                                if (cekAdmin == true){

                                }

                                userl.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        Admin adm = dataSnapshot.getValue(Admin.class);

                                        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();



                                        if (adm.getEmail().toString().trim().equals(email.getText().toString().trim())){
                                            Intent i = new Intent(Login_ACT.this, HomeAdmin.class);


                                            editor.putString("username", adm.getNama());
                                            editor.putString("user", "admin");
                                            editor.apply();

                                            startActivity(i);
                                            finish();
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

        lupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kereset = new Intent(getApplicationContext(), LupaPasswordACT.class);
                startActivity(kereset);
            }
        });

    }

    private void pesan(boolean benarsalah){
        if (benarsalah){
//            Toast.makeText(this, "Login suksesi",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Email / Password salah",Toast.LENGTH_SHORT).show();
        }
    }

    public void pesankosong(){
        Toast.makeText(this,"Email / Password harus diisi",Toast.LENGTH_SHORT).show();
    }
}
