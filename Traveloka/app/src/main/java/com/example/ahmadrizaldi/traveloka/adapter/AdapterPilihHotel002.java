package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.ReservasiHotel_PemilikHotel;
import com.example.ahmadrizaldi.traveloka.TambahRoomHotel;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 1/7/18.
 */

public class AdapterPilihHotel002 extends RecyclerView.Adapter<AdapterPilihHotel002.ViewHolder> {

    private Context context;
    private ArrayList<Hotel> rvData;

    public AdapterPilihHotel002() {
    }

    public AdapterPilihHotel002(Context context, ArrayList<Hotel> rvData) {
        this.context = context;
        this.rvData = rvData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listhotel_pemilik, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.namaHotel.setText(rvData.get(position).getNamaHotel());
        holder.lokasi.setText(rvData.get(position).getLokasi());

        if (rvData.get(position).getReputasi().equals("1")){
            holder.b1.setVisibility(View.VISIBLE);
            holder.b2.setVisibility(View.INVISIBLE);
            holder.b3.setVisibility(View.INVISIBLE);
            holder.b4.setVisibility(View.INVISIBLE);
            holder.b5.setVisibility(View.INVISIBLE);

        }else if (rvData.get(position).getReputasi().equals("2")){
            holder.b1.setVisibility(View.VISIBLE);
            holder.b2.setVisibility(View.VISIBLE);
            holder.b3.setVisibility(View.INVISIBLE);
            holder.b4.setVisibility(View.INVISIBLE);
            holder.b5.setVisibility(View.INVISIBLE);

        }else if (rvData.get(position).getReputasi().equals("3")){
            holder.b1.setVisibility(View.VISIBLE);
            holder.b2.setVisibility(View.VISIBLE);
            holder.b3.setVisibility(View.VISIBLE);
            holder.b4.setVisibility(View.INVISIBLE);
            holder.b5.setVisibility(View.INVISIBLE);
        }else if (rvData.get(position).getReputasi().equals("4")){
            holder.b1.setVisibility(View.VISIBLE);
            holder.b2.setVisibility(View.VISIBLE);
            holder.b3.setVisibility(View.VISIBLE);
            holder.b4.setVisibility(View.VISIBLE);
            holder.b5.setVisibility(View.INVISIBLE);
        }else{
            holder.b1.setVisibility(View.VISIBLE);
            holder.b2.setVisibility(View.VISIBLE);
            holder.b3.setVisibility(View.VISIBLE);
            holder.b4.setVisibility(View.VISIBLE);
            holder.b5.setVisibility(View.VISIBLE);
        }

        loadImageFromUrl(rvData.get(position).getGM1().toString(), holder.gambar);

        holder.namaHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ReservasiHotel_PemilikHotel.class);
                Bundle b = new Bundle();
                b.putString("idHotell", rvData.get(position).getID_Hotel().toString());
                b.putString("id_hotel", rvData.get(position).getID_Hotel().toString());
                b.putString("lat", rvData.get(position).getLat().toString());
                b.putString("lang", rvData.get(position).getLang().toString());
                b.putString("lokasi", rvData.get(position).getLokasi());
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
        public ImageView gambar, b1, b2,b3,b4,b5;
        public TextView namaHotel, lokasi;

        public ViewHolder(View itemView) {
            super(itemView);

            b1 = (ImageView)itemView.findViewById(R.id.bintang1T);
            b2 = (ImageView)itemView.findViewById(R.id.bintang2T);
            b3 = (ImageView)itemView.findViewById(R.id.bintang3T);
            b4 = (ImageView)itemView.findViewById(R.id.bintang4T);
            b5 = (ImageView)itemView.findViewById(R.id.bintang5T);
            gambar = (ImageView)itemView.findViewById(R.id.imageView12T);

            namaHotel = (TextView)itemView.findViewById(R.id.namaHotelT);
            lokasi = (TextView)itemView.findViewById(R.id.lokasihotelT);

        }
}

    private void loadImageFromUrl(String uri, ImageView gambar){
        Picasso.with(context).load(uri).placeholder(R.drawable.travelokalogo)
                .error(R.drawable.travelokalogo)
                .into(gambar, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

//        ImageView im =  Picasso.with(context).load(uri).placeholder(R.drawable.travelokalogo).error(R.drawable.travelokalogo);

    }
}
