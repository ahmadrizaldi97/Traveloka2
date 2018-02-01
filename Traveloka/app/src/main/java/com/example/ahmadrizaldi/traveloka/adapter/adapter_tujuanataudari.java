package com.example.ahmadrizaldi.traveloka.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.list.daftartujuan;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 10/11/17.
 */

public class adapter_tujuanataudari extends RecyclerView.Adapter<adapter_tujuanataudari.ViewHolder> {

    private Context context;
    private ArrayList<daftartujuan> rvData;

    public adapter_tujuanataudari(Context context, ArrayList<daftartujuan> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public adapter_tujuanataudari.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detaillistpenerbangan, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(adapter_tujuanataudari.ViewHolder holder, final int position) {


        Bundle b = ((Activity)context).getIntent().getExtras();

            holder.textView.setText(rvData.get(position).getNamaLokasi());
            holder.textView1.setText(rvData.get(position).getNamaBandara());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent_ = new Intent();
                    intent_.putExtra("bandara", rvData.get(position).getNamaBandara());
                    intent_.putExtra("namaLokasi", rvData.get(position).getNamaLokasi());
                    intent_.putExtra("kodebandara", rvData.get(position).getKodeBandara());
                    String lengkap = rvData.get(position).getKodeBandara() + "*" + rvData.get(position).getNamaBandara() + "^" + rvData.get(position).getNamaLokasi();
                    intent_.putExtra("lengkap",lengkap );
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

        public TextView textView;
        public TextView textView1;
        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.kota);
            textView1 = (TextView)itemView.findViewById(R.id.bandara);
        }
    }
}
