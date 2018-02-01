package com.example.ahmadrizaldi.traveloka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.adapter_penerbangantersedia;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PenerbanganTersediaR extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DetailDataPenerbangan> datapenerbanganArrayList = new ArrayList<>();
    private adapter_penerbangantersedia mAdapter;
    private DatabaseReference dataPenerbangan;

    private String darimana_ms, kemana_ms,namaMaskapai, tanggal_berangkat, kelas = "";
    private int jumlahpenumpang, kuota = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penerbangan_tersedia_r);

        Bundle b = getIntent().getExtras();

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewkeduaR);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        mAdapter = new adapter_penerbangantersedia(PenerbanganTersediaR.this, datapenerbanganArrayList,
//                String.valueOf(b.getCharSequence("jumlahpenumpang")), String.valueOf(b.getCharSequence("kelas")), String.valueOf(b.getCharSequence("lok_dari")), String.valueOf(b.getCharSequence("lok_ke")));


        mAdapter = new adapter_penerbangantersedia(PenerbanganTersediaR.this, datapenerbanganArrayList, true, b.getString("kelas"), b.getString("dari"), b.getString("kemana"), b.getString("Passanger"), b.getString("Passanger_Awal"), b.getString("ID_Reservasi"), b.getString("jumlah_penumpang"));
        recyclerView.setAdapter(mAdapter);

        dataPenerbangan = FirebaseDatabase.getInstance().getReference("Detail Flights");

        kelas = b.getString("kelas");
        darimana_ms = b.getString("dari");
        kemana_ms = b.getString("kemana");
        namaMaskapai = b.getString("namaMaskapai");

        dataPenerbangan.orderByChild("dates").equalTo(b.getString("tanggal").toString()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan newPost = dataSnapshot.getValue(DetailDataPenerbangan.class);

                if (kelas.equals("Economy Class")){
                    kuota = Integer.parseInt(newPost.getKuota_E().toString());
                }else if (kelas.equals("Business Class")){
                    kuota = Integer.parseInt(newPost.getKuota_B().toString());
                }else if(kelas.equals("First Class")){
                    kuota = Integer.parseInt(newPost.getKuota_F().toString());
                }

                if (newPost.getFrom().toString().trim().equals(darimana_ms) && newPost.getTo().toString().trim().equals(kemana_ms)
                        && kuota > jumlahpenumpang && newPost.getId().toString().indexOf(namaMaskapai) > 0){


                    System.out.println(newPost.getFlights_id().toString().trim());

                    tambahPenerbangan(newPost.getId().toString(), newPost.getFlights_id().toString().trim(), newPost.getFrom().toString().trim(),
                            newPost.getTo().toString().trim(), newPost.getDates().toString().trim(),
                            newPost.getJamBerangkat().toString().trim(), newPost.getJamTiba().toString().trim(),
                            newPost.getKuota_E().toString().trim(), newPost.getKuota_B().toString().trim(),
                            newPost.getKuota_F().toString().trim(), newPost.getHarga_E().toString().trim(),
                            newPost.getHarga_B().toString().trim(), newPost.getHarga_F().toString().trim(), newPost.getMaxReschedule());
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

    private void tambahPenerbangan(String id, String Flights_id, String From, String To, String Dates, String JamBerangkat, String JamTiba ,
                                   String Kuota_E, String Kuota_B, String Kuota_F,
                                   String Harga_E, String Harga_B, String Harga_F, String maxsssssss){

        //private String Flights_id, From, To, Dates, JamBerangkat, JamTiba , Kuota_E, Kuota_B, Kuota_F, Harga_E, Harga_B, Harga_F;

        DetailDataPenerbangan dat = new  DetailDataPenerbangan(id, Flights_id, From, To, Dates, JamBerangkat, JamTiba, Kuota_E, Kuota_B, Kuota_F,
                Harga_E, Harga_B, Harga_F, maxsssssss);
        datapenerbanganArrayList.add(dat);

        mAdapter.notifyDataSetChanged();

    }
}
