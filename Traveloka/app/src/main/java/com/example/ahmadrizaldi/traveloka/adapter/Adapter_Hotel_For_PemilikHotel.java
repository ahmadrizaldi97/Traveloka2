 package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.RoomPemilikHotel;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 12/14/17.
 */

public class Adapter_Hotel_For_PemilikHotel extends RecyclerView.Adapter<Adapter_Hotel_For_PemilikHotel.ViewHolder> {

    private Context context;
    private ArrayList<Hotel> rvData;

    public Adapter_Hotel_For_PemilikHotel() {
    }

    public Adapter_Hotel_For_PemilikHotel(Context context, ArrayList<Hotel> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_hotel_pemilik_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.namaHotel.setText(rvData.get(position).getNamaHotel());
        holder.LokasiHotel.setText(rvData.get(position).getLokasi());

        loadImageFromUrl(rvData.get(position).getGM1(), holder.gambar);

        holder.namaHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, RoomPemilikHotel.class);
                Bundle b = new Bundle();
                b.putString("id_hotel", rvData.get(position).getID_Hotel());
                b.putString("idHotell", rvData.get(position).getID_Hotel().toString());
                i.putExtras(b);
                context.startActivity(i);
            }
        });

        holder.lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, RoomPemilikHotel.class);
                Bundle b = new Bundle();
                b.putString("id_hotel", rvData.get(position).getID_Hotel());
                b.putString("idHotell", rvData.get(position).getID_Hotel().toString());
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

        public TextView namaHotel, LokasiHotel, rating;
        public Button lanjut;
        public ImageView gambar, b1, b2, b3, b4, b5;

        public ViewHolder(View itemView) {
            super(itemView);

            namaHotel = (TextView)itemView.findViewById(R.id.namaHotel_pmk);
            LokasiHotel = (TextView)itemView.findViewById(R.id.lokasihotelaaa);
            rating = (TextView)itemView.findViewById(R.id.textView65a08a);

            lanjut = (Button)itemView.findViewById(R.id.LihatRoom_pmk);

            gambar = (ImageView)itemView.findViewById(R.id.imageView12aaaaaaa);
            b1 = (ImageView)itemView.findViewById(R.id.bintang111);
            b2 = (ImageView)itemView.findViewById(R.id.bintang211);
            b3 = (ImageView)itemView.findViewById(R.id.bintang311);
            b4 = (ImageView)itemView.findViewById(R.id.bintang411);
            b5 = (ImageView)itemView.findViewById(R.id.bintang511);

        }
    }

    private void loadImageFromUrl(String uri, ImageView gambar) {
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
    }
}
