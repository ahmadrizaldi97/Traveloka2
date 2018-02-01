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

import com.example.ahmadrizaldi.traveloka.EditRoom;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel_Fix;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 12/9/17.
 */

public class Adapter_Room_Pemilik extends RecyclerView.Adapter<Adapter_Room_Pemilik.ViewHolder>{


    private Context context;
    private ArrayList<RoomHotel_Fix> rvData;

    public Adapter_Room_Pemilik(Context context, ArrayList<RoomHotel_Fix> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_room_pemilik, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nama.setText(rvData.get(position).getNamaRoom());
        holder.harga.setText(rvData.get(position).getHarga());

        loadImageFromUrl(rvData.get(position).getGm1(), holder.gambar);

        holder.setJualOrNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.Hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditRoom.class);

                Bundle b = new Bundle();
//
                b.putString("ID", rvData.get(position).getIDHotel());
                b.putString("IDRoom", rvData.get(position).getID_Room());
                b.putString("namaroom", rvData.get(position).getNamaRoom());
                b.putString("harga", rvData.get(position).getHarga());
                b.putString("deskripsi", rvData.get(position).getDeskripsiKamar());
                b.putString("fasilitaskamar", rvData.get(position).getFasilitasKamar());
                b.putString("fasilitasMandi", rvData.get(position).getFasilitasKamarMandi());
                b.putString("stok", rvData.get(position).getStok());
                b.putString("g1", rvData.get(position).getGm1());
                b.putString("g2", rvData.get(position).getGm2());
                b.putString("g3", rvData.get(position).getGm3());
                b.putString("g4", rvData.get(position).getGm4());
                b.putString("g5", rvData.get(position).getGm5());
                b.putString("g6", rvData.get(position).getGm6());
                b.putString("tipetempattidur", rvData.get(position).getTipeTempatTidur());
                b.putString("ukuran", rvData.get(position).getUkuranKamar());


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

        private ImageView gambar;
        private TextView nama, harga, stok;
        private Button setJualOrNot, Hapus;
        public ViewHolder(View itemView) {
            super(itemView);

            gambar = (ImageView)itemView.findViewById(R.id.gambar_room);

            nama = (TextView)itemView.findViewById(R.id.textView110);
            harga = (TextView)itemView.findViewById(R.id.textView111);
            stok = (TextView)itemView.findViewById(R.id.stokroom);

            setJualOrNot = (Button)itemView.findViewById(R.id.button3as);
            Hapus = (Button)itemView.findViewById(R.id.hapusroom);

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
