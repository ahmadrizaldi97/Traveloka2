package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 12/24/17.
 */

public class Adapter_datapenerbanganMaskapai extends RecyclerView.Adapter<Adapter_datapenerbanganMaskapai.ViewHolder>{

    private Context context;
    private ArrayList<DetailDataPenerbangan> rvData;

    public Adapter_datapenerbanganMaskapai(Context context, ArrayList<DetailDataPenerbangan> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_penerbangan_mskp_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.FlightID.setText(rvData.get(position).getFlights_id());
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView FlightID;

        public ViewHolder(View itemView) {
            super(itemView);

            FlightID = (TextView)itemView.findViewById(R.id.txt_card);
        }
    }
}
