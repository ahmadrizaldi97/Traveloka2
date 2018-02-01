package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.adapter_for_reschedule;
import com.example.ahmadrizaldi.traveloka.model.Reschedule;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Konfirmasi_Reschedule extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Reschedule> RescheduleArrayList = new ArrayList<>();
    private  adapter_for_reschedule mAdapter;
    private DatabaseReference rescedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi__reschedule);

        rescedule = FirebaseDatabase.getInstance().getReference("Reschedule");
        recyclerView = (RecyclerView)findViewById(R.id.recycleviewkonfirmasireschedule);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new adapter_for_reschedule(Konfirmasi_Reschedule.this, RescheduleArrayList);
        recyclerView.setAdapter(mAdapter);

        rescedule.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reschedule resc = dataSnapshot.getValue(Reschedule.class);

                SharedPreferences sharedPref =  getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                final String email = sharedPref.getString("username",null);


                if (resc.getId().toString().indexOf(email) >= 0) {

                    reschedule(resc.getId_Reservasi(), resc.getTotalBayar(), resc.getFlight_id(), resc.getKelas(), resc.getJumlahPenumpang(),
                            resc.getDaftarPenumpang(), resc.getStatus(), resc.getId(), resc.getId_baru());
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

    private void reschedule(String Id_Reservasi, String Id_Reservasi_baru,String Flight_id,String kelass,String jumlahPenumpang,String daftarPenumpang,String Status, String idd, String idLama){
        Reschedule resc = new Reschedule(Id_Reservasi, Id_Reservasi_baru, Flight_id, kelass, jumlahPenumpang, daftarPenumpang, Status, idd, idLama);
        RescheduleArrayList.add(resc);
        mAdapter.notifyDataSetChanged();
    }
}
