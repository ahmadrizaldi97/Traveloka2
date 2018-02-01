package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.DataPNRMaskapai;
import com.example.ahmadrizaldi.traveloka.ETicket;
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
 * Created by ahmadrizaldi on 1/1/18.
 */

public class AdapterReservasiForMaskapai extends RecyclerView.Adapter<AdapterReservasiForMaskapai.ViewHolder>{

    private Context context;
    private ArrayList<Reservasi> rvData = new ArrayList<>();
    private DatabaseReference dataPenerbangan;
    private String maskapai;

    private DatabaseReference dataPenerbanganku;

    String Darimana ="";
    String Kemana = "";
    String flightID_;
    String jamBerangkat, JamTiba, Tanggal;


    public AdapterReservasiForMaskapai() {
    }

    public AdapterReservasiForMaskapai(Context context, ArrayList<Reservasi> rvData, String maskapai) {
        this.context = context;
        this.rvData = rvData;
        this.maskapai = maskapai;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listreservasikhususmaskapai, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        dataPenerbangan = FirebaseDatabase.getInstance().getReference("Detail Flights");



        dataPenerbangan.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan dat = dataSnapshot.getValue(DetailDataPenerbangan.class);

                if (dat.getId().equals(rvData.get(position).getFlights_id())){

                    holder.kodePenerbangan.setText(dat.getFlights_id().toString());
                    int index = dat.getFrom().toString().indexOf("*");
                    holder.tujuan.setText(dat.getFrom().toString().substring(0, index) + " - " + dat.getTo().toString().substring(0, index) + " * " + rvData.get(position).getJumlahPenumpang() + " penumpang");
                    Tanggal = dat.getDates();

                    Darimana = dat.getFrom();
                    Kemana = dat.getTo();
                    flightID_ = dat.getFlights_id();
                    jamBerangkat = dat.getJamBerangkat();
                    JamTiba = dat.getJamTiba();
                    Tanggal = dat.getDates();

                    holder.tangga.setText(dat.getDates());

                    int index1, index2;
                    index1 = dat.getFrom().toString().indexOf("*");
                    index2 = dat.getTo().toString().indexOf("*");

                    int index1_, index2_;
                    index1_ = dat.getFrom().toString().indexOf("^") + 1;
                    index2_ = dat.getTo().toString().indexOf("^") + 1;


                    int indexSpecial;
                    indexSpecial = dat.getId().toString().indexOf("*") + 1;





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


        holder.kodeBooking.setText(rvData.get(position).getPNR().toString());
        holder.kodePenerbangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ETicket.class);
                Bundle b1 = new Bundle();

                b1.putString("jenis","maskapai");
                b1.putString("darimana", Darimana);
                b1.putString("kemana", Kemana);
                b1.putString("namaMaskapai", maskapai);
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

                i.putExtras(b1);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView kodePenerbangan, tujuan, kodeBooking, tangga;
        public ViewHolder(View itemView) {
            super(itemView);
            tangga = (TextView)itemView.findViewById(R.id.l24);
            kodePenerbangan = (TextView)itemView.findViewById(R.id.l1);
            tujuan = (TextView)itemView.findViewById(R.id.l2);
            kodeBooking = (TextView)itemView.findViewById(R.id.l3);
        }
    }
}
