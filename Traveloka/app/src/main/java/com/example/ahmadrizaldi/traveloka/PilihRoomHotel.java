package com.example.ahmadrizaldi.traveloka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.Adapter_Choose_Room;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel_Fix;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PilihRoomHotel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RoomHotel_Fix> roomHotelArrayList = new ArrayList<>();
    private Adapter_Choose_Room mAdapter;

    DatabaseReference Room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_room_hotel);


        Room = FirebaseDatabase.getInstance().getReference("Room Hotel");

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewkeLima_);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//

        Bundle b = getIntent().getExtras();

        mAdapter = new Adapter_Choose_Room(PilihRoomHotel.this, roomHotelArrayList, b);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(itemDecoration);

        Room.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                RoomHotel_Fix Room = dataSnapshot.getValue(RoomHotel_Fix.class);

                String r1 = "",r2 = "",r3= "",r4 = "",r5 ="",r6 = "";

                if (Room.getGm1()!= null){
                    r1 = Room.getGm1();
                }

                if (Room.getGm2()!= null){
                    r2 = Room.getGm2();
                }

                if (Room.getGm3()!= null){
                    r3 = Room.getGm3();
                }

                if (Room.getGm4()!= null){
                    r4 = Room.getGm4();
                }

                if (Room.getGm5()!= null){
                    r5 = Room.getGm5();
                }
                if (Room.getGm6()!= null){
                    r6 = Room.getGm6();
                }


                Bundle bs = getIntent().getExtras();

                if (Room.getIDHotel().equals(bs.getString("id_hotel"))) {

                    tambahData(Room.getIDHotel(), Room.getID_Room(), Room.getNamaRoom(), Room.getHarga(), r1, r2, r3, r4, r5, r6,
                            Room.getUkuranKamar(), Room.getTipeTempatTidur(), Room.getFasilitasKamar(), Room.getFasilitasKamarMandi(), Room.getDeskripsiKamar(), Room.getStok());
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

    //ID_Room, ID_Hotel, NamaRoom, Harga, Fasilitas, UkuranKamar, tipeTempatTidur,Kebijakan;

    private void tambahData(String IDHotel, String ID_Room, String namaRoom, String harga, String gm1, String gm2, String gm3,
                            String gm4, String gm5, String gm6, String ukuranKamar, String tipeTempatTidur, String fasilitasKamar,
                            String fasilitasKamarMandi, String deskripsiKamar, String stok){
        RoomHotel_Fix rm = new RoomHotel_Fix(IDHotel, ID_Room, namaRoom, harga, gm1, gm2, gm3,gm4,gm5,gm6, ukuranKamar, tipeTempatTidur, fasilitasKamar,
                fasilitasKamarMandi,deskripsiKamar, stok);
        roomHotelArrayList.add(rm);

        mAdapter.notifyDataSetChanged();
    }
}
