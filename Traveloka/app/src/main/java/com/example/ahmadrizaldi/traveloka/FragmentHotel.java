package com.example.ahmadrizaldi.traveloka;


import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.preference.PreferenceManager;
import android.widget.Button;

import com.example.ahmadrizaldi.traveloka.adapter.Adapter_Booking_Hotel;
import com.example.ahmadrizaldi.traveloka.adapter.adapter_for_booking_list;
import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;
import com.example.ahmadrizaldi.traveloka.model.UserList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHotel extends Fragment {


    private String email;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ReservasiHotel> listReservasi = new ArrayList<>();
    private Adapter_Booking_Hotel mAdapter;

    private DatabaseReference dataBooking, dataUser;


    public FragmentHotel() {
        // Required empty public constructor
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_hotel, container, false);

        Button kePenerbangan = (Button)view.findViewById(R.id.kePenerbangan);

        SharedPreferences sharedPref =  this.getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final String email = sharedPref.getString("username",null);


        kePenerbangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BookingDashboard homeFragment = new BookingDashboard();
                homeFragment.setEmail(email);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, homeFragment);
                fragmentTransaction.commit();
            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.recycleViewBookingHotel2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

//        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mAdapter = new Adapter_Booking_Hotel(this.getContext(), listReservasi);
        recyclerView.setAdapter(mAdapter);

        dataBooking = FirebaseDatabase.getInstance().getReference("Reservasi Hotel");

        dataBooking.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ReservasiHotel res = dataSnapshot.getValue(ReservasiHotel.class);


                String sa = res.getBookingdate().toString().replace("/",".");
                final String sPlit[] = sa.split("\\.");

                Calendar c = Calendar.getInstance();

                Calendar c2 = Calendar.getInstance();


                int posisiTerakhir = 0;
                String tahun = "";
                String bulan = "";

                for (int i=0; i< sPlit.length; i++){
                    if (sPlit[i].indexOf(" ") >1){
                        posisiTerakhir = i;
                        break;
                    }

                    if (i == 0){
                        tahun = sPlit[i];
                        System.out.println("tahun " + sPlit[i]);
                    }

                    if (i == 1){
                        bulan = sPlit[i];
                        System.out.println("bulan " + sPlit[i]);
                    }

                }

                String datee = "";
                String jam, menit;
                int index = sPlit[posisiTerakhir].indexOf(" ");
                int index2 = sPlit[posisiTerakhir].indexOf(":");
                datee = sPlit[posisiTerakhir].substring(0, index);
                jam = sPlit[posisiTerakhir].substring(index + 1, index2);
                menit = sPlit[posisiTerakhir].substring(index2 + 1);


                int BulanFix = 0;

                if (bulan.equals("01")){
                    BulanFix = Calendar.JANUARY;
                }else if (bulan.equals("02")){
                    BulanFix = Calendar.FEBRUARY;
                }else if (bulan.equals("03")){
                    BulanFix = Calendar.MARCH;
                }else if (bulan.equals("04")){
                    BulanFix = Calendar.APRIL;
                }else if (bulan.equals("05")){
                    BulanFix = Calendar.MAY;
                }else if (bulan.equals("06")){
                    BulanFix = Calendar.JUNE;
                }else if (bulan.equals("07")){
                    BulanFix = Calendar.JULY;
                }else if (bulan.equals("08")){
                    BulanFix = Calendar.AUGUST;
                }else if (bulan.equals("09")){
                    BulanFix = Calendar.SEPTEMBER;
                }else if (bulan.equals("10")){
                    BulanFix = Calendar.OCTOBER;
                }else if (bulan.equals("11")){
                    BulanFix = Calendar.NOVEMBER;
                }else if (bulan.equals("12")){
                    BulanFix = Calendar.DECEMBER;
                }

                int jam2Fix = 0;
                jam2Fix = Integer.parseInt(jam);

                c2.set(Integer.parseInt(tahun), BulanFix, Integer.parseInt(datee), jam2Fix, Integer.parseInt(menit));



                long millis1 = c.getTimeInMillis();
                long millis2 = c2.getTimeInMillis();

                long diff = millis2 - millis1;
                long diffSeconds = diff / 1000;
                long diffMinutes = diff / (60 * 1000);
                long diffHours = diff / (60 * 60 * 1000);

                if (diffMinutes < 0 && res.getStatus().equals("Belum Bayar")){

                    updateReservasiHotel(res.getIdReservasi(), res.getEmail(), res.getBookingdate(), res.getIdHotel(),
                            res.getIdRoom(), res.getBank(), res.getTotal(), res.getKodePembayaran(), res.getPoinTerpakai(),
                            res.getPoinPending(), res.getPermintaanKhusus(), res.getDurasi(), res.getJumlahKamar(),
                            res.getNamaHotel(), res.getNamaRoom(), res.getDataPemesan(), "Waktu Habis", res.getTanggalCheckIn(), res.getTanggalCheckOut());
                    System.out.println("In Minte " + diffMinutes+ " Batal");
                }



//                SharedPreferences sharedPref =  this.Activity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//                final String email = sharedPref.getString("username",null);

//                String email2 = "team1@gmail.com";
                if (res.getEmail().toString().trim().equals(email.toString().trim())){
                    TambahReservasiHotel(res.getIdReservasi(), res.getEmail(), res.getBookingdate(),
                            res.getIdHotel(), res.getIdRoom(), res.getBank(), res.getTotal(),
                            res.getKodePembayaran(), res.getPoinTerpakai(), res.getPoinPending(),
                            res.getPermintaanKhusus(), res.getDurasi(), res.getJumlahKamar(), res.getNamaHotel(),
                            res.getNamaRoom(), res.getDataPemesan(), res.getStatus(), res.getVoucher(),
                            res.getUrl(), res.getTanggalCheckIn(), res.getTanggalCheckOut());
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


        return view;
    }

    private void updateReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String
            total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus, String durasiss, String jumlahKamarss,
                                      String namaHotela, String namaRoomaa, String dataPemesana, String Statuss, String tanggalCheckIn, String checkOut){
        ReservasiHotel res = new ReservasiHotel(idReservasi, email, bookingdate, idHotel, idRoom, bank, total, kodePembayaran,
                poinTerpakai, poinPending, permintaanKhusus, Statuss, durasiss, jumlahKamarss, namaHotela, namaRoomaa, dataPemesana, "---", "---", tanggalCheckIn, checkOut );

        dataBooking.child(idReservasi).setValue(res);
        potongPoin(email, poinTerpakai);
    }

    private void potongPoin(final String emailll, final String pot) {

        dataUser = FirebaseDatabase.getInstance().getReference("User");

        dataUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserList ul = dataSnapshot.getValue(UserList.class);

                if (ul.getEmail().equals(emailll)) {
                    update_user(dataSnapshot.getKey(), emailll, String.valueOf(Integer.parseInt(ul.getPoin().toString()) + Integer.parseInt(pot)));
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

    private void update_user(String key, String emai, String poi){
        UserList us = new UserList(emai, poi);
        dataUser.child(key).setValue(us);
    }


    private void TambahReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String
            total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus,
                                      String durasii, String jumlahKamarr, String namaHotell, String namaRooma,
                                      String dataPemesana, String statys, String voucherr, String urll, String tanggalCheckIn, String checkOut){
        ReservasiHotel res = new ReservasiHotel(idReservasi, email, bookingdate, idHotel, idRoom, bank, total,
                kodePembayaran, poinTerpakai, poinPending, permintaanKhusus, statys, durasii, jumlahKamarr, namaHotell, namaRooma, dataPemesana,
                voucherr, urll, tanggalCheckIn, checkOut);

        listReservasi.add(res);
        mAdapter.notifyDataSetChanged();
    }

}
