package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.RequiresApi;
import android.widget.Button;

import com.example.ahmadrizaldi.traveloka.adapter.adapter_for_booking_list;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class BookingDashboard extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private adapter_for_booking_list mAdapter;
    private ArrayList<Reservasi> resList = new ArrayList<>();
    private String email = " ";

    private DatabaseReference dataReservasi, penerbangan;

    public BookingDashboard() {
        // Required empty public constructor
    }

    public void setEmail(String emaill){
        email = emaill;
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        penerbangan = FirebaseDatabase.getInstance().getReference("Detail Flights");


        View view = inflater.inflate(R.layout.fragment_booking_dashboard, container, false);

        Button keHotel = (Button)view.findViewById(R.id.keHotell);

        SharedPreferences sharedPref =  this.getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final String email = sharedPref.getString("username",null);

        keHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentHotel homeFragments = new FragmentHotel();
                homeFragments.setEmail(email);
                FragmentTransaction fragmentTransactions = getFragmentManager().beginTransaction();
                fragmentTransactions.replace(R.id.content, homeFragments);
                fragmentTransactions.commit();
            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.daftarbooking_user2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

//        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mAdapter = new adapter_for_booking_list(this.getContext(), resList);
        recyclerView.setAdapter(mAdapter);
//        recyclerView.addItemDecoration(itemDecoration);



        dataReservasi = FirebaseDatabase.getInstance().getReference("Reservasi");

        dataReservasi.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reservasi newPost = dataSnapshot.getValue(Reservasi.class);

                String sa = newPost.getBookingDate().toString().replace("/",".");
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



                c2.set(Integer.parseInt(tahun), BulanFix, Integer.parseInt(datee), Integer.parseInt(jam), Integer.parseInt(menit));

                long millis1 = c.getTimeInMillis();
                long millis2 = c2.getTimeInMillis();

                long diff = millis2 - millis1;
                long diffSeconds = diff / 1000;
                long diffMinutes = diff / (60 * 1000);
                long diffHours = diff / (60 * 60 * 1000);


                System.out.println("hari " + datee);
                System.out.println("jam " + jam);
                System.out.println("menit " + menit);


                if ( !newPost.getStatus().toString().equals("Konfirmasi") && !newPost.getStatus().toString().equals("Bayar") && !newPost.getStatus().toString().equals("Bayar*")){
                    if (diffMinutes > 0){
                        System.out.println("In Minte " + diffMinutes + " Lanjut");
                    }else{
                        ubahStatusReservasi(newPost.getIdreservasi(), newPost.getFlights_id(), newPost.getKelas(), newPost.getBookingDate(),
                                newPost.getTotal(), newPost.getPembayaranKe(), "Waktu Habis",
                                newPost.getUrl(), newPost.getEmail(), newPost.getPoinPending(), newPost.getPotongPoin(),
                                newPost.getJumlahPenumpang(), newPost.getKodePembayaran(), newPost.getReschedule());
                        System.out.println("In Minte " + diffMinutes+ " Batal " + newPost.getStatus().toString());
                        tambahKuota(newPost.getFlights_id(), newPost.getJumlahPenumpang(), newPost.getKelas());
                    }
                }




//
//                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//                final String email = sharedPref.getString("username",null);
//
                if (email.equals(newPost.getEmail().toString())){
                    openBooking(newPost.getIdreservasi().toString(), newPost.getFlights_id().toString(), newPost.getKelas().toString(),
                            newPost.getBookingDate().toString(), newPost.getTotal().toString(),
                            newPost.getPembayaranKe().toString(), newPost.getStatus().toString(),
                            newPost.getUrl().toString(), newPost.getPNR() ,newPost.getEmail().toString(),
                            newPost.getPoinPending().toString(), newPost.getPotongPoin(),
                            newPost.getJumlahPenumpang(), newPost.getKodePembayaran(),
                            newPost.getReschedule(), newPost.getIDBaru());


                    resList.size();
                }

//batasjdkasdjaksjdahsdkjsadaskdas

//                if (email.equals(newPost.getEmail().toString())){
//                    openBooking(newPost.getIdreservasi().toString(), newPost.getFlights_id().toString(), newPost.getKelas().toString(),
//                            newPost.getBookingDate().toString(), newPost.getTotal().toString(),
//                            newPost.getPembayaranKe().toString(), newPost.getStatus().toString(), newPost.getUrl().toString(), newPost.getPNR(),newPost.getEmail().toString(), newPost.getPoinPending().toString(), newPost.getPotongPoin(), newPost.getJumlahPenumpang(), newPost.getKodePembayaran(), newPost.getReschedule(), newPost.getIDBaru());
//
//
//                    resList.size();
//                }


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

    private void ubahStatusReservasi(String _idreservasi, String _flights_id, String _kelas, String _bookingDate, String _total, String _pembayaranKe, String _status, String _Url_, String email_, String poins_, String potong, String jumlahPenumpang, String kodePembayaran, String resccc){

        Reservasi rev = new Reservasi(_idreservasi, _flights_id, _kelas, _bookingDate, _total, _pembayaranKe, _status, _Url_, "---",email_, poins_, potong, jumlahPenumpang, kodePembayaran, resccc, "");
        dataReservasi.child(_idreservasi).setValue(rev);


//
    }

    private void openBooking(String idreservasi_, String flights_id_, String kelas_, String bookingDate_, String total_, String pembayaranKe_, String status_, String Urk, String Bookingid, String emailss, String poinp, String potong, String jumlahPenumpang, String kodePembayaran, String resss, String gd){
        Reservasi rev = new Reservasi(idreservasi_, flights_id_, kelas_, bookingDate_, total_, pembayaranKe_, status_, Urk, Bookingid, emailss, poinp, potong, jumlahPenumpang, kodePembayaran, resss, gd);
        resList.add(rev);
        mAdapter.notifyDataSetChanged();
    }

    private void tambahKuota(final String idPenerb, final String jumlahP, final String kelass){

        penerbangan.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan dat = dataSnapshot.getValue(DetailDataPenerbangan.class);

                if (idPenerb.equals(dat.getId().toString())){

                    String kelas = kelass;
                    int jumlahKuota = 0;

                    if (kelas.equals("Economy Class")){
                        jumlahKuota = Integer.parseInt(dat.getKuota_E());
                        jumlahKuota += Integer.parseInt(jumlahP);

                        DetailDataPenerbangan dataPenerbangan2 = new DetailDataPenerbangan(dat.getId(), dat.getFlights_id(),
                                dat.getFrom(), dat.getTo(), dat.getDates(), dat.getJamBerangkat(),
                                dat.getJamTiba(), String.valueOf(jumlahKuota), dat.getKuota_B(),
                                dat.getKuota_F(), dat.getHarga_E(), dat.getHarga_B(), dat.getHarga_F(), dat.getMaxReschedule());

                        penerbangan.child(dat.getId()).setValue(dataPenerbangan2);

                    }else if (kelas.equals("Business Class")){

                        jumlahKuota = Integer.parseInt(dat.getKuota_B());
                        jumlahKuota += Integer.parseInt(jumlahP);

                        DetailDataPenerbangan dataPenerbangan2 = new DetailDataPenerbangan(dat.getId(), dat.getFlights_id(),
                                dat.getFrom(), dat.getTo(), dat.getDates(), dat.getJamBerangkat(),
                                dat.getJamTiba(), dat.getKuota_E(), String.valueOf(jumlahKuota),
                                dat.getKuota_F(), dat.getHarga_E(), dat.getHarga_B(), dat.getHarga_F(), dat.getMaxReschedule());

                        penerbangan.child(dat.getId()).setValue(dataPenerbangan2);

                    }else if(kelas.equals("First Class")){
                        jumlahKuota = Integer.parseInt(dat.getKuota_F());
                        jumlahKuota += Integer.parseInt(jumlahP);

                        DetailDataPenerbangan dataPenerbangan2 = new DetailDataPenerbangan(dat.getId(), dat.getFlights_id(),
                                dat.getFrom(), dat.getTo(), dat.getDates(), dat.getJamBerangkat(),
                                dat.getJamTiba(), dat.getKuota_E(), dat.getKuota_B(),
                                String.valueOf(jumlahKuota), dat.getHarga_E(), dat.getHarga_B(), dat.getHarga_F(), dat.getMaxReschedule());

                        penerbangan.child(dat.getId()).setValue(dataPenerbangan2);

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

    }


}
