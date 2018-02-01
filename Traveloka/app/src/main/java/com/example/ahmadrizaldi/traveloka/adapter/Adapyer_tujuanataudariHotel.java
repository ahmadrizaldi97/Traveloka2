package com.example.ahmadrizaldi.traveloka.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.Lokasi;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.LokasiPnc;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 1/4/18.
 */

public class Adapyer_tujuanataudariHotel extends RecyclerView.Adapter<Adapyer_tujuanataudariHotel.ViewHolder>{

    private Context context;
    private ArrayList<LokasiPnc> rvData;

    public Adapyer_tujuanataudariHotel(Context context, ArrayList<LokasiPnc> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lokasilist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.namaKota.setText(rvData.get(position).getNama());

        holder.namaKota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_ = new Intent();
                intent_.putExtra("nama", rvData.get(position).getNama());
                ((Activity)context).setResult(Activity.RESULT_OK, intent_);
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaKota;
        public ViewHolder(View itemView) {
            super(itemView);

            namaKota = (TextView)itemView.findViewById(R.id.kotaHotel2);
        }
    }
}
