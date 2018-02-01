package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.EditFlight_Maskapai;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.TambahRoomHotel;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 1/9/18.
 */

public class AdapterFlightForMaskapai extends RecyclerView.Adapter<AdapterFlightForMaskapai.ViewHolder> {


    private Context context;
    private ArrayList<DetailDataPenerbangan> rvData;

    public AdapterFlightForMaskapai() {
    }

    public AdapterFlightForMaskapai(Context context, ArrayList<DetailDataPenerbangan> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listpenerbanganformaskapai, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        int index = rvData.get(position).getFrom().indexOf("*");
        int index2 = rvData.get(position).getTo().indexOf("*");

        holder.kode.setText(rvData.get(position).getFlights_id());
        holder.rute.setText(rvData.get(position).getFrom().substring(0, index) + " - " + rvData.get(position).getTo().substring(0, index2));
        holder.tgl.setText(rvData.get(position).getDates());
        holder.eco.setText("Eco " + rvData.get(position).getKuota_E() + " seat / " + "Rp. " + rvData.get(position).getHarga_E());
        holder.bus.setText("Bus " + rvData.get(position).getKuota_B() + " seat / " + "Rp. " + rvData.get(position).getHarga_B());
        holder.firs.setText("Fir " + rvData.get(position).getKuota_F() + " seat / " + "Rp. " + rvData.get(position).getHarga_F());

        holder.kode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, EditFlight_Maskapai.class);


                Bundle b = new Bundle();
                b.putString("id", rvData.get(position).getId());
                b.putString("date", rvData.get(position).getDates());
                b.putString("flightid", rvData.get(position).getFlights_id());
                b.putString("from", rvData.get(position).getFrom());
                b.putString("to", rvData.get(position).getTo());
                b.putString("hargae", rvData.get(position).getHarga_E());
                b.putString("hargab", rvData.get(position).getHarga_B());
                b.putString("hargaf", rvData.get(position).getHarga_F());
                b.putString("kuotae", rvData.get(position).getKuota_E());
                b.putString("kuotab", rvData.get(position).getKuota_B());
                b.putString("kuotaf", rvData.get(position).getKuota_F());
                b.putString("jamB", rvData.get(position).getJamBerangkat());
                b.putString("jamT", rvData.get(position).getJamTiba());
                b.putString("maxResc", rvData.get(position).getMaxReschedule());

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
        public TextView kode, rute, tgl, eco, bus, firs;
        public ViewHolder(View itemView) {
            super(itemView);

            kode = (TextView)itemView.findViewById(R.id.a1);
            rute = (TextView)itemView.findViewById(R.id.a2);
            tgl = (TextView)itemView.findViewById(R.id.a3);
            eco = (TextView)itemView.findViewById(R.id.a4);
            bus = (TextView)itemView.findViewById(R.id.a5);
            firs = (TextView)itemView.findViewById(R.id.a6);
        }
    }
}
