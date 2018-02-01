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

import com.example.ahmadrizaldi.traveloka.HotelDetail;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel_Fix;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 11/9/17.
 */

public class Adapter_hotel_tersedia extends RecyclerView.Adapter<Adapter_hotel_tersedia.ViewHolder> {

    private Context context;
    private ArrayList<Hotel> rvData;
    private int hargaTermurah = 0;
    private int hargaTermurahAkhir = 0;
    private Bundle bs;

    DatabaseReference dataRoom;


    public Adapter_hotel_tersedia(Context context, ArrayList<Hotel> rvData, Bundle bundle) {
        this.context = context;
        this.rvData = rvData;
        this.bs = bundle;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hoteltersedia_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        dataRoom = FirebaseDatabase.getInstance().getReference("Room Hotel");



        dataRoom.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                RoomHotel_Fix rsa = dataSnapshot.getValue(RoomHotel_Fix.class);
                if (rsa.getIDHotel().toString().equals(rvData.get(position).getID_Hotel().toString())){

                    if (hargaTermurah < Integer.parseInt(rsa.getHarga())){
                        hargaTermurah = Integer.parseInt(rsa.getHarga());




                        holder.Harga.setText("Rp. " + String.valueOf(hargaTermurah));
                    }




                }



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

        loadImageFromUrl(rvData.get(position).getGM1(),holder.gambarHeader);

        holder.namaHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, HotelDetail.class);
                Bundle b = new Bundle();

                b.putString("fasilitasUtama", rvData.get(position).getFasilitas().toString());
                b.putString("id_hotel", rvData.get(position).getID_Hotel().toString());
                b.putString("lokasi", rvData.get(position).getLokasi().toString());
                b.putString("reputasi", rvData.get(position).getReputasi().toString());
                b.putString("gm1", rvData.get(position).getGM1());
                b.putString("gm2", rvData.get(position).getGM2());
                b.putString("gm3", rvData.get(position).getGM3());
                b.putString("gm4", rvData.get(position).getGM4());
                b.putString("gm5", rvData.get(position).getGM5());
                b.putString("mulaiHarga", String.valueOf(hargaTermurah));
                b.putString("durasi", bs.getString("durasi"));
                b.putString("jumlahKamar", bs.getString("jumlahKamar"));
                b.putString("tanggalCheckIn", bs.getString("tanggalCheckIn"));
                b.putString("kebikakan",rvData.get(position).getKebijakan());
                b.putString("namaHotel", rvData.get(position).getNamaHotel().toString());
                b.putString("deskripsi_h", rvData.get(position).getDeskripsi().toString());
                b.putString("alamatH", rvData.get(position).getAlamat().toString());
                b.putDouble("lat", rvData.get(position).getLat());
                b.putDouble("lang", rvData.get(position).getLang());

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
        public ImageView gambarHeader, b1, b2, b3, b4, b5;
        public TextView namaHotel, Harga, lokasi;
        public ViewHolder(View itemView) {
            super(itemView);

            b1 = (ImageView)itemView.findViewById(R.id.bintang1);
            b2 = (ImageView)itemView.findViewById(R.id.bintang2);
            b3 = (ImageView)itemView.findViewById(R.id.bintang3);
            b4 = (ImageView)itemView.findViewById(R.id.bintang4);
            b5 = (ImageView)itemView.findViewById(R.id.bintang5);
            gambarHeader = (ImageView)itemView.findViewById(R.id.imageView12);
            namaHotel = (TextView) itemView.findViewById(R.id.namaHotel);
            Harga = (TextView) itemView.findViewById(R.id.hargahotel);
            lokasi = (TextView) itemView.findViewById(R.id.lokasihotel);

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
