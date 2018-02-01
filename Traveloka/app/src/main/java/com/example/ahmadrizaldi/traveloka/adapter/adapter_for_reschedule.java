package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.Konfirmasi_Reschedule_D;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.Reschedule;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 10/26/17.
 */

public class adapter_for_reschedule extends RecyclerView.Adapter<adapter_for_reschedule.ViewHolder>{

    private Context context;
    private ArrayList<Reschedule> rvData;

    public adapter_for_reschedule(Context context, ArrayList<Reschedule> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listkonfirmasireschedule, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.aa.setText(rvData.get(position).getId_Reservasi());
        holder.bb.setText(rvData.get(position).getFlight_id());

        holder.aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Konfirmasi_Reschedule_D.class);
                Bundle b = new Bundle();

                b.putString("namaPenumpang", rvData.get(position).getDaftarPenumpang().toString());
                b.putString("flightId", rvData.get(position).getFlight_id().toString());
                b.putString("idReservasi",rvData.get(position).getId_Reservasi().toString());
                b.putString("id_penerbangan", rvData.get(position).getId().toString());
                b.putString("id_baru", rvData.get(position).getId_baru().toString());
                b.putString("kelas", rvData.get(position).getKelas().toString());
                b.putString("idLama",rvData.get(position).getId_Reservasi());
                b.putString("jumlahPenumpang", rvData.get(position).getJumlahPenumpang().toString());

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
        public TextView aa, bb;
        public ViewHolder(View itemView) {
            super(itemView);
            aa = (TextView)itemView.findViewById(R.id.bookingid_rescss);
            bb = (TextView)itemView.findViewById(R.id.flightId_rescss);
        }
    }
}
