package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.Pemesanan;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 10/18/17.
 */

public class adapter_for_booking_list extends RecyclerView.Adapter<adapter_for_booking_list.ViewHolder> {

    private Context context;
    private ArrayList<Reservasi> rvData;
    private ArrayList<DetailDataPenerbangan> detailDataPenerbangen = new ArrayList<>();
    private DatabaseReference dataPenerbanganku;

    String Darimana ="";
    String Kemana = "";
    String flightID_;
    String jamBerangkat, JamTiba, Tanggal;

    public adapter_for_booking_list(Context context, ArrayList<Reservasi> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdaftarbooking, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        dataPenerbanganku = FirebaseDatabase.getInstance().getReference("Detail Flights");

        dataPenerbanganku.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan dat = dataSnapshot.getValue(DetailDataPenerbangan.class);


                if (dat.getId().equals(rvData.get(position).getFlights_id())) {

                    detailDataPenerbangen.add(dat);
                    Darimana = dat.getFrom();
                    Kemana = dat.getTo();
                    flightID_ = dat.getFlights_id();
                    jamBerangkat = dat.getJamBerangkat();
                    JamTiba = dat.getJamTiba();
                    Tanggal = dat.getDates();

                    holder.tanggal.setText(dat.getDates().toString() + " " + dat.getJamBerangkat());


                    int index1, index2;
                index1 = dat.getFrom().toString().indexOf("*");
                index2 = dat.getTo().toString().indexOf("*");

                int index1_, index2_;
                index1_ = dat.getFrom().toString().indexOf("^") + 1;
                index2_ = dat.getTo().toString().indexOf("^") + 1;

                holder.darike.setText(dat.getFrom().toString().substring(index1_) + " - " + dat.getTo().toString().substring(index2_));

                int indexSpecial;
                indexSpecial = dat.getId().toString().indexOf("*") + 1;
                holder.maskapai.setText(dat.getId().toString().substring(indexSpecial));

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

//

        String sa = rvData.get(position).getBookingDate().toString().replace("/",".");
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



//


        holder.opooo.setText("Menunggu pembayaran : " + String.valueOf(diffMinutes) + " menit");

        if (rvData.get(position).getStatus().equals("Konfirmasi")){
            holder.opooo.setText("E-TIKET TELAH TERBIT");
            holder.opooo.setBackgroundColor(Color.parseColor("#00A651"));
            holder.opooo.setTextColor(Color.WHITE);
        }else if(rvData.get(position).getStatus().equals("Waktu Habis")){
            holder.opooo.setText("WAKTU PEMBAYARAN HABIS");
            holder.opooo.setBackgroundColor(Color.parseColor("#f4424b"));
            holder.opooo.setTextColor(Color.WHITE);
        }else if(rvData.get(position).getStatus().equals("Bayar*")){
            holder.opooo.setText("MEMVERIFIKASI PEMBAYARAN ANDA");
        }else if(rvData.get(position).getStatus().equals("Bayar")){
            holder.opooo.setText("UPLOAD BUKTI PEMBAYARAN ANDA");
        }else if(rvData.get(position).getStatus().equals("Menunggu Konfirmasi")){
            holder.opooo.setText("Menunggu Konfirmasi");
        }else if(rvData.get(position).getStatus().equals("Belum Bayar R")){
            holder.opooo.setText("Menunggu pembayaran : " + String.valueOf(diffMinutes) + " menit");
        }



        holder.darike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rvData.get(position).getStatus().equals("Waktu Habis")){

                }else {


                    Intent dkasd = new Intent(context, Pemesanan.class);

                    Bundle b1 = new Bundle();

                    //private String Idreservasi ,Flights_id, Kelas, BookingDate, Total, PembayaranKe, Status, Url, PNR;

                    for (int i = 0; i < detailDataPenerbangen.size(); i++) {

                        if (detailDataPenerbangen.get(i).getId().equals(rvData.get(position).getFlights_id())) {
                            Darimana = detailDataPenerbangen.get(i).getFrom();
                            Kemana = detailDataPenerbangen.get(i).getTo();
                            jamBerangkat = detailDataPenerbangen.get(i).getJamBerangkat();
                            JamTiba = detailDataPenerbangen.get(i).getJamTiba();
                            Tanggal = detailDataPenerbangen.get(i).getDates();
                            flightID_ = detailDataPenerbangen.get(i).getFlights_id();
//                        System.out.println(detailDataPenerbangen.get(position).getJamBerangkat() + " Flights");
                        }

                    }

                    b1.putString("jenis","user");
                    b1.putString("darimana", Darimana);
                    b1.putString("kemana", Kemana);
                    b1.putString("namaMaskapai", holder.maskapai.getText().toString());
                    b1.putString("jamBerangkat", jamBerangkat);
                    b1.putString("jamTiba", JamTiba);
                    b1.putString("tanggalBerangkat", Tanggal);
                    b1.putString("FLIGHT_ID_02", flightID_);
                    b1.putString("ID_RESERVASI", rvData.get(position).getIdreservasi());
                    b1.putString("FLIGHT_ID", rvData.get(position).getFlights_id());
                    b1.putString("KELAS", rvData.get(position).getKelas());
                    b1.putString("BOOKINGDATE", rvData.get(position).getBookingDate());
                    b1.putString("TOTAL", rvData.get(position).getTotal());
                    b1.putString("PEMBAYARANKE", rvData.get(position).getPembayaranKe());
                    b1.putString("STATUS", rvData.get(position).getStatus());
                    b1.putString("URL", rvData.get(position).getUrl());
                    b1.putString("PNR", rvData.get(position).getPNR());
                    b1.putString("EMAIL", rvData.get(position).getEmail());
                    b1.putString("POIN", rvData.get(position).getPoinPending());
                    b1.putString("POIN_P", rvData.get(position).getPotongPoin());
                    b1.putString("jumlahPenumpang", rvData.get(position).getJumlahPenumpang());
                    b1.putString("kodePembayaran", rvData.get(position).getKodePembayaran());
                    b1.putString("reschedule", rvData.get(position).getReschedule());
                    b1.putString("idBaru", rvData.get(position).getIDBaru());

                    dkasd.putExtras(b1);


                    context.startActivity(dkasd);

                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView darike, tanggal, maskapai, opooo;
        public ViewHolder(View itemView) {
            super(itemView);
            darike = (TextView)itemView.findViewById(R.id.darimanakemana_booking);
            tanggal = (TextView)itemView.findViewById(R.id.tanggal_bookingss);
            maskapai = (TextView)itemView.findViewById(R.id.maskapai_bookingid);
            opooo = (TextView)itemView.findViewById(R.id.status_booking);
        }
    }
}
