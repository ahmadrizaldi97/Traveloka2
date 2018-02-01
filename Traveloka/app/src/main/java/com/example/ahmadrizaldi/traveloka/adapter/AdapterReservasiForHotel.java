package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 1/7/18.
 */

public class AdapterReservasiForHotel extends RecyclerView.Adapter<AdapterReservasiForHotel.ViewHolder> {

    private Context context;
    private ArrayList<ReservasiHotel> rvData;
    private Bundle bs;

    public AdapterReservasiForHotel() {
    }

    public AdapterReservasiForHotel(Context context, ArrayList<ReservasiHotel> rvData, Bundle bs) {
        this.context = context;
        this.rvData = rvData;
        this.bs = bs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listreservasiforpemilik, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




        String dataPemesan = rvData.get(position).getDataPemesan();

        dataPemesan = dataPemesan.replace("*","/");
        dataPemesan = dataPemesan.replace("^","/");

        holder.namaPemesan.setText(dataPemesan);
        holder.transaksiID.setText("Transaksi ID : " + rvData.get(position).getIdReservasi());

        holder.namaHotel.setText(rvData.get(position).getNamaHotel() + " (" + rvData.get(position).getNamaRoom().toString() + ")");
        holder.tanggal.setText(rvData.get(position).getTanggalCheckIn()+ " * "  + rvData.get(position).getDurasi().toString() + " malam " + " * " + rvData.get(position).getJumlahKamar().toString() + " kamar");
        holder.lokasi.setText(bs.getString("lokasi").toString());
        holder.voucher.setText("Voucher id : " + rvData.get(position).getVoucher());

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView namaHotel, tanggal, lokasi, voucher, namaPemesan, transaksiID;


        public ViewHolder(View itemView) {
            super(itemView);


            namaPemesan = (TextView)itemView.findViewById(R.id.ooo29);
            transaksiID = (TextView)itemView.findViewById(R.id.ooo30);
            namaHotel = (TextView)itemView.findViewById(R.id.ooo);
            tanggal = (TextView)itemView.findViewById(R.id.ooo1);
            lokasi = (TextView)itemView.findViewById(R.id.ooo2);
            
            voucher = (TextView)itemView.findViewById(R.id.ooo3);

        }
    }
}
