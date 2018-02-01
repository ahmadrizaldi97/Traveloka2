package com.example.ahmadrizaldi.traveloka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.list.datapenerbangan;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.ahmadrizaldi.traveloka.model.das;

import java.lang.reflect.Array;

public class Tampildata extends AppCompatActivity {

    TextView sad;
    DatabaseReference database, teaja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampildata);

        sad = (TextView)findViewById(R.id.dasdas);
        database = FirebaseDatabase.getInstance().getReference("Detail Flights");
        teaja = FirebaseDatabase.getInstance().getReference("Flights");



        teaja.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                datapenerbangan sa = dataSnapshot.getValue(datapenerbangan.class);
                String ka = dataSnapshot.getKey();
                asasa(ka);

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




        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan newPost = dataSnapshot.getValue(DetailDataPenerbangan.class);


                if (newPost.getFrom().toString().trim().equals("SUB") && newPost.getTo().toString().trim().equals("DJJ")){

//                    System.out.println(newPost.getFlights_id());
                    asasa2(newPost.getFlights_id());
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
    }

    private void asasa(String AS){
        Toast.makeText(this, AS, Toast.LENGTH_SHORT).show();
    }

    private void asasa2(String AS){
        Toast.makeText(this, AS, Toast.LENGTH_SHORT).show();
    }
}
