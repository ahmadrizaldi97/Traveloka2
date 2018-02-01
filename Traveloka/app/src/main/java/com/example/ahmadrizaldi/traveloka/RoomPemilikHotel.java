package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ahmadrizaldi.traveloka.adapter.Adapter_Room_Pemilik;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel_Fix;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RoomPemilikHotel extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<RoomHotel_Fix> listRoom = new ArrayList<>();
    Adapter_Room_Pemilik mAdapter;

    Button tambahRoom_pmk;

    DatabaseReference dataRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_pemilik_hotel);

        dataRoom = FirebaseDatabase.getInstance().getReference("Room Hotel");
        tambahRoom_pmk = (Button)findViewById(R.id.tambahRoom_pmk);

        recyclerView = (RecyclerView)findViewById(R.id.room_pemilikhotel);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Room_Pemilik(RoomPemilikHotel.this, listRoom);
        recyclerView.setAdapter(mAdapter);

        dataRoom.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                RoomHotel_Fix room = dataSnapshot.getValue(RoomHotel_Fix.class);

                Bundle bs = getIntent().getExtras();
                if (room.getIDHotel().equals(bs.getString("id_hotel"))) {

                    tambahDataRoom(room.getIDHotel(), room.getID_Room(), room.getNamaRoom(), room.getHarga(),
                            room.getGm1(), room.getGm2(), room.getGm3(), room.getGm4(), room.getGm5(),
                            room.getGm6(), room.getUkuranKamar(), room.getTipeTempatTidur(), room.getFasilitasKamar(),room.getFasilitasKamarMandi(),
                            room.getDeskripsiKamar(), room.getStok());

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

        tambahRoom_pmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RoomPemilikHotel.this, TambahRoomHotel.class);

                Bundle b = new Bundle();
                Bundle bs = getIntent().getExtras();
                b.putString("id_hotel", bs.getString("id_hotel"));
                b.putString("idHotell", bs.getString("idHotell"));
                i.putExtras(b);

                startActivity(i);
            }
        });

    }


    private void tambahDataRoom(String IDHotel, String id_room, String namaRoom, String harga, String gm1, String gm2, String gm3,
                                String gm4, String gm5, String gm6, String ukuranKamar, String tipeTempatTidur, String fasilitasKamar,
                                String fasilitasKamarMandi, String deskripsiKamar, String stok){
        RoomHotel_Fix rm = new RoomHotel_Fix(IDHotel, id_room, namaRoom, harga, gm1, gm2, gm3, gm4, gm5, gm6, ukuranKamar, tipeTempatTidur, fasilitasKamar,
                fasilitasKamarMandi,deskripsiKamar, stok) ;
        listRoom.add(rm);
        mAdapter.notifyDataSetChanged();
    }

}
