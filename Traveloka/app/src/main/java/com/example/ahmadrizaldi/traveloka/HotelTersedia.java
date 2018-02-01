package com.example.ahmadrizaldi.traveloka;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ahmadrizaldi.traveloka.adapter.Adapter_Choose_Room;
import com.example.ahmadrizaldi.traveloka.adapter.Adapter_hotel_tersedia;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HotelTersedia extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private Adapter_hotel_tersedia mAdapter;

    private DatabaseReference dataHotel;

    private Button filters;
    private String filterBintang;

    private boolean cb1 = false;
    private boolean sebelumNya, sebelumNya2, sebelumNya3, sebelumNya4, sebelumNya5;
    private String SsebelumNya = " ", SsebelumNya2 = " ", SsebelumNya3 = " ", SsebelumNya4 = " ", SsebelumNya5 = " ";

    private boolean cek, cek2, cek3, cek4, cek5, cek6, cek7, cek8;
    private String Scek, Scek2, Scek3, Scek4, Scek5, Scek6, Scek7, Scek8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_tersedia);

        filters = (Button)findViewById(R.id.filterPencarianHotel);
        dataHotel = FirebaseDatabase.getInstance().getReference("Hotel");

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewketiga_11);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        final Bundle bs = getIntent().getExtras();


        mAdapter = new Adapter_hotel_tersedia(HotelTersedia.this, listHotel, bs);
        recyclerView.setAdapter(mAdapter);

        final ProgressDialog dialog = new ProgressDialog(HotelTersedia.this);


        dialog.setTitle("Load Hotel");
        dialog.show();

        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HotelTersedia.this);
                View parentView = getLayoutInflater().inflate(R.layout.dialogfilterhotel, null);
                bottomSheetDialog.setContentView(parentView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500, getResources().getDisplayMetrics()));
                bottomSheetDialog.show();

                sebelumNya = false;
                sebelumNya2 = false;
                sebelumNya3 = false;
                sebelumNya4 = false;
                sebelumNya5 = false;

                SsebelumNya = "_";
                SsebelumNya2 = "_";
                SsebelumNya3 = "_";
                SsebelumNya4 = "_";
                SsebelumNya5 = "_";

                cek = false;
                cek2 = false;
                cek3 = false;
                cek4 = false;
                cek5 = false;
                cek6 = false;
                cek7 = false;
                cek8 = false;

                Scek = ".";
                Scek2 = ".";
                Scek3 = ".";
                Scek4 = ".";
                Scek5 = ".";
                Scek6 = ".";
                Scek7 = ".";
                Scek8  = ".";

                final Button b1 = (Button)parentView.findViewById(R.id.b1);
                final Button b2 = (Button)parentView.findViewById(R.id.b2);
                final Button b3 = (Button)parentView.findViewById(R.id.b3);
                final Button b4 = (Button)parentView.findViewById(R.id.b4);
                final Button b5 = (Button)parentView.findViewById(R.id.b5);

                final Button fa1 = (Button)parentView.findViewById(R.id.fa1);
                final Button fa2 = (Button)parentView.findViewById(R.id.fa2);
                final Button fa3 = (Button)parentView.findViewById(R.id.fa3);
                final Button fa4 = (Button)parentView.findViewById(R.id.fa4);
                final Button fa5 = (Button)parentView.findViewById(R.id.fa5);
                final Button fa6 = (Button)parentView.findViewById(R.id.fa6);
                final Button fa7 = (Button)parentView.findViewById(R.id.fa7);
                final Button fa8 = (Button)parentView.findViewById(R.id.fa8);

                final Button selesai = (Button)parentView.findViewById(R.id.filtersekarang);

                selesai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       clearData();


                        if (sebelumNya == true){
                            SsebelumNya = "1";
                        }

                        if (sebelumNya2 == true){
                            SsebelumNya2 = "2";
                        }

                        if (sebelumNya3 == true){
                            SsebelumNya3 = "3";
                        }

                        if (sebelumNya4 == true){
                            SsebelumNya4 = "4";
                        }

                        if (sebelumNya5 == true){
                            SsebelumNya5 = "5";
                        }

                        if (cek == true){
                            Scek = "wifi";
                        }

                        if (cek2 == true){
                            Scek2 = "kolam";
                        }

                        if (cek3 == true){
                            Scek3 = "parkir";
                        }

                        if (cek4 == true){
                            Scek4 = "restoran";
                        }

                        if (cek5 == true){
                            Scek5 = "lift";
                        }

                        if (cek6 == true){
                            Scek6 = "kebugaran";
                        }

                        if (cek7 == true){
                            Scek7 = "rapat";
                        }

                        if (cek8 == true){
                            Scek8 = "antar";
                        }


                        dataHotel.addChildEventListener(new ChildEventListener() {


                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                                ArrayList<Hotel> listHotelSebelum = new ArrayList<>();
                                listHotelSebelum = listHotel;



                                Hotel datahotel = dataSnapshot.getValue(Hotel.class);

                                if (datahotel.getLokasi().equals(bs.getString("lokasi"))) {

                                if (datahotel.getReputasi().toString().indexOf(SsebelumNya) >= 0 ||
                                        datahotel.getReputasi().toString().indexOf(SsebelumNya2)  >= 0  ||
                                        datahotel.getReputasi().toString().indexOf(SsebelumNya3) >= 0  ||
                                        datahotel.getReputasi().toString().indexOf(SsebelumNya4) >= 0 ||
                                        datahotel.getReputasi().toString().indexOf(SsebelumNya5) >= 0  ){


                                    if (datahotel.getFasilitas().toString().indexOf(Scek) >= 0 &&
                                            datahotel.getFasilitas().toString().indexOf(Scek2) >= 0 &&
                                            datahotel.getFasilitas().toString().indexOf(Scek3) >= 0 &&
                                            datahotel.getFasilitas().toString().indexOf(Scek4) >= 0 &&
                                            datahotel.getFasilitas().toString().indexOf(Scek5) >= 0 &&
                                            datahotel.getFasilitas().toString().indexOf(Scek6) >= 0 &&
                                            datahotel.getFasilitas().toString().indexOf(Scek7) >= 0 &&
                                            datahotel.getFasilitas().toString().indexOf(Scek8) >= 0) {

                                        tambahHotel(datahotel.getID_Hotel().toString(), datahotel.getNamaHotel().toString(), datahotel.getReputasi().toString(), datahotel.getLokasi().toString(),
                                                datahotel.getFasilitas(), datahotel.getDeskripsi(), datahotel.getKebijakan(), datahotel.getLat(),
                                                datahotel.getLang(), datahotel.getGM1().toString(), datahotel.getGM2().toString(), datahotel.getGM3().toString(),
                                                datahotel.getGM4().toString(), datahotel.getGM5().toString(), datahotel.getAlamat(), datahotel.getId_Pemilik());

                                    }
                                    System.out.println("C1 " + Scek);
                                    System.out.println("C2 " + Scek2);
                                    System.out.println("C3 " + Scek3);
                                    System.out.println("C4 " + Scek4);
                                    System.out.println("C5 " + Scek5);
                                    System.out.println("C6 " + Scek6);
                                    System.out.println("C7 " + Scek7);
                                    System.out.println("C8 " + Scek8);

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

                fa1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cek = cekAktifFasilitas(fa1, cek, 1);
                    }
                });

                fa2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cek2 = cekAktifFasilitas(fa2, cek2, 2);
                    }
                });

                fa3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cek3 = cekAktifFasilitas(fa3, cek3, 3);
                    }
                });

                fa4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cek4 = cekAktifFasilitas(fa4, cek4, 4);
                    }
                });

                fa5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cek5 = cekAktifFasilitas(fa5, cek5, 5);
                    }
                });

                fa6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cek6 = cekAktifFasilitas(fa6, cek6, 6);
                    }
                });

                fa7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cek7 = cekAktifFasilitas(fa7, cek7, 7);
                    }
                });

                fa8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cek8 = cekAktifFasilitas(fa8, cek8, 8);
                    }
                });


                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sebelumNya = cekAktif(b1, sebelumNya);

                    }
                });

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sebelumNya2 = cekAktif(b2, sebelumNya2);

                    }
                });

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sebelumNya3 = cekAktif(b3, sebelumNya3);

                    }
                });

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sebelumNya4 = cekAktif(b4, sebelumNya4);

                    }
                });

                b5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sebelumNya5 = cekAktif(b5, sebelumNya5);

                    }
                });

            }
        });

        dataHotel.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {




                Hotel datahotel = dataSnapshot.getValue(Hotel.class);

                if (datahotel.getLokasi().equals(bs.getString("lokasi"))) {

                    tambahHotel(datahotel.getID_Hotel().toString(), datahotel.getNamaHotel().toString(), datahotel.getReputasi().toString(), datahotel.getLokasi().toString(),
                            datahotel.getFasilitas(), datahotel.getDeskripsi(), datahotel.getKebijakan(), datahotel.getLat(),
                            datahotel.getLang(), datahotel.getGM1().toString(), datahotel.getGM2().toString(), datahotel.getGM3().toString(),
                            datahotel.getGM4().toString(), datahotel.getGM5().toString(), datahotel.getAlamat(), datahotel.getId_Pemilik());
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

        dialog.dismiss();


    }

    private boolean cekAktifFasilitas(Button b, boolean sebelum, int urutan){


        if (urutan == 1){
            if (!sebelum){

                sebelum = true;
                int color = Color.parseColor("#F4FBFE");
                b.setBackgroundColor(color);
                color = Color.parseColor("#1BA0E2");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.t_wifi, 0, 0);
            }else{
                sebelum = false;
                int color = Color.parseColor("#FFFFFF");
                b.setBackgroundColor(color);
                color = Color.parseColor("#000000");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.wifi_fcopy, 0, 0);
            }
        }else if (urutan == 2){
            if (!sebelum){

                sebelum = true;
                int color = Color.parseColor("#F4FBFE");
                b.setBackgroundColor(color);
                color = Color.parseColor("#1BA0E2");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.t_kolam, 0, 0);
            }else{
                sebelum = false;
                int color = Color.parseColor("#FFFFFF");
                b.setBackgroundColor(color);
                color = Color.parseColor("#000000");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pool_fcopy, 0, 0);
            }
        }else if (urutan == 3){
            if (!sebelum){

                sebelum = true;
                int color = Color.parseColor("#F4FBFE");
                b.setBackgroundColor(color);
                color = Color.parseColor("#1BA0E2");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.t_parkir, 0, 0);
            }else{
                sebelum = false;
                int color = Color.parseColor("#FFFFFF");
                b.setBackgroundColor(color);
                color = Color.parseColor("#000000");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.parkir_f, 0, 0);
            }
        }else if (urutan == 4){
            if (!sebelum){

                sebelum = true;
                int color = Color.parseColor("#F4FBFE");
                b.setBackgroundColor(color);
                color = Color.parseColor("#1BA0E2");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.t_restaurant, 0, 0);
            }else{
                sebelum = false;
                int color = Color.parseColor("#FFFFFF");
                b.setBackgroundColor(color);
                color = Color.parseColor("#000000");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restoran_f, 0, 0);
            }
        }else if (urutan == 5){
            if (!sebelum){

                sebelum = true;
                int color = Color.parseColor("#F4FBFE");
                b.setBackgroundColor(color);
                color = Color.parseColor("#1BA0E2");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.t_lift, 0, 0);
            }else{
                sebelum = false;
                int color = Color.parseColor("#FFFFFF");
                b.setBackgroundColor(color);
                color = Color.parseColor("#000000");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lift_f, 0, 0);
            }
        }else if (urutan == 6){
            if (!sebelum){

                sebelum = true;
                int color = Color.parseColor("#F4FBFE");
                b.setBackgroundColor(color);
                color = Color.parseColor("#1BA0E2");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.t_kebugaran, 0, 0);
            }else{
                sebelum = false;
                int color = Color.parseColor("#FFFFFF");
                b.setBackgroundColor(color);
                color = Color.parseColor("#000000");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pusatkebugaran_f, 0, 0);
            }
        }else if (urutan == 7){
            if (!sebelum){

                sebelum = true;
                int color = Color.parseColor("#F4FBFE");
                b.setBackgroundColor(color);
                color = Color.parseColor("#1BA0E2");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.t_rapat, 0, 0);
            }else{
                sebelum = false;
                int color = Color.parseColor("#FFFFFF");
                b.setBackgroundColor(color);
                color = Color.parseColor("#000000");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.rapat_f, 0, 0);
            }
        }else if (urutan == 8){
            if (!sebelum){

                sebelum = true;
                int color = Color.parseColor("#F4FBFE");
                b.setBackgroundColor(color);
                color = Color.parseColor("#1BA0E2");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.t_antar, 0, 0);
            }else{
                sebelum = false;
                int color = Color.parseColor("#FFFFFF");
                b.setBackgroundColor(color);
                color = Color.parseColor("#000000");
                b.setTextColor(color);
                b.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.antar_f, 0, 0);
            }
        }

        return sebelum;
    }

    private boolean cekAktif(Button b1, boolean sebelum){

        if (!sebelum){
            sebelum = true;
            int color = Color.parseColor("#F4FBFE");
            b1.setBackgroundColor(color);
            color = Color.parseColor("#FFC412");
            b1.setTextColor(color);
            b1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bintangaktifcopy, 0, 0, 0);

        }else{
            sebelum = false;
            int color = Color.parseColor("#FFFFFF");
            b1.setBackgroundColor(color);
            color = Color.parseColor("#000000");
            b1.setTextColor(color);
            b1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bintangtidakaktifcopy, 0, 0, 0);
        }

        return sebelum;
    }

    private void clearData(){
        listHotel.clear();
        mAdapter.notifyDataSetChanged();
    }

    private void tambahHotel(String ID_Hotel, String NamaHotel, String Reputasi, String Lokasi, String Fasilitas, String Deskripsi, String Kebijakan,
                             double lat, double lang, String g1, String g2 , String g3, String g4, String g5, String alamat, String pemilik) {
        Hotel hot = new Hotel(ID_Hotel , NamaHotel, Reputasi, Lokasi, Fasilitas, Deskripsi, Kebijakan, lat, lang, g1, g2, g3, g4, g5, alamat, pemilik);
        listHotel.add(hot);

        mAdapter.notifyDataSetChanged();
    }


}
