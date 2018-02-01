package com.example.ahmadrizaldi.traveloka;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.ahmadrizaldi.traveloka.adapter.adapter_tujuanataudari;
import com.example.ahmadrizaldi.traveloka.list.daftartujuan;

import java.util.ArrayList;

public class ListPenerbangans extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private adapter_tujuanataudari mAdapter;
    private ArrayList<daftartujuan> daftarList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_penerbangans);

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewpertama);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        mAdapter = new adapter_tujuanataudari(ListPenerbangans.this, daftarList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(itemDecoration);

        tambahkota();

    }

    private void tambahkota(){
        daftartujuan df = new daftartujuan("Ambon","Pattimura","AMQ");
        daftarList.add(df);
        df = new daftartujuan("Aceh","Sultan Iskandarmuda","BTJ");
        daftarList.add(df);
        df = new daftartujuan("Bandung","Husein Sastranegara","BDO");
        daftarList.add(df);
        df = new daftartujuan("Banjarmasin","Syamsudin Noor","BDJ");
        daftarList.add(df);
        df = new daftartujuan("Gorontalo","Jalaluddin","GTO");
        daftarList.add(df);
        df = new daftartujuan("Jakarta","Soekarno Hatta","CGK");
        daftarList.add(df);
        df = new daftartujuan("Jayapura","Sentani","DJJ");
        daftarList.add(df);
        df = new daftartujuan("Kendari","Haluoleo","KDI");
        daftarList.add(df);
        df = new daftartujuan("Lombok","Lombok Praya","LOP");
        daftarList.add(df);
        df = new daftartujuan("Makassar","Sultan Hasanuddin","UPG");
        daftarList.add(df);
        df = new daftartujuan("Malang","Abdul Rachman Saleh","MLG");
        daftarList.add(df);
        df = new daftartujuan("Medan","Kuala Namu","KNO");
        daftarList.add(df);
        df = new daftartujuan("Padang","Minangkabau","PDG");
        daftarList.add(df);
        df = new daftartujuan("Semarang","Achmad Yani","SRG");
        daftarList.add(df);
        df = new daftartujuan("Surabaya","Juanda","SUB");
        daftarList.add(df);
        df = new daftartujuan("Wamena","Bandara Udara Wamena","WMX");
        daftarList.add(df);
        df = new daftartujuan("Yogyakarta","Adi Sutjipto","JOG");
        daftarList.add(df);
        mAdapter.notifyDataSetChanged();
    }
}
