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

import com.example.ahmadrizaldi.traveloka.Hotel_Isi_data;
import com.example.ahmadrizaldi.traveloka.InsertDataHotel;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.RoomDetail;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel_Fix;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 11/12/17.
 */

public class Adapter_Choose_Room extends RecyclerView.Adapter<Adapter_Choose_Room.ViewHolder> {

    private Context context;
    private ArrayList<RoomHotel_Fix> rvData;
    Bundle baa = new Bundle();
    private int poin;
    private String poinPending;

    public Adapter_Choose_Room(Context context, ArrayList<RoomHotel_Fix> rvData, Bundle ba) {
        this.context = context;
        this.rvData = rvData;
        baa = ba;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listroom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        poin = Integer.parseInt(rvData.get(position).getHarga());
        int panjang = String.valueOf(poin).length();

        String uang = rvData.get(position).getHarga();

        if (panjang == 6){
            holder.poinHotel.setText(uang.substring(0,2) + " poin");
            poinPending = uang.substring(0,2);
        }else if (panjang == 7){
            holder.poinHotel.setText(uang.substring(0,3)+ " poin");
            poinPending = uang.substring(0,3);
        }else if (panjang == 8){
            holder.poinHotel.setText(uang.substring(0,4)+ " poin");
            poinPending = uang.substring(0,4);
        }else if (panjang == 9){
            holder.poinHotel.setText(uang.substring(0,5)+ " poin");
            poinPending = uang.substring(0,5);
        }else if (panjang == 10){
            holder.poinHotel.setText(uang.substring(0,6)+ " poin");
            poinPending = uang.substring(0,6);
        }


        holder.namaRoom.setText(rvData.get(position).getNamaRoom());
        holder.hargaRoom.setText("Rp." + rvData.get(position).getHarga());

        loadImageFromUrl(rvData.get(position).getGm1(), holder.gambar);

        holder.lihatDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent io = new Intent(context, RoomDetail.class);
                Bundle b = new Bundle();

                b.putString("deskripsiKamar", rvData.get(position).getDeskripsiKamar());
                b.putString("fasilitasKamar", rvData.get(position).getFasilitasKamar());
                b.putString("deskripsiKamarMandi", rvData.get(position).getFasilitasKamarMandi());
                b.putString("gm1", rvData.get(position).getGm1());
                b.putString("gm2", rvData.get(position).getGm6());
                b.putString("gm3", rvData.get(position).getGm3());
                b.putString("gm4", rvData.get(position).getGm4());
                b.putString("gm5", rvData.get(position).getGm5());
                b.putString("stok", rvData.get(position).getStok());
                b.putString("tipeTempatTidur", rvData.get(position).getTipeTempatTidur());
                b.putString("ukuranKamar", rvData.get(position).getUkuranKamar());
                b.putString("ID", rvData.get(position).getIDHotel());
                b.putString("IDRoom", rvData.get(position).getID_Room());
                b.putString("namaroom", rvData.get(position).getNamaRoom());
                b.putString("harga", rvData.get(position).getHarga());
                b.putString("durasi", baa.getCharSequence("durasi").toString());
                b.putString("tanggalCheckIn", baa.getString("tanggalCheckIn"));
                b.putString("jumlahKamar", baa.getCharSequence("jumlahKamar").toString());
                b.putString("kebikakan", baa.getCharSequence("kebikakan").toString());
                b.putString(("namaHotel"), baa.getCharSequence("namaHotel").toString());
                b.putString(("poin"), poinPending);

                io.putExtras(b);

                context.startActivity(io);
            }
        });

        holder.namaRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent io = new Intent(context, RoomDetail.class);
                Bundle b = new Bundle();
//

                b.putString("deskripsiKamar", rvData.get(position).getDeskripsiKamar());
                b.putString("fasilitasKamar", rvData.get(position).getFasilitasKamar());
                b.putString("deskripsiKamarMandi", rvData.get(position).getFasilitasKamarMandi());
                b.putString("gm1", rvData.get(position).getGm1());
                b.putString("gm2", rvData.get(position).getGm6());
                b.putString("gm3", rvData.get(position).getGm3());
                b.putString("gm4", rvData.get(position).getGm4());
                b.putString("gm5", rvData.get(position).getGm5());
                b.putString("stok", rvData.get(position).getStok());
                b.putString("tipeTempatTidur", rvData.get(position).getTipeTempatTidur());
                b.putString("ukuranKamar", rvData.get(position).getUkuranKamar());
                b.putString("ID", rvData.get(position).getIDHotel());
                b.putString("IDRoom", rvData.get(position).getID_Room());
                b.putString("namaroom", rvData.get(position).getNamaRoom());
                b.putString("harga", rvData.get(position).getHarga());
                b.putString("durasi", baa.getCharSequence("durasi").toString());
                b.putString("tanggalCheckIn", baa.getString("tanggalCheckIn"));
                b.putString("jumlahKamar", baa.getCharSequence("jumlahKamar").toString());
                b.putString("kebikakan", baa.getCharSequence("kebikakan").toString());
                b.putString(("namaHotel"), baa.getCharSequence("namaHotel").toString());
                b.putString(("poin"), poinPending);

                io.putExtras(b);

                context.startActivity(io);
            }
        });

        holder.pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent io = new Intent(context, InsertDataHotel.class);
                Bundle b = new Bundle();
//
                b.putString("ID", rvData.get(position).getIDHotel());
                b.putString("IDRoom", rvData.get(position).getID_Room());
                b.putString("namaroom", rvData.get(position).getNamaRoom());
                b.putString("harga", rvData.get(position).getHarga());
                b.putString("durasi", baa.getCharSequence("durasi").toString());
                b.putString("tanggalCheckIn", baa.getString("tanggalCheckIn"));
                b.putString("jumlahKamar", baa.getCharSequence("jumlahKamar").toString());
                b.putString("kebikakan", baa.getCharSequence("kebikakan").toString());
                b.putString(("namaHotel"), baa.getCharSequence("namaHotel").toString());
                b.putString(("poin"), poinPending);

                io.putExtras(b);

                context.startActivity(io);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaRoom, hargaRoom, poinHotel, lihatDetail;
        public Button pilih;
        public ImageView gambar;

        public ViewHolder(View itemView) {
            super(itemView);
            namaRoom = (TextView)itemView.findViewById(R.id.textView90);
            hargaRoom = (TextView)itemView.findViewById(R.id.hargaHotelllllll);
            poinHotel = (TextView)itemView.findViewById(R.id.poinHotel);
            lihatDetail = (TextView)itemView.findViewById(R.id.textView83saaaaa);

            pilih = (Button)itemView.findViewById(R.id.pilihRoom);

            gambar = (ImageView)itemView.findViewById(R.id.imageView17);
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


    }
}
