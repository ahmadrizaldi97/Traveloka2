package com.example.ahmadrizaldi.traveloka;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.adapter.adapter_penerbangantersedia;
import com.example.ahmadrizaldi.traveloka.list.datapenerbangan;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PenerbanganTersedia extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DetailDataPenerbangan> datapenerbanganArrayList = new ArrayList<>();
    private adapter_penerbangantersedia mAdapter;
    private DatabaseReference dataPenerbangan;

    private Button filterpencarian;

    private TextView jumlah;
    private String darimana_ms, kemana_ms, tanggal_berangkat,filterString, hargaFilter, kelas = "";
    private int jumlahpenumpang, kuota = 0;

    private boolean sebelumNya, sebelumNya2, sebelumNya3, sebelumNya4, sebelumNya5;
    private String SsebelumNya = "*", SsebelumNya2 = "*", SsebelumNya3 = "*", SsebelumNya4 = "*", SsebelumNya5 = "*";

    private boolean cek, cek2, cek3, cek4;
    private int Scek, Scek2, Scek3, Scek4, SScek, SScek2, SScek3, SScek4;

    private int hargaMinimal = 0;
    private int maxImal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penerbangan_tersedia);

        filterpencarian = (Button)findViewById(R.id.filterPencarian);

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewkedua);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        final Bundle b = getIntent().getExtras();

        if (!String.valueOf(b.getCharSequence("ID_Reservasi")).equals("kosong")){
            mAdapter = new adapter_penerbangantersedia(PenerbanganTersedia.this, datapenerbanganArrayList,
                    true, String.valueOf(b.getCharSequence("kelas")), String.valueOf(b.getCharSequence("lok_dari")), String.valueOf(b.getCharSequence("lok_ke")), String.valueOf(b.getCharSequence("Passanger")), String.valueOf(b.getCharSequence("Passanger_Awal")), String.valueOf(b.getCharSequence("ID_Reservasi")), String.valueOf(b.getCharSequence("jumlah_penumpang")) );
        }else{
            mAdapter = new adapter_penerbangantersedia(PenerbanganTersedia.this, datapenerbanganArrayList,
                String.valueOf(b.getCharSequence("jumlahpenumpang")), String.valueOf(b.getCharSequence("kelas")), String.valueOf(b.getCharSequence("lok_dari")), String.valueOf(b.getCharSequence("lok_ke")));
        }

        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(itemDecoration);

        dataPenerbangan = FirebaseDatabase.getInstance().getReference("Detail Flights");

//        Bundle b = getIntent().getExtras();
        kelas = String.valueOf(b.getCharSequence("kelas"));
        darimana_ms = b.getCharSequence("dari").toString();
        kemana_ms = b.getCharSequence("kemana").toString();
        tanggal_berangkat = b.getCharSequence("tanggal").toString();


        if (b.getCharSequence("jumlahpenumpang") == null){
            jumlahpenumpang = 2;
        }else {
            jumlahpenumpang = Integer.parseInt(b.getCharSequence("jumlahpenumpang").toString());
        }

        filterpencarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PenerbanganTersedia.this);
                View parentView = getLayoutInflater().inflate(R.layout.dialogfilter, null);
                bottomSheetDialog.setContentView(parentView);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 900, getResources().getDisplayMetrics()));

                bottomSheetDialog.show();

                SeekBar hargaSeek = (SeekBar)parentView.findViewById(R.id.seekBarHarga);
                final CheckBox ms1 = (CheckBox)parentView.findViewById(R.id.ms1);
                final CheckBox ms2 = (CheckBox)parentView.findViewById(R.id.ms2);
                final CheckBox ms3 = (CheckBox)parentView.findViewById(R.id.ms3);
                final CheckBox ms4 = (CheckBox)parentView.findViewById(R.id.ms4);
                final CheckBox ms5 = (CheckBox)parentView.findViewById(R.id.ms5);

                final CheckBox dr1 = (CheckBox)parentView.findViewById(R.id.j1);
                final CheckBox dr2 = (CheckBox)parentView.findViewById(R.id.j2);
                final CheckBox dr3 = (CheckBox)parentView.findViewById(R.id.j3);
                final CheckBox dr4 = (CheckBox)parentView.findViewById(R.id.j4);

                Button simpanFilter = (Button)parentView.findViewById(R.id.simpanFilter);

                jumlah = (TextView)parentView.findViewById(R.id.hargaFilter);

                jumlah.setText("Rp. 0");
                SsebelumNya = "kosong";
                SsebelumNya2 = "kosong";
                SsebelumNya3 = "kosong";
                SsebelumNya4 = "kosong";
                SsebelumNya5 = "kosong";


                hargaMinimal = 0;

                hargaSeek.setMax(0);
                hargaSeek.setMax(maxImal);



                hargaSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        jumlah.setText("Rp. " + i);

                        hargaMinimal = i;

                        hargaFilter = String.valueOf(i);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                simpanFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        clearData();


                        SsebelumNya = "kosong";
                        SsebelumNya2 = "kosong";
                        SsebelumNya3 = "kosong";
                        SsebelumNya4 = "kosong";
                        SsebelumNya5 = "kosong";

                        Scek = 100;
                        Scek2 = 100;
                        Scek3 = 100;
                        Scek4 = 100;

                        SScek = 100;
                        SScek2 = 100;
                        SScek3 = 100;
                        SScek4 = 100;

                        if (dr1.isChecked()){
                            Scek = 00;
                            SScek = 06;
                        }

                        if (dr2.isChecked()){
                            Scek2 = 06;
                            SScek2 = 12;
                        }

                        if (dr3.isChecked()){
                            Scek3 = 12;
                            SScek3 = 18;
                        }

                        if (dr4.isChecked()){
                            Scek4 = 18;
                            SScek4 = 24;
                        }

                        if (dr1.isChecked() == false && dr2.isChecked() == false
                                && dr3.isChecked() == false && dr4.isChecked() == false){

                            Scek = 0;
                            Scek2 = 0;
                            Scek3 = 0;
                            Scek4 = 0;

                            SScek = 0;
                            SScek2 = 0;
                            SScek3 = 0;
                            SScek4 = 0;
                        }


                        if (ms1.isChecked() == true){
                            SsebelumNya = "Garuda";
                        }

                        if (ms2.isChecked() == true){
                            SsebelumNya2 = "Citilink";
                        }

                        if (ms3.isChecked() == true){
                            SsebelumNya3 = "Sriwijaya";
                        }

                        if (ms4.isChecked() == true){
                            SsebelumNya4 = "Lion";
                        }

                        if (ms5.isChecked() == true){
                            SsebelumNya5 = "Batik";
                        }

                        if (ms1.isChecked() == false && ms2.isChecked() == false
                                && ms3.isChecked() == false && ms4.isChecked() == false && ms5.isChecked() == false){

                            SsebelumNya = "*";
                            SsebelumNya2 = "*";
                            SsebelumNya3 = "*";
                            SsebelumNya4 = "*";
                            SsebelumNya5 = "*";

                        }




                        dataPenerbangan.orderByChild("dates").equalTo(b.getCharSequence("tanggal").toString()).addChildEventListener(new ChildEventListener() {
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
                                        && kuota > jumlahpenumpang){

                                    if (newPost.getId().toString().indexOf(SsebelumNya) >= 0 ||
                                            newPost.getId().toString().indexOf(SsebelumNya2) >= 0 ||
                                            newPost.getId().toString().indexOf(SsebelumNya3) >= 0 ||
                                            newPost.getId().toString().indexOf(SsebelumNya4) >= 0 ||
                                            newPost.getId().toString().indexOf(SsebelumNya5) >= 0){


                                        String jam, menit;
                                        int index = 0;
                                        int jamInt, menitInt;
                                        jamInt = 0;
                                        menitInt = 0;

                                        index = newPost.getJamBerangkat().indexOf(":");
                                        jam = newPost.getJamBerangkat().substring(0, index);
                                        menit = newPost.getJamBerangkat().substring(index + 1);

                                        jamInt = Integer.parseInt(jam);
                                        menitInt = Integer.parseInt(menit);

                                        if (jamInt >= Scek && jamInt <= SScek  || jamInt >= Scek2 && jamInt <= SScek2 ||
                                                jamInt >= Scek3 && jamInt <= SScek3 || jamInt >= Scek4 && jamInt <= SScek4 ) {


                                            if (kelas.equals("Economy Class")){
                                                if (hargaMinimal < Integer.parseInt(newPost.getHarga_E().toString())){
                                                    tambahPenerbangan(newPost.getId().toString(), newPost.getFlights_id().toString().trim(), newPost.getFrom().toString().trim(),
                                                            newPost.getTo().toString().trim(), newPost.getDates().toString().trim(),
                                                            newPost.getJamBerangkat().toString().trim(), newPost.getJamTiba().toString().trim(),
                                                            newPost.getKuota_E().toString().trim(), newPost.getKuota_B().toString().trim(),
                                                            newPost.getKuota_F().toString().trim(), newPost.getHarga_E().toString().trim(),
                                                            newPost.getHarga_B().toString().trim(), newPost.getHarga_F().toString().trim(), newPost.getMaxReschedule());

                                                }
                                            }

                                            if (kelas.equals("Business Class")){
                                                if (hargaMinimal < Integer.parseInt(newPost.getHarga_B().toString())){
                                                    tambahPenerbangan(newPost.getId().toString(), newPost.getFlights_id().toString().trim(), newPost.getFrom().toString().trim(),
                                                            newPost.getTo().toString().trim(), newPost.getDates().toString().trim(),
                                                            newPost.getJamBerangkat().toString().trim(), newPost.getJamTiba().toString().trim(),
                                                            newPost.getKuota_E().toString().trim(), newPost.getKuota_B().toString().trim(),
                                                            newPost.getKuota_F().toString().trim(), newPost.getHarga_E().toString().trim(),
                                                            newPost.getHarga_B().toString().trim(), newPost.getHarga_F().toString().trim(), newPost.getMaxReschedule());

                                                }
                                            }

                                            if(kelas.equals("First Class")){
                                                if (hargaMinimal < Integer.parseInt(newPost.getHarga_F().toString())){
                                                    tambahPenerbangan(newPost.getId().toString(), newPost.getFlights_id().toString().trim(), newPost.getFrom().toString().trim(),
                                                            newPost.getTo().toString().trim(), newPost.getDates().toString().trim(),
                                                            newPost.getJamBerangkat().toString().trim(), newPost.getJamTiba().toString().trim(),
                                                            newPost.getKuota_E().toString().trim(), newPost.getKuota_B().toString().trim(),
                                                            newPost.getKuota_F().toString().trim(), newPost.getHarga_E().toString().trim(),
                                                            newPost.getHarga_B().toString().trim(), newPost.getHarga_F().toString().trim(), newPost.getMaxReschedule());

                                                }
                                            }



                                        }
                                        System.out.println("jam " + hargaMinimal);
                                        System.out.println("menit " + menitInt);


                                    }

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


                        bottomSheetDialog.dismiss();

                    }


                });

            }
        });

        dataPenerbangan.orderByChild("dates").equalTo(b.getCharSequence("tanggal").toString()).addChildEventListener(new ChildEventListener() {
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
                       && kuota > jumlahpenumpang){

                    if (kelas.equals("Economy Class")){

                        if (maxImal < Integer.parseInt(newPost.getHarga_E().toString())){
                            maxImal = Integer.parseInt(newPost.getHarga_E().toString());
                        }

                    }else if (kelas.equals("Business Class")){
                        if (maxImal < Integer.parseInt(newPost.getHarga_B().toString())){
                            maxImal = Integer.parseInt(newPost.getHarga_B().toString());
                        }
                    }else if(kelas.equals("First Class")){
                        if (maxImal < Integer.parseInt(newPost.getHarga_F().toString())){
                            maxImal = Integer.parseInt(newPost.getHarga_F().toString());
                        }
                    }

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

//        System.out.println(darimana_ms + kemana_ms);



//        tambahPenerbangan();

    }

//    private boolean cekAktif(CheckBox b1, boolean sebelum){
//
//        if (!sebelum){
//            sebelum = true;
//            int color = Color.parseColor("#F4FBFE");
//            b1.setBackgroundColor(color);
//            color = Color.parseColor("#FFC412");
//            b1.setTextColor(color);
//            b1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bintangaktifcopy, 0, 0, 0);
//
//        }else{
//            sebelum = false;
//            int color = Color.parseColor("#FFFFFF");
//            b1.setBackgroundColor(color);
//            color = Color.parseColor("#000000");
//            b1.setTextColor(color);
//            b1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bintangtidakaktifcopy, 0, 0, 0);
//        }
//
//        return sebelum;
//    }

    private void clearData(){
        datapenerbanganArrayList.clear();
        mAdapter.notifyDataSetChanged();
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
