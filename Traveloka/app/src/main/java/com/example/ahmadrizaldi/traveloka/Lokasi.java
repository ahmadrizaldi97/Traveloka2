package com.example.ahmadrizaldi.traveloka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmadrizaldi.traveloka.adapter.Adapyer_tujuanataudariHotel;
import com.example.ahmadrizaldi.traveloka.model.LokasiPnc;

import java.util.ArrayList;

public class Lokasi extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<LokasiPnc> lokasiPncs = new ArrayList<>();
    Adapyer_tujuanataudariHotel mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);

        recyclerView = (RecyclerView)findViewById(R.id.dadhjasdhgjahjsdha);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);


        mAdapter = new Adapyer_tujuanataudariHotel(Lokasi.this, lokasiPncs);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(itemDecoration);
        TambahData();
    }

    private void TambahData(){
        LokasiPnc lok = new LokasiPnc("Malang");
        lokasiPncs.add(lok);
        lok = new LokasiPnc("Makassar");
        lokasiPncs.add(lok);
        lok = new LokasiPnc("Surabaya");
        lokasiPncs.add(lok);
        lok = new LokasiPnc("Jakarta");
        lokasiPncs.add(lok);
        lok = new LokasiPnc("Medan");
        lokasiPncs.add(lok);
        lok = new LokasiPnc("Jayapura");
        lokasiPncs.add(lok);
        lok = new LokasiPnc("Merauke");
        lokasiPncs.add(lok);
        mAdapter.notifyDataSetChanged();
    }
}
