package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.KonfirmasiPembayaran;
import com.example.ahmadrizaldi.traveloka.KonfirmasiPembayaran_Detail;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 10/18/17.
 */

public class adapter_reservasi extends RecyclerView.Adapter<adapter_reservasi.ViewHolder> {

    private String konf;
    private Context context;
    private ArrayList<Reservasi> rvData;

    public adapter_reservasi(Context context, ArrayList<Reservasi> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listkonfirmasipembayaran, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (rvData.get(position).getStatus().equals("Konfirmasi")){
            konf = " (Konfirmasi) ";
        }else{
            konf = "";
        }

        if (rvData.get(position).getIDBaru().equals("")){
            holder.bookingid.setText("Booking ID : " + rvData.get(position).getIdreservasi() + konf);
        }else{
            holder.bookingid.setText("Booking ID : " + rvData.get(position).getIDBaru() + konf);
        }


        holder.totalPembayaran.setText("Rp. " + rvData.get(position).getTotal());

        holder.bookingid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent opp = new Intent(context, KonfirmasiPembayaran_Detail.class);
                Bundle b = new Bundle();

                //private String Idreservasi ,Flights_id, Kelas, BookingDate, Total, PembayaranKe, Status, Url;
                b.putString("ID_RESERVASI", rvData.get(position).getIdreservasi());
                b.putString("FLIGHT_ID", rvData.get(position).getFlights_id());
                b.putString("KELAS", rvData.get(position).getKelas());
                b.putString("BOOKINGDATE", rvData.get(position).getBookingDate());
                b.putString("TOTAL", rvData.get(position).getTotal());
                b.putString("PEMBAYARANKE", rvData.get(position).getPembayaranKe());
                b.putString("STATUS", rvData.get(position).getStatus());
                b.putString("URL", rvData.get(position).getUrl());
                b.putString("EMAIL", rvData.get(position).getEmail());
                b.putString("POIN", rvData.get(position).getPoinPending());
                b.putString("POIN_P", rvData.get(position).getPotongPoin());
                b.putString("jumlahPenumpang", rvData.get(position).getJumlahPenumpang());
                b.putString("kodePembayaran", rvData.get(position).getKodePembayaran());

                b.putString("idbaru", rvData.get(position).getIDBaru());

                if (rvData.get(position).getStatus().equals("Konfirmasi")){
                    b.putString("konfirmasi", "iya");
                }else{
                    b.putString("konfirmasi", "belum");
                }

                opp.putExtras(b);

                context.startActivity(opp);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bookingid, totalPembayaran;
        public ViewHolder(View itemView) {
            super(itemView);

            bookingid = (TextView)itemView.findViewById(R.id.bookingid_konfirmasi);
            totalPembayaran = (TextView)itemView.findViewById(R.id.totalpembayaran);

        }
    }
}
