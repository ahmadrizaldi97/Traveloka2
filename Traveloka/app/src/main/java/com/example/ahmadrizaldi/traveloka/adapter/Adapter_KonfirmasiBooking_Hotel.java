package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.KonfirmasiPembayaranHotelDetail;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 12/29/17.
 */

public class Adapter_KonfirmasiBooking_Hotel extends RecyclerView.Adapter<Adapter_KonfirmasiBooking_Hotel.ViewHolder> {
    private Context context;
    private ArrayList<ReservasiHotel> rvData;

    public Adapter_KonfirmasiBooking_Hotel() {
    }

    public Adapter_KonfirmasiBooking_Hotel(Context context, ArrayList<ReservasiHotel> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.konfirmasi_pembayaran_d_hotel, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bookingID.setText("Booking id : " + rvData.get(position).getIdReservasi());
        holder.totalBayar.setText("Rp. " + rvData.get(position).getTotal());

        holder.bookingID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, KonfirmasiPembayaranHotelDetail.class);
                Bundle b = new Bundle();

                b.putString("checkOut", rvData.get(position).getTanggalCheckOut());
                b.putString("idReservasi", rvData.get(position).getIdReservasi());
                b.putString("email", rvData.get(position).getEmail());
                b.putString("bookingdate", rvData.get(position).getBookingdate());
                b.putString("idHotel", rvData.get(position).getIdHotel());
                b.putString("idRoom", rvData.get(position).getIdRoom());
                b.putString("bank", rvData.get(position).getBank());
                b.putString("total", rvData.get(position).getTotal());
                b.putString("tanggalCheckIn", rvData.get(position).getTanggalCheckIn().toString());
                b.putString("namaRoom", rvData.get(position).getNamaRoom());
                b.putString("kodePembayaran", rvData.get(position).getKodePembayaran());
                b.putString("poinTerpakai", rvData.get(position).getPoinTerpakai());
                b.putString("poinPending", rvData.get(position).getPoinPending());
                b.putString("permintaanKhusus", rvData.get(position).getPermintaanKhusus());
                b.putString("status", rvData.get(position).getStatus());
                b.putString("durasi", rvData.get(position).getDurasi());
                b.putString("jumlahKamar", rvData.get(position).getJumlahKamar());
                b.putString("namaHotel", rvData.get(position).getNamaHotel());
                b.putString("dataPemesan", rvData.get(position).getDataPemesan());
                b.putString("url", rvData.get(position).getUrl().toString());
                i.putExtras(b);

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bookingID, totalBayar;
        public ViewHolder(View itemView) {
            super(itemView);
            bookingID = (TextView)itemView.findViewById(R.id.bookingIdHotell);
            totalBayar = (TextView)itemView.findViewById(R.id.totalHotell);
        }
    }
}
